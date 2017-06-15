package lineaPedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OALineaPedido{
	
	private Connection conexion;
	
	public OALineaPedido(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ArrayList<LineaPedido> cargarLineas() throws SQLException {
		ArrayList<LineaPedido> lineas = new ArrayList<LineaPedido>();
		String consulta = 
			"SELECT " 
			    + "LP.CODIGO_PEDIDO, "
			    + "LP.CODIGO_MATERIAL, "
			    + "M.NOMBRE_MATERIAL, "
			    + "LP.CANTIDAD, "
			    + "LP.PRECIO "
			+ "FROM "
			    + "LINEA_PEDIDO LP, "
			    + "MATERIAL M "
			+ "WHERE "
			    + "LP.CODIGO_MATERIAL = M.CODIGO_MATERIAL "
			+ "ORDER BY "
			    + "LP.CODIGO_PEDIDO";
		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			LineaPedido a = new LineaPedido();
			a.setCodigoPedido(rset.getInt(1));
			a.setCodigoMaterial(rset.getInt(2));
			a.setNombreMaterial(rset.getString(3));
			a.setCantidad(rset.getInt(4));
			a.setPrecio(rset.getFloat(5));
			lineas.add(a);

		}
		rset.close();
		stmt.close();
		
		return lineas;
	}
	
	public void insertarLinea(LineaPedido a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
				"INSERT INTO "
						+ "LINEA_PEDIDO "
				+ "VALUES("
						+ a.getCodigoPedido() + ", "
						+ a.getCodigoMaterial() + ", "
						+ a.getCantidad() + ", "
						+ a.getPrecio()
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public void modificarLinea(LineaPedido a) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = 
			"UPDATE LINEA_PEDIDO SET "
				+ "CODIGO_MATERIAL = " + a.getCodigoMaterial()+ ", "
				+ "CANTIDAD = " + a.getCantidad() + ", "
				+ "PRECIO = " + a.getPrecio() + " "
			+ "WHERE CODIGO_PEDIDO = " + a.getCodigoPedido();		
		stmt.executeUpdate(cadSQL);
		stmt.close();		
	}
	
	public int borrarLinea(String id) throws SQLException {
		Statement stmt = conexion.createStatement();
		String cadSQL = "DELETE FROM LINEA_PEDIDO WHERE CODIGO_PEDIDO = " + id;
		int eliminados = stmt.executeUpdate(cadSQL);
		stmt.close();
		
		return eliminados;
	}
	
	public LineaPedido buscarLineaPorID(String id) throws SQLException {
		
		String busSQL = "SELECT * FROM LINEA_PEDIDO WHERE CODIGO_PEDIDO = "+ id;
		Statement stmt = conexion.prepareStatement(busSQL);

		LineaPedido a = null;
		ResultSet rset = stmt.executeQuery(busSQL);
		if (rset.next()){
			a = new LineaPedido();
			a.setCodigoPedido(rset.getInt(1));
			a.setCodigoMaterial(rset.getInt(2));
			a.setCantidad(rset.getInt(3));
			a.setPrecio(rset.getInt(4));
		}	
		rset.close();
		stmt.close();
		
		return a;
	}	
}
