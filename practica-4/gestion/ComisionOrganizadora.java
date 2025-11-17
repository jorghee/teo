package gestion;

import java.util.ArrayList;
import java.util.List;
import persona.Persona;
import participante.Participante;

/**
 * CLASE GENÉRICA: ComisionOrganizadora<T extends Persona>
 * Demuestra el uso de:
 * - Programación genérica con bounded type parameters
 * - Covarianza (? extends)
 * - Contravarianza (? super)
 */
public class ComisionOrganizadora<T extends Persona> {
  private String nombreComision;
  private List<T> miembros;
  private String nombreEvento;
  private java.util.Date fecha;

  public ComisionOrganizadora(String nombreComision) {
    this.nombreComision = nombreComision;
    this.miembros = new ArrayList<>();
    this.nombreEvento = "";
    this.fecha = new java.util.Date();
    System.out.println("=== COMISIÓN ORGANIZADORA CREADA ===");
    System.out.println("Nombre: " + nombreComision);
    System.out.println("=====================================\n");
  }

  public ComisionOrganizadora(String nombreComision, String nombreEvento, java.util.Date fecha) {
    this.nombreComision = nombreComision;
    this.miembros = new ArrayList<>();
    this.nombreEvento = nombreEvento;
    this.fecha = fecha;
    System.out.println("=== COMISIÓN ORGANIZADORA CREADA ===");
    System.out.println("Nombre: " + nombreComision);
    System.out.println("Evento: " + nombreEvento);
    System.out.println("Fecha: " + fecha);
    System.out.println("=====================================\n");
  }

  // ========== MÉTODO BÁSICO - AGREGAR MIEMBRO ==========
  public void agregarMiembro(T persona) {
    miembros.add(persona);
    System.out.println("[AGREGADO] " + persona.obtenerTipo() + ": " + persona.getNombreCompleto());
  }

  // Método para registrar una persona (alias de agregarMiembro)
  public void registrarPersona(T persona) {
    agregarMiembro(persona);
  }

  // ========== COVARIANZA - EJEMPLO 1 ==========
  /**
   * COVARIANZA: Retorna List<? extends Persona>
   * Permite LECTURA pero NO escritura
   * El que llama puede leer objetos como Persona o cualquier subtipo
   */
  public List<? extends Persona> obtenerMiembros() {
    System.out.println("\n[COVARIANZA] Retornando lista de solo lectura...");
    System.out.println("Tipo retornado: List<? extends Persona>");
    System.out.println("Permite: LECTURA de elementos");
    System.out.println("No permite: ESCRITURA de nuevos elementos");
    return miembros;
  }

  // ========== CONTRAVARIANZA - EJEMPLO 1 ==========
  /**
   * CONTRAVARIANZA: Acepta List<? super Participante>
   * Permite ESCRITURA pero lectura limitada
   * Podemos agregar Participantes o subtipos, pero solo leer como Object
   */
  public void registrarParticipantes(List<? super Participante> destino) {
    System.out.println("\n[CONTRAVARIANZA] Registrando participantes...");
    System.out.println("Parámetro: List<? super Participante>");
    System.out.println("Permite: ESCRITURA de Participantes y subtipos");
    
    int count = 0;
    for (T miembro : miembros) {
      if (miembro instanceof Participante) {
        destino.add((Participante) miembro);
        count++;
      }
    }
    System.out.println("Total participantes registrados: " + count);
  }

  // ========== COVARIANZA - EJEMPLO 2 ==========
  /**
   * Método genérico que filtra por tipo específico
   * Demuestra uso de bounded wildcards para lectura
   */
  public <S extends Persona> List<S> obtenerMiembrosPorTipo(Class<S> tipo) {
    System.out.println("\n[GENÉRICO] Filtrando miembros por tipo: " + tipo.getSimpleName());
    List<S> resultado = new ArrayList<>();
    for (T miembro : miembros) {
      if (tipo.isInstance(miembro)) {
        resultado.add(tipo.cast(miembro));
      }
    }
    System.out.println("Encontrados: " + resultado.size() + " " + tipo.getSimpleName() + "(s)");
    return resultado;
  }

