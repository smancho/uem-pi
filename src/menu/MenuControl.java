package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

import asiste.CAsiste;
import asiste.VAsiste;
import bdd.ConexionOracle;
import evento.CEvento;
import evento.VEvento;
import planifica.CPlanifica;
import planifica.VPlanifica;
import usuario.CUsuario;
import usuario.VUsuario;

public class MenuControl extends MouseAdapter implements ActionListener, WindowListener {

	private VMenu vmenu = null;
	private Connection conn;
	
	public MenuControl(VMenu vmenu) {
		this.vmenu = vmenu;
		try {
			conn = ConexionOracle.conectar();
		} catch (SQLException e) {
			System.err.println("Error al conectar con la bdd:" + e.getMessage());
			e.printStackTrace();			
		} catch (ClassNotFoundException e) {
			System.err.println("Excepcion de configuracion:" + e.getMessage());
			e.printStackTrace();			
		} catch (Exception e) {
			System.err.println("Excepcion no esperada:" + e.getMessage());
			e.printStackTrace();
		}
		
		iniciarMenuUsuario();
		iniciarMenuEvento();
		iniciarMenuProyecto();
		
	}
	
	private void iniciarMenuUsuario() {
		vmenu.miUsuarioGestion.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				VUsuario vusuario = new VUsuario();
				CUsuario contVentanaUsuario = new CUsuario(vusuario, conn);
				vusuario.setControlador(contVentanaUsuario);
				vusuario.centrarEnPantalla();
				vusuario.setVisible(true);
			}
		});
		
		vmenu.miUsuarioAsisteEvento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VAsiste vasiste = new VAsiste();
				CAsiste contVentanaAsiste = new CAsiste(vasiste, conn);
				vasiste.setControlador(contVentanaAsiste);
				vasiste.centrarEnPantalla();
				vasiste.setVisible(true);
			}
		});
		
		vmenu.miUsuarioPlanificaProyecto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VPlanifica vplanifica = new VPlanifica();
				CPlanifica contVentanaPlanifica = new CPlanifica(vplanifica, conn);
				vplanifica.setControlador(contVentanaPlanifica);
				vplanifica.centrarEnPantalla();
				vplanifica.setVisible(true);
			}
		});
	}
	
	private void iniciarMenuEvento() {
		
		vmenu.miEventoGestion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VEvento vevento = new VEvento();
				CEvento contVentanaEvento = new CEvento(vevento, conn);
				vevento.setControlador(contVentanaEvento);
				vevento.centrarEnPantalla();
				vevento.setVisible(true);
			}	
		});
		
		vmenu.miEventoAsiste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAsiste vasiste = new VAsiste();
				CAsiste contVentanaAsiste = new CAsiste(vasiste, conn);
				vasiste.setControlador(contVentanaAsiste);
				vasiste.centrarEnPantalla();
				vasiste.setVisible(true);	
			}	
		});
	}
	private void iniciarMenuProyecto() {
		
	}

	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			ConexionOracle.desconectar(conn);
		} catch (NullPointerException | SQLException exception) {
			System.err.println("Error cerrando conexion a bdd");
			System.err.println(exception.getMessage());
			exception.printStackTrace(System.err);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}

