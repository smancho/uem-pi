package proyecto;

public class Proyecto {

	private int codigoProyecto;
	private String nombreProyecto;
	private String descripcion;

	public Proyecto() {
	}
	
	public Proyecto(int codigoProyecto, String nombreProyecto,
			String descripcion) {
		super();
		this.codigoProyecto = codigoProyecto;
		this.nombreProyecto = nombreProyecto;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
