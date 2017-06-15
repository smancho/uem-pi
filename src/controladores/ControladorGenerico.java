package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ventanas.VentanaGenerica;

public abstract class ControladorGenerico extends MouseAdapter implements ActionListener, KeyListener {
	public static final int BUTTONS_STATE_DEFAULT = 0;
	public static final int BUTTONS_STATE_ROW_SELECTED = 1;
	public static final int BUTTONS_STATE_NEW = 2;
	public static final int BUTTONS_STATE_MODIFY = 3;

	protected VentanaGenerica ventana;
	protected int fila = -1;
	
	protected ArrayList<?> registros;
	
	protected void toggleWarning(boolean show) {
		if (show) {
			ventana.lblAviso.setText("Debe introducir todos los datos");
			ventana.lblAviso.setVisible(true);
		} else {
			ventana.lblAviso.setText("Todos los campos correctos");
			ventana.lblAviso.setVisible(false);
		}
	}

	protected void setButtonsState(int state) {
		switch (state) {
		case BUTTONS_STATE_DEFAULT:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(true);
			ventana.btnBorrar.setEnabled(false);
			ventana.btnModificar.setEnabled(false);
			ventana.btnBuscar.setEnabled(true);
			ventana.btnNuevo.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_ROW_SELECTED:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(true);
			ventana.btnBorrar.setEnabled(true);
			ventana.btnModificar.setEnabled(true);
			ventana.btnBuscar.setEnabled(false);
			ventana.btnNuevo.setEnabled(false);
			break;

		case BUTTONS_STATE_NEW:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(false);
			ventana.btnBorrar.setEnabled(false);
			ventana.btnModificar.setEnabled(false);
			ventana.btnBuscar.setEnabled(false);
			ventana.btnNuevo.setEnabled(true);

			fila = -1;
			break;

		case BUTTONS_STATE_MODIFY:
			ventana.btnSalir.setEnabled(true);
			ventana.btnCancelar.setEnabled(true);
			ventana.btnBorrar.setEnabled(false);
			ventana.btnModificar.setEnabled(true);
			ventana.btnBuscar.setEnabled(false);
			ventana.btnNuevo.setEnabled(false);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == ventana.btnNuevo) {
			nuevo();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (source == ventana.btnBorrar) {
			borrar();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (source == ventana.btnModificar) {
			modificar();
			setButtonsState(BUTTONS_STATE_ROW_SELECTED);
		 } else if (source == ventana.btnBuscar) {
			 buscar();
			 setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (source == ventana.btnCancelar){
			cancelar();
			setButtonsState(BUTTONS_STATE_DEFAULT);
		} else if (source == ventana.btnSalir) {
			ventana.dispose();
		}		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		fila = ventana.table.getSelectedRow();
		this.rellenarForm(registros.get(fila));

		setButtonsState(BUTTONS_STATE_ROW_SELECTED);	
	}

	@Override
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

	protected void cancelar() {
		ventana.table.clearSelection();
		setButtonsState(BUTTONS_STATE_DEFAULT);
		resetFormFields();		
	}
	
	protected abstract void nuevo();
	protected abstract void buscar();
	protected abstract void borrar();
	protected abstract void modificar();
	
	protected abstract void rellenarForm(Object o);
	protected abstract void resetFormFields();
	
	protected abstract boolean isFormValid();
	
	protected abstract void rellenarTabla();
	protected abstract void recargarTabla();
}
