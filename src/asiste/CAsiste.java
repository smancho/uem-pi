package asiste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class CAsiste extends MouseAdapter implements ActionListener,
		KeyListener {

	VAsiste vasiste = null;
	ArrayList<Asiste> asistencias = null;
	OAAsiste bbdd = null;
	int fila = -1;
	
	public static final int BUTTONS_STATE_DEFAULT = 0;
	public static final int BUTTONS_STATE_ROW_SELECTED = 1;
	public static final int BUTTONS_STATE_NEW = 2;
	public static final int BUTTONS_STATE_MODIFY = 3;

	private static final String EMPTY_STRING = "";

	public CAsiste() {

	}

	public CAsiste(VAsiste vasiste, Connection con) {
		this.vasiste = vasiste;
		this.asistencias = new ArrayList<Asiste>();
		this.bbdd = new OAAsiste(con);
		this.recargarTabla();
	}

	private void recargarTabla() {
		try {
			this.asistencias = bbdd.cargarAsistencias();
			this.rellenarTabla();
		} catch (Exception e) {
			vasiste.lblAviso.setText("Error al cargar la tabla de asistencias");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}
	}

	public void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vasiste.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Asiste a : asistencias) {
			model.addRow(new Object[] {  
					a.getEvento() , a.getNombreApellidos(), 
			});
		}

	}
	
	public void rellenarFormulario(Asiste a) {
		vasiste.codigoUsuario.setText(String.valueOf(a.getCodigoUsuario()));
		vasiste.codigoEvento.setText(String.valueOf(a.getCodigoEvento()));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == vasiste.btnNuevaAsistencia) {
			insertarAsistencia();
		} else if (obj == vasiste.btnBuscarAsistencia) {
			buscarAsistencia();
		} else if (obj == vasiste.btnBorrarAsistencia) {
			borrarAsistencia();
		} else if (obj == vasiste.btnCancelar) {
			resetFormFields();
		} else if (obj == vasiste.btnSalir) {
			vasiste.dispose();
		}
	}

	private void buscarAsistencia() {

		String codigoUsuario = vasiste.codigoUsuario.getText();
		setButtonsState(CAsiste.BUTTONS_STATE_MODIFY);
		
		vasiste.lblAviso.setText("");

		ArrayList<Asiste> asistencias = bbdd.buscarAsistenciaPorUsuario(codigoUsuario);
		DefaultTableModel model = (DefaultTableModel)vasiste.table.getModel();
		int rowCount = model.getRowCount();
		for (Asiste asistencia : asistencias) {
			for (int i = 0; i < rowCount; i++) {
				if (((String)model.getValueAt(i, 0)).equals(asistencia.getCodigoUsuario())) {
					vasiste.table.setRowSelectionInterval( i , i );
					vasiste.table.scrollRectToVisible(vasiste.table.getCellRect(i, 0, true));
					this.rellenarFormulario(asistencia);
					return;
				}
			}
		}
		this.resetFormFields(false);
		vasiste.lblAviso.setText("El usuario no asiste a eventos proximamente.");
	}

	private void borrarAsistencia() {
		int fila = vasiste.table.getSelectedRow();
		DefaultTableModel table = (DefaultTableModel) vasiste.table
				.getModel();

		bbdd.borrarAsistencia(vasiste.codigoUsuario.getText());
		table.removeRow(fila);
		asistencias.remove(fila);

		recargarTabla();
		resetFormFields();
	}

	private void insertarAsistencia() throws NumberFormatException {
		if (!isFormValid()) {
			toggleWarning(true);
		} else {
			toggleWarning(false);
			Asiste a = new Asiste();

			try {
				a.setCodigoUsuario(Integer.parseInt(vasiste.codigoUsuario.getText()));
				a.setCodigoEvento(Integer.parseInt(vasiste.codigoEvento.getText()));
				
				asistencias.add(a);

				bbdd.insertarAsistencia(a);
				DefaultTableModel table = (DefaultTableModel) vasiste.table
						.getModel();
				table.addRow(new Object[] { a.getCodigoUsuario(), a.getNombreApellidos(), 
						a.getCodigoEvento(), a.getEvento() });

				resetFormFields();
				recargarTabla();
			} catch (SQLException ex) {
				if (ex.getErrorCode() == 1) {
					vasiste.lblAviso.setText("Asistencia duplicada.");
				} else {
					System.out.println(ex.getErrorCode() + ": No se ha podido crear la asistencia.");
					vasiste.lblAviso.setText("No se ha podido crear la asistencia.");
				}
				vasiste.lblAviso.setVisible(true);
				System.err.println(ex.getMessage());
				ex.printStackTrace(System.err);
			} catch (NumberFormatException ex) {
				vasiste.lblAviso.setText("No se ha podido crear la asistencia.");
				vasiste.lblAviso.setVisible(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();

		if (obj == vasiste.table) {
			this.fila = vasiste.table.getSelectedRow();
			setButtonsState(CAsiste.BUTTONS_STATE_ROW_SELECTED);
			this.rellenarFormulario(asistencias.get(this.fila));
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
		} else {
			setButtonsState(BUTTONS_STATE_DEFAULT);
		}
		toggleWarning(!valid);
		
	}

	public void setButtonsState(int state) {
		switch (state) {
		case BUTTONS_STATE_DEFAULT:
			vasiste.btnSalir.setEnabled(true);
			vasiste.btnCancelar.setEnabled(true);
			vasiste.btnBorrarAsistencia.setEnabled(false);
			vasiste.btnBuscarAsistencia.setEnabled(true);
			vasiste.btnNuevaAsistencia.setEnabled(false);

			fila = -1;
			break;

		case BUTTONS_STATE_ROW_SELECTED:
			vasiste.btnSalir.setEnabled(true);
			vasiste.btnCancelar.setEnabled(true);
			vasiste.btnBorrarAsistencia.setEnabled(true);
			vasiste.btnBuscarAsistencia.setEnabled(false);
			vasiste.btnNuevaAsistencia.setEnabled(false);
			break;

		case BUTTONS_STATE_NEW:
			vasiste.btnSalir.setEnabled(true);
			vasiste.btnCancelar.setEnabled(true);
			vasiste.btnBorrarAsistencia.setEnabled(false);
			vasiste.btnBuscarAsistencia.setEnabled(false);
			vasiste.btnNuevaAsistencia.setEnabled(true);

			break;

		default:
			break;
		}
	}

	private void resetFormFields() {
		this.resetFormFields(true);
	}
	private void resetFormFields(boolean resetCodigoUsuario) {
		if (resetCodigoUsuario) {
			vasiste.codigoUsuario.setText("");
		}
		vasiste.codigoUsuario.setText("");
		vasiste.codigoEvento.setText("");
		
		this.fila = -1;
	}

	private void toggleWarning(boolean show) {
		if (show) {
			vasiste.lblAviso.setText("Debe introducir todos los datos");
			vasiste.lblAviso.setVisible(true);
		} else {
			vasiste.lblAviso.setText("Todos los campos correctos");
			vasiste.lblAviso.setVisible(false);
		}
	}
	
	private boolean isFormValid() {
		
		if (vasiste.codigoUsuario.getText().equals(EMPTY_STRING))
			return false;
		if (vasiste.codigoEvento.getText().equals(EMPTY_STRING))
			return false;
		
		return true;
	}
}
