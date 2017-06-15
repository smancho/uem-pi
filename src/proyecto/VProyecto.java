package proyecto;

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


public class VProyecto extends VentanaGenerica {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField codigoProyecto;
	protected JTextField nombreProyecto;
	protected JTextField descripcion;
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

		btnNuevo = new JButton("Nuevo proyecto");
		btnNuevo.setBounds(535, 150, 134, 29);
		contentPane.add(btnNuevo);
		
		btnBuscar = new JButton("Buscar proyecto");
		btnBuscar.setBounds(535, 185, 134, 29);
		contentPane.add(btnBuscar);

		btnModificar = new JButton("Modificar proyecto");
		btnModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnModificar.setEnabled(false);
		btnModificar.setBounds(535, 220, 134, 29);
		contentPane.add(btnModificar);

		btnBorrar = new JButton("Borrar proyecto");
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(535, 255, 134, 29);
		contentPane.add(btnBorrar);

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
		

		btnNuevo.addActionListener(c);
		btnModificar.addActionListener(c);
		btnBuscar.addActionListener(c);
		btnBorrar.addActionListener(c);
		btnCancelar.addActionListener(c);
		btnSalir.addActionListener(c);
		table.addMouseListener(c);
	}
}
