package pedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.table.DefaultTableModel;

import util.Fechas;
import util.Strings;

public class CPedido extends MouseAdapter implements ActionListener,
		KeyListener {
	
	VPedido vpedido = null;
	OAPedido bbdd = null;
	Connection conexion;
	ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
	int fila = -1;
	
	public static final int BUTTONS_STATE_DEFAULT = 0;
	public static final int BUTTONS_STATE_ROW_SELECTED = 1;
	public static final int BUTTONS_STATE_NEW = 2;
	public static final int BUTTONS_STATE_MODIFY = 3;

	public CPedido() {}

	public CPedido(VPedido vpedido, Connection conexion) {
		this.conexion = conexion;
		this.vpedido = vpedido;
		
		this.bbdd = new OAPedido(this.conexion);
		this.recargarTabla();
	}


	private void setButtonsState(int state) {
		switch (state) {
		case BUTTONS_STATE_DEFAULT:
			vpedido.btnSalir.setEnabled(true);
			vpedido.btnCancelar.setEnabled(true);
			vpedido.btnBorrarPedido.setEnabled(false);
			vpedido.btnModificarPedido.setEnabled(false);
			vpedido.btnBuscarPedido.setEnabled(true);
			vpedido.btnNuevoPedido.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_ROW_SELECTED:
			vpedido.btnSalir.setEnabled(true);
			vpedido.btnCancelar.setEnabled(true);
			vpedido.btnBorrarPedido.setEnabled(true);
			vpedido.btnModificarPedido.setEnabled(true);
			vpedido.btnBuscarPedido.setEnabled(false);
			vpedido.btnNuevoPedido.setEnabled(false);
			break;

		case BUTTONS_STATE_NEW:
			vpedido.btnSalir.setEnabled(true);
			vpedido.btnCancelar.setEnabled(false);
			vpedido.btnBorrarPedido.setEnabled(false);
			vpedido.btnModificarPedido.setEnabled(false);
			vpedido.btnBuscarPedido.setEnabled(false);
			vpedido.btnNuevoPedido.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_MODIFY:
			vpedido.btnSalir.setEnabled(true);
			vpedido.btnCancelar.setEnabled(true);
			vpedido.btnBorrarPedido.setEnabled(false);
			vpedido.btnModificarPedido.setEnabled(true);
			vpedido.btnBuscarPedido.setEnabled(false);
			vpedido.btnNuevoPedido.setEnabled(false);
			break;

		default:
			break;
		}
	}

	private void resetFormFields() {
		this.resetFormFields(true);
	}
	private void resetFormFields(boolean resetCodigo) {
		if (resetCodigo) {
			vpedido.codigoPedido.setText("");
		}
		vpedido.codigoPedido.setText("");
		vpedido.fecha.setDate(null);
		vpedido.precioTotal.setText("");
		vpedido.cifProveedor.setText("");
	}

	private void toggleWarning(boolean show) {
		if (show) {
			vpedido.lblAviso.setText("Debe introducir todos los datos");
			vpedido.lblAviso.setVisible(true);
		} else {
			vpedido.lblAviso.setText("Todos los campos correctos");
			vpedido.lblAviso.setVisible(false);
		}
	}

	private boolean isFormValid() {
		
		if (vpedido.codigoPedido.getText().equals(Strings.EMPTY_STRING))
			return false;
		if (vpedido.fecha.getDate() == null)
			return false;
		if( vpedido.precioTotal.getText().equals(0))
			return false;
		if (vpedido.cifProveedor.getText().equals(Strings.EMPTY_STRING))
			return false;

		return true;
	}

	
	private void nuevo() {

		if (!isFormValid()) {
			toggleWarning(true);
		} else
			try {
				Pedido pedido = new Pedido(
					0, vpedido.cifProveedor.getText(), 0,
					Fechas.toOracleFormat(vpedido.fecha.getDate())
				);
				this.bbdd.insertarPedido(pedido);
				this.recargarTabla();
			} catch (InputMismatchException e1) {
				vpedido.lblAviso.setText("Debe introducir valor");
				vpedido.lblAviso.setVisible(true);
			} catch (NumberFormatException e1) {
				vpedido.lblAviso.setText("Debe introducir un numero.");
				vpedido.lblAviso.setVisible(true);
			} catch (SQLException sqlEx) {
				vpedido.lblAviso.setText(sqlEx.getErrorCode() + ": Error al crear usuario.");
				vpedido.lblAviso.setVisible(true);
				System.err.println(sqlEx.getMessage());
				sqlEx.printStackTrace(System.err);
			}

		resetFormFields();
		vpedido.lblAviso.setVisible(false);
		setButtonsState(BUTTONS_STATE_DEFAULT);
		
		this.recargarTabla();
	}
	
	private void buscarPedido() {
		try {
			Pedido pedido = this.bbdd.buscarPedidoPorID(vpedido.codigoPedido.getText());
			if (pedido != null) {
				vpedido.lblAviso.setText("");
				this.rellenarForm(pedido);
				
				DefaultTableModel model = (DefaultTableModel)vpedido.table.getModel();
				int rowCount = model.getRowCount();
	
				for (int i = 0; i < rowCount; i++) {
					if (Integer.parseInt(model.getValueAt(i, 0).toString()) == pedido.getCodigoPedido()) {			
						vpedido.table.setRowSelectionInterval( i , i );
						vpedido.table.scrollRectToVisible(vpedido.table.getCellRect(i, 0, true));
						break;
					}
				}
			} else {
				vpedido.lblAviso.setText("Pedido no encontrado");
				this.resetFormFields(false);
			}
		} catch (SQLException sqlEx) {
			vpedido.lblAviso.setText(sqlEx.getErrorCode() + ": Error al buscar pedido");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		} catch (Exception ex) {
			vpedido.lblAviso.setText("Error al buscar pedido");
			System.err.println(ex.getMessage());
			ex.printStackTrace(System.err);			
		}
	}


	private void borrar() {
		int fila = vpedido.table.getSelectedRow();
		try {
			this.bbdd.borrarPedido(vpedido.table.getValueAt(fila, 0).toString());
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vpedido.lblAviso.setText(sqlEx.getErrorCode() + ": Error al borrar pedido");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);			
		}
	}


	private void modificar() {
		Pedido pedido = new Pedido();
		pedido.setCodigoPedido(Integer.parseInt(vpedido.codigoPedido.getText()));
		pedido.setFecha(Fechas.toOracleFormat(vpedido.fecha.getDate()));
		pedido.setPrecioTotal(Float.parseFloat(vpedido.precioTotal.getText()));
		pedido.setCifProveedor(vpedido.cifProveedor.getText());
	
		try {
			this.bbdd.modificarPedido(pedido);
			this.recargarTabla();
	
			resetFormFields();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} catch (SQLException sqlEx) {
			vpedido.lblAviso.setText(sqlEx.getErrorCode() + ": Error al modificar el usuario");
			System.err.println(sqlEx.getMessage());
			sqlEx.printStackTrace(System.err);
		}
	}
	
	private void recargarTabla() {
		try {
			this.pedidos = bbdd.cargarPedidos();
			this.rellenarTabla();
		} catch (Exception e) {
			vpedido.lblAviso.setText("Error al cargar la tabla de pedidos");
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}
	}
	
	protected void rellenarTabla() {
		DefaultTableModel model = (DefaultTableModel)vpedido.table.getModel();
		for(int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
		
		DefaultTableModel table = (DefaultTableModel) vpedido.table.getModel();
		for (Pedido ped : pedidos) {
			Object[] rowData = { ped.getCodigoPedido(), 
					ped.getFecha().substring(0, ped.getFecha().indexOf(" ")),
					ped.getPrecioTotal(),ped.getCifProveedor() };
			table.addRow(rowData);

		}
	}

	private void rellenarForm(Pedido p) throws ParseException {
		vpedido.codigoPedido.setText(Integer.toString(p.getCodigoPedido()));
		vpedido.fecha.setDate(Fechas.stringToDate(p.getFecha()));
		vpedido.precioTotal.setText(String.valueOf(p.getPrecioTotal()));
		vpedido.cifProveedor.setText(p.getCifProveedor());
	}
	
	private void mostrar() {
		for (Pedido ped : pedidos) {
			System.out.println(ped);
		}
	}

	private void cancelar() {
		vpedido.table.clearSelection();
		setButtonsState(BUTTONS_STATE_DEFAULT);
		resetFormFields();
	}

	public void mouseClicked(MouseEvent e) {
		fila = vpedido.table.getSelectedRow();
		try {
			this.rellenarForm(pedidos.get(fila));
		}  catch (Exception ex) {
			vpedido.lblAviso.setText("Error al buscar pedido");
			System.err.println(ex.getMessage());
			ex.printStackTrace(System.err);			
		}

		setButtonsState(BUTTONS_STATE_ROW_SELECTED);	
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == vpedido.btnNuevoPedido) {
			nuevo();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vpedido.btnBorrarPedido) {
			borrar();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vpedido.btnModificarPedido) {
			modificar();
			setButtonsState(BUTTONS_STATE_ROW_SELECTED);
		 } else if (obj == vpedido.btnBuscarPedido) {
			 buscarPedido();
			 setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vpedido.btnCancelar){
			cancelar();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (obj == vpedido.btnSalir){
			vpedido.dispose();
		} else {
			mostrar();
		}

	}

	public void keyTyped(KeyEvent e) {
		boolean valid = isFormValid();
		if (valid) {
			if (fila == -1)
				setButtonsState(BUTTONS_STATE_DEFAULT);
			else
				setButtonsState(BUTTONS_STATE_MODIFY);
		}
		toggleWarning(!valid);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyTyped(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyTyped(e);
	}

}
