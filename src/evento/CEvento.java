package evento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import controladores.ControladorGenerico;
import util.Fechas;
import util.Strings;

public class CEvento extends ControladorGenerico {

	private VEvento vevento;
	private Connection conexion;

	private OAEvento bbdd = null;
	
	public CEvento(VEvento vevento, Connection conexion) {
		this.vevento = vevento;
		super.ventana = vevento;
		this.conexion = conexion;
	
		this.bbdd = new OAEvento(this.conexion);		
		this.recargarTabla();
	}

	@Override
	protected void recargarTabla() {
		try {
			super.registros = bbdd.cargarEventos();
			this.rellenarTabla();
		} catch (Exception e) {
			vevento.lblAviso.setText("Error al cargar la tabla de eventos");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
	}
	
	@Override
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vevento.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Object registro: this.registros) {
			Evento evento = (Evento) registro;
			Object[] rowData = {
					evento.getCodigoEvento(),
					evento.getTipo(),
					evento.getDescripcion(),
					Fechas.stringToDate(evento.getFechaInicio()),
					Fechas.stringToDate(evento.getFechaFin())
			};
			model.addRow(rowData);
		}
	}
	
	@Override
	protected boolean isFormValid() {
		if (vevento.codigoEvento.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vevento.tipoEvento.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vevento.descripcion.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vevento.fechaInicio.getDate() == null)
			return false;
		if (vevento.fechaFin.getDate() == null)
			return false;

		return true;
	}

	@Override
	protected void nuevo() {
		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Evento a = new Evento(
						Integer.parseInt(vevento.codigoEvento.getText()),
						vevento.tipoEvento.getText(),
						vevento.descripcion.getText(),
						Fechas.toOracleFormat(vevento.fechaInicio.getDate()),
						Fechas.toOracleFormat(vevento.fechaFin.getDate())
				);
				this.bbdd.insertarEvento(a);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vevento.lblAviso.setText("Debe introducir valor");
				vevento.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vevento.lblAviso.setText("Debe introducir un numero.");
				vevento.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vevento.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear el evento.");
				vevento.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vevento.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}

	@Override
	protected void buscar() {
		try {
			Evento material = this.bbdd.buscarEventoPorID(vevento.codigoEvento.getText());
			if (material != null) {
				vevento.lblAviso.setText("");
				this.rellenarForm(material);
				
				DefaultTableModel model = (DefaultTableModel)vevento.table.getModel();
				int rowCount = model.getRowCount();
				
				int inicio = -1;
				int fin = -1;
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == material.getCodigoEvento()) {
						if (inicio == -1) {
							inicio = i;
						}
						fin = i;
					}
				}
				if ( inicio != -1 && fin != -1) {
					vevento.table.setRowSelectionInterval( inicio , fin );
					vevento.table.scrollRectToVisible(vevento.table.getCellRect(inicio, 0, true));
				}

			} else {
				vevento.lblAviso.setText("Linea de pedido no encontrada.");
				this.resetFormFields();
			}
		} catch (SQLException sqlEx) {
			vevento.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar evento.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void borrar() {
		int fila = vevento.table.getSelectedRow();
		try {
			this.bbdd.borrarEvento(vevento.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vevento.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar el evento.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}		
	}

	@Override
	protected void modificar() {
		Evento a = new Evento(
				Integer.parseInt(vevento.codigoEvento.getText()),
				vevento.tipoEvento.getText(),
				vevento.descripcion.getText(),
				Fechas.toOracleFormat(vevento.fechaInicio.getDate()),
				Fechas.toOracleFormat(vevento.fechaFin.getDate())
		);
		
		try {
			this.bbdd.modificarEvento(a);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vevento.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar el evento.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void rellenarForm(Object o) {
		Evento evento = (Evento)o;
		vevento.codigoEvento.setText(String.valueOf(evento.getCodigoEvento()));
		vevento.tipoEvento.setText(evento.getTipo());
		vevento.descripcion.setText(evento.getDescripcion());
		vevento.fechaInicio.setDate(Fechas.stringToDate(evento.getFechaInicio()));
		vevento.fechaFin.setDate(Fechas.stringToDate(evento.getFechaFin()));
	}
	
	@Override
	protected void resetFormFields() {
		vevento.codigoEvento.setText("");
		vevento.tipoEvento.setText("");
		vevento.descripcion.setText("");
		vevento.fechaFin.setDate(null);
		vevento.fechaInicio.setDate(null);
	}	

}
