package maquina;

import java.awt.BorderLayout;
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

public class VMaquina extends VentanaGenerica {


	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevaMaquina;
	protected JButton btnBuscarMaquina;
	protected JButton btnModificarMaquina;
	protected JButton btnBorrarMaquina;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoMaquina;
	protected JTextField nombreMaquina;
	protected JTextField descripcion;
	protected JTextField disponibilidad;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblCodigoMaquina;
	protected JLabel lblNombreMaquina;
	protected JLabel lblDescripcion;
	protected JLabel lblDisponibilidad;

	public VMaquina() {
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
				new String[] { "Cod. maquina", "Nombre maquina", "Descripcion", "Disponibilidad" }));
		scrollPane.setViewportView(table);

		lblCodigoMaquina = new JLabel("Codigo maquina:");
		lblCodigoMaquina.setBounds(49, 60, 117, 16);
		contentPane.add(lblCodigoMaquina);

		lblNombreMaquina = new JLabel("Nombre maquina:");
		lblNombreMaquina.setBounds(49, 85, 117, 16);
		contentPane.add(lblNombreMaquina);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(49, 110, 93, 16);
		contentPane.add(lblDescripcion);
		
		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setBounds(49, 135, 103, 16);
		contentPane.add(lblDisponibilidad);
		
		
		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(172, 30, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VMaquina.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoMaquina = new JTextField();
		codigoMaquina.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoMaquina.setBounds(166, 60, 157, 16);
		contentPane.add(codigoMaquina);
		codigoMaquina.setColumns(10);
		
		nombreMaquina = new JTextField();
		nombreMaquina.setBounds(166, 85, 157, 16);
		contentPane.add(nombreMaquina);
		nombreMaquina.setColumns(10);

		descripcion = new JTextField();
		descripcion.setBounds(145, 110, 178, 16);
		contentPane.add(descripcion, BorderLayout.NORTH);
		
		disponibilidad = new JTextField();
		disponibilidad.setBounds(145, 135, 180, 16);
		contentPane.add(disponibilidad, BorderLayout.NORTH);
		

		btnNuevaMaquina = new JButton("Nueva maquina");
		btnNuevaMaquina.setBounds(535, 150, 134, 29);
		contentPane.add(btnNuevaMaquina);


		btnBuscarMaquina = new JButton("Buscar maquina");
		btnBuscarMaquina.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBuscarMaquina.setBounds(535, 185, 134, 29);
		contentPane.add(btnBuscarMaquina );
		
		btnModificarMaquina = new JButton("Modificar maquina");
		btnModificarMaquina.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnModificarMaquina.setEnabled(false);
		btnModificarMaquina.setBounds(535, 220, 134, 29);
		contentPane.add(btnModificarMaquina);
		
		btnBorrarMaquina = new JButton("Borrar maquina");
		btnBorrarMaquina.setEnabled(false);
		btnBorrarMaquina.setBounds(535, 255, 134, 29);
		contentPane.add(btnBorrarMaquina);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CMaquina c) {
		codigoMaquina.addKeyListener(c);
		nombreMaquina.addKeyListener(c);
		descripcion.addKeyListener(c);
		disponibilidad.addKeyListener(c);
		

		btnNuevaMaquina.addActionListener(c);
		btnBuscarMaquina.addActionListener(c);
		btnModificarMaquina.addActionListener(c);
		btnBorrarMaquina.addActionListener(c);
		btnCancelar.addActionListener(c);
		btnSalir.addActionListener(c);
		table.addMouseListener(c);
	}
}
