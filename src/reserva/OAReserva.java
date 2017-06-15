package reserva;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.Fechas;

public class OAReserva {
	
	private Connection conexion;
	
	public OAReserva(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Reserva> cargarReservas() throws SQLException {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		String consulta = 
			"SELECT "
			    + "R.CODIGO_PROYECTO, "
			    + "P.NOMBRE_PROYECTO, "
			    + "R.CODIGO_MAQUINA, "
			    + "M.NOMBRE_MAQUINA, "
			    + "R.FECHA_INICIO, "
			    + "R.FECHA_FIN "
			+ "FROM "
			    + "RESERVA R, "
			    + "PROYECTO P, "
			    + "MAQUINA M "
			+ "WHERE "
			    + "R.CODIGO_PROYECTO = P.CODIGO_PROYECTO "
			    + "AND R.CODIGO_MAQUINA = M.CODIGO_MAQUINA "
			+ "ORDER BY "
			    + "R.CODIGO_PROYECTO";
		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Reserva a = new Reserva();
			a.setCodigoProyecto(rset.getInt(1));
			a.setNombreProyecto(rset.getString(2));
			a.setCodigoMaquina(rset.getInt(3));
			a.setNombreMaquina(rset.getString(4));
			a.setFechaInicio(Fechas.dateToString(rset.getDate(5)));
			a.setFechaFin(Fechas.dateToString(rset.getDate(6)));
			reservas.add(a);
		}
		rset.close();
		stmt.close();
		
		return reservas;
	}
	
	public void insertarReserva(Reserva a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
				"INSERT INTO "
						+ "RESERVA "
				+ "VALUES("
						+ a.getCodigoProyecto()+ ", "
						+ a.getCodigoMaquina() + ", "
						+ "TO_DATE('" 
							+ Fechas.toOracleFormat(Fechas.stringToDate(a.getFechaInicio())) 
							+ "', 'YYYY-MM-DD HH24:mi:SS'), "
						+ "TO_DATE('" 
							+ Fechas.toOracleFormat(Fechas.stringToDate(a.getFechaFin())) 
							+ "', 'YYYY-MM-DD HH24:mi:SS') "
				+ ")";
		System.out.println(cadSQL);
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public void modificarReserva(Reserva a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
			"UPDATE RESERVA SET "
				+ "CODIGO_MAQUINA = " + a.getCodigoMaquina() + ", "
				+ "FECHA_INICIO = " 
					+ "TO_DATE('" 
						+ Fechas.toOracleFormat(Fechas.stringToDate(a.getFechaInicio())) 
						+ "', 'YYYY-MM-DD HH24:mi:SS'), "
				+ "FECHA_FIN = "
					+ "TO_DATE('" 
						+ Fechas.toOracleFormat(Fechas.stringToDate(a.getFechaInicio())) 
						+ "', 'YYYY-MM-DD HH24:mi:SS') "						
			+ "WHERE CODIGO_PROYECTO = " + a.getCodigoProyecto();		
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public int borrarReserva(String id) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = "DELETE FROM RESERVA WHERE CODIGO_PROYECTO= " + id;
		int eliminados = stmt.executeUpdate(cadSQL);
		stmt.close();
		
		return eliminados;
	}
	
	public Reserva buscarReservaPorID(String id) throws SQLException {
		
		String busSQL = "SELECT * FROM RESERVA WHERE CODIGO_PROYECTO = "+ id;
		Statement stmt = conexion.prepareStatement(busSQL);

		Reserva a = null;
		ResultSet rset = stmt.executeQuery(busSQL);
		if (rset.next()) {
			a = new Reserva();
			a.setCodigoProyecto(rset.getInt(1));
			a.setCodigoMaquina(rset.getInt(2));
			a.setFechaInicio(Fechas.dateToString(rset.getDate(3)));
			a.setFechaFin(Fechas.dateToString(rset.getDate(4)));
		}	
		rset.close();
		stmt.close();
		
		return a;
	}	
	
}
