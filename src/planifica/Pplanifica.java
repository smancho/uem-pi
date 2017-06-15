package planifica;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class Pplanifica {
	
public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VPlanifica vplan = new VPlanifica();
		CPlanifica controlador = new CPlanifica(vplan, ConexionOracle.conectar());
		vplan.setControlador(controlador);
		vplan.setVisible(true);	
	}

}
