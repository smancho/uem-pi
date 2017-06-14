package ventanas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class VentanaGenerica extends JFrame {
	private static final long serialVersionUID = -4491644709120961250L;

	public void centrarEnPantalla() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("this.size:" +this.getSize().getWidth() + " x " + this.getSize().getHeight());
		this.setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, dim.width/2, dim.height/2);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);		
	}
}
