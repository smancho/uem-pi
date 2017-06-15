package evento;

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

public class VEvento extends VentanaGenerica {


	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField codigoEvento;
	protected JTextField tipoEvento;
	protected JTextField descripcion;
	protected JLabel lblLogoLabel;
	protected JLabel lblCodigoEvento;
	protected JLabel lblTipoEvento;
	protected JLabel lblDescripcion;
	protected JDateChooser fechaInicio;
	protected JDateChooser fechaFin;

	public VEvento() {
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
				new String[] { "Cod. Evento", "Tipo Evento", "Descripcion", 
						"Fecha Inicio", "Fecha Fin" }));
		scrollPane.setViewportView(table);

		lblCodigoEvento = new JLabel("Codigo evento:");
		lblCodigoEvento.setBounds(49, 40, 105, 16);
		contentPane.add(lblCodigoEvento);

		lblTipoEvento = new JLabel("Tipo evento:");
		lblTipoEvento.setBounds(49, 65, 105, 16);
		contentPane.add(lblTipoEvento);
		
		lblDescripcion= new JLabel("Descripcion:");
		lblDescripcion.setBounds(49, 90, 105, 16);
		contentPane.add(lblDescripcion);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setBounds(49, 115, 105, 16);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin= new JLabel("Fecha fin:");
		lblFechaFin.setBounds(49, 140, 105, 16);
		contentPane.add(lblFechaFin);
		
		
		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(362, 115, 239, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VEvento.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoEvento = new JTextField();
		codigoEvento.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoEvento.setBounds(150, 40, 100, 16);
		contentPane.add(codigoEvento);
		codigoEvento.setColumns(10);
		
		tipoEvento = new JTextField();
		tipoEvento.setBounds(150, 65, 170, 16);
		contentPane.add(tipoEvento);
		tipoEvento.setColumns(10);
		
		descripcion= new JTextField();
		descripcion.setBounds(150, 90, 170, 16);
		contentPane.add(descripcion);
		descripcion.setColumns(10);
		
		fechaInicio = new JDateChooser();
		fechaInicio.setDateFormatString("yyyy-MM-dd");
		fechaInicio.setBounds(150, 115, 170, 18);
		contentPane.add(fechaInicio, BorderLayout.NORTH);
		
		fechaFin = new JDateChooser();
		fechaFin.setDateFormatString("yyyy-MM-dd");
		fechaFin.setBounds(150, 140, 170, 18);
		contentPane.add(fechaFin, BorderLayout.NORTH);
		

		btnNuevo = new JButton("Nuevo evento");
		btnNuevo.setBounds(535, 150, 134, 29);
		contentPane.add(btnNuevo);


		btnBuscar = new JButton("Buscar evento");
		btnBuscar .setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBuscar .setBounds(535, 185, 134, 29);
		contentPane.add(btnBuscar );
		
		btnModificar = new JButton("Modificar evento");
		btnModificar.setEnabled(false);
		btnModificar.setBounds(535, 220, 134, 29);
		contentPane.add(btnModificar);
		
		btnBorrar = new JButton("Borrar evento");
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
	
	public void setControlador(CEvento c) {
		codigoEvento.addKeyListener(c);
		codigoEvento.addKeyListener(c);
		

		btnNuevo.addActionListener(c);
		btnBuscar.addActionListener(c);
		btnModificar.addActionListener(c);
		btnBorrar.addActionListener(c);
		btnCancelar.addActionListener(c);
		btnSalir.addActionListener(c);
		table.addMouseListener(c);
	}
}
