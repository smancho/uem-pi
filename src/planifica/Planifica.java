package planifica;

public class Planifica {

	private int codigoUsuario;
	private int codigoProyecto;

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

}

