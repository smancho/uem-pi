package planifica;

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

public class VPlanifica extends VentanaGenerica {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevoPlan;
	protected JButton btnBuscarPlan;
	protected JButton btnBorrarPlan;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoUsuario;
	protected JTextField codigoProyecto;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblcodigoUsuario;
	protected JLabel lblcodigoProyecto;

	public VPlanifica() {
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
				new String[] { "Proyecto", "Usuario" }));
		scrollPane.setViewportView(table);

		lblcodigoUsuario = new JLabel("Codigo usuario:");
		lblcodigoUsuario.setBounds(49, 75, 117, 16);
		contentPane.add(lblcodigoUsuario);

		lblcodigoProyecto = new JLabel("Codigo proyecto:");
		lblcodigoProyecto.setBounds(49, 110, 117, 16);
		contentPane.add(lblcodigoProyecto);
		
		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(160, 30, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VPlanifica.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoUsuario = new JTextField();
		codigoUsuario.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoUsuario.setBounds(160, 75, 148, 16);
		contentPane.add(codigoUsuario);
		codigoUsuario.setColumns(10);
		
		codigoProyecto = new JTextField();
		codigoProyecto.setBounds(160, 110, 148, 16);
		contentPane.add(codigoProyecto);
		codigoProyecto.setColumns(10);

		btnNuevoPlan = new JButton("Nuevo plan");
		btnNuevoPlan.setBounds(535, 177, 134, 29);
		contentPane.add(btnNuevoPlan);


		btnBuscarPlan= new JButton("Buscar plan");
		btnBuscarPlan.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBuscarPlan.setBounds(535, 217, 134, 29);
		contentPane.add(btnBuscarPlan);
		
		btnBorrarPlan = new JButton("Borrar plan");
		btnBorrarPlan.setEnabled(false);
		btnBorrarPlan.setBounds(535, 257, 134, 29);
		contentPane.add(btnBorrarPlan);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CPlanifica c) {
		codigoUsuario.addKeyListener(c);
		codigoProyecto.addKeyListener(c);
		

		btnNuevoPlan.addActionListener(c);
		btnBuscarPlan.addActionListener(c);
		btnBorrarPlan.addActionListener(c);
		btnCancelar.addActionListener(c);
		btnSalir.addActionListener(c);
		table.addMouseListener(c);
	}
}
