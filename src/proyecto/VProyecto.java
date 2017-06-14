package proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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


public class VProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevoProyecto;
	protected JButton btnBuscarProyecto;
	protected JButton btnModificarProyecto;
	protected JButton btnBorrarProyecto;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoProyecto;
	protected JTextField nombreProyecto;
	protected JTextField descripcion;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblCodigoProyecto;
	protected JLabel lblNombreProyecto;
	protected JLabel lblDescripcion;

	public VProyecto() {
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
				new String[] { "Cod. proyecto", "Nombre proyecto", "Descripcion" }));
		scrollPane.setViewportView(table);

		lblCodigoProyecto = new JLabel("Codigo proyecto:");
		lblCodigoProyecto.setBounds(49, 70, 120, 16);
		contentPane.add(lblCodigoProyecto);

		lblNombreProyecto = new JLabel("Nombre proyecto:");
		lblNombreProyecto.setBounds(49, 100, 117, 16);
		contentPane.add(lblNombreProyecto);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(49, 130, 86, 16);
		contentPane.add(lblDescripcion);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(165, 30, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VProyecto.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoProyecto = new JTextField();
		codigoProyecto.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoProyecto.setBounds(165, 70, 165, 16);
		contentPane.add(codigoProyecto);
		codigoProyecto.setColumns(10);
		
		nombreProyecto = new JTextField();
		nombreProyecto.setBounds(165, 100, 165, 16);
		contentPane.add(nombreProyecto);
		nombreProyecto.setColumns(10);
		
		descripcion = new JTextField();
		descripcion.setBounds(130, 130, 200, 16);
		contentPane.add(descripcion);
		descripcion.setColumns(10);

		btnNuevoProyecto = new JButton("Nuevo proyecto");
		btnNuevoProyecto.setBounds(535, 150, 134, 29);
		contentPane.add(btnNuevoProyecto);
		
		btnBuscarProyecto = new JButton("Buscar proyecto");
		btnBuscarProyecto.setBounds(535, 185, 134, 29);
		contentPane.add(btnBuscarProyecto);

		btnModificarProyecto = new JButton("Modificar proyecto");
		btnModificarProyecto.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnModificarProyecto.setEnabled(false);
		btnModificarProyecto.setBounds(535, 220, 134, 29);
		contentPane.add(btnModificarProyecto);

		btnBorrarProyecto = new JButton("Borrar proyecto");
		btnBorrarProyecto.setEnabled(false);
		btnBorrarProyecto.setBounds(535, 255, 134, 29);
		contentPane.add(btnBorrarProyecto);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CProyecto c) {
		codigoProyecto.addKeyListener(c);
		nombreProyecto.addKeyListener(c);
		descripcion.addKeyListener(c);
		

		btnNuevoProyecto.addActionListener(c);
		btnModificarProyecto.addActionListener(c);
		btnBuscarProyecto.addActionListener(c);
		btnBorrarProyecto.addActionListener(c);
		btnCancelar.addActionListener(c);
		table.addMouseListener(c);
	}
}
