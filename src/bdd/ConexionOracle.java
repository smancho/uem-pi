package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {
	private static final String url = "jdbc:oracle:thin:@192.168.1.13:1521:XE";
	private static final String user = "PROYECTO";
	private static final String pass = "Morpheu$";
	
	public static Connection conectar() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conexion = DriverManager.getConnection(url, user, pass);
		System.out.println("- Conexion con ORACLE establecida -");
		return conexion;
	}

	public static void desconectar(Connection conexion) throws NullPointerException, SQLException {
		System.out.println("- Cerrando conexion a ORACLE -");
		conexion.close();
	}
}