  // ========== CONTRAVARIANZA - EJEMPLO 2 ==========
  /**
   * Procesa miembros con una interfaz funcional contravariante
   */
  public void procesarMiembrosCon(Procesador<? super T> procesador) {
    System.out.println("\n[CONTRAVARIANZA] Procesando con función contravariante...");
    for (T miembro : miembros) {
      procesador.procesar(miembro);
    }
  }

  // ========== MÉTODO DE VISUALIZACIÓN ==========
  public void mostrarMiembros() {
    System.out.println("\n========== MIEMBROS DE LA COMISIÓN ==========");
    System.out.println("Comisión: " + nombreComision);
    System.out.println("Total de miembros: " + miembros.size());
    System.out.println("---------------------------------------------");
    
    for (int i = 0; i < miembros.size(); i++) {
      T miembro = miembros.get(i);
      System.out.println((i + 1) + ". " + miembro.obtenerTipo() + " - " + 
                         miembro.getNombreCompleto() + " (" + miembro.getEmail() + ")");
    }
    System.out.println("=============================================\n");
  }

  // ========== MÉTODO GENÉRICO - NOTIFICAR GRUPO ==========
  /**
   * Método genérico que notifica a un grupo específico de personas
   * Demuestra uso de bounded type parameter
   */
  public <S extends Persona> void notificarGrupo(List<S> grupo, String mensaje) {
    System.out.println("\n[NOTIFICACIÓN GENÉRICA] Enviando mensaje a grupo...");
    System.out.println("Mensaje: \"" + mensaje + "\"");
    System.out.println("Destinatarios: " + grupo.size());
    System.out.println("------------------------------------------------------------");
    for (S persona : grupo) {
      System.out.println("  ✉ Enviando a: " + persona.getNombreCompleto() + 
                         " (" + persona.obtenerTipo() + ") - " + persona.getEmail());
    }
    System.out.println("------------------------------------------------------------");
    System.out.println("✓ Notificaciones enviadas exitosamente\n");
  }

  // ========== MÉTODOS DE GESTIÓN ==========
  public void generarCertificados() {
    System.out.println("\n[GENERANDO CERTIFICADOS] Para todos los miembros...");
    for (T miembro : miembros) {
      System.out.println("  ✓ Certificado generado para: " + miembro.getNombreCompleto());
    }
    System.out.println("Total certificados generados: " + miembros.size() + "\n");
  }

  public void obtenerEstadisticas() {
    System.out.println("\n========== ESTADÍSTICAS DEL EVENTO ==========");
    System.out.println("Evento: " + nombreEvento);
    System.out.println("Comisión: " + nombreComision);
    System.out.println("Total de miembros registrados: " + miembros.size());
    
    // Contar por tipo
    int estudiantes = 0, docentes = 0, ponentes = 0, otros = 0;
    for (T miembro : miembros) {
      String tipo = miembro.obtenerTipo();
      if (tipo.contains("Estudiante")) estudiantes++;
      else if (tipo.contains("Docente")) docentes++;
      else if (tipo.contains("Ponente")) ponentes++;
      else otros++;
    }
    
    System.out.println("  - Estudiantes: " + estudiantes);
    System.out.println("  - Docentes: " + docentes);
    System.out.println("  - Ponentes: " + ponentes);
    System.out.println("  - Otros: " + otros);
    System.out.println("=============================================\n");
  }

  // ========== GETTERS ==========
  public String getNombreComision() {
    return nombreComision;
  }

  public String getNombreEvento() {
    return nombreEvento;
  }

  public java.util.Date getFecha() {
    return fecha;
  }

  public int getTotalMiembros() {
    return miembros.size();
  }

  // ========== INTERFAZ FUNCIONAL PARA CONTRAVARIANZA ==========
  @FunctionalInterface
  public interface Procesador<T> {
    void procesar(T elemento);
  }
}
