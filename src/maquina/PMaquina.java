package maquina;

import java.sql.SQLException;

import bdd.ConexionOracle;

public class PMaquina {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VMaquina vmaquina = new VMaquina();
		CMaquina controlador = new CMaquina(vmaquina, ConexionOracle.conectar());
		vmaquina.setControlador(controlador);
		vmaquina.setVisible(true);	
	}
}
