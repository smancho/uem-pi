package pedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class OAPedido {
	private Connection conexion;

	public OAPedido(Connection conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Pedido> cargarPedidos() throws SQLException {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		String consulta = 
			"SELECT " +
				"CODIGO_PEDIDO, FECHA, PRECIO_TOTAL, CIF_PROVEEDOR " +
			"FROM " +
				"PEDIDO " + 
			"ORDER BY " +
				"CODIGO_PEDIDO";

		Statement stmt = conexion.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		while (rset.next()) {
			Pedido p = new Pedido();
			p.setCodigoPedido(rset.getInt(1));
			p.setFecha(rset.getString(2));
			p.setPrecioTotal(rset.getFloat(3));
			p.setCifProveedor(rset.getString(4));
			
			pedidos.add(p);

		}
		rset.close();
		stmt.close();
		
		return pedidos;
	}
	
	public void insertarPedido(Pedido p) throws SQLException {
		Statement stmt;
		String cadSQL = null;
		stmt=conexion.createStatement();
		
		cadSQL = "INSERT INTO PEDIDO "
				+ "(FECHA, PRECIO_TOTAL, CIF_PROVEEDOR) "
				+ "VALUES("
				+ "FECHA = TO_DATE('" + p.getFecha() + "', 'YYYY-MM-DD HH24:mi:SS'), "
				+ "(select sum(precio*cantidad)" 
				+ " from pedido p, linea_pedido l"
				+ " where p.codigo_pedido = l.codigo_pedido"
				+ " and p.codigo_pedido = "
				+ p.getCodigoPedido()
				+ "), "
				+ "'" + p.getCifProveedor() + "', "
				
				+ ")";
		stmt.executeUpdate(cadSQL);
		stmt.close();
	}
	
	public Pedido buscarPedidoPorID(String codigoPedido) throws SQLException {
		Statement stmt1;
		String busSQL = null;
		busSQL = "SELECT * FROM PEDIDO WHERE CODIGO_PEDIDO = "+ codigoPedido;
		stmt1 = conexion.prepareStatement(busSQL);

		Pedido pedido = null;
		ResultSet rset = stmt1.executeQuery(busSQL);
		if (rset.next()){
			pedido = new Pedido();
			pedido.setCodigoPedido(rset.getInt(1));
			pedido.setFecha(rset.getString(2));
			pedido.setPrecioTotal(rset.getFloat(3));
			pedido.setCifProveedor(rset.getString(4));
		}	
		rset.close();
		stmt1.close();
		
		return pedido;
	}
	
	public void modificarPedido(Pedido p) throws SQLException {
		Statement stmt;
		stmt=conexion.createStatement();
		String cadSQL = 
			"UPDATE PEDIDO SET "
				+ "FECHA = TO_DATE('" + p.getFecha() + "', 'YYYY-MM-DD HH24:mi:SS'), "
				+ "PRECIO_TOTAL = " + p.getPrecioTotal() + ", "
				+ "CIF_PROVEEDOR = '" + p.getCifProveedor() + "' "
			+ "WHERE CODIGO_PEDIDO = "+ p.getCodigoPedido();
		
		stmt.executeUpdate(cadSQL);
		stmt.close();
	}

	public int borrarPedido(String codigoPedido) throws SQLException {
		Statement stmt;
		String borrar = "DELETE FROM PEDIDO WHERE CODIGO_PEDIDO = " + codigoPedido;
		stmt = conexion.createStatement();
		int eliminados = stmt.executeUpdate(borrar);
		stmt.close();
		
		return eliminados;
	}

}
