package evento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import participante.Participante;
import participante.Estudiante;
import participante.Docente;

public class Evento {
  private String nombre;
  private Date fecha;
  private String lugar;
  private List<Participante> participantes;

  public Evento(String nombre, Date fecha, String lugar) {
    this.nombre = nombre;
    this.fecha = fecha;
    this.lugar = lugar;
    this.participantes = new ArrayList<>();
  }

  // ========== MÉTODOS BÁSICOS ==========

  public void registrarAsistente(Participante participante) {
    participantes.add(participante);
    System.out.println(participante.getNombreCompleto() +
        " registrado en " + nombre);
  }

  public void iniciarEvento() {
    System.out.println("Iniciando evento: " + nombre);
    System.out.println("Fecha: " + fecha);
    System.out.println("Lugar: " + lugar);
    System.out.println("Participantes: " + participantes.size());
  }

  // ========== COVARIANZA - EJEMPLO 1 ==========
  // Retorna lista covariante (? extends) - SOLO LECTURA
  public List<? extends Participante> obtenerParticipantes() {
    System.out.println("[COVARIANZA] Retornando lista de lectura");
    return participantes;
  }

  // ========== COVARIANZA - EJEMPLO 2 ==========
  // Retorna tipo específico (más específico que Participante)
  public Estudiante obtenerPrimerEstudiante() {
    System.out.println("[COVARIANZA] Buscando primer estudiante...");
    for (Participante p : participantes) {
      if (p instanceof Estudiante) {
        return (Estudiante) p;
      }
    }
    return null;
  }

  // ========== COVARIANZA - EJEMPLO 3 ==========
  // Arrays covariantes
  public Participante[] obtenerParticipantesArray() {
    System.out.println("[COVARIANZA] Convirtiendo a array...");
    // Array de Participante puede contener Estudiante, Docente, etc.
    return participantes.toArray(new Participante[0]);
  }

  // ========== CONTRAVARIANZA - EJEMPLO 1 ==========
  // Acepta lista contravariante (? super) - SOLO ESCRITURA
  public void exportarParticipantesA(List<? super Participante> destino) {
    System.out.println("[CONTRAVARIANZA] Exportando a lista contravariante...");
    for (Participante p : participantes) {
      destino.add(p); // Seguro: siempre puedo agregar Participante
    }
    System.out.println(participantes.size() + " participantes exportados");
  }

  // ========== CONTRAVARIANZA - EJEMPLO 2 ==========
  // Procesa con función contravariante
  public void procesarParticipantesCon(Consumer<? super Participante> procesador) {
    System.out.println("[CONTRAVARIANZA] Procesando con función contravariante...");
    participantes.forEach(procesador);
  }

  // ========== PROGRAMACIÓN GENÉRICA - EJEMPLO ==========
  /**
   * MÉTODO GENÉRICO: Filtra participantes por tipo específico
   * Demuestra uso de genéricos con bounded type
   */
  public <T extends Participante> List<T> obtenerParticipantesPorTipo(Class<T> tipo) {
    System.out.println("[GENÉRICO] Filtrando por tipo: " + tipo.getSimpleName());
    List<T> resultado = new ArrayList<>();
    for (Participante p : participantes) {
      if (tipo.isInstance(p)) {
        resultado.add(tipo.cast(p));
      }
    }
    System.out.println("Encontrados: " + resultado.size());
    return resultado;
  }

  // ========== GETTERS ==========

  public String getNombre() {
    return nombre;
  }

  public Date getFecha() {
    return fecha;
  }

  public String getLugar() {
    return lugar;
  }

  public int getTotalParticipantes() {
    return participantes.size();
  }

  @Override
  public String toString() {
    return "Evento: " + nombre + " | Fecha: " + fecha +
        " | Participantes: " + participantes.size();
  }
}
