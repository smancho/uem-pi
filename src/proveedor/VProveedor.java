package proveedor;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import usuario.VUsuario;
import ventanas.VentanaGenerica;

public class VProveedor extends VentanaGenerica {
	
	private static final long serialVersionUID = 1L;
	
	private CProveedor controlador;
	
	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevoProveedor;
	protected JButton btnBuscarProveedor;
	protected JButton btnModificarProveedor;
	protected JButton btnBorrarProveedor;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField CIF;
	protected JTextField nombre_proveedor;
	protected JTextField direccion;
	protected JTextField ciudad;
	protected JTextField CP;
	protected JTextField telefono;
	protected JTextField mail;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblNewLabel;

	public VProveedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 175, 504, 170);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"CIF", "Nombre", "Direccion", "Ciudad", "CP", "Telefono",
				"Mail" }));
		scrollPane.setViewportView(table);

		JLabel lblCIF = new JLabel("CIF:");
		lblCIF.setBounds(49, 25, 61, 16);
		contentPane.add(lblCIF, BorderLayout.NORTH);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(49, 45, 61, 16);
		contentPane.add(lblNombre);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(49, 65, 76, 16);
		contentPane.add(lblDireccion);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(49, 85, 114, 16);
		contentPane.add(lblCiudad, BorderLayout.WEST);

		JLabel lblCP = new JLabel("Codigo postal:");
		lblCP.setBounds(49, 105, 114, 16);
		contentPane.add(lblCP);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(49, 125, 61, 16);
		contentPane.add(lblTelefono);

		JLabel lblMail = new JLabel("Mail:");
		lblMail.setBounds(49, 145, 61, 16);
		contentPane.add(lblMail, BorderLayout.WEST);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(350, 110, 330, 20);
		contentPane.add(lblAviso, BorderLayout.CENTER);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VUsuario.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		CIF = new JTextField();
		CIF.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		CIF.setBounds(120, 25, 200, 16);
		contentPane.add(CIF);
		CIF.setColumns(10);

		nombre_proveedor = new JTextField();
		nombre_proveedor.setBounds(120, 45, 200, 16);
		contentPane.add(nombre_proveedor);
		nombre_proveedor.setColumns(10);

		direccion = new JTextField();
		direccion.setBounds(120, 65, 200, 16);
		contentPane.add(direccion);
		direccion.setColumns(10);

		ciudad = new JTextField();
		ciudad.setBounds(120, 85, 200, 16);
		contentPane.add(ciudad, BorderLayout.CENTER);
		ciudad.setColumns(10);

		CP = new JTextField();
		CP.setBounds(150, 105, 170, 16);
		contentPane.add(CP);
		CP.setColumns(10);

		telefono = new JTextField();
		telefono.setBounds(120, 125, 200, 16);
		contentPane.add(telefono);
		telefono.setColumns(10);

		mail = new JTextField();
		mail.setBounds(120, 145, 200, 16);
		contentPane.add(mail);
		mail.setColumns(10);

		btnNuevoProveedor = new JButton("Nuevo proveedor");
		btnNuevoProveedor.setBounds(535, 150, 134, 29);
		contentPane.add(btnNuevoProveedor);
		
		btnBuscarProveedor = new JButton("Buscar proveedor");
		btnBuscarProveedor.setBounds(535, 185, 134, 29);
		contentPane.add(btnBuscarProveedor);

		btnModificarProveedor = new JButton("Modificar proveedor");
		btnModificarProveedor.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnModificarProveedor.setEnabled(false);
		btnModificarProveedor.setBounds(535, 220, 134, 29);
		contentPane.add(btnModificarProveedor);

		btnBorrarProveedor = new JButton("Borrar proveedor");
		btnBorrarProveedor.setEnabled(false);
		btnBorrarProveedor.setBounds(535, 255, 134, 29);
		contentPane.add(btnBorrarProveedor);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon("C:\\Users\\Sandra\\Desktop\\Proyectos\\img\\LogoFablab.png"));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

	}

	public void setControlador(CProveedor c) {
		this.controlador = c;
		this.controlador.rellenarTabla();
		this.controlador.setButtonsState(CProveedor.BUTTONS_STATE_DEFAULT);
		
		CIF.addKeyListener(c);
		nombre_proveedor.addKeyListener(c);
		direccion.addKeyListener(c);
		ciudad.addKeyListener(c);
		CP.addKeyListener(c);
		telefono.addKeyListener(c);
		mail.addKeyListener(c);

		btnNuevoProveedor.addActionListener(c);
		btnBorrarProveedor.addActionListener(c);
		btnModificarProveedor.addActionListener(c);
		btnBuscarProveedor.addActionListener(c);
		btnCancelar.addActionListener(c);

		table.addMouseListener(c);
	}
}
