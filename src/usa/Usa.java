package usa;

public class Usa {

	private int codigoProyecto;
	private String nombreProyecto;
	private int codigoMaterial;
	private String nombreMaterial;
	private int cantidad;
	
	public Usa() {
	}
	
	public Usa(int codigoProyecto, int codigoMaterial, int cantidad) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.codigoMaterial = codigoMaterial;
		this.cantidad = cantidad;
	}

	public Usa(int codigoProyecto, String nombreProyecto, int codigoMaterial, String nombreMaterial, int cantidad) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.nombreProyecto = nombreProyecto;
		this.codigoMaterial = codigoMaterial;
		this.nombreMaterial = nombreMaterial;
		this.cantidad = cantidad;
	}

	public int getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public int getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(int codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public String getNombreMaterial() {
		return nombreMaterial;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
