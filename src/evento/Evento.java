package evento;

public class Evento {
	private int codigoEvento;
	private String tipo;
	private String descripcion;
	private String fechaInicio;
	private String fechaFin;

	public Evento() {}
	
	public Evento(int codigoEvento, String tipo, String descripcion,
			String fechaInicio, String fechaFin) {
		this.codigoEvento = codigoEvento;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public int getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(int codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
