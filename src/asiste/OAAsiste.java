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
	
	public ArrayList<Asiste> cargarAsistencias() {
		ArrayList<Asiste> asistencias = new ArrayList<>();
		try{
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(
					"SELECT "
							+ "E.DESCRIPCION, "
							+ "U.NOMBRE_USUARIO || ' ' || U.APELLIDOS \"NOMBRE Y APELLIDOS\", "
							+ "E.CODIGO_EVENTO, "
							+ "U.CODIGO_USUARIO "
					+ "FROM "
						+ "ASISTE A, USUARIO U, EVENTO E "
					+ "WHERE "
						+ "A.CODIGO_USUARIO = U.CODIGO_USUARIO "
						+ "AND A.CODIGO_EVENTO = E.CODIGO_EVENTO "
					+ "ORDER BY "
						+ "A.CODIGO_EVENTO"
			);
			
			while(rset.next()){
				Asiste a = new Asiste();
				a.setEvento(rset.getString(1));
				a.setNombreApellidos(rset.getString(2));
				a.setCodigoEvento(rset.getInt(3));
				a.setCodigoUsuario(rset.getInt(4));
				asistencias.add(a);
			}
			rset.close();
			stmt.close();
		}
		catch(SQLException s){
			s.printStackTrace();
		}
		return asistencias;
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
}

