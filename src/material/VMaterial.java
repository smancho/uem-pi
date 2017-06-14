package material;

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

public class VMaterial extends VentanaGenerica {


	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevoMaterial;
	protected JButton btnBuscarMaterial;
	protected JButton btnModificarMaterial;
	protected JButton btnBorrarMaterial;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoMaterial;
	protected JTextField nombreMaterial;
	protected JTextField tipoMaterial;
	protected JTextField descripcion;
	protected JTextField stock;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblCodigoMaterial;
	protected JLabel lblNombreMaterial;
	protected JLabel lblTipoMaterial;
	protected JLabel lblDescripcion;
	protected JLabel lblStock;


	public VMaterial() {
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
				new String[] { "Cod. material", "Nombre", "Tipo", "Descripcion",
						"Stock"}));
		scrollPane.setViewportView(table);

		lblCodigoMaterial = new JLabel("Codigo material:");
		lblCodigoMaterial.setBounds(49, 35, 117, 16);
		contentPane.add(lblCodigoMaterial);

		lblNombreMaterial = new JLabel("Nombre:");
		lblNombreMaterial.setBounds(49, 55, 61, 16);
		contentPane.add(lblNombreMaterial);

		lblTipoMaterial = new JLabel("Tipo material:");
		lblTipoMaterial.setBounds(49, 75, 90, 16);
		contentPane.add(lblTipoMaterial);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(49, 95, 90, 16);
		contentPane.add(lblDescripcion);

		lblStock = new JLabel("Telefono:");
		lblStock.setBounds(49, 115, 61, 16);
		contentPane.add(lblStock);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(367, 117, 247, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VMaterial.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoMaterial = new JTextField();
		codigoMaterial.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoMaterial.setBounds(165, 35, 90, 16);
		contentPane.add(codigoMaterial);
		codigoMaterial.setColumns(10);
		
		nombreMaterial = new JTextField();
		nombreMaterial.setBounds(135, 55, 200, 16);
		contentPane.add(nombreMaterial);
		nombreMaterial.setColumns(10);

		tipoMaterial = new JTextField();
		tipoMaterial.setBounds(135, 75, 200, 16);
		contentPane.add(tipoMaterial);
		tipoMaterial.setColumns(10);

		descripcion = new JTextField();
		descripcion.setBounds(135, 95, 200, 16);
		contentPane.add(descripcion);
		descripcion.setColumns(10);

		stock = new JTextField();
		stock.setBounds(135, 115, 200, 16);
		contentPane.add(stock);
		stock.setColumns(10);

		btnNuevoMaterial = new JButton("Nuevo material");
		btnNuevoMaterial.setBounds(535, 167, 134, 29);
		contentPane.add(btnNuevoMaterial);

		btnBorrarMaterial = new JButton("Borrar material");
		btnBorrarMaterial.setEnabled(false);
		btnBorrarMaterial.setBounds(535, 257, 134, 29);
		contentPane.add(btnBorrarMaterial);

		btnModificarMaterial = new JButton("Modificar material");
		btnModificarMaterial.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnModificarMaterial.setEnabled(false);
		btnModificarMaterial.setBounds(535, 227, 134, 29);
		contentPane.add(btnModificarMaterial);

		btnBuscarMaterial = new JButton("Buscar material");
		btnBuscarMaterial.setBounds(535, 197, 134, 29);
		contentPane.add(btnBuscarMaterial);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CMaterial c) {
		codigoMaterial.addKeyListener(c);
		nombreMaterial.addKeyListener(c);
		tipoMaterial.addKeyListener(c);
		descripcion.addKeyListener(c);
		stock.addKeyListener(c);

		btnNuevoMaterial.addActionListener(c);
		btnBorrarMaterial.addActionListener(c);
		btnModificarMaterial.addActionListener(c);
		btnBuscarMaterial.addActionListener(c);
		btnCancelar.addActionListener(c);
		table.addMouseListener(c);
	}
}
