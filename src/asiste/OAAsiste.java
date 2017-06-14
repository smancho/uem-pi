package asiste;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OAAsiste {

	private Connection conexion;
	
	public OAAsiste(Connection conexion) {
		this.conexion = conexion;
	}
	
	public void cargarAsistencias(ArrayList<Asiste> asistencias){
		try{
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(
					"SELECT U.CODIGO_USUARIO, U.NOMBRE_USUARIO || ' ' || "
					+ "U.APELLIDOS \"NOMBRE Y APELLIDOS\" , E.CODIGO_EVENTO, E.DESCRIPCION "
					+ "FROM ASISTE A, USUARIO U, EVENTO E "
					+ "WHERE A.CODIGO_USUARIO = U.CODIGO_USUARIO "
					+ "AND A.CODIGO_EVENTO = E.CODIGO_EVENTO "
					+ "ORDER BY A.CODIGO_USUARIO"
			);
			
			while(rset.next()){
				Asiste a = new Asiste();
				a.setCodigoUsuario(rset.getInt(1));
				a.setNombreApellidos(rset.getString(2));
				a.setCodigoEvento(rset.getInt(3));
				a.setEvento(rset.getString(4));
				
				asistencias.add(a);
			}
			rset.close();
			stmt.close();
		}
		catch(SQLException s){
			s.printStackTrace();
		}
	}
	public void insertarAsistencia(Asiste a) throws SQLException{
		Statement stmt;
		String cadSQL = null;
		stmt = conexion.createStatement();
		
		cadSQL = "INSERT INTO ASISTE VALUES (" +
				 + a.getCodigoUsuario() +", " 
				 + a.getCodigoEvento() + 
			     ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();
	}
	
	public ArrayList<Asiste> buscarAsistenciaPorUsuario(String codigoUsuario) {
		Statement stmt1;
		String busSQL = null;
		ArrayList<Asiste> asistencias = new ArrayList<Asiste>(1);
		try {
			busSQL = "SELECT * FROM ASISTE WHERE CODIGO_USUARIO = "+ codigoUsuario;
			stmt1 = conexion.prepareStatement(busSQL);

			ResultSet rset = stmt1.executeQuery(busSQL);
			while (rset.next()){
				Asiste a = new Asiste();
				a.setCodigoUsuario(rset.getInt(1));
				a.setCodigoEvento(rset.getInt(2));
				asistencias.add(a);
			}	
			rset.close();
			stmt1.close();
			asistencias.trimToSize();
			
			return asistencias;
		} catch(SQLException e) {
			System.err.println("error en SQL:" + busSQL);
			e.printStackTrace();
			return null;
		}
	}
	public void borrarAsistencia(String codigoUsuario){
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
	
	public void cerrarBBDD(){
		try{
			conexion.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}

