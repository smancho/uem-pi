package planifica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OAPlanifica {

	private Connection conexion;
	
	public OAPlanifica(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Planifica> cargarPlanificaciones() {
		ArrayList<Planifica> planificaciones = new ArrayList<>();
		try{
			Statement stmt = conexion.createStatement();
			String consulta = 
					"SELECT "
							+ "P.NOMBRE_PROYECTO, "
							+ "U.NOMBRE_USUARIO || ' ' || U.APELLIDOS \"NOMBRE Y APELLIDOS\", "
							+ "P.CODIGO_PROYECTO, "
							+ "U.CODIGO_USUARIO "
					+ "FROM "
						+ "PROYECTO P, USUARIO U, PLANIFICA PL "
					+ "WHERE "
						+ "PL.CODIGO_USUARIO = U.CODIGO_USUARIO "
						+ "AND PL.CODIGO_PROYECTO = P.CODIGO_PROYECTO "
					+ "ORDER BY "
						+ "P.CODIGO_PROYECTO";
			ResultSet rset = stmt.executeQuery(consulta);
			
			while(rset.next()){
				Planifica a = new Planifica();
				a.setNombreProyecto(rset.getString(1));
				a.setNombreUsuario(rset.getString(2));
				a.setCodigoProyecto(rset.getInt(3));
				a.setCodigoUsuario(rset.getInt(4));
				planificaciones .add(a);
			}
			rset.close();
			stmt.close();
		}
		catch(SQLException s) {
			s.printStackTrace();
		}
		
		return planificaciones ;
	}
	
	public void insertarPlanificacion(Planifica a) throws SQLException{
		Statement stmt;
		String cadSQL = null;
		stmt = conexion.createStatement();
		
		cadSQL = "INSERT INTO ASISTE VALUES (" +
				 + a.getCodigoUsuario() +", " 
				 + a.getCodigoProyecto() + 
			     ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();
	}
	
	public ArrayList<Planifica> buscarPlanificacionPorUsuario(String codigoUsuario) {
		Statement stmt1;
		String busSQL = null;
		ArrayList<Planifica> planificaciones = new ArrayList<Planifica>(1);
		try {
			busSQL = "SELECT * FROM ASISTE WHERE CODIGO_USUARIO = "+ codigoUsuario;
			stmt1 = conexion.prepareStatement(busSQL);

			ResultSet rset = stmt1.executeQuery(busSQL);
			while (rset.next()){
				Planifica a = new Planifica();
				a.setCodigoUsuario(rset.getInt(1));
				a.setCodigoProyecto(rset.getInt(2));
				planificaciones.add(a);
			}	
			rset.close();
			stmt1.close();
			planificaciones .trimToSize();
			
			return planificaciones ;
		} catch(SQLException e) {
			System.err.println("error en SQL:" + busSQL);
			e.printStackTrace();
			return null;
		}
	}
	
	public void borrarPlanificacion(String codigoUsuario){
		Statement stmt;
		String cadSQL ="DELETE FROM ASISTE WHERE CODIGO_USUARIO = " + codigoUsuario;
		try{
			stmt = conexion.createStatement();
			stmt.executeUpdate(cadSQL);
			
			stmt.close();
		}
		catch (SQLException e1){
			e1.printStackTrace();
		}
	}
}
