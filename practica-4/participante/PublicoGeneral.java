package participante;

public class PublicoGeneral extends Participante {
  private String ocupacion;
  private String intereses;

  public PublicoGeneral(String id, String nombre, String apellido, String email, String telefono, String ocupacion) {
    super(id, nombre, apellido, email, telefono);
    this.ocupacion = ocupacion;
  }

  @Override
  public String obtenerTipo() {
    return "Publico General";
  }
  
  public String getIntereses() {
    return intereses;
  }
}
