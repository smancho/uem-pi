package pedido;

public class Pedido {

	private int codigoPedido;
	private String cifProveedor;
	private float precioTotal;
	private String fecha;

	public Pedido(){
		
	}
	
	public Pedido(int codigoPedido, String cifProveedor, float precioTotal,
			String fecha) {
		super();
		this.codigoPedido = codigoPedido;
		this.cifProveedor = cifProveedor;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
	}

	public int getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getCifProveedor() {
		return cifProveedor;
	}

	public void setCifProveedor(String cifProveedor) {
		this.cifProveedor = cifProveedor;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
