package planifica;

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


public class CPlanifica extends MouseAdapter implements ActionListener,
KeyListener{
	VPlanifica vplanifica = null;
	ArrayList<Planifica> planificaciones = null;
	OAPlanifica bbdd = null;
	int fila = -1;
	
	public static final int BUTTONS_STATE_DEFAULT = 0;
	public static final int BUTTONS_STATE_ROW_SELECTED = 1;
	public static final int BUTTONS_STATE_NEW = 2;
	public static final int BUTTONS_STATE_MODIFY = 3;

	private static final String EMPTY_STRING = "";

	public CPlanifica() {

	}

	public CPlanifica(VPlanifica vplanifica, Connection con) {
		this.vplanifica = vplanifica;
		this.planificaciones = new ArrayList<Planifica>();
		this.bbdd = new OAPlanifica(con);
		this.recargarTabla();
	}

	private void recargarTabla() {
		try {
			this.planificaciones = bbdd.cargarPlanificaciones();
			this.rellenarTabla();
		} catch (Exception e) {
			vplanifica.lblAviso.setText("Error al cargar la tabla de planificaciones");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}
	}

	public void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vplanifica.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Planifica a : planificaciones) {
			model.addRow(new Object[] {  
					a.getNombreProyecto(), a.getNombreUsuario() 
			});
		}

	}
	
	public void rellenarFormulario(Planifica a) {
		vplanifica.codigoUsuario.setText(String.valueOf(a.getCodigoUsuario()));
		vplanifica.codigoProyecto.setText(String.valueOf(a.getCodigoProyecto()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == vplanifica.btnNuevoPlan) {
			insertarPlanificacion();
		} else if (obj == vplanifica.btnBuscarPlan) {
			buscarPlanificacion();
		} else if (obj == vplanifica.btnBorrarPlan) {
			borrarPlanificacion();
		} else if (obj == vplanifica.btnCancelar) {
			resetFormFields();
		} else if (obj == vplanifica.btnSalir) {
			vplanifica.dispose();
		}
	}

	private void buscarPlanificacion() {

		String codigoUsuario = vplanifica.codigoUsuario.getText();
		setButtonsState(CPlanifica.BUTTONS_STATE_MODIFY);
		
		vplanifica.lblAviso.setText("");

		ArrayList<Planifica> p = bbdd.buscarPlanificacionPorUsuario(codigoUsuario);
		DefaultTableModel model = (DefaultTableModel)vplanifica.table.getModel();
		int rowCount = model.getRowCount();
		for (Planifica planifica: p) {
			for (int i = 0; i < rowCount; i++) {
				if (((String)model.getValueAt(i, 0)).equals(planifica.getCodigoUsuario())) {
					vplanifica.table.setRowSelectionInterval( i , i );
					vplanifica.table.scrollRectToVisible(vplanifica.table.getCellRect(i, 0, true));
					this.rellenarFormulario(planifica);
					return;
				}
			}
		}
		this.resetFormFields(false);
		vplanifica.lblAviso.setText("El usuario sin proyectos planificados.");
	}

	private void borrarPlanificacion() {
		bbdd.borrarPlanificacion(vplanifica.codigoUsuario.getText());

		recargarTabla();
		resetFormFields();
	}

	private void insertarPlanificacion() throws NumberFormatException {
		if (!isFormValid()) {
			toggleWarning(true);
		} else {
			toggleWarning(false);

			try {
				Planifica a = new Planifica(
						Integer.parseInt(vplanifica.codigoUsuario.getText()),
						Integer.parseInt(vplanifica.codigoProyecto.getText())
				);
				
				bbdd.insertarPlanificacion(a);

				resetFormFields();
				recargarTabla();
			} catch (SQLException ex) {
				if (ex.getErrorCode() == 1) {
					vplanifica.lblAviso.setText("Planificación duplicada.");
				} else {
					System.out.println(ex.getErrorCode() + ": No se ha podido crear la planificación.");
					vplanifica.lblAviso.setText("No se ha podido crear la planificación.");
				}
				vplanifica.lblAviso.setVisible(true);
				System.err.println(ex.getMessage());
				ex.printStackTrace(System.err);
			} catch (NumberFormatException ex) {
				vplanifica.lblAviso.setText("No se ha podido crear la planificacion.");
				vplanifica.lblAviso.setVisible(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();

		if (obj == vplanifica.table) {
			this.fila = vplanifica.table.getSelectedRow();
			setButtonsState(CPlanifica.BUTTONS_STATE_ROW_SELECTED);
			this.rellenarFormulario(planificaciones.get(this.fila));
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
			vplanifica.btnSalir.setEnabled(true);
			vplanifica.btnCancelar.setEnabled(true);
			vplanifica.btnBorrarPlan.setEnabled(false);
			vplanifica.btnBuscarPlan.setEnabled(true);
			vplanifica.btnNuevoPlan.setEnabled(false);

			fila = -1;
			break;

		case BUTTONS_STATE_ROW_SELECTED:
			vplanifica.btnSalir.setEnabled(true);
			vplanifica.btnCancelar.setEnabled(true);
			vplanifica.btnBorrarPlan.setEnabled(true);
			vplanifica.btnBuscarPlan.setEnabled(false);
			vplanifica.btnNuevoPlan.setEnabled(false);
			break;

		case BUTTONS_STATE_NEW:
			vplanifica.btnSalir.setEnabled(true);
			vplanifica.btnCancelar.setEnabled(true);
			vplanifica.btnBorrarPlan.setEnabled(false);
			vplanifica.btnBuscarPlan.setEnabled(false);
			vplanifica.btnNuevoPlan.setEnabled(true);
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
			vplanifica.codigoUsuario.setText("");
		}
		vplanifica.codigoUsuario.setText("");
		vplanifica.codigoProyecto.setText("");
		
		this.fila = -1;
	}

	private void toggleWarning(boolean show) {
		if (show) {
			vplanifica.lblAviso.setText("Debe introducir todos los datos");
			vplanifica.lblAviso.setVisible(true);
		} else {
			vplanifica.lblAviso.setText("Todos los campos correctos");
			vplanifica.lblAviso.setVisible(false);
		}
	}
	
	private boolean isFormValid() {
		if (vplanifica.codigoUsuario.getText().equals(EMPTY_STRING))
			return false;
		if (vplanifica.codigoProyecto.getText().equals(EMPTY_STRING))
			return false;
		
		return true;
	}
}
