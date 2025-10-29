package persona;

public abstract class Persona {
	protected String id;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected String telefono;

	public Persona(String id, String nombre, String apellido, String email, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	// comportamiento por defecto al registrar una persona; las subclases pueden sobreescribirlo
	public void registrar() {
		System.out.println("Persona " + getNombreCompleto() + " registrada.");
	}

	// cada subclase debe indicar su tipo (Estudiante, Docente, Participante, Ponente, etc.)
	public abstract String obtenerTipo();

	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}
}
