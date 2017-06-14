package usuario;

public class Usuario {

	private int usuarioID;
	private String nombre;
	private String apellidos;
	private String mail;
	private String telefono;
	private String tipoUsuario;

	public Usuario(int usuarioID, String nombre, String apellidos, String mail,
			String telefono, String tipoUsuario) {
		super();
		this.usuarioID = usuarioID;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.mail = mail;
		this.telefono = telefono;
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario() {

	}

	public int getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String mostrarUsuario() {
		return ("El usuario nº " + usuarioID + " se llama " + nombre + " "
				+ apellidos + " su correo es " + mail + " su teléfono es "
				+ telefono + " y es un usuario de tipo " + tipoUsuario + ".");
	}

	public Object[] toArray() {
		return new Object[] { usuarioID, nombre, apellidos, mail, telefono,
				tipoUsuario };
	}
}
