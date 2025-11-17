import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import evento.Certificado;
import gestion.ComisionOrganizadora;
import evento.Evento;
import evento.Inscripcion;
import participante.Docente;
import participante.Estudiante;
import participante.Participante;
import participante.PublicoGeneral;
import persona.Persona;
import ponente.Ponente;
import ponente.PonenteNacional;
import ponente.PonenteInternacional;

/**
 * CLASE PRINCIPAL - SEMINARIO INTERNACIONAL DE INGENIERÍA DE SOFTWARE
 * 
 * Demuestra:
 * 1. Programación Genérica con ComisionOrganizadora<T extends Persona>
 * 2. Covarianza (? extends) - para lectura
 * 3. Contravarianza (? super) - para escritura
 * 4. Uso completo de las 13 clases del proyecto
 */
public class Main {
  
  public static void main(String[] args) {
    imprimirEncabezado();
    
    // Paso 1: Crear comisión
    ComisionOrganizadora<Persona> comision = crearComision();
    
    // Paso 2: Crear actores
    Persona[] actores = crearActores();
    
    // Paso 3: Registrar actores
    registrarActores(comision, actores);
    
    // Paso 4: Demostrar programación genérica
    demostrarProgramacionGenerica(comision, actores);
    
    // Paso 5: Demostrar covarianza y contravarianza
    demostrarVarianza(comision);
    
    // Paso 6: Crear evento y gestionar inscripciones
    Evento seminario = crearEventoYRegistrar(actores);
    
    // Paso 7: Generar certificados
    generarCertificados(actores);
    
    // Resumen final
    imprimirResumen(comision, seminario);
  }
  
  // ==================== MÉTODOS AUXILIARES ====================
  
  private static void imprimirEncabezado() {
    System.out.println("╔═══════════════════════════════════════════════════════════════╗");
    System.out.println("║   SEMINARIO INTERNACIONAL DE INGENIERÍA DE SOFTWARE 2024     ║");
    System.out.println("║   Demostración de Programación Genérica y Varianza           ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");
  }
  
  private static ComisionOrganizadora<Persona> crearComision() {
    System.out.println(">>> PASO 1: Creando la Comisión Organizadora\n");
    return new ComisionOrganizadora<>(
        "Comisión Organizadora del Seminario Internacional 2024",
        "III Seminario Internacional de Ingeniería de Software",
        new Date()
    );
  }
  
  private static Persona[] crearActores() {
    System.out.println("\n>>> PASO 2: Creando actores del seminario\n");
    
    Persona[] actores = new Persona[8];
    
    actores[0] = new Ponente("P001", "Dr. Carlos", "Mendoza", 
        "carlos.mendoza@mit.edu", "555-1001",
        "Arquitectura de Software", "Experto en Microservicios");
    
    actores[1] = new PonenteNacional("PN001", "Dr. Jorge", "Valdez", 
        "jorge.valdez@unsa.edu.pe", "555-1003",
        "Desarrollo de Software", "Especialista en metodologías ágiles",
        "Arequipa", "UNSA");
    
    actores[2] = new PonenteInternacional("PI001", "Dr. Robert", "Smith", 
        "robert.smith@stanford.edu", "555-1004",
        "Inteligencia Artificial", "Investigador en Deep Learning",
        "Estados Unidos", true);

    actores[3] = new Docente("D001", "Dr. Luis", "Paredes", 
        "luis.paredes@unsa.edu.pe", "555-2001", "UNSA", "Ingeniería de Software");

    actores[4] = new Docente("D002", "Mg. Maria", "Torres", 
        "maria.torres@unsa.edu.pe", "555-2002", "UNSA", "Bases de Datos");

    actores[5] = new Estudiante("E001", "Juan", "Pérez", 
        "juan.perez@unsa.edu.pe", "555-3001", "UNSA", "Ingeniería de Sistemas");

    actores[6] = new Estudiante("E002", "Sofia", "Ramirez", 
        "sofia.ramirez@unsa.edu.pe", "555-3002", "UNSA", "Ciencias de la Computación");

    actores[7] = new PublicoGeneral("PG001", "Roberto", "Gomez", 
        "roberto.gomez@gmail.com", "555-4001", "Desarrollador de Software");

    System.out.println("✓ Creados: 3 Ponentes, 2 Docentes, 2 Estudiantes, 1 Público General");
    return actores;
  }
  
