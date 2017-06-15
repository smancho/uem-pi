package reserva;

public class Reserva {

	private int codigoProyecto;
	private String nombreProyecto;
	private int codigoMaquina;
	private String nombreMaquina;
	private String fechaInicio;
	private String fechaFin;

	public Reserva() {
		super();
	}
	
	public Reserva(int codigoProyecto, int codigoMaquina, String fechaInicio, String fechaFin) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.codigoMaquina = codigoMaquina;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Reserva(int codigoProyecto, String nombreProyecto, int codigoMaquina, String nombreMaquina,
			String fechaInicio, String fechaFin) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.nombreProyecto = nombreProyecto;
		this.codigoMaquina = codigoMaquina;
		this.nombreMaquina = nombreMaquina;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
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
