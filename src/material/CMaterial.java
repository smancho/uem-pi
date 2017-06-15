package material;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import controladores.ControladorGenerico;
import util.Strings;

public class CMaterial extends ControladorGenerico {

	private VMaterial vmaterial;
	private Connection conexion;

	private OAMaterial bbdd = null;
	
	public CMaterial(VMaterial vmaterial, Connection conexion) {
		this.vmaterial = vmaterial;
		super.ventana = vmaterial;
		this.conexion = conexion;
	
		this.bbdd = new OAMaterial(this.conexion);		
		this.recargarTabla();
	}

	@Override
	protected void recargarTabla() {
		try {
			super.registros = bbdd.cargarMateriales();
			this.rellenarTabla();
		} catch (Exception e) {
			vmaterial.lblAviso.setText("Error al cargar la tabla de lineas de pedidos");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
	}
	
	@Override
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vmaterial.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Object registro: this.registros) {
			Material material = (Material) registro;
			Object[] rowData = {
					material.getCodigoMaterial(),
					material.getNombreMaterial(),
					material.getTipoMaterial(),
					material.getDescripcion(),
					material.getStock()
			};
			model.addRow(rowData);
		}
	}
	
	@Override
	protected boolean isFormValid() {
		if (vmaterial.codigoMaterial.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vmaterial.nombreMaterial.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vmaterial.tipoMaterial.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vmaterial.descripcion.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vmaterial.stock.getText().equals(Strings.EMPTY_STRING))
			return false;

		return true;
	}

	@Override
	protected void nuevo() {
		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Material a = new Material(
						Integer.parseInt(vmaterial.codigoMaterial.getText()),
						vmaterial.nombreMaterial.getText(),
						vmaterial.tipoMaterial.getText(),
						vmaterial.descripcion.getText(),
						Integer.parseInt(vmaterial.stock.getText())
				);
				this.bbdd.insertarMaterial(a);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vmaterial.lblAviso.setText("Debe introducir valor");
				vmaterial.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vmaterial.lblAviso.setText("Debe introducir un numero.");
				vmaterial.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vmaterial.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear material.");
				vmaterial.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vmaterial.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}

	@Override
	protected void buscar() {
		try {
			Material material = this.bbdd.buscarMaterialPorID(vmaterial.codigoMaterial.getText());
			if (material != null) {
				vmaterial.lblAviso.setText("");
				this.rellenarForm(material);
				
				DefaultTableModel model = (DefaultTableModel)vmaterial.table.getModel();
				int rowCount = model.getRowCount();
				
				int inicio = -1;
				int fin = -1;
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == material.getCodigoMaterial()) {
						if (inicio == -1) {
							inicio = i;
						}
						fin = i;
					}
				}
				if ( inicio != -1 && fin != -1) {
					vmaterial.table.setRowSelectionInterval( inicio , fin );
					vmaterial.table.scrollRectToVisible(vmaterial.table.getCellRect(inicio, 0, true));
				}

			} else {
				vmaterial.lblAviso.setText("Linea de pedido no encontrada.");
				this.resetFormFields();
			}
		} catch (SQLException sqlEx) {
			vmaterial.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar material.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void borrar() {
		int fila = vmaterial.table.getSelectedRow();
		try {
			this.bbdd.borrarMaterial(vmaterial.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vmaterial.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar material.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}		
	}

	@Override
	protected void modificar() {
		Material a = new Material(
				Integer.parseInt(vmaterial.codigoMaterial.getText()),
				vmaterial.nombreMaterial.getText(),
				vmaterial.tipoMaterial.getText(),
				vmaterial.descripcion.getText(),
				Integer.parseInt(vmaterial.stock.getText())
		);
		
		try {
			this.bbdd.modificarMaterial(a);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vmaterial.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar el material.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void rellenarForm(Object o) {
		Material material = (Material)o;
		vmaterial.codigoMaterial.setText(String.valueOf(material.getCodigoMaterial()));
		vmaterial.nombreMaterial.setText(material.getNombreMaterial());
		vmaterial.tipoMaterial.setText(material.getTipoMaterial());
		vmaterial.descripcion.setText(material.getDescripcion());
		vmaterial.stock.setText(String.valueOf(material.getStock()));
	}
	
	@Override
	protected void resetFormFields() {
		vmaterial.codigoMaterial.setText("");
		vmaterial.nombreMaterial.setText("");
		vmaterial.tipoMaterial.setText("");
		vmaterial.descripcion.setText("");
		vmaterial.stock.setText("");
	}	

}
