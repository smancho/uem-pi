package asiste;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PAsiste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		VAsiste vasiste = new VAsiste();
		CAsiste controlador = new CAsiste(vasiste, ConexionOracle.conectar());
		vasiste.setControlador(controlador);
		vasiste.setVisible(true);
	}

}
