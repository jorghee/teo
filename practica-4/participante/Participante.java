package participante;

import java.util.Date;
import persona.Persona;

public abstract class Participante extends Persona {
  protected Date fechaRegistro;
  protected String estado;

  public Participante(String id, String nombre, String apellido, String email, String telefono) {
    super(id, nombre, apellido, email, telefono);
    this.estado = "Pendiente";
  }

  public void confirmarAsistencia() {
    this.estado = "Confirmado";
    System.out.println("Asistencia confirmada para: " + getNombreCompleto());
  }

  public void cancelarRegistro() {
    this.estado = "Cancelado";
    System.out.println("Registro cancelado para: " + getNombreCompleto());
  }
  
  public String getEstado() {
    return estado;
  }
  
  @Override
  public void registrar() {
    this.fechaRegistro = new Date();
    System.out.println("Participante " + getNombreCompleto() + " registrado.");
  }
}


