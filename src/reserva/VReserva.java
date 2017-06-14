package reserva;

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

public class VReserva extends VentanaGenerica {


	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevaReserva;
	protected JButton btnBuscarReserva;
	protected JButton btnModificarReserva;
	protected JButton btnBorrarReserva;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoProyecto;
	protected JTextField codigoMaquina;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblcodigoProyecto;
	protected JLabel lblcodigoMaquina;
	protected JLabel lblDescripcion;
	protected JDateChooser fechaInicio;
	protected JDateChooser fechaFin;

	public VReserva() {
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
				new String[] { "Usuario", "Evento" }));
		scrollPane.setViewportView(table);

		lblcodigoProyecto = new JLabel("Codigo Proyecto:");
		lblcodigoProyecto.setBounds(49, 60, 117, 16);
		contentPane.add(lblcodigoProyecto);

		lblcodigoMaquina = new JLabel("Codigo Maquina:");
		lblcodigoMaquina.setBounds(49, 85, 117, 16);
		contentPane.add(lblcodigoMaquina);

		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setBounds(49, 110, 93, 16);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin= new JLabel("Fecha fin:");
		lblFechaFin.setBounds(49, 135, 73, 16);
		contentPane.add(lblFechaFin);
		
		
		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(200, 35, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VReserva.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoProyecto = new JTextField();
		codigoProyecto.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoProyecto.setBounds(166, 60, 148, 16);
		contentPane.add(codigoProyecto);
		codigoProyecto.setColumns(10);
		
		codigoMaquina = new JTextField();
		codigoMaquina.setBounds(166, 85, 148, 16);
		contentPane.add(codigoMaquina);
		codigoMaquina.setColumns(10);

		fechaInicio = new JDateChooser();
		fechaInicio.setDateFormatString("yyyy-MM-dd");
		fechaInicio.setBounds(136, 110, 178, 18);
		contentPane.add(fechaInicio, BorderLayout.NORTH);
		
		fechaFin = new JDateChooser();
		fechaFin.setDateFormatString("yyyy-MM-dd");
		fechaFin.setBounds(134, 135, 180, 18);
		contentPane.add(fechaFin, BorderLayout.NORTH);
		

		btnNuevaReserva = new JButton("Nueva reserva");
		btnNuevaReserva.setBounds(535, 177, 134, 29);
		contentPane.add(btnNuevaReserva);


		btnBuscarReserva = new JButton("Buscar reserva");
		btnBuscarReserva .setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBuscarReserva .setBounds(535, 217, 134, 29);
		contentPane.add(btnBuscarReserva );
		
		btnBorrarReserva = new JButton("Borrar reserva");
		btnBorrarReserva.setEnabled(false);
		btnBorrarReserva.setBounds(535, 257, 134, 29);
		contentPane.add(btnBorrarReserva);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CReserva c) {
		codigoProyecto.addKeyListener(c);
		codigoProyecto.addKeyListener(c);
		

		btnNuevaReserva.addActionListener(c);
		btnBuscarReserva.addActionListener(c);
		btnModificarReserva.addActionListener(c);
		btnBorrarReserva.addActionListener(c);
		btnCancelar.addActionListener(c);
		btnSalir.addActionListener(c);
		table.addMouseListener(c);
	}
}
