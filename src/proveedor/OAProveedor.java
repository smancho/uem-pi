package proveedor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OAProveedor {

	private String surl= "jdbc:oracle:thin:@localhost:1521:XE";
	private Connection conexion;
	
	public OAProveedor(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion=DriverManager.getConnection(surl, "PROYECTO", "Morpheu$");
			System.out.println("Conexion con Oracle establecida.");
		}
		catch(Exception e){
			System.out.println("Error de Conexion con Oracle.");
			e.printStackTrace();
		}
	}
	
	public void cargarProveedores(ArrayList<Proveedor> proveedores){
		try{
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(
					"SELECT " +
						"CIF, NOMBRE_PROVEEDOR, DIRECCION, CIUDAD, CP, TELEFONO, MAIL " +
					"FROM " +
						"PROVEEDOR " + 
					"ORDER BY " +
						"NOMBRE_PROVEEDOR"
			);
			
			while(rset.next()){
				Proveedor p = new Proveedor();
				p.setCif(rset.getString(1));
				p.setNombre(rset.getString(2));
				p.setDireccion(rset.getString(3));
				p.setCiudad(rset.getString(4));
				p.setCp(rset.getInt(5));
				p.setTelefono(rset.getString(6));
				p.setMail(rset.getString(7));
				proveedores.add(p);
			}
			rset.close();
			stmt.close();
		}
		catch(SQLException s){
			s.printStackTrace();
		}
	}
	public void insertarProveedor(Proveedor p) throws SQLException{
		Statement stmt;
		String cadSQL = null;
		stmt = conexion.createStatement();
		
		cadSQL = "INSERT INTO PROVEEDOR VALUES (" +
				"'" + p.getCif() +"', " +
				"'" + p.getNombre() + "', " +
				"'" + p.getDireccion() + "', " +
				"'" + p.getCiudad() + "', " + 
				p.getCp() + ", " + 
				"'" + p.getTelefono() + "', " + 
				"'" + p.getMail() + "' " +
			")";
		stmt.executeUpdate(cadSQL);
		stmt.close();
	}
	
	public ArrayList<Proveedor> buscarProveedorPorCIF(String cif) {
		Statement stmt1;
		String busSQL = null;
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>(1);
		try {
			busSQL = "SELECT * FROM PROVEEDOR WHERE CIF = '"+ cif +"'";
			stmt1 = conexion.prepareStatement(busSQL);

			ResultSet rset = stmt1.executeQuery(busSQL);
			while (rset.next()){
				Proveedor p = new Proveedor();
				p.setCif(rset.getString(1));
				p.setNombre(rset.getString(2));
				p.setDireccion(rset.getString(3));
				p.setCiudad(rset.getString(4));
				p.setCp(rset.getInt(5));
				p.setTelefono(rset.getString(6));
				p.setMail(rset.getString(7));
				proveedores.add(p);
			}	
			rset.close();
			stmt1.close();
			proveedores.trimToSize();
			
			return proveedores;
		} catch(SQLException e) {
			System.err.println("error en SQL:" + busSQL);
			e.printStackTrace();
			return null;
		}
	}
	public void borrarProveedor(String CIF){
		Statement stmt;
		String cadSQL ="DELETE FROM PROVEEDOR WHERE CIF = '" + CIF + "'";
		try{
			stmt = conexion.createStatement();
			stmt.executeUpdate(cadSQL);
			
			stmt.close();
		}
		catch (SQLException e1){
			e1.printStackTrace();
		}
	}
	public void modificarProveedor(Proveedor p){
		Statement stmt;
		try{
			stmt=conexion.createStatement();
			String cadSQL = 
					"UPDATE PROVEEDOR SET " +
							"CIF = '"+p.getCif()+"', " +
							"NOMBRE_PROVEEDOR = '" + p.getNombre() +"', " +
							"DIRECCION = '" + p.getDireccion()+ "', " +
							"CIUDAD = '" + p.getCiudad()+"', " +
							"CP = " + p.getCp() + ", " +
							"TELEFONO = '" + p.getTelefono() + "', " +
							"MAIL = '" + p.getMail() + "' " +
					"WHERE CIF = '"+p.getCif()+"'";
			
			stmt.executeUpdate(cadSQL);
			stmt.close();
		}
		catch(SQLException e1){
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

