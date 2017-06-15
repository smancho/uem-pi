package pedido;

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

import com.toedter.calendar.JDateChooser;

import ventanas.VentanaGenerica;

public class VPedido extends VentanaGenerica {

	private static final long serialVersionUID = 1L;
	
	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevoPedido;
	protected JButton btnBuscarPedido;
	protected JButton btnModificarPedido;
	protected JButton btnBorrarPedido;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoPedido;
	protected JTextField precioTotal;
	protected JTextField cifProveedor;
	protected JLabel lblAviso;
	protected JDateChooser fecha;
	private JLabel lblLogoLabel;
	
	public VPedido() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				"Codigo pedido", "Fecha pedido", "Precio", "CIF proveedor" }));
		scrollPane.setViewportView(table);

		JLabel lblCodigo = new JLabel("Codigo pedido:");
		lblCodigo.setBounds(49, 65, 105, 16);
		contentPane.add(lblCodigo, BorderLayout.NORTH);

		JLabel lblFecha = new JLabel("Fecha pedido:");
		lblFecha.setBounds(49, 90, 105, 16);
		contentPane.add(lblFecha);

		JLabel lblPrecio = new JLabel("Precio total:");
		lblPrecio.setBounds(49, 115, 114, 16);
		contentPane.add(lblPrecio);

		JLabel lblCif = new JLabel("CIF proveedor:");
		lblCif.setBounds(49, 140, 95, 16);
		contentPane.add(lblCif, BorderLayout.WEST);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(350, 110, 330, 28);
		contentPane.add(lblAviso, BorderLayout.CENTER);

		codigoPedido = new JTextField();
		codigoPedido.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoPedido.setBounds(150, 65, 200, 16);
		contentPane.add(codigoPedido);
		codigoPedido.setColumns(10);
		
		

		precioTotal = new JTextField();
		precioTotal.setBounds(150, 115, 200, 16);
		contentPane.add(precioTotal);
		precioTotal.setColumns(10);

		cifProveedor = new JTextField();
		cifProveedor.setBounds(150, 140, 200, 16);
		contentPane.add(cifProveedor, BorderLayout.CENTER);
		cifProveedor.setColumns(10);

		btnNuevoPedido = new JButton("Nuevo pedido");
		btnNuevoPedido.setBounds(535, 150, 134, 29);
		contentPane.add(btnNuevoPedido);
		
		btnBuscarPedido = new JButton("Buscar pedido");
		btnBuscarPedido.setBounds(535, 185, 134, 29);
		contentPane.add(btnBuscarPedido);

		btnModificarPedido = new JButton("Modificar pedido");
		btnModificarPedido.setEnabled(false);
		btnModificarPedido.setBounds(535, 220, 134, 29);
		contentPane.add(btnModificarPedido);

		btnBorrarPedido = new JButton("Borrar pedido");
		btnBorrarPedido.setEnabled(false);
		btnBorrarPedido.setBounds(535, 255, 134, 29);
		contentPane.add(btnBorrarPedido);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VPedido.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);
		
		fecha = new JDateChooser();
		fecha.setDateFormatString("yyyy-MM-dd");
		fecha.setBounds(150, 90, 170, 18);
		contentPane.add(fecha, BorderLayout.NORTH);
		// fecha.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{fecha.getCalendarButton()}));

	}



	public void setControlador(CPedido c) {
		
		codigoPedido.addKeyListener(c);
		fecha.addKeyListener(c);
		precioTotal.addKeyListener(c);
		cifProveedor.addKeyListener(c);

		btnNuevoPedido.addActionListener(c);
		btnBorrarPedido.addActionListener(c);
		btnModificarPedido.addActionListener(c);
		btnBuscarPedido.addActionListener(c);
		btnCancelar.addActionListener(c);
		btnSalir.addActionListener(c);

		table.addMouseListener(c);
		
	}
}

