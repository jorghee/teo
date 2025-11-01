package participante;

public class Ponente extends Participante {
  private String especialidad;
  private String tema;
  private String institucion;

  public Ponente(String id, String nombre, String apellido, String email, String telefono, 
                 String especialidad, String tema, String institucion) {
    super(id, nombre, apellido, email, telefono);
    this.especialidad = especialidad;
    this.tema = tema;
    this.institucion = institucion;
  }

  @Override
  public String obtenerTipo() {
    return "Ponente";
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public String getTema() {
    return tema;
  }

  public String getInstitucion() {
    return institucion;
  }

  @Override
  public void registrar() {
    super.registrar();
    System.out.println("Ponente registrado con tema: " + tema);
  }
}
