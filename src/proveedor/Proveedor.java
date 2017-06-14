package proveedor;

public class Proveedor {
	private String cif;
	private String nombre;
	private String direccion;
	private String ciudad;
	private int cp;
	private String telefono;
	private String mail;
	
	public Proveedor(){
		
	}

	public Proveedor(String cif, String nombreProveedor, String direccion,
			String ciudad, int cp, String telefono, String mail) {
		super();
		this.cif = cif;
		this.nombre = nombreProveedor;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.cp = cp;
		this.telefono = telefono;
		this.mail = mail;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreProveedor) {
		this.nombre = nombreProveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
