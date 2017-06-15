package material;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OAMaterial {
	
	private Connection conexion;
	
	public OAMaterial(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<Material> cargarMateriales() throws SQLException {
		ArrayList<Material> materiales = new ArrayList<Material>();
		String consulta = 
			"SELECT " 
			    + "CODIGO_MATERIAL, "
			    + "NOMBRE_MATERIAL, "
			    + "TIPO_MATERIAL, "
			    + "DESCRIPCION, "
			    + "STOCK "
			+ "FROM "
			    + "MATERIAL "
			+ "ORDER BY "
			    + "CODIGO_MATERIAL";
		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Material a = new Material();
			a.setCodigoMaterial(rset.getInt(1));
			a.setNombreMaterial(rset.getString(2));
			a.setTipoMaterial(rset.getString(3));
			a.setDescripcion(rset.getString(4));
			a.setStock(rset.getInt(5));
			materiales.add(a);

		}
		rset.close();
		stmt.close();
		
		return materiales;
	}
	
	public void insertarMaterial(Material a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
				"INSERT INTO "
						+ "MATERIAL "
				+ "VALUES("
						+ a.getCodigoMaterial() + ", "
						+ "'" + a.getNombreMaterial() + "', "
						+ "'" + a.getDescripcion() + "', "
						+ "'" + a.getTipoMaterial() + "', "
						+ a.getStock()
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public void modificarMaterial(Material a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
			"UPDATE MATERIAL SET "
				+ "CODIGO_MATERIAL = " + a.getCodigoMaterial()+ ", "
				+ "NOMBRE_MATERIAL ='" + a.getNombreMaterial() + "', "
				+ "TIPO_MATERIAL ='" + a.getTipoMaterial() + "', "
				+ "DESCRIPCION = '" + a.getDescripcion() + "', "
				+ "STOCK= " + a.getStock() + " "
			+ "WHERE CODIGO_MATERIAL = " + a.getCodigoMaterial();		
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public int borrarMaterial(String id) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = "DELETE FROM MATERIAL WHERE CODIGO_MATERIAL = " + id;
		int eliminados = stmt.executeUpdate(cadSQL);
		stmt.close();
		
		return eliminados;
	}
	
	public Material buscarMaterialPorID(String id) throws SQLException {
		
		String busSQL = "SELECT * FROM MATERIAL WHERE CODIGO_MATERIAL = "+ id;
		Statement stmt = conexion.prepareStatement(busSQL);

		Material a = null;
		ResultSet rset = stmt.executeQuery(busSQL);
		if (rset.next()){
			a = new Material();
			a.setCodigoMaterial(rset.getInt(1));
			a.setNombreMaterial(rset.getString(2));
			a.setTipoMaterial(rset.getString(3));
			a.setDescripcion(rset.getString(4));
			a.setStock(rset.getInt(5));
		}	
		rset.close();
		stmt.close();
		
		return a;
	}	
}
