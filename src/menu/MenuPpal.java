package menu;

public class MenuPpal {
	public MenuPpal() {}

	public static void main(String[] args) {
		VMenu ventanaMenuPrincipal = new VMenu();		

		MenuControl controlador = new MenuControl(ventanaMenuPrincipal);
		ventanaMenuPrincipal.setControlador(controlador);
		
		ventanaMenuPrincipal.setVisible(true);
		ventanaMenuPrincipal.centrarEnPantalla();
		ventanaMenuPrincipal.repaint();
	}

}
