package lineaPedido;

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

public class VLineaPedido extends VentanaGenerica {


	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField codigoPedido;
	protected JTextField codigoMaterial;
	protected JTextField cantidad;
	protected JTextField precio;
	protected JLabel lblLogoLabel;
	protected JLabel lblcodigoProyecto;
	protected JLabel lblcodigoMaterial;
	protected JLabel lblDescripcion;

	public VLineaPedido() {
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
				new String[] { "Cod. pedido", "Cod. material", "Material", "Cantidad", "Precio" }));
		scrollPane.setViewportView(table);

		lblcodigoProyecto = new JLabel("Codigo pedido:");
		lblcodigoProyecto.setBounds(49, 60, 117, 16);
		contentPane.add(lblcodigoProyecto);

		lblcodigoMaterial = new JLabel("Codigo material:");
		lblcodigoMaterial.setBounds(49, 85, 117, 16);
		contentPane.add(lblcodigoMaterial);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(49, 110, 93, 16);
		contentPane.add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(49, 135, 73, 16);
		contentPane.add(lblPrecio);
		
		
		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(173, 30, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VLineaPedido.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoPedido = new JTextField();
		codigoPedido.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoPedido.setBounds(166, 60, 148, 16);
		contentPane.add(codigoPedido);
		codigoPedido.setColumns(10);
		
		codigoMaterial = new JTextField();
		codigoMaterial.setBounds(166, 85, 148, 16);
		contentPane.add(codigoMaterial);
		codigoMaterial.setColumns(10);

		cantidad = new JTextField();
		cantidad.setBounds(136, 110, 178, 18);
		contentPane.add(cantidad, BorderLayout.NORTH);
		
		precio = new JTextField();
		precio.setBounds(134, 135, 180, 18);
		contentPane.add(precio, BorderLayout.NORTH);
		

		btnNuevo = new JButton("Nueva linea");
		btnNuevo.setBounds(535, 150, 134, 29);
		contentPane.add(btnNuevo);


		btnBuscar = new JButton("Buscar linea");
		btnBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBuscar.setBounds(535, 185, 134, 29);
		contentPane.add(btnBuscar );
		
		btnModificar = new JButton("Modificar linea");
		btnModificar.setEnabled(false);
		btnModificar.setBounds(535, 220, 134, 29);
		contentPane.add(btnModificar);
		
		btnBorrar = new JButton("Borrar linea");
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
	
	public void setControlador(CLineaPedido c) {
		codigoPedido.addKeyListener(c);
		codigoMaterial.addKeyListener(c);
		cantidad.addKeyListener(c);
		precio.addKeyListener(c);		

		btnNuevo.addActionListener(c);
		btnBuscar.addActionListener(c);
		btnModificar.addActionListener(c);
		btnBorrar.addActionListener(c);
		btnCancelar.addActionListener(c);
		btnSalir.addActionListener(c);
		table.addMouseListener(c);
	}
}
