package planifica;

public class Planifica {

	private int codigoUsuario;
	private int codigoProyecto;
	private String nombreProyecto;
	private String nombreUsuario;

	public Planifica() {}
	
	public Planifica(int CodigoUsuario, int CodigoProyecto) {
		this.codigoProyecto = CodigoProyecto;
		this.codigoUsuario = CodigoUsuario;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}

