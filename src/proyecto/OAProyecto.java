package proyecto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OAProyecto {
	
	private Connection conexion;
	
	public OAProyecto(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Proyecto> cargarProyectos() throws SQLException {
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		String consulta = 
			"SELECT " 
			    + "CODIGO_PROYECTO, "
			    + "NOMBRE_PROYECTO, "
			    + "DESCRIPCION "
			+ "FROM "
			    + "PROYECTO "
			+ "ORDER BY "
			    + "CODIGO_PROYECTO";
		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Proyecto a = new Proyecto();
			a.setCodigoProyecto(rset.getInt(1));
			a.setNombreProyecto(rset.getString(2));
			a.setDescripcion(rset.getString(3));
			proyectos.add(a);

		}
		rset.close();
		stmt.close();
		
		return proyectos;
	}
	
	public void insertarProyectos(Proyecto a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
				"INSERT INTO "
						+ "PROYECTO "
				+ "VALUES("
						+ a.getCodigoProyecto() + ", "
						+ "'" + a.getNombreProyecto() + "', "
						+ "'" + a.getDescripcion() + "'"
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public void modificarProyecto(Proyecto a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
			"UPDATE PROYECTO SET "
				+ "NOMBRE_PROYECTO = '" + a.getNombreProyecto() + "', "
				+ "DESCRIPCION = '" + a.getDescripcion() + "' "
			+ "WHERE CODIGO_PROYECTO = " + a.getCodigoProyecto();		
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public int borrarProyecto(String id) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = "DELETE FROM PROYECTO WHERE CODIGO_PROYECTO = " + id;
		int eliminados = stmt.executeUpdate(cadSQL);
		stmt.close();
		
		return eliminados;
	}
	
	public Proyecto buscarProyectoPorID(String id) throws SQLException {
		
		String busSQL = "SELECT * FROM PROYECTO WHERE CODIGO_PROYECTO = "+ id;
		Statement stmt = conexion.prepareStatement(busSQL);

		Proyecto a = null;
		ResultSet rset = stmt.executeQuery(busSQL);
		if (rset.next()){
			a = new Proyecto();
			a.setCodigoProyecto(rset.getInt(1));
			a.setNombreProyecto(rset.getString(2));
			a.setDescripcion(rset.getString(3));
		}	
		rset.close();
		stmt.close();
		
		return a;
	}
}
