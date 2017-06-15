package lineaPedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import controladores.ControladorGenerico;
import util.Strings;

public class CLineaPedido extends ControladorGenerico {

	private VLineaPedido vlinea;
	private Connection conexion;

	private OALineaPedido bbdd = null;
	
	public CLineaPedido(VLineaPedido vlinea, Connection conexion) {
		this.vlinea = vlinea;
		super.ventana = vlinea;
		this.conexion = conexion;
	
		this.bbdd = new OALineaPedido(this.conexion);		
		this.recargarTabla();
	}

	@Override
	protected void recargarTabla() {
		try {
			super.registros = bbdd.cargarLineas();
			this.rellenarTabla();
		} catch (Exception e) {
			vlinea.lblAviso.setText("Error al cargar la tabla de lineas de pedidos");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
	}
	
	@Override
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vlinea.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Object registro: this.registros) {
			LineaPedido linea = (LineaPedido) registro;
			Object[] rowData = {
					linea.getCodigoPedido(),
					linea.getCodigoMaterial(),
					linea.getNombreMaterial(),
					linea.getCantidad(),
					linea.getPrecio()
			};
			model.addRow(rowData);
		}
	}
	
	@Override
	protected boolean isFormValid() {
		if (vlinea.codigoPedido.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vlinea.codigoMaterial.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vlinea.cantidad.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vlinea.precio.getText().equals(Strings.EMPTY_STRING))
			return false;

		return true;
	}

	@Override
	protected void nuevo() {
		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				LineaPedido a = new LineaPedido(
						Integer.parseInt(vlinea.codigoPedido.getText()),
						Integer.parseInt(vlinea.codigoMaterial.getText()),
						Integer.parseInt(vlinea.cantidad.getText()),
						Float.parseFloat(vlinea.precio.getText())
				);
				this.bbdd.insertarLinea(a);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vlinea.lblAviso.setText("Debe introducir valor");
				vlinea.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vlinea.lblAviso.setText("Debe introducir un numero.");
				vlinea.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vlinea.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear linea de pedido.");
				vlinea.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vlinea.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}

	@Override
	protected void buscar() {
		try {
			LineaPedido lp = this.bbdd.buscarLineaPorID(vlinea.codigoPedido.getText());
			if (lp != null) {
				vlinea.lblAviso.setText("");
				this.rellenarForm(lp);
				
				DefaultTableModel model = (DefaultTableModel)vlinea.table.getModel();
				int rowCount = model.getRowCount();
				
				int inicio = -1;
				int fin = -1;
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == lp.getCodigoPedido()) {
						if (inicio == -1) {
							inicio = i;
						}
						fin = i;
					}
				}
				if ( inicio != -1 && fin != -1) {
					vlinea.table.setRowSelectionInterval( inicio , fin );
					vlinea.table.scrollRectToVisible(vlinea.table.getCellRect(inicio, 0, true));
				}

			} else {
				vlinea.lblAviso.setText("Linea de pedido no encontrada.");
				this.resetFormFields();
			}
		} catch (SQLException sqlEx) {
			vlinea.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar la linea de pedido.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void borrar() {
		int fila = vlinea.table.getSelectedRow();
		try {
			this.bbdd.borrarLinea(vlinea.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vlinea.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar la linea de pedido");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}		
	}

	@Override
	protected void modificar() {
		LineaPedido a = new LineaPedido(
				Integer.parseInt(vlinea.codigoPedido.getText()),
				Integer.parseInt(vlinea.codigoMaterial.getText()),
				Integer.parseInt(vlinea.cantidad.getText()),
				Float.parseFloat(vlinea.precio.getText())
		);
		
		try {
			this.bbdd.modificarLinea(a);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vlinea.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar la linea de pedido");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void rellenarForm(Object o) {
		LineaPedido lp = (LineaPedido)o;
		vlinea.codigoPedido.setText(String.valueOf(lp.getCodigoPedido()));
		vlinea.codigoMaterial.setText(String.valueOf(lp.getCodigoMaterial()));
		vlinea.cantidad.setText(String.valueOf(lp.getCantidad()));
		vlinea.precio.setText(String.valueOf(lp.getPrecio()));
	}
	
	@Override
	protected void resetFormFields() {
		vlinea.codigoPedido.setText("");
		vlinea.codigoMaterial.setText("");
		vlinea.cantidad.setText("");
		vlinea.precio.setText("");
	}	
}
