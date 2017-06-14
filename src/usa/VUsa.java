package usa;

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

public class VUsa extends VentanaGenerica {


	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevoUso;
	protected JButton btnBuscarUso;
	protected JButton btnBorrarUso;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoProyecto;
	protected JTextField codigoMaterial;
	protected JTextField cantidad;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblCodigoProyecto;
	protected JLabel lblCodigoMaterial;
	protected JLabel lblCantidad;

	public VUsa() {
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
				new String[] { "Proyecto", "Material", "Cantidad" }));
		scrollPane.setViewportView(table);

		lblCodigoProyecto = new JLabel("Codigo proyecto:");
		lblCodigoProyecto.setBounds(49, 70, 120, 16);
		contentPane.add(lblCodigoProyecto);

		lblCodigoMaterial = new JLabel("Codigo material:");
		lblCodigoMaterial.setBounds(49, 100, 105, 16);
		contentPane.add(lblCodigoMaterial);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(49, 130, 75, 16);
		contentPane.add(lblCantidad);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(164, 30, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VUsa.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoProyecto = new JTextField();
		codigoProyecto.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoProyecto.setBounds(160, 70, 120, 16);
		contentPane.add(codigoProyecto);
		codigoProyecto.setColumns(10);
		
		codigoMaterial = new JTextField();
		codigoMaterial.setBounds(160, 100, 120, 16);
		contentPane.add(codigoMaterial);
		codigoMaterial.setColumns(10);
		
		cantidad = new JTextField();
		cantidad.setBounds(120, 130, 160, 16);
		contentPane.add(cantidad);
		cantidad.setColumns(10);

		btnNuevoUso = new JButton("Nuevo uso");
		btnNuevoUso.setBounds(535, 177, 134, 29);
		contentPane.add(btnNuevoUso);

		btnBuscarUso = new JButton("Buscar uso");
		btnBuscarUso.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBuscarUso.setBounds(535, 217, 134, 29);
		contentPane.add(btnBuscarUso);
		
		btnBorrarUso = new JButton("Borrar uso");
		btnBorrarUso.setEnabled(false);
		btnBorrarUso.setBounds(535, 257, 134, 29);
		contentPane.add(btnBorrarUso);


		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CUsa c) {
		codigoProyecto.addKeyListener(c);
		codigoMaterial.addKeyListener(c);
		cantidad.addKeyListener(c);
		

		btnNuevoUso.addActionListener(c);
		btnBorrarUso.addActionListener(c);
		btnBuscarUso.addActionListener(c);
		btnCancelar.addActionListener(c);
		table.addMouseListener(c);
	}
}
