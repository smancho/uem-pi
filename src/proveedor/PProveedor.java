package proveedor;

public class PProveedor {

	public static void main(String[] args) {
		
		OAProveedor bbdd = new OAProveedor();
		VProveedor vproveedor = new VProveedor();
		CProveedor controlador = new CProveedor(vproveedor, bbdd);
		vproveedor.setControlador(controlador);
		vproveedor.setVisible(true);
	}
}
