package evento;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PEvento{
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	VEvento vevento = new VEvento();
	CEvento controlador = new CEvento(vevento, ConexionOracle.conectar());
	vevento.setControlador(controlador);
	vevento.setVisible(true);
	}
}
