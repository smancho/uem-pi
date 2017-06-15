package material;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PMaterial {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VMaterial vmaterial = new VMaterial();
		CMaterial controlador = new CMaterial(vmaterial, ConexionOracle.conectar());
		vmaterial.setControlador(controlador);
		vmaterial.setVisible(true);	
	}

}
