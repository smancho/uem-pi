package evento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.Fechas;

public class OAEvento {
	
	private Connection conexion;
	
	public OAEvento(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Evento> cargarEventos() throws SQLException {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String consulta = 
			"SELECT " 
			    + "CODIGO_EVENTO, "
			    + "TIPO_EVENTO, "
			    + "DESCRIPCION, "
			    + "FECHA_INICIO,"
			    + "FECHA_FIN "
			+ "FROM "
			    + "EVENTO "
			+ "ORDER BY "
			    + "CODIGO_EVENTO";
		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Evento a = new Evento();
			a.setCodigoEvento(rset.getInt(1));
			a.setTipo(rset.getString(2));
			a.setDescripcion(rset.getString(3));
			a.setFechaInicio(Fechas.dateToString(rset.getDate(4)));
			a.setFechaFin(Fechas.dateToString(rset.getDate(5)));
			eventos.add(a);

		}
		rset.close();
		stmt.close();
		
		return eventos;
	}
	
	public void insertarEvento(Evento a) throws SQLException {
		Statement stmt = conexion.createStatement();
		
		String cadSQL = 
				"INSERT INTO "
						+ "EVENTO "
				+ "VALUES("
						+ a.getCodigoEvento() + ", "
						+ "'" + a.getTipo() + "', "
						+ "'" + a.getDescripcion() + "', "
						+ "TO_DATE('" + a.getFechaInicio() + "', 'YYYY-MM-DD HH24:mi:SS'), "
						+ "TO_DATE('" + a.getFechaFin() + "', 'YYYY-MM-DD HH24:mi:SS') "
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public void modificarEvento(Evento a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
			"UPDATE EVENTO SET "
				+ "TIPO_EVENTO ='" + a.getTipo() + "', "
				+ "DESCRIPCION = '" + a.getDescripcion() + "', "
				+ "FECHA_INICIO = TO_DATE('" + a.getFechaInicio() + "', 'YYYY-MM-DD HH24:mi:SS'), "
				+ "FECHA_FIN = TO_DATE('" + a.getFechaFin() + "', 'YYYY-MM-DD HH24:mi:SS') "
			+ "WHERE CODIGO_EVENTO = " + a.getCodigoEvento();		
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public int borrarEvento(String id) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = "DELETE FROM EVENTO WHERE CODIGO_EVENTO = " + id;
		int eliminados = stmt.executeUpdate(cadSQL);
		stmt.close();
		
		return eliminados;
	}
	
	public Evento buscarEventoPorID(String id) throws SQLException {
		
		String busSQL = "SELECT * FROM EVENTO WHERE CODIGO_EVENTO = "+ id;
		Statement stmt = conexion.prepareStatement(busSQL);

		Evento a = null;
		ResultSet rset = stmt.executeQuery(busSQL);
		if (rset.next()){
			a = new Evento();
			a.setCodigoEvento(rset.getInt(1));
			a.setTipo(rset.getString(2));
			a.setDescripcion(rset.getString(4));
			a.setFechaInicio(Fechas.dateToString(rset.getDate(4)));
			a.setFechaFin(Fechas.dateToString(rset.getDate(5)));
		}	
		rset.close();
		stmt.close();
		
		return a;
	}	
}
