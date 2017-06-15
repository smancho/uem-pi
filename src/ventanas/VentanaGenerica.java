package ventanas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class VentanaGenerica extends JFrame {
	private static final long serialVersionUID = -4491644709120961250L;
	
	public JLabel lblAviso; 
	
	public JButton btnNuevo;
	public JButton btnBuscar;
	public JButton btnModificar;
	public JButton btnBorrar;
	public JButton btnCancelar;
	public JButton btnSalir;
	
	public JTable table;
	
	public void centrarEnPantalla() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("this.size:" +this.getSize().getWidth() + " x " + this.getSize().getHeight());
		this.setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, dim.width/2, dim.height/2);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);		
	}
}