  private static void registrarActores(ComisionOrganizadora<Persona> comision, Persona[] actores) {
    System.out.println("\n>>> PASO 3: Registrando actores en la Comisión\n");
    for (Persona actor : actores) {
      comision.registrarPersona(actor);
    }
  }
  
  private static void demostrarProgramacionGenerica(ComisionOrganizadora<Persona> comision, Persona[] actores) {
    System.out.println("\n>>> PASO 4: Programación Genérica - Método notificarGrupo<T>\n");
    
    List<Estudiante> estudiantes = new ArrayList<>();
    List<Ponente> ponentes = new ArrayList<>();
    
    for (Persona p : actores) {
      if (p instanceof Estudiante) estudiantes.add((Estudiante) p);
      if (p instanceof Ponente) ponentes.add((Ponente) p);
    }
    
    comision.notificarGrupo(estudiantes, "Inscripción confirmada. ¡Bienvenidos!");
    comision.notificarGrupo(ponentes, "Confirme su horario de exposición.");
  }
  
  private static void demostrarVarianza(ComisionOrganizadora<Persona> comision) {
    System.out.println("\n>>> PASO 5: Covarianza y Contravarianza\n");
    
    // COVARIANZA (? extends) - Solo lectura
    System.out.println("[COVARIANZA] Lista de solo lectura:");
    List<? extends Persona> miembros = comision.obtenerMiembros();
    System.out.println("  Total miembros: " + miembros.size());
    
    // CONTRAVARIANZA (? super) - Solo escritura
    System.out.println("\n[CONTRAVARIANZA] Exportando participantes:");
    List<Persona> destino = new ArrayList<>();
    comision.registrarParticipantes(destino);
    System.out.println("  Participantes exportados: " + destino.size());
    
    // Mostrar estadísticas
    comision.obtenerEstadisticas();
  }
  
  private static Evento crearEventoYRegistrar(Persona[] actores) {
    System.out.println("\n>>> PASO 6: Evento e Inscripciones\n");
    
    Evento seminario = new Evento(
        "III Seminario Internacional de Ingeniería de Software",
        new Date(),
        "Auditorio Principal - UNSA"
    );

    // Registrar participantes
    for (Persona p : actores) {
      if (p instanceof Participante) {
        seminario.registrarAsistente((Participante) p);
      }
    }
    
    // Registrar ponentes
    for (Persona p : actores) {
      if (p instanceof Ponente) {
        seminario.registrarAsistente((Ponente) p);
      }
    }

    System.out.println();
    seminario.iniciarEvento();
    
    // Crear inscripciones
    System.out.println("\nCreando inscripciones:");
    int count = 0;
    for (Persona p : actores) {
      if (p instanceof Participante && count < 5) {
        new Inscripcion("INS00" + (++count), seminario.getNombre(), (Participante) p);
      }
    }
    System.out.println("✓ Total inscripciones: " + Inscripcion.getTotalInscripciones());
    
    return seminario;
  }
  
  private static void generarCertificados(Persona[] actores) {
    System.out.println("\n>>> PASO 7: Generando Certificados\n");
    
    for (Persona p : actores) {
      new Certificado<>(p);
    }
    
    System.out.println("\n[COVARIANZA] Total certificados: " + Certificado.getCertificados().size());
    
    System.out.println("\nEnviando certificados por email:");
    Certificado.enviarTodosPorEmail();
  }
  
  private static void imprimirResumen(ComisionOrganizadora<Persona> comision, Evento seminario) {
    System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
    System.out.println("║                        RESUMEN FINAL                          ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    System.out.println("✓ Comisión: " + comision.getTotalMiembros() + " miembros");
    System.out.println("✓ Evento: " + seminario.getTotalParticipantes() + " participantes");
    System.out.println("✓ Inscripciones: " + Inscripcion.getTotalInscripciones());
    System.out.println("✓ Certificados: " + Certificado.getCertificados().size());
    System.out.println("\n✓ Conceptos demostrados:");
    System.out.println("  • Programación Genérica (<T extends Persona>)");
    System.out.println("  • Covarianza (? extends) - lectura");
    System.out.println("  • Contravarianza (? super) - escritura");
    System.out.println("  • Bounded Type Parameters");
    System.out.println("  • Todas las 13 clases del proyecto");
    System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
    System.out.println("║              ¡PROGRAMA FINALIZADO EXITOSAMENTE!               ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");
  }
}
