package proveedor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

public class CProveedor extends MouseAdapter implements ActionListener,
		KeyListener {

	VProveedor vproveedor = null;
	ArrayList<Proveedor> proveedores = null;
	OAProveedor conexion = null;
	int fila = -1;
	
	public static final int BUTTONS_STATE_DEFAULT = 0;
	public static final int BUTTONS_STATE_ROW_SELECTED = 1;
	public static final int BUTTONS_STATE_NEW = 2;
	public static final int BUTTONS_STATE_MODIFY = 3;

	private static final String EMPTY_STRING = "";

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public CProveedor() {

	}

	public CProveedor(VProveedor vproveedor, OAProveedor conexionBBDD) {
		this.vproveedor = vproveedor;
		this.proveedores = new ArrayList<Proveedor>();
		conexionBBDD.cargarProveedores(this.proveedores);
		this.conexion = conexionBBDD;
	}

	public void rellenarTabla() {
		DefaultTableModel table = (DefaultTableModel) vproveedor.table
				.getModel();
		for (Proveedor p : proveedores) {
			table.addRow(new Object[] { p.getCif(), p.getNombre(),
					p.getDireccion(), p.getCiudad(), p.getCp(),
					p.getTelefono(), p.getMail() });
		}

	}
	
	public void rellenarFormulario(Proveedor p) {
		vproveedor.CIF.setText(p.getCif());
		vproveedor.nombre_proveedor.setText(p.getNombre());
		vproveedor.direccion.setText(p.getDireccion());
		vproveedor.ciudad.setText(p.getCiudad());
		vproveedor.CP.setText("" + p.getCp());
		vproveedor.telefono.setText(p.getTelefono());
		vproveedor.mail.setText(p.getMail());		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == vproveedor.btnNuevoProveedor) {
			insertarProveedor();
		} else if (obj == vproveedor.btnBuscarProveedor) {
			buscarProveedor();
		} else if (obj == vproveedor.btnModificarProveedor) {
			modificarProveedor();
		} else if (obj == vproveedor.btnBorrarProveedor) {
			borrarProveedor();
		} else if (obj == vproveedor.btnCancelar) {
			resetFormFields();

		} else if (obj == vproveedor.btnSalir) {
			vproveedor.dispose();
		}
	}

	private void modificarProveedor() {
		int fila = vproveedor.table.getSelectedRow();

		DefaultTableModel table = (DefaultTableModel) vproveedor.table
				.getModel();

		Proveedor p = new Proveedor();

		p.setCif(vproveedor.CIF.getText());
		p.setNombre(vproveedor.nombre_proveedor.getText());
		p.setDireccion(vproveedor.direccion.getText());
		p.setCiudad(vproveedor.ciudad.getText());
		p.setCp(Integer.parseInt(vproveedor.CP.getText()));
		p.setTelefono(vproveedor.telefono.getText());
		p.setMail(vproveedor.mail.getText());

		proveedores.set(fila, p);

		table.setValueAt(p.getCif(), fila, 0);
		table.setValueAt(p.getNombre(), fila, 1);
		table.setValueAt(p.getDireccion(), fila, 2);
		table.setValueAt(p.getCiudad(), fila, 3);
		table.setValueAt(p.getCp(), fila, 4);
		table.setValueAt(p.getTelefono(), fila, 5);
		table.setValueAt(p.getMail(), fila, 6);

		conexion.modificarProveedor(p);

		resetFormFields();

	}

	private void buscarProveedor() {

		String cif = vproveedor.CIF.getText();
		setButtonsState(CProveedor.BUTTONS_STATE_MODIFY);
		if (cif.trim().equals("")) {
			vproveedor.lblAviso.setText("El campo CIF no puede estar vacío");
			return;
		}
		
		vproveedor.lblAviso.setText("");

		ArrayList<Proveedor> proveedores = conexion.buscarProveedorPorCIF(cif);
		DefaultTableModel model = (DefaultTableModel)vproveedor.table.getModel();
		int rowCount = model.getRowCount();
		for (Proveedor proveedor : proveedores) {
			for (int i = 0; i < rowCount; i++) {
				if (((String)model.getValueAt(i, 0)).equals(proveedor.getCif())) {
					vproveedor.table.setRowSelectionInterval( i , i );
					vproveedor.table.scrollRectToVisible(vproveedor.table.getCellRect(i, 0, true));
					this.rellenarFormulario(proveedor);
					return;
				}
			}
		}
		this.resetFormFields(false);
		vproveedor.lblAviso.setText("Proveedor no encontrado.");
	}

	private void borrarProveedor() {
		int fila = vproveedor.table.getSelectedRow();
		DefaultTableModel table = (DefaultTableModel) vproveedor.table
				.getModel();

		conexion.borrarProveedor(vproveedor.CIF.getText());
		table.removeRow(fila);
		proveedores.remove(fila);

		resetFormFields();
	}

	private void insertarProveedor() throws NumberFormatException {
		if (!isFormValid()) {
			toggleWarning(true);
		} else {
			toggleWarning(false);
			Proveedor p = new Proveedor();

			try {
				p.setCif(vproveedor.CIF.getText());
				p.setNombre(vproveedor.nombre_proveedor.getText());
				p.setDireccion(vproveedor.direccion.getText());
				p.setCiudad(vproveedor.ciudad.getText());
				p.setCp(Integer.parseInt(vproveedor.CP.getText()));
				p.setTelefono(vproveedor.telefono.getText());
				p.setMail(vproveedor.mail.getText());

				proveedores.add(p);

				conexion.insertarProveedor(p);
				DefaultTableModel table = (DefaultTableModel) vproveedor.table
						.getModel();
				table.addRow(new Object[] { p.getCif(), p.getNombre(),
						p.getDireccion(), p.getCiudad(), p.getCp(),
						p.getTelefono(), p.getMail() });

				resetFormFields();
			} catch (SQLException ex) {
				if (ex.getErrorCode() == 1) {
					vproveedor.lblAviso.setText("Proveedor duplicado.");
				} else {
					System.out.println(ex.getErrorCode() + ": No se ha podido crear el proveedor.");
					vproveedor.lblAviso.setText("No se ha podido crear el proveedor.");
				}
				vproveedor.lblAviso.setVisible(true);
				System.err.println(ex.getMessage());
				ex.printStackTrace(System.err);
			} catch (NumberFormatException ex) {
				vproveedor.lblAviso.setText("No se ha podido crear el proveedor.");
				vproveedor.lblAviso.setVisible(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();

		if (obj == vproveedor.table) {
			this.fila = vproveedor.table.getSelectedRow();
			setButtonsState(CProveedor.BUTTONS_STATE_ROW_SELECTED);
			this.rellenarFormulario(proveedores.get(this.fila));
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		boolean valid = isFormValid();
		if (valid) {
			if (fila == -1)
				setButtonsState(BUTTONS_STATE_NEW);
			else
				setButtonsState(BUTTONS_STATE_MODIFY);
		} else {
			setButtonsState(BUTTONS_STATE_DEFAULT);
		}
		toggleWarning(!valid);
		
	}

	public void setButtonsState(int state) {
		switch (state) {
		case BUTTONS_STATE_DEFAULT:
			vproveedor.btnSalir.setEnabled(true);
			vproveedor.btnCancelar.setEnabled(true);
			vproveedor.btnBorrarProveedor.setEnabled(false);
			vproveedor.btnModificarProveedor.setEnabled(false);
			vproveedor.btnBuscarProveedor.setEnabled(true);
			vproveedor.btnNuevoProveedor.setEnabled(false);

			fila = -1;
			break;

		case BUTTONS_STATE_ROW_SELECTED:
			vproveedor.btnSalir.setEnabled(true);
			vproveedor.btnCancelar.setEnabled(true);
			vproveedor.btnBorrarProveedor.setEnabled(true);
			vproveedor.btnModificarProveedor.setEnabled(true);
			vproveedor.btnBuscarProveedor.setEnabled(false);
			vproveedor.btnNuevoProveedor.setEnabled(false);
			break;

		case BUTTONS_STATE_NEW:
			vproveedor.btnSalir.setEnabled(true);
			vproveedor.btnCancelar.setEnabled(true);
			vproveedor.btnBorrarProveedor.setEnabled(false);
			vproveedor.btnModificarProveedor.setEnabled(false);
			vproveedor.btnBuscarProveedor.setEnabled(false);
			vproveedor.btnNuevoProveedor.setEnabled(true);

			break;

		case BUTTONS_STATE_MODIFY:
			vproveedor.btnSalir.setEnabled(true);
			vproveedor.btnCancelar.setEnabled(true);
			vproveedor.btnBorrarProveedor.setEnabled(false);
			vproveedor.btnModificarProveedor.setEnabled(true);
			vproveedor.btnBuscarProveedor.setEnabled(true);
			vproveedor.btnNuevoProveedor.setEnabled(false);
			break;

		default:
			break;
		}
	}

	private void resetFormFields() {
		this.resetFormFields(true);
	}
	private void resetFormFields(boolean resetCIF) {
		if (resetCIF) {
			vproveedor.CIF.setText("");
		}
		vproveedor.nombre_proveedor.setText("");
		vproveedor.direccion.setText("");
		vproveedor.ciudad.setText("");
		vproveedor.CP.setText("");
		vproveedor.telefono.setText("");
		vproveedor.mail.setText("");
		
		this.fila = -1;
	}

	private void toggleWarning(boolean show) {
		if (show) {
			vproveedor.lblAviso.setText("Debe introducir todos los datos");
			vproveedor.lblAviso.setVisible(true);
		} else {
			vproveedor.lblAviso.setText("Todos los campos correctos");
			vproveedor.lblAviso.setVisible(false);
		}
	}

	public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}
	
	private boolean isFormValid() {
		if (vproveedor.CIF.getText().trim().length() != 9) {
			vproveedor.CIF.setForeground(Color.RED);
			vproveedor.lblAviso.setText("Introduce CIF de proveedor valido.");
			return false;
		}
		if (vproveedor.CIF.getText().equals(EMPTY_STRING)) {
			return false;
		}
		// @TODO validar que es un CIF		
		vproveedor.CIF.setForeground(Color.BLACK);
			
		if (vproveedor.nombre_proveedor.getText().equals(EMPTY_STRING))
			return false;
		if (vproveedor.direccion.getText().equals(EMPTY_STRING))
			return false;
		if (vproveedor.ciudad.getText().equals(EMPTY_STRING))
			return false;
		if (vproveedor.CP.getText().equals(EMPTY_STRING))
			return false;
		// @TODO validar que es un teléfono válido
		if (vproveedor.telefono.getText().equals(EMPTY_STRING))
			return false;
		// @TODO validar que es un email (formato)
		if (vproveedor.mail.getText().trim().equals(EMPTY_STRING) || !validateEmail(vproveedor.mail.getText().trim())) {
			vproveedor.mail.setForeground(Color.RED);
			return false;
		} else {
			vproveedor.mail.setForeground(Color.BLACK);
		}

		return true;
	}

}
