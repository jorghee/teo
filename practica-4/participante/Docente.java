package participante;

public class Docente extends Participante {
  private String universidad;
  private String especialidad;

  public Docente(String id, String nombre, String apellido, String email, String telefono, String universidad, String especialidad) {
    super(id, nombre, apellido, email, telefono);
    this.universidad = universidad;
    this.especialidad = especialidad;
  }
  
  @Override
  public String obtenerTipo() {
    return "Docente";
  }

  public String getEspecialidad() {
    return especialidad;
  }
}
