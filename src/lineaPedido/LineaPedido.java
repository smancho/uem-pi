package lineaPedido;

public class LineaPedido {

	private int codigoPedido;
	private int codigoMaterial;
	private String nombreMaterial;
	private int cantidad;
	private float precio;

	public LineaPedido() {
	}

	public LineaPedido(int codigoPedido, int codigoMaterial, int cantidad, float precio) {
		super();
		this.codigoPedido = codigoPedido;
		this.codigoMaterial = codigoMaterial;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public LineaPedido(int codigoPedido, int codigoMaterial, String nombreMaterial, int cantidad, float precio) {
		super();
		this.codigoPedido = codigoPedido;
		this.codigoMaterial = codigoMaterial;
		this.nombreMaterial = nombreMaterial;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public int getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
