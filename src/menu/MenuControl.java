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
import lineaPedido.CLineaPedido;
import lineaPedido.VLineaPedido;
import maquina.CMaquina;
import maquina.VMaquina;
import material.CMaterial;
import material.VMaterial;
import pedido.CPedido;
import pedido.VPedido;
import planifica.CPlanifica;
import planifica.VPlanifica;
import proveedor.CProveedor;
import proveedor.VProveedor;
import proyecto.CProyecto;
import proyecto.VProyecto;
import reserva.CReserva;
import reserva.VReserva;
import usa.CUsa;
import usa.VUsa;
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
		iniciarMenuMaquina();
		iniciarMenuMaterial();
		iniciarMenuProveedor();
		
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
		
		vmenu.miProveedorGestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProyecto vproyecto = new VProyecto();
				CProyecto contVentanaProyecto = new CProyecto(vproyecto, conn);
				vproyecto.setControlador(contVentanaProyecto);
				vproyecto.centrarEnPantalla();
				vproyecto.setVisible(true);	
			}
		});
		
		vmenu.miProyectoPlan.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VPlanifica vplanifica = new VPlanifica();
				CPlanifica contVentanaPlanifica = new CPlanifica(vplanifica, conn);
				vplanifica.setControlador(contVentanaPlanifica);
				vplanifica.centrarEnPantalla();
				vplanifica.setVisible(true);	
			}
		});
		
		vmenu.miProyectoUsa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VUsa vusa = new VUsa();
				CUsa contVentanaUsa = new CUsa(vusa, conn);
				vusa.setControlador(contVentanaUsa);
				vusa.centrarEnPantalla();
				vusa.setVisible(true);
			}
		});
		
		vmenu.miProyectoReserva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VReserva vreserva = new VReserva();
				CReserva contVentanaReserva = new CReserva(vreserva, conn);
				vreserva.setControlador(contVentanaReserva);
				vreserva.centrarEnPantalla();
				vreserva.setVisible(true);
			}
		});
	}
	
	private void iniciarMenuMaquina(){
		
		vmenu.miMaquinaGestion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VMaquina vmaquina = new VMaquina();
				CMaquina contVentanaMaquina = new CMaquina(vmaquina, conn);
				vmaquina.setControlador(contVentanaMaquina);
				vmaquina.setVisible(true);	
			}
		});
		
		vmenu.miMaquinaReserva.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VReserva vreserva = new VReserva();
				CReserva contVentanaReserva = new CReserva(vreserva, conn);
				vreserva.setControlador(contVentanaReserva);
				vreserva.centrarEnPantalla();
				vreserva.setVisible(true);
			}
		});
		
	}
	
	private void iniciarMenuMaterial(){
		
		vmenu.miMaterialGestion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VMaterial vmaterial = new VMaterial();
				CMaterial contVentanaMaterial = new CMaterial(vmaterial, conn);
				vmaterial.setControlador(contVentanaMaterial);
				vmaterial.setVisible(true);	
			}
		});
		
		vmenu.miMaterialProyecto.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VUsa vusa = new VUsa();
				CUsa contVentanaUsa = new CUsa(vusa, conn);
				vusa.setControlador(contVentanaUsa);
				vusa.centrarEnPantalla();
				vusa.setVisible(true);
			}
		});
		
		vmenu.miMaterialLineaPedido.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VLineaPedido vlinea = new VLineaPedido();
				CLineaPedido contVentanaLinea = new CLineaPedido(vlinea, conn);
				vlinea.setControlador(contVentanaLinea);
				vlinea.centrarEnPantalla();
				vlinea.setVisible(true);
			}
		});
	}
	
	private void iniciarMenuProveedor(){
		
		vmenu.miProveedorGestion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedor vproveedor = new VProveedor();
				CProveedor contVentanaProveedor = new CProveedor(vproveedor, conn);
				vproveedor.setControlador(contVentanaProveedor);
				vproveedor.centrarEnPantalla();
				vproveedor.setVisible(true);
			}
		});
		
		vmenu.miProveedorPedido.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VPedido vpedido = new VPedido();
				CPedido contVentanaPedido = new CPedido(vpedido, conn);
				vpedido.setControlador(contVentanaPedido);
				vpedido.centrarEnPantalla();
				vpedido.setVisible(true);
			}
		});
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

