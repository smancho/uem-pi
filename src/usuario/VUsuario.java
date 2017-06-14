package usuario;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ventanas.VentanaGenerica;

public class VUsuario extends VentanaGenerica {

	private static final long serialVersionUID = 3492560752912221884L;

	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevoUsuario;
	protected JButton btnBuscarUsuario;
	protected JButton btnModificarUsuario;
	protected JButton btnBorrarUsuario;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField usuarioID;
	protected JTextField userName;
	protected JTextField userSurname;
	protected JTextField userMail;
	protected JTextField userPhone;
	protected JTextField userKind;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblTipoUsuario;
	protected JLabel lblTelefono;
	protected JLabel lblMail;
	protected JLabel lblApellidos;
	protected JLabel lblNombre;
	protected JLabel lblUsuarioID;

	public VUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 420);
		setVisible(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 171, 504, 170);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Usuario", "Nombre", "Apellidos", "Mail",
						"Telefono", "Tipo" }));
		scrollPane.setViewportView(table);

		lblUsuarioID = new JLabel("Codigo usuario:");
		lblUsuarioID.setBounds(49, 35, 100, 16);
		contentPane.add(lblUsuarioID);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(49, 55, 61, 16);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(49, 75, 76, 16);
		contentPane.add(lblApellidos);

		lblMail = new JLabel("Mail:");
		lblMail.setBounds(49, 95, 61, 16);
		contentPane.add(lblMail);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(49, 115, 61, 16);
		contentPane.add(lblTelefono);

		lblTipoUsuario = new JLabel("Tipo usuario:");
		lblTipoUsuario.setBounds(49, 135, 90, 16);
		contentPane.add(lblTipoUsuario);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(200, 35, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VUsuario.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		usuarioID = new JTextField();
		usuarioID.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		usuarioID.setBounds(150, 35, 90, 16);
		contentPane.add(usuarioID);
		usuarioID.setColumns(10);
		
		userName = new JTextField();
		userName.setBounds(120, 55, 200, 16);
		contentPane.add(userName);
		userName.setColumns(10);

		userSurname = new JTextField();
		userSurname.setBounds(120, 75, 200, 16);
		contentPane.add(userSurname);
		userSurname.setColumns(10);

		userMail = new JTextField();
		userMail.setBounds(120, 95, 200, 16);
		contentPane.add(userMail);
		userMail.setColumns(10);

		userPhone = new JTextField();
		userPhone.setBounds(120, 115, 200, 16);
		contentPane.add(userPhone);
		userPhone.setColumns(10);

		userKind = new JTextField();
		userKind.setBounds(140, 135, 184, 16);
		contentPane.add(userKind);
		userKind.setColumns(10);

		btnNuevoUsuario = new JButton("Nuevo usuario");
		btnNuevoUsuario.setBounds(535, 167, 134, 29);
		contentPane.add(btnNuevoUsuario);

		btnBorrarUsuario = new JButton("Borrar usuario");
		btnBorrarUsuario.setEnabled(false);
		btnBorrarUsuario.setBounds(535, 257, 134, 29);
		contentPane.add(btnBorrarUsuario);

		btnModificarUsuario = new JButton("Modificar usuario");
		btnModificarUsuario.setEnabled(false);
		btnModificarUsuario.setBounds(535, 227, 134, 29);
		contentPane.add(btnModificarUsuario);

		btnBuscarUsuario = new JButton("Buscar usuario");
		btnBuscarUsuario.setBounds(535, 197, 134, 29);
		contentPane.add(btnBuscarUsuario);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CUsuario c) {
		usuarioID.addKeyListener(c);
		userName.addKeyListener(c);
		userSurname.addKeyListener(c);
		userMail.addKeyListener(c);
		userPhone.addKeyListener(c);
		userKind.addKeyListener(c);

		btnNuevoUsuario.addActionListener(c);
		btnBorrarUsuario.addActionListener(c);
		btnModificarUsuario.addActionListener(c);
		btnBuscarUsuario.addActionListener(c);
		btnCancelar.addActionListener(c);
		table.addMouseListener(c);
	}
}
