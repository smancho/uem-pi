package proveedor;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PProveedor {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		VProveedor vproveedor = new VProveedor();
		CProveedor controlador = new CProveedor(vproveedor, ConexionOracle.conectar());
		vproveedor.setControlador(controlador);
		vproveedor.setVisible(true);
	}
}
