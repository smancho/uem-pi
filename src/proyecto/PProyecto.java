package proyecto;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PProyecto {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VProyecto vproyecto = new VProyecto();
		CProyecto controlador = new CProyecto(vproyecto, ConexionOracle.conectar());
		vproyecto.setControlador(controlador);
		vproyecto.setVisible(true);	
	}
}
