package usa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import controladores.ControladorGenerico;
import util.Strings;

public class CUsa extends ControladorGenerico {

	private VUsa vusa;
	private Connection conexion;

	private OAUsa bbdd = null;
	
	public CUsa(VUsa vusa, Connection conexion) {
		this.vusa = vusa;
		super.ventana = vusa;
		this.conexion = conexion;
	
		this.bbdd = new OAUsa(this.conexion);		
		this.recargarTabla();
	}

	@Override
	protected void recargarTabla() {
		try {
			super.registros = bbdd.cargarUsos();
			this.rellenarTabla();
		} catch (Exception e) {
			vusa.lblAviso.setText("Error al cargar la tabla de lineas de pedidos");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
	}
	
	@Override
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vusa.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Object registro: this.registros) {
			Usa usa = (Usa) registro;
			Object[] rowData = {
					usa.getCodigoProyecto(),
					usa.getNombreProyecto(),
					usa.getCodigoMaterial(),
					usa.getNombreMaterial(),
					usa.getCantidad()
			};
			model.addRow(rowData);
		}
	}
	
	@Override
	protected boolean isFormValid() {
		if (vusa.codigoProyecto.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vusa.codigoMaterial.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vusa.cantidad.getText().equals(Strings.EMPTY_STRING))
			return false;

		return true;
	}

	@Override
	protected void nuevo() {
		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Usa a = new Usa(
						Integer.parseInt(vusa.codigoProyecto.getText()),
						Integer.parseInt(vusa.codigoMaterial.getText()),
						Integer.parseInt(vusa.cantidad.getText())
				);
				this.bbdd.insertarUso(a);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vusa.lblAviso.setText("Debe introducir valor");
				vusa.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vusa.lblAviso.setText("Debe introducir un numero.");
				vusa.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vusa.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear el uso.");
				vusa.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vusa.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}

	@Override
	protected void buscar() {
		try {
			Usa usa = this.bbdd.buscarUsoPorID(vusa.codigoProyecto.getText());
			if (usa != null) {
				vusa.lblAviso.setText("");
				this.rellenarForm(usa);
				
				DefaultTableModel model = (DefaultTableModel)vusa.table.getModel();
				int rowCount = model.getRowCount();
				
				int inicio = -1;
				int fin = -1;
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == usa.getCodigoProyecto()) {
						if (inicio == -1) {
							inicio = i;
						}
						fin = i;
					}
				}
				if ( inicio != -1 && fin != -1) {
					vusa.table.setRowSelectionInterval( inicio , fin );
					vusa.table.scrollRectToVisible(vusa.table.getCellRect(inicio, 0, true));
				}

			} else {
				vusa.lblAviso.setText("Linea de pedido no encontrada.");
				this.resetFormFields();
			}
		} catch (SQLException sqlEx) {
			vusa.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar el uso.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void borrar() {
		int fila = vusa.table.getSelectedRow();
		try {
			this.bbdd.borrarUso(vusa.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vusa.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar el uso");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}		
	}

	@Override
	protected void modificar() {
		Usa a = new Usa(
				Integer.parseInt(vusa.codigoProyecto.getText()),
				Integer.parseInt(vusa.codigoMaterial.getText()),
				Integer.parseInt(vusa.cantidad.getText())
		);
		
		try {
			this.bbdd.modificarUso(a);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vusa.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar la linea de pedido");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void rellenarForm(Object o) {
		Usa usa = (Usa)o;
		vusa.codigoProyecto.setText(String.valueOf(usa.getCodigoProyecto()));
		vusa.codigoMaterial.setText(String.valueOf(usa.getCodigoMaterial()));
		vusa.cantidad.setText(String.valueOf(usa.getCantidad()));
	}
	
	@Override
	protected void resetFormFields() {
		vusa.codigoProyecto.setText("");
		vusa.codigoMaterial.setText("");
		vusa.cantidad.setText("");
	}
	
	@Override
	protected void setButtonsState(int state) {
		switch (state) {
		case BUTTONS_STATE_DEFAULT:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(true);
			ventana.btnBorrar.setEnabled(false);
			ventana.btnBuscar.setEnabled(true);
			ventana.btnNuevo.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_ROW_SELECTED:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(true);
			ventana.btnBorrar.setEnabled(true);
			ventana.btnBuscar.setEnabled(false);
			ventana.btnNuevo.setEnabled(false);
			break;

		case BUTTONS_STATE_NEW:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(false);
			ventana.btnBorrar.setEnabled(false);
			ventana.btnBuscar.setEnabled(false);
			ventana.btnNuevo.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_MODIFY:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(true);
			ventana.btnBorrar.setEnabled(false);
			ventana.btnBuscar.setEnabled(false);
			ventana.btnNuevo.setEnabled(false);
			break;

		default:
			break;
		}
	}
}
