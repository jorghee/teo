package gestion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import participante.Participante;
import participante.Estudiante;

public class Inscripcion {
  private String id;
  private Date fechaInscripcion;
  private String evento;
  private String estado;
  private Participante participante;

  // Lista estática para demostrar operaciones de colección
  private static List<Inscripcion> inscripciones = new ArrayList<>();

  public Inscripcion(String id, String evento, Participante participante) {
    this.id = id;
    this.evento = evento;
    this.participante = participante;
    this.fechaInscripcion = new Date();
    this.estado = "Pendiente";
    inscripciones.add(this);
  }

  // ========== MÉTODOS BÁSICOS ==========

  public void confirmar() {
    this.estado = "Confirmada";
    participante.confirmarAsistencia();
    System.out.println("Inscripción confirmada: " + id);
  }

  public void cancelar() {
    this.estado = "Cancelada";
    participante.cancelarRegistro();
    System.out.println("Inscripción cancelada: " + id);
  }

  // ========== COVARIANZA - EJEMPLO 1 ==========
  public static Participante[] obtenerParticipantesArray() {
    System.out.println("[COVARIANZA] Creando array covariante...");
    // Array de Participante puede contener Estudiantes
    Estudiante[] estudiantes = new Estudiante[5];
    Participante[] participantes = estudiantes; // Covarianza
    System.out.println("Array covariante creado");
    return participantes;
  }

  // ========== COVARIANZA - EJEMPLO 2 ==========
  public static List<? extends Inscripcion> obtenerInscripciones() {
    System.out.println("[COVARIANZA] Retornando lista de lectura...");
    return inscripciones;
  }

  // ========== CONTRAVARIANZA - EJEMPLO 1 ==========
  public static void ordenarPorNombre(List<Participante> lista,
      Comparator<? super Participante> comparador) {
    System.out.println("[CONTRAVARIANZA] Ordenando con comparador contravariante...");
    lista.sort(comparador);
    System.out.println("Lista ordenada");
  }

  // ========== CONTRAVARIANZA - EJEMPLO 2 ==========
  public static void exportarInscripcionesA(List<? super Inscripcion> destino) {
    System.out.println("[CONTRAVARIANZA] Exportando inscripciones...");
    destino.addAll(inscripciones);
    System.out.println(inscripciones.size() + " inscripciones exportadas");
  }

  // ========== PROGRAMACIÓN GENÉRICA - EJEMPLO ==========
  public static <T extends Participante> List<Inscripcion> filtrarPorTipoParticipante(Class<T> tipo) {
    System.out.println("[GENÉRICO] Filtrando inscripciones por: " + tipo.getSimpleName());
    List<Inscripcion> resultado = new ArrayList<>();
    for (Inscripcion ins : inscripciones) {
      if (tipo.isInstance(ins.participante)) {
        resultado.add(ins);
      }
    }
    System.out.println("Encontradas: " + resultado.size() + " inscripciones");
    return resultado;
  }

  /**
   * MÉTODO GENÉRICO: Procesa inscripciones con función genérica
   */
  public static <T> List<T> mapearInscripciones(Funcion<Inscripcion, T> funcion) {
    System.out.println("[GENÉRICO] Mapeando inscripciones...");
    List<T> resultado = new ArrayList<>();
    for (Inscripcion ins : inscripciones) {
      resultado.add(funcion.aplicar(ins));
    }
    return resultado;
  }

  // Interfaz funcional genérica
  @FunctionalInterface
  public interface Funcion<T, R> {
    R aplicar(T t);
  }

  // ========== GETTERS ==========

  public String getId() {
    return id;
  }

  public String getEstado() {
    return estado;
  }

  public Date getFechaInscripcion() {
    return fechaInscripcion;
  }

  public String getEvento() {
    return evento;
  }

  public Participante getParticipante() {
    return participante;
  }

  public static int getTotalInscripciones() {
    return inscripciones.size();
  }

  public static void limpiarInscripciones() {
    inscripciones.clear();
  }

  @Override
  public String toString() {
    return "Inscripción[" + id + "] " + participante.getNombreCompleto() +
        " - Estado: " + estado;
  }
}
