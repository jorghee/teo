package evento;

import java.sql.Date;
import persona.Persona;

public class Certificado<T extends Persona> {
  private String id;
  private T per;
  private Date fechaEmision;

  public Certificado(T p) {
    this.id = "CER-" + p.getId();
    this.fechaEmision = new Date(System.currentTimeMillis());
    this.per = p;
  };

  public void generar() {
    System.out.println("Fecha de Emisión: " + fechaEmision + "\n" + "- Certificado " + id + " otorgado a "
        + per.getNombreCompleto() + " por su participacion como " + per.obtenerTipo());
  }

  public void enviarPorEmail() {
    System.out.println("Enviando certificado " + id + " a " + per.getEmail());
  }

}
