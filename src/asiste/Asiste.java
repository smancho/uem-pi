package asiste;

public class Asiste {

	private int codigoUsuario;
	private int codigoEvento;
	private String nombreApellidos;
	private String evento;

	public Asiste(){
	}

	public Asiste(int codigoUsuario, int codigoEvento, String nombreApellidos,
			String evento) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.codigoEvento = codigoEvento;
		this.nombreApellidos = nombreApellidos;
		this.evento = evento;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public int getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(int codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getNombreApellidos() {
		return nombreApellidos;
	}

	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

}

