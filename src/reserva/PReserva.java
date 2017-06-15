package reserva;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PReserva {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VReserva vreserva = new VReserva();
		CReserva controlador = new CReserva(vreserva, ConexionOracle.conectar());
		vreserva.setControlador(controlador);
		vreserva.setVisible(true);	
	}
}
