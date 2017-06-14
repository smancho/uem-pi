package usuario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import util.Email;
import util.Strings;

public class CUsuario extends MouseAdapter implements ActionListener,
		KeyListener {
	
	VUsuario vusuario = null;
	OAUsuario bbdd = null;
	Connection conexion;
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	int fila = -1;
	
	public static final int BUTTONS_STATE_DEFAULT = 0;
	public static final int BUTTONS_STATE_ROW_SELECTED = 1;
	public static final int BUTTONS_STATE_NEW = 2;
	public static final int BUTTONS_STATE_MODIFY = 3;

	public CUsuario() {}

	public CUsuario(VUsuario vusuario, Connection conexion) {
		this.conexion = conexion;
		this.vusuario = vusuario;
		
		this.bbdd = new OAUsuario(this.conexion);		
		this.recargarTabla();
	}


	private void setButtonsState(int state) {
		switch (state) {
		case BUTTONS_STATE_DEFAULT:
			vusuario.btnSalir.setEnabled(true);
			vusuario.btnCancelar.setEnabled(true);
			vusuario.btnBorrarUsuario.setEnabled(false);
			vusuario.btnModificarUsuario.setEnabled(false);
			vusuario.btnBuscarUsuario.setEnabled(true);
			vusuario.btnNuevoUsuario.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_ROW_SELECTED:
			vusuario.btnSalir.setEnabled(true);
			vusuario.btnCancelar.setEnabled(true);
			vusuario.btnBorrarUsuario.setEnabled(true);
			vusuario.btnModificarUsuario.setEnabled(true);
			vusuario.btnBuscarUsuario.setEnabled(false);
			vusuario.btnNuevoUsuario.setEnabled(false);
			break;

		case BUTTONS_STATE_NEW:
			vusuario.btnSalir.setEnabled(true);
			vusuario.btnCancelar.setEnabled(false);
			vusuario.btnBorrarUsuario.setEnabled(false);
			vusuario.btnModificarUsuario.setEnabled(false);
			vusuario.btnBuscarUsuario.setEnabled(false);
			vusuario.btnNuevoUsuario.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_MODIFY:
			vusuario.btnSalir.setEnabled(true);
			vusuario.btnCancelar.setEnabled(true);
			vusuario.btnBorrarUsuario.setEnabled(false);
			vusuario.btnModificarUsuario.setEnabled(true);
			vusuario.btnBuscarUsuario.setEnabled(false);
			vusuario.btnNuevoUsuario.setEnabled(false);
			break;

		default:
			break;
		}
	}

	private void resetFormFields() {
		this.resetFormFields(true);
	}
	private void resetFormFields(boolean resetUsuarioID) {
		if (resetUsuarioID) {
			vusuario.usuarioID.setText("");
		}
		vusuario.userName.setText("");
		vusuario.userSurname.setText("");
		vusuario.userMail.setText("");
		vusuario.userPhone.setText("");
		vusuario.userKind.setText("");
	}

	private void toggleWarning(boolean show) {
		if (show) {
			vusuario.lblAviso.setText("Debe introducir todos los datos");
			vusuario.lblAviso.setVisible(true);
		} else {
			vusuario.lblAviso.setText("Todos los campos correctos");
			vusuario.lblAviso.setVisible(false);
		}
	}

	private boolean isFormValid() {
		
		if (vusuario.userName.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vusuario.userSurname.getText().equals(Strings.EMPTY_STRING))
			return false;
		JTextField field = vusuario.userMail;
		if (Email.validateEmail(field.getText())) {
			field.setForeground(Color.BLACK);
		} else {
			field.setForeground(Color.RED);
			return false;
		}

		if (vusuario.userPhone.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vusuario.userKind.getText().equals(Strings.EMPTY_STRING))
			return false;

		return true;
	}

	
	private void nuevo() {

		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Usuario usuario = new Usuario(
					0,
					vusuario.userName.getText(),
					vusuario.userSurname.getText(), 
					vusuario.userMail.getText(),
					vusuario.userPhone.getText(), 
					vusuario.userKind.getText()
				);
				this.bbdd.insertarUsuario(usuario);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vusuario.lblAviso.setText("Debe introducir valor");
				vusuario.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vusuario.lblAviso.setText("Debe introducir un numero.");
				vusuario.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vusuario.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear usuario.");
				vusuario.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vusuario.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}
	
	private void buscarUsuario() {
		try {
			Usuario usuario = this.bbdd.buscarUsuarioPorID(vusuario.usuarioID.getText());
			if (usuario != null) {
				vusuario.lblAviso.setText("");
				this.rellenarForm(usuario);
				
				DefaultTableModel model = (DefaultTableModel)vusuario.table.getModel();
				int rowCount = model.getRowCount();
	
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == usuario.getUsuarioID()) {			
						vusuario.table.setRowSelectionInterval( i , i );
						vusuario.table.scrollRectToVisible(vusuario.table.getCellRect(i, 0, true));
						break;
					}
				}
			} else {
				vusuario.lblAviso.setText("Usuario no encontrado");
				this.resetFormFields(false);
			}
		} catch (SQLException sqlEx) {
			vusuario.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar usuario");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}


	private void borrar() {
		int fila = vusuario.table.getSelectedRow();
		try {
			this.bbdd.borrarUsuario(vusuario.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vusuario.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar usuario");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}
	}


	private void modificar() {
		Usuario usuario = new Usuario();
		usuario.setUsuarioID(Integer.parseInt(vusuario.usuarioID.getText()));
		usuario.setNombre(vusuario.userName.getText());
		usuario.setApellidos(vusuario.userSurname.getText());
		usuario.setMail(vusuario.userMail.getText());
		usuario.setTelefono(vusuario.userPhone.getText());
		usuario.setTipoUsuario(vusuario.userKind.getText());
		
		try {
			this.bbdd.modificarUsuario(usuario);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vusuario.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar el usuario");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}
	
	private void recargarTabla() {
		try {
			this.usuarios = bbdd.cargarUsuarios();
			this.rellenarTabla();
		} catch (Exception e) {
			vusuario.lblAviso.setText("Error al cargar la tabla de usuarios");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}
	}
	
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vusuario.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		DefaultTableModel table = (DefaultTableModel) vusuario.table.getModel();
		for (Usuario usr1 : usuarios) {
			Object[] rowData = { usr1.getUsuarioID(), usr1.getNombre(),
					usr1.getApellidos(), usr1.getMail(), usr1.getTelefono(),
					usr1.getTipoUsuario() };
			table.addRow(rowData);

		}
	}

	private void rellenarForm(Usuario u) {
		vusuario.usuarioID.setText(Integer.toString(u.getUsuarioID()));
		vusuario.userName.setText(u.getNombre());
		vusuario.userSurname.setText(u.getApellidos());
		vusuario.userMail.setText(u.getMail());
		vusuario.userPhone.setText(u.getTelefono());
		vusuario.userKind.setText(u.getTipoUsuario());
	}
	

	private void mostrar() {
		for (Usuario usr1 : usuarios) {
			System.out.println(usr1.mostrarUsuario());
		}
	}

	private void cancelar() {
		vusuario.table.clearSelection();
		setButtonsState(BUTTONS_STATE_DEFAULT);
		resetFormFields();
	}

	public void mouseClicked(MouseEvent e) {
		fila = vusuario.table.getSelectedRow();
		this.rellenarForm(usuarios.get(fila));

		setButtonsState(BUTTONS_STATE_ROW_SELECTED);	
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == vusuario.btnNuevoUsuario) {
			nuevo();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vusuario.btnBorrarUsuario) {
			borrar();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vusuario.btnModificarUsuario) {
			modificar();
			setButtonsState(BUTTONS_STATE_ROW_SELECTED);
		 } else if (obj == vusuario.btnBuscarUsuario) {
			 buscarUsuario();
			 setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vusuario.btnCancelar){
			cancelar();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vusuario.btnSalir){
			vusuario.dispose();
		} else {
			mostrar();
		}

	}

	public void keyTyped(KeyEvent e) {
		boolean valid = isFormValid();
		if (valid) {
			if (fila == -1)
				setButtonsState(BUTTONS_STATE_DEFAULT);
			else
				setButtonsState(BUTTONS_STATE_MODIFY);
		}
		toggleWarning(!valid);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyTyped(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyTyped(e);
	}

}
