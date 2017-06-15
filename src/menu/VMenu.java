package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ventanas.VentanaGenerica;



public class VMenu extends VentanaGenerica {

	private static final long serialVersionUID = 1L;

	protected JPanel contentPane;
	protected JButton btnSalir;
	protected JMenuBar menuBar;
	protected JMenu menuUsuario;
	protected JMenu menuEvento;
	protected JMenu menuProyecto;
	protected JMenu menuMaquina;
	protected JMenu menuMaterial;
	protected JMenu menuProveedor;
	protected JMenuItem miUsuarioGestion, miUsuarioAsiste, miUsuarioPlanifica;
	protected JMenuItem miEventoGestion, miEventoAsiste, miEventoCalendario;
	protected JMenuItem miProyectoGestion, miProyectoReserva, miProyectoUsa, miProyectoPlan;	
	protected JMenuItem miMaquinaGestion, miMaquinaReserva;
	protected JMenuItem miMaterialGestion, miMaterialProyecto, miMaterialLinea;
	protected JMenuItem miProveedorGestion, miProveedorPedido;

	public VMenu() {
		setTitle("FABLABUE - Proyecto final");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(1024, 768));
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));

		menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setJMenuBar(menuBar);

		menuUsuario = new JMenu("Usuario");		
		miUsuarioGestion = new JMenuItem("Gestion de usuario");
		miUsuarioAsiste = new JMenuItem("Asiste a evento");
		miUsuarioPlanifica = new JMenuItem("Planifica proyecto");
		menuUsuario.add(miUsuarioGestion);
		menuUsuario.add(miUsuarioAsiste);
		menuUsuario.add(miUsuarioPlanifica);
		menuBar.add(menuUsuario);

		menuEvento = new JMenu("Evento");
		miEventoGestion = new JMenuItem("Gestion de evento");
		miEventoAsiste = new JMenuItem("Asiste a evento");
		miEventoCalendario = new JMenuItem("Calendario de eventos");
		menuEvento.add(miEventoGestion);
		menuEvento.add(miEventoAsiste);
		menuEvento.add(miEventoCalendario);
		menuBar.add(menuEvento);

		menuProyecto = new JMenu("Proyecto");
		miProyectoGestion = new JMenuItem("Gestion de proyecto");
		miProyectoPlan = new JMenuItem("Usuario que planifica");
		miProyectoUsa = new JMenuItem("Material que usa");
		miProyectoReserva = new JMenuItem("Reserva maquina");
		miMaterialProyecto = new JMenuItem("Material que usa");
		menuProyecto.add(miProyectoGestion);
		menuProyecto.add(miProyectoPlan);
		menuProyecto.add(miProyectoUsa);
		menuProyecto.add(miProyectoReserva);
		menuBar.add(menuProyecto);


		menuMaquina = new JMenu("Maquina");
		miMaquinaGestion = new JMenuItem("Gestion de maquina");
		miMaquinaReserva = new JMenuItem("Proyecto que la reserva");
		menuMaquina.add(miMaquinaGestion);
		menuMaquina.add(miMaquinaReserva);
		menuBar.add(menuMaquina);

		menuMaterial = new JMenu("Material");
		miMaterialGestion = new JMenuItem("Gestion de material");
		miMaterialProyecto = new JMenuItem("Proyecto que lo usa");
		miMaterialLinea = new JMenuItem("Linea de pedido");
		menuMaterial.add(miMaterialGestion);
		menuMaterial.add(miMaterialProyecto);
		menuMaterial.add(miMaterialLinea);
		menuBar.add(menuMaterial);


		menuProveedor = new JMenu("Proveedor");
		miProveedorGestion = new JMenuItem("Gestion de Proveedor");
		miProveedorPedido = new JMenuItem("Pedido");
		menuProveedor.add(miProveedorGestion);
		menuProveedor.add(miProveedorPedido);
		menuBar.add(menuProveedor);

		miUsuarioGestion.setEnabled(true);		
		miUsuarioAsiste.setEnabled(true);
		miUsuarioPlanifica.setEnabled(true);
		miEventoGestion.setEnabled(true);
		miEventoAsiste.setEnabled(true);
		miEventoCalendario.setEnabled(true);
		miProyectoGestion.setEnabled(true);
		miProyectoPlan.setEnabled(true);
		miProyectoReserva.setEnabled(true);
		miProyectoUsa.setEnabled(true);
		miMaquinaGestion.setEnabled(true);
		miMaquinaReserva.setEnabled(true);
		miMaterialGestion.setEnabled(true);
		miMaterialProyecto.setEnabled(true);
		miMaterialLinea.setEnabled(true);
		miProveedorGestion.setEnabled(true);
		miProveedorPedido.setEnabled(true);

		BackgroundPanel panelCentral = new BackgroundPanel(
				Toolkit.getDefaultToolkit().getImage(VMenu.class.getResource("/img/ImagenFabLab.jpg")));
		contentPane.add(panelCentral);

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(255, 215, 0));
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblTwitter = new JLabel("@fablabue");
		lblTwitter.setHorizontalAlignment(SwingConstants.CENTER);
		lblTwitter.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblTwitter.setIcon(new ImageIcon(VMenu.class.getResource("/img/logoTw.png")));
		panelInferior.add(lblTwitter);

		JLabel lblFb = new JLabel("FabLabUE");
		lblFb.setHorizontalAlignment(SwingConstants.CENTER);
		lblFb.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblFb.setIcon(new ImageIcon(VMenu.class.getResource("/img/logoFb.png")));
		panelInferior.add(lblFb);

		JLabel lblWeb = new JLabel("http://fablab.uem.es");
		lblWeb.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeb.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblWeb.setIcon(new ImageIcon(VMenu.class.getResource("/img/wegLogoDef.png")));
		panelInferior.add(lblWeb);

		btnSalir = new JButton("SALIR");
		btnSalir.getBackground();
		btnSalir.setBackground(Color.LIGHT_GRAY);
		btnSalir.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnSalir.setEnabled(true);
		panelInferior.add(btnSalir);
		
		this.pack();
	}
	
	public void setControlador(MenuControl controlador) {
		miUsuarioGestion.addActionListener(controlador);
		miUsuarioAsiste.addActionListener(controlador);
		miUsuarioPlanifica.addActionListener(controlador);
		
		miEventoGestion.addActionListener(controlador);
		miEventoAsiste.addActionListener(controlador);
		miEventoCalendario.addActionListener(controlador);
		
		miProyectoGestion.addActionListener(controlador);
		miProyectoReserva.addActionListener(controlador);
		miProyectoPlan.addActionListener(controlador);
		miProyectoUsa.addActionListener(controlador);
		
		miMaquinaGestion.addActionListener(controlador);
		miMaquinaReserva.addActionListener(controlador);
		
		miMaterialGestion.addActionListener(controlador);
		miMaterialProyecto.addActionListener(controlador);
		miMaterialLinea.addActionListener(controlador);
		
		miProveedorGestion.addActionListener(controlador);
		miProveedorPedido.addActionListener(controlador);

		btnSalir.addActionListener(controlador);

	}
	
}

