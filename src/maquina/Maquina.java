package maquina;

public class Maquina {

	private int codigoMaquina;
	private String nombreMaquina;
	private String descripcion;
	private boolean disponibilidad;

	public Maquina() {
	}
	
	public Maquina(int codigoMaquina, String nombreMaquina, String descripcion,
			boolean disponibilidad) {
		super();
		this.codigoMaquina = codigoMaquina;
		this.nombreMaquina = nombreMaquina;
		this.descripcion = descripcion;
		this.disponibilidad = disponibilidad;
	}

	public int getCodigoMaquina() {
		return codigoMaquina;
	}

	public void setCodigoMaquina(int codigoMaquina) {
		this.codigoMaquina = codigoMaquina;
	}

	public String getNombreMaquina() {
		return nombreMaquina;
	}

	public void setNombreMaquina(String nombreMaquina) {
		this.nombreMaquina = nombreMaquina;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String disponibilidad() {
		String disponible = "NO DISPONIBLE";
		if (this.isDisponibilidad()) {
			disponible = "DISPONIBLE";
		}
		return disponible;
	}
}
