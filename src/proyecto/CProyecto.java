package proyecto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import controladores.ControladorGenerico;
import util.Strings;

public class CProyecto extends ControladorGenerico {

	private VProyecto vproyecto;
	private Connection conexion;

	private OAProyecto bbdd = null;
	
	public CProyecto(VProyecto vproyecto, Connection conexion) {
		this.vproyecto = vproyecto;
		super.ventana = vproyecto;
		this.conexion = conexion;
	
		this.bbdd = new OAProyecto(this.conexion);		
		this.recargarTabla();
	}

	@Override
	protected void recargarTabla() {
		try {
			super.registros = bbdd.cargarProyectos();
			this.rellenarTabla();
		} catch (Exception e) {
			vproyecto.lblAviso.setText("Error al cargar la tabla de proyectos");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
	}
	
	@Override
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vproyecto.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Object registro: this.registros) {
			Proyecto proyecto = (Proyecto) registro;
			Object[] rowData = {
					proyecto.getCodigoProyecto(),
					proyecto.getNombreProyecto(),
					proyecto.getDescripcion()
			};
			model.addRow(rowData);
		}
	}
	
	@Override
	protected boolean isFormValid() {
		if (vproyecto.codigoProyecto.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vproyecto.nombreProyecto.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vproyecto.descripcion.getText().equals(Strings.EMPTY_STRING))
			return false;

		return true;
	}

	@Override
	protected void nuevo() {
		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Proyecto a = new Proyecto(
						Integer.parseInt(vproyecto.codigoProyecto.getText()),
						vproyecto.nombreProyecto.getText(),
						vproyecto.descripcion.getText()
				);
				this.bbdd.insertarProyectos(a);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vproyecto.lblAviso.setText("Debe introducir valor");
				vproyecto.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vproyecto.lblAviso.setText("Debe introducir un numero.");
				vproyecto.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vproyecto.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear linea de pedido.");
				vproyecto.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vproyecto.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}

	@Override
	protected void buscar() {
		try {
			Proyecto proyecto = this.bbdd.buscarProyectoPorID(vproyecto.codigoProyecto.getText());
			if (proyecto != null) {
				vproyecto.lblAviso.setText("");
				this.rellenarForm(proyecto);
				
				DefaultTableModel model = (DefaultTableModel)vproyecto.table.getModel();
				int rowCount = model.getRowCount();
				
				int inicio = -1;
				int fin = -1;
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == proyecto.getCodigoProyecto()) {
						if (inicio == -1) {
							inicio = i;
						}
						fin = i;
					}
				}
				if ( inicio != -1 && fin != -1) {
					vproyecto.table.setRowSelectionInterval( inicio , fin );
					vproyecto.table.scrollRectToVisible(vproyecto.table.getCellRect(inicio, 0, true));
				}

			} else {
				vproyecto.lblAviso.setText("Linea de pedido no encontrada.");
				this.resetFormFields();
			}
		} catch (SQLException sqlEx) {
			vproyecto.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar la linea de pedido.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void borrar() {
		int fila = vproyecto.table.getSelectedRow();
		try {
			this.bbdd.borrarProyecto(vproyecto.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vproyecto.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar la linea de pedido");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}		
	}

	@Override
	protected void modificar() {
		Proyecto a = new Proyecto(
				Integer.parseInt(vproyecto.codigoProyecto.getText()),
				vproyecto.nombreProyecto.getText(),
				vproyecto.descripcion.getText()
		);
		
		try {
			this.bbdd.modificarProyecto(a);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vproyecto.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar la linea de pedido");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void rellenarForm(Object o) {
		Proyecto proyecto = (Proyecto)o;
		vproyecto.codigoProyecto.setText(String.valueOf(proyecto.getCodigoProyecto()));
		vproyecto.nombreProyecto.setText(proyecto.getNombreProyecto());
		vproyecto.descripcion.setText(proyecto.getDescripcion());
	}
	
	@Override
	protected void resetFormFields() {
		vproyecto.codigoProyecto.setText("");
		vproyecto.nombreProyecto.setText("");
		vproyecto.descripcion.setText("");
	}	
}
