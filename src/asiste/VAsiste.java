package asiste;

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

public class VAsiste extends VentanaGenerica {

	private static final long serialVersionUID = 3492560752912221884L;

	protected JPanel contentPane;
	protected JTable table;
	protected JButton btnNuevaAsistencia;
	protected JButton btnBuscarAsistencia;
	protected JButton btnBorrarAsistencia;
	protected JButton btnCancelar;
	protected JButton btnSalir;
	protected JTextField codigoUsuario;
	protected JTextField codigoEvento;
	protected JLabel lblAviso;
	protected JLabel lblLogoLabel;
	protected JLabel lblCodigoUsuario;
	protected JLabel lblCodigoEvento;

	public VAsiste() {
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

		lblCodigoUsuario = new JLabel("ID Usuario:");
		lblCodigoUsuario.setBounds(49, 75, 90, 16);
		contentPane.add(lblCodigoUsuario);

		lblCodigoEvento = new JLabel("Codigo evento:");
		lblCodigoEvento.setBounds(49, 100, 105, 16);
		contentPane.add(lblCodigoEvento);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(200, 35, 200, 14);
		contentPane.add(lblAviso);
		
		lblLogoLabel = new JLabel("");
		lblLogoLabel.setIcon(new ImageIcon(VAsiste.class.getResource("/img/LogoFablab.png")));
		lblLogoLabel.setBounds(440, 30, 206, 60);
		contentPane.add(lblLogoLabel);

		codigoUsuario = new JTextField();
		codigoUsuario.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		codigoUsuario.setBounds(130, 75, 120, 16);
		contentPane.add(codigoUsuario);
		codigoUsuario.setColumns(10);
		
		codigoEvento = new JTextField();
		codigoEvento.setBounds(150, 100, 100, 16);
		contentPane.add(codigoEvento);
		codigoEvento.setColumns(10);

		btnNuevaAsistencia = new JButton("Nueva asistencia");
		btnNuevaAsistencia.setBounds(535, 177, 134, 29);
		contentPane.add(btnNuevaAsistencia);

		btnBuscarAsistencia = new JButton("Buscar asistencia");
		btnBuscarAsistencia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscarAsistencia.setBounds(535, 217, 134, 29);
		contentPane.add(btnBuscarAsistencia);
		
		btnBorrarAsistencia = new JButton("Borrar asistencia");
		btnBorrarAsistencia.setEnabled(false);
		btnBorrarAsistencia.setBounds(535, 257, 134, 29);
		contentPane.add(btnBorrarAsistencia);


		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 300, 117, 29);
		contentPane.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(550, 335, 117, 29);
		contentPane.add(btnSalir);

	}
	
	public void setControlador(CAsiste c) {
		codigoUsuario.addKeyListener(c);
		codigoEvento.addKeyListener(c);
		

		btnNuevaAsistencia.addActionListener(c);
		btnBorrarAsistencia.addActionListener(c);
		btnBuscarAsistencia.addActionListener(c);
		btnCancelar.addActionListener(c);
		table.addMouseListener(c);
	}
}
