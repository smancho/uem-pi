package usa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OAUsa {
	private Connection conexion;
	
	public OAUsa(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Usa> cargarUsos() throws SQLException {
		ArrayList<Usa> usa = new ArrayList<Usa>();
		String consulta = 
			"SELECT " 
			    + "U.CODIGO_PROYECTO, "
			    + "P.NOMBRE_PROYECTO, "
			    + "U.CODIGO_MATERIAL, "
			    + "M.NOMBRE_MATERIAL, "
			    + "U.CANTIDAD "
			+ "FROM "
				+ "USA U, "
				+ "PROYECTO P, "
			    + "MATERIAL M "
			+ "WHERE "
			    + "U.CODIGO_PROYECTO = P.CODIGO_PROYECTO "
			    + "AND U.CODIGO_MATERIAL = M.CODIGO_MATERIAL "
			+ "ORDER BY "
			    + "U.CODIGO_PROYECTO";
		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Usa u = new Usa();
			u.setCodigoProyecto(rset.getInt(1));
			u.setNombreProyecto(rset.getString(2));
			u.setCodigoMaterial(rset.getInt(3));
			u.setNombreMaterial(rset.getString(4));
			u.setCantidad(rset.getInt(5));
			usa.add(u);
		}
		rset.close();
		stmt.close();
		
		return usa;
	}
	
	public void insertarUso(Usa a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
				"INSERT INTO "
						+ "USA "
				+ "VALUES("
						+ a.getCodigoProyecto() + ", "
						+ a.getCodigoMaterial() + ", "
						+ a.getCantidad() + " "
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public void modificarUso(Usa a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
			"UPDATE USA SET "
				+ "CODIGO_MATERIAL = " + a.getCodigoMaterial()+ ", "
				+ "CANTIDAD = " + a.getCantidad() + " "
			+ "WHERE CODIGO_PROYECTO = " + a.getCodigoProyecto();		
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public int borrarUso(String id) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = "DELETE FROM USA WHERE CODIGO_PROYECTO = " + id;
		int eliminados = stmt.executeUpdate(cadSQL);
		stmt.close();
		
		return eliminados;
	}
	
	public Usa buscarUsoPorID(String id) throws SQLException {
		
		String busSQL = "SELECT * FROM USA WHERE CODIGO_PROYECTO= "+ id;
		Statement stmt = conexion.prepareStatement(busSQL);

		Usa a = null;
		ResultSet rset = stmt.executeQuery(busSQL);
		if (rset.next()){
			a = new Usa();
			a.setCodigoProyecto(rset.getInt(1));
			a.setCodigoMaterial(rset.getInt(2));
			a.setCantidad(rset.getInt(3));
		}	
		rset.close();
		stmt.close();
		
		return a;
	}	
}
