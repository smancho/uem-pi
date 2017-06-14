package reserva;

public class Reserva {

	private int codigoProyecto;
	private int codigoMaquina;
	private String fechaInicio;
	private String fechaFin;

	public Reserva(int codigoProyecto, int codigoMaquina, String fechaInicio,
			String fechaFin) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.codigoMaquina = codigoMaquina;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public int getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public int getCodigoMaquina() {
		return codigoMaquina;
	}

	public void setCodigoMaquina(int codigoMaquina) {
		this.codigoMaquina = codigoMaquina;
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
