package maquina;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import controladores.ControladorGenerico;
import util.Strings;

public class CMaquina extends ControladorGenerico {

	private VMaquina vmaquina;
	private Connection conexion;

	private OAMaquina bbdd = null;
	
	public CMaquina(VMaquina vmaquina, Connection conexion) {
		this.vmaquina = vmaquina;
		super.ventana = vmaquina;
		this.conexion = conexion;
	
		this.bbdd = new OAMaquina(this.conexion);		
		this.recargarTabla();
	}

	@Override
	protected void recargarTabla() {
		try {
			super.registros = bbdd.cargarMaquinas();
			this.rellenarTabla();
		} catch (Exception e) {
			vmaquina.lblAviso.setText("Error al cargar la tabla de maquinas.");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
	}
	
	@Override
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vmaquina.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Object registro: this.registros) {
			Maquina maquina = (Maquina) registro;
			Object[] rowData = {
					maquina.getCodigoMaquina(),
					maquina.getNombreMaquina(),
					maquina.getDescripcion(),
					maquina.disponibilidad()
			};
			model.addRow(rowData);
		}
	}
	
	@Override
	protected boolean isFormValid() {
		if (vmaquina.codigoMaquina.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vmaquina.nombreMaquina.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vmaquina.descripcion.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vmaquina.disponibilidad.getText().equals(Strings.EMPTY_STRING))
			return false;

		return true;
	}

	@Override
	protected void nuevo() {
		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Maquina a = new Maquina(
						Integer.parseInt(vmaquina.codigoMaquina.getText()),
						vmaquina.nombreMaquina.getText(),
						vmaquina.descripcion.getText(),
						vmaquina.disponibilidad.getText().equalsIgnoreCase("DISPONIBLE")
				);
				this.bbdd.insertarMaquina(a);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vmaquina.lblAviso.setText("Debe introducir valor");
				vmaquina.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vmaquina.lblAviso.setText("Debe introducir un numero.");
				vmaquina.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vmaquina.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear maquina.");
				vmaquina.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vmaquina.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}

	@Override
	protected void buscar() {
		try {
			Maquina material = this.bbdd.buscarMaquinaPorID(vmaquina.codigoMaquina.getText());
			if (material != null) {
				vmaquina.lblAviso.setText("");
				this.rellenarForm(material);
				
				DefaultTableModel model = (DefaultTableModel)vmaquina.table.getModel();
				int rowCount = model.getRowCount();
				
				int inicio = -1;
				int fin = -1;
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == material.getCodigoMaquina()) {
						if (inicio == -1) {
							inicio = i;
						}
						fin = i;
					}
				}
				if ( inicio != -1 && fin != -1) {
					vmaquina.table.setRowSelectionInterval( inicio , fin );
					vmaquina.table.scrollRectToVisible(vmaquina.table.getCellRect(inicio, 0, true));
				}

			} else {
				vmaquina.lblAviso.setText("Linea de pedido no encontrada.");
				this.resetFormFields();
			}
		} catch (SQLException sqlEx) {
			vmaquina.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar material.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void borrar() {
		int fila = vmaquina.table.getSelectedRow();
		try {
			this.bbdd.borrarMaquina(vmaquina.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vmaquina.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar material.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}		
	}

	@Override
	protected void modificar() {
		Maquina a = new Maquina(
				Integer.parseInt(vmaquina.codigoMaquina.getText()),
				vmaquina.nombreMaquina.getText(),
				vmaquina.descripcion.getText(),
				vmaquina.disponibilidad.getText().equalsIgnoreCase("DISPONIBLE")
		);
		
		try {
			this.bbdd.modificarMaquina(a);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vmaquina.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar el material.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void rellenarForm(Object o) {
		Maquina material = (Maquina)o;
		vmaquina.codigoMaquina.setText(String.valueOf(material.getCodigoMaquina()));
		vmaquina.nombreMaquina.setText(material.getNombreMaquina());
		vmaquina.descripcion.setText(material.getDescripcion());
		vmaquina.disponibilidad.setText(material.disponibilidad());
	}
	
	@Override
	protected void resetFormFields() {
		vmaquina.codigoMaquina.setText("");
		vmaquina.nombreMaquina.setText("");
		vmaquina.descripcion.setText("");
		vmaquina.disponibilidad.setText("");
	}	
}
