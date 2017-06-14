package usuario;

import java.sql.Connection;
import java.sql.SQLException;

import bdd.ConexionOracle;

public class PUsuario {

	public static void main(String[] args) {
		Connection conn = null;
		// Conectamos con la bdd controlando los posibles errores por separado
		// Al hacerlo de esta manera, el sistema será más robusto y mantenible
		try {
			conn = ConexionOracle.conectar();
		} catch (SQLException e) {
			System.err.println("Error al conectar con la bdd:" + e.getMessage());
			e.printStackTrace();			
		} catch (ClassNotFoundException e) {
			System.err.println("Excepcion de configuracion:" + e.getMessage());
			e.printStackTrace();			
		} catch (Exception e) {
			System.err.println("Excepcion no esperada:" + e.getMessage());
			e.printStackTrace();
		}
		VUsuario vusuario = new VUsuario();
		CUsuario controlador = new CUsuario(vusuario, conn);
		vusuario.setControlador(controlador);
		vusuario.setVisible(true);
	}
}
