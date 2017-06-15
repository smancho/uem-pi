package lineaPedido;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PLineaPedido {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VLineaPedido vlinea = new VLineaPedido();
		CLineaPedido controlador = new CLineaPedido(vlinea, ConexionOracle.conectar());
		vlinea.setControlador(controlador);
		vlinea.setVisible(true);
	}
	

}
