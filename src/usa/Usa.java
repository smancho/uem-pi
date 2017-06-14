package usa;

public class Usa {

	private int codigoProyecto;
	private int codigoMaterial;
	private int cantidad;

	public Usa(int codigoProyecto, int codigoMaterial, int cantidad) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.codigoMaterial = codigoMaterial;
		this.cantidad = cantidad;
	}

	public int getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public int getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(int codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
