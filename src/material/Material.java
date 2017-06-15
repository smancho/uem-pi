package material;

public class Material {

	private int codigoMaterial;
	private String nombreMaterial;
	private String tipoMaterial;
	private String descripcion;
	private int stock;

	public Material() {
	}
	
	public Material(int codigoMaterial, String nombreMaterial,
			String tipoMaterial, String descripcion, int stock) {
		super();
		this.codigoMaterial = codigoMaterial;
		this.nombreMaterial = nombreMaterial;
		this.tipoMaterial = tipoMaterial;
		this.descripcion = descripcion;
		this.stock = stock;
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

	public String getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}

