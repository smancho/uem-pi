package pedido;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PPedido {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VPedido vpedido = new VPedido();
		CPedido controlador = new CPedido(vpedido, ConexionOracle.conectar());
		vpedido.setControlador(controlador);
		vpedido.setVisible(true);
	}
}
