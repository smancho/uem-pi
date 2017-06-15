package reserva;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import controladores.ControladorGenerico;
import util.Fechas;
import util.Strings;

public class CReserva extends ControladorGenerico {

	private VReserva vreserva;
	private Connection conexion;

	private OAReserva bbdd = null;
	
	public CReserva(VReserva vreserva, Connection conexion) {
		this.vreserva = vreserva;
		super.ventana = vreserva;
		this.conexion = conexion;
	
		this.bbdd = new OAReserva(this.conexion);		
		this.recargarTabla();
	}

	@Override
	protected void recargarTabla() {
		try {
			super.registros = bbdd.cargarReservas();
			this.rellenarTabla();
		} catch (Exception e) {
			vreserva.lblAviso.setText("Error al cargar la tabla de reservas.");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
	}
	
	@Override
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vreserva.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		for (Object registro: this.registros) {
			Reserva reserva = (Reserva) registro;
			Object[] rowData = {
					reserva.getCodigoProyecto(),
					reserva.getNombreProyecto(),
					reserva.getCodigoMaquina(),
					reserva.getNombreMaquina(),
					reserva.getFechaInicio(),
					reserva.getFechaFin()
			};
			model.addRow(rowData);
		}
	}
	
	@Override
	protected boolean isFormValid() {
		if (vreserva.codigoProyecto.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vreserva.codigoMaquina.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vreserva.fechaInicio.getDate() == null)
			return false;
		if (vreserva.fechaFin.getDate() == null)
			return false;

		return true;
	}

	@Override
	protected void nuevo() {
		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Reserva a = new Reserva(
						Integer.parseInt(vreserva.codigoProyecto.getText()),
						Integer.parseInt(vreserva.codigoMaquina.getText()),
						Fechas.dateToString(vreserva.fechaInicio.getDate()),
						Fechas.dateToString(vreserva.fechaFin.getDate())
				);
				this.bbdd.insertarReserva(a);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vreserva.lblAviso.setText("Debe introducir valor");
				vreserva.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vreserva.lblAviso.setText("Debe introducir un numero.");
				vreserva.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vreserva.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear la reserva.");
				vreserva.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vreserva.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}

	@Override
	protected void buscar() {
		try {
			Reserva lp = this.bbdd.buscarReservaPorID(vreserva.codigoProyecto.getText());
			if (lp != null) {
				vreserva.lblAviso.setText("");
				this.rellenarForm(lp);
				
				DefaultTableModel model = (DefaultTableModel)vreserva.table.getModel();
				int rowCount = model.getRowCount();
				
				int inicio = -1;
				int fin = -1;
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == lp.getCodigoProyecto()) {
						if (inicio == -1) {
							inicio = i;
						}
						fin = i;
					}
				}
				if ( inicio != -1 && fin != -1) {
					vreserva.table.setRowSelectionInterval( inicio , fin );
					vreserva.table.scrollRectToVisible(vreserva.table.getCellRect(inicio, 0, true));
				}

			} else {
				vreserva.lblAviso.setText("Reserva no encontrada.");
				this.resetFormFields();
			}
		} catch (SQLException sqlEx) {
			vreserva.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar la reserva.");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void borrar() {
		int fila = vreserva.table.getSelectedRow();
		try {
			this.bbdd.borrarReserva(vreserva.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vreserva.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar la reserva");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}		
	}

	@Override
	protected void modificar() {
		Reserva a = new Reserva(
				Integer.parseInt(vreserva.codigoProyecto.getText()),
				Integer.parseInt(vreserva.codigoMaquina.getText()),
				Fechas.dateToString(vreserva.fechaInicio.getDate()),
				Fechas.dateToString(vreserva.fechaFin.getDate())
		);
		
		try {
			this.bbdd.modificarReserva(a);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vreserva.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar la reserva");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}

	@Override
	protected void rellenarForm(Object o) {
		Reserva reserva = (Reserva)o;
		vreserva.codigoProyecto.setText(String.valueOf(reserva.getCodigoProyecto()));
		vreserva.codigoMaquina.setText(String.valueOf(reserva.getCodigoMaquina()));
		vreserva.fechaInicio.setDate(Fechas.stringToDate(reserva.getFechaInicio()));
		vreserva.fechaFin.setDate(Fechas.stringToDate(reserva.getFechaFin()));
	}
	
	@Override
	protected void resetFormFields() {
		vreserva.codigoProyecto.setText("");
		vreserva.codigoMaquina.setText("");
		vreserva.fechaInicio.setDate(null);
		vreserva.fechaFin.setDate(null);
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
