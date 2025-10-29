package participante;

public class Estudiante extends Participante {
  private String universidad;
  private String carrera;

  public Estudiante(String id, String nombre, String apellido, String email, String telefono, String universidad, String carrera) {
    super(id, nombre, apellido, email, telefono);
    this.universidad = universidad;
    this.carrera = carrera;
  }

  @Override
  public String obtenerTipo() {
    return "Estudiante";
  }
  
  public String getUniversidad() {
    return universidad;
  }
}
