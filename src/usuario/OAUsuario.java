package usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class OAUsuario  {
	private Connection conexion;

	public OAUsuario(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Usuario> cargarUsuarios() throws SQLException {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String consulta = 
			"SELECT " +
				"CODIGO_USUARIO, NOMBRE_USUARIO, APELLIDOS, MAIL, TELEFONO, TIPO_USUARIO " +
			"FROM " +
				"USUARIO " + 
			"ORDER BY " +
				"CODIGO_USUARIO";

		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Usuario a = new Usuario();
			a.setUsuarioID(rset.getInt(1));
			a.setNombre(rset.getString(2));
			a.setApellidos(rset.getString(3));
			a.setMail(rset.getString(4));
			a.setTelefono(rset.getString(5));
			a.setTipoUsuario(rset.getString(6));
			usuarios.add(a);

		}
		rset.close();
		stmt.close();
		
		return usuarios;
	}
	
	public void insertarUsuario(Usuario a) throws SQLException {
		Statement stmt;
		String cadSQL = null;
		stmt=conexion.createStatement();
		
		cadSQL = "INSERT INTO USUARIO "
				+ "(NOMBRE_USUARIO, APELLIDOS, MAIL, TELEFONO, TIPO_USUARIO) "
				+ "VALUES("
				+ "'" + a.getNombre() +"', "
				+ "'"+ a.getApellidos() + "', "
				+ "'" + a.getMail() + "', "
				+ "'" + a.getTelefono() +"', "
				+ "'" + a.getTipoUsuario() + "'"
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();
	}
	
	public Usuario buscarUsuarioPorID(String usuarioID) throws SQLException {
		Statement stmt1;
		String busSQL = null;
		busSQL = "SELECT * FROM USUARIO WHERE CODIGO_USUARIO = "+ usuarioID;
		stmt1 = conexion.prepareStatement(busSQL);

		Usuario usuario = null;
		ResultSet rset = stmt1.executeQuery(busSQL);
		if (rset.next()){
			usuario = new Usuario();
			usuario.setUsuarioID(rset.getInt(1));
			usuario.setNombre(rset.getString(2));
			usuario.setApellidos(rset.getString(3));
			usuario.setMail(rset.getString(4));
			usuario.setTelefono(rset.getString(5));
			usuario.setTipoUsuario(rset.getString(6));
		}	
		rset.close();
		stmt1.close();
		
		return usuario;
	}
	
	public void modificarUsuario(Usuario a) throws SQLException {
		Statement stmt;
		stmt=conexion.createStatement();
		String cadSQL = 
			"UPDATE USUARIO SET "
				+ "CODIGO_USUARIO = " + a.getUsuarioID() + ", "
				+ "NOMBRE_USUARIO = '" + a.getNombre() + "', "
				+ "APELLIDOS = '" + a.getApellidos()+ "', "
				+ "MAIL = '" + a.getMail() + "', "
				+ "TELEFONO = '"+a.getTelefono()+"', "
				+ "TIPO_USUARIO = '" + a.getTipoUsuario() + "' "
			+ "WHERE CODIGO_USUARIO = "+a.getUsuarioID();
		
		stmt.executeUpdate(cadSQL);
		stmt.close();
	}

	public int borrarUsuario(String usuarioID) throws SQLException {
		Statement stmt;
		String borrar = "DELETE FROM USUARIO WHERE CODIGO_USUARIO = " + usuarioID;
		stmt = conexion.createStatement();
		int eliminados = stmt.executeUpdate(borrar);
		stmt.close();
		
		return eliminados;
	}


}