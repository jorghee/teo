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

  public ComisionOrganizadora(String nombreComision) {
    this.nombreComision = nombreComision;
    this.miembros = new ArrayList<>();
    System.out.println("=== COMISIÓN ORGANIZADORA CREADA ===");
    System.out.println("Nombre: " + nombreComision);
    System.out.println("=====================================\n");
  }

  // ========== MÉTODO BÁSICO - AGREGAR MIEMBRO ==========
  public void agregarMiembro(T persona) {
    miembros.add(persona);
    System.out.println("[AGREGADO] " + persona.obtenerTipo() + ": " + persona.getNombreCompleto());
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

  // ========== GETTERS ==========
  public String getNombreComision() {
    return nombreComision;
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
