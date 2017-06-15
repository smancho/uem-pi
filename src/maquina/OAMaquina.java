package maquina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OAMaquina {
	
	private Connection conexion;
	
	public OAMaquina(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Maquina> cargarMaquinas() throws SQLException {
		ArrayList<Maquina> maquinas = new ArrayList<Maquina>();
		String consulta = 
			"SELECT " 
			    + "CODIGO_MAQUINA, "
			    + "NOMBRE_MAQUINA, "
			    + "DESCRIPCION, "
			    + "DISPONIBILIDAD "
			+ "FROM "
			    + "MAQUINA "
			+ "ORDER BY "
			    + "CODIGO_MAQUINA";
		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Maquina a = new Maquina();
			a.setCodigoMaquina(rset.getInt(1));
			a.setNombreMaquina(rset.getString(2));
			a.setDescripcion(rset.getString(3));
			a.setDisponibilidad(rset.getString(4).equalsIgnoreCase("DISPONIBLE"));
			maquinas.add(a);

		}
		rset.close();
		stmt.close();
		
		return maquinas;
	}
	
	public void insertarMaquina(Maquina a) throws SQLException {
		Statement stmt = conexion.createStatement();
		
		String cadSQL = 
				"INSERT INTO "
						+ "MAQUINA "
				+ "VALUES("
						+ a.getCodigoMaquina() + ", "
						+ "'" + a.getNombreMaquina() + "', "
						+ "'" + a.getDescripcion() + "', "
						+ "'" + a.disponibilidad() + "' "
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public void modificarMaquina(Maquina a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
			"UPDATE MAQUINA SET "
				+ "CODIGO_MAQUINA = " + a.getCodigoMaquina()+ ", "
				+ "NOMBRE_MAQUINA ='" + a.getNombreMaquina() + "', "
				+ "DESCRIPCION = '" + a.getDescripcion() + "', "
				+ "DISPONIBILIDAD= '" + a.disponibilidad() + "' "
			+ "WHERE CODIGO_MAQUINA = " + a.getCodigoMaquina();		
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public int borrarMaquina(String id) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = "DELETE FROM MAQUINA WHERE CODIGO_MAQUINA = " + id;
		int eliminados = stmt.executeUpdate(cadSQL);
		stmt.close();
		
		return eliminados;
	}
	
	public Maquina buscarMaquinaPorID(String id) throws SQLException {
		
		String busSQL = "SELECT * FROM MAQUINA WHERE CODIGO_MAQUINA = "+ id;
		Statement stmt = conexion.prepareStatement(busSQL);

		Maquina a = null;
		ResultSet rset = stmt.executeQuery(busSQL);
		if (rset.next()){
			a = new Maquina();
			a.setCodigoMaquina(rset.getInt(1));
			a.setNombreMaquina(rset.getString(2));
			a.setDescripcion(rset.getString(4));
			a.setDisponibilidad(rset.getString(4) == "DISPONIBLE");
		}	
		rset.close();
		stmt.close();
		
		return a;
	}	
}
