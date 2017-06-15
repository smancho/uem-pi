package usa;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PUsa {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VUsa vusa = new VUsa();
		CUsa controlador = new CUsa(vusa, ConexionOracle.conectar());
		vusa.setControlador(controlador);
		vusa.setVisible(true);	
	}
}
