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
import participante.Ponente;
import participante.PublicoGeneral;
import persona.Persona;
/**
 * CLASE PRINCIPAL - SEMINARIO INTERNACIONAL DE INGENIERÍA DE SOFTWARE
 * 
 * Demuestra:
 * 1. Programación Genérica con ComisionOrganizadora<T extends Persona>
 * 2. Covarianza (? extends) - para lectura
 * 3. Contravarianza (? super) - para escritura
 * 4. Integración con las clases existentes (Evento, Inscripcion)
 */
public class Main {
  
  public static void main(String[] args) {
    System.out.println("╔═══════════════════════════════════════════════════════════════╗");
    System.out.println("║   SEMINARIO INTERNACIONAL DE INGENIERÍA DE SOFTWARE 2024     ║");
    System.out.println("║   Demostración de Programación Genérica y Varianza           ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

    // ========== PASO 1: CREAR LA COMISIÓN ORGANIZADORA ==========
    System.out.println(">>> PASO 1: Creando la Comisión Organizadora");
    System.out.println("------------------------------------------------------------");
    ComisionOrganizadora<Persona> comision = new ComisionOrganizadora<>(
        "Comisión Organizadora del Seminario Internacional 2024"
    );

    // ========== PASO 2: CREAR Y AGREGAR MIEMBROS ==========
    System.out.println("\n>>> PASO 2: Agregando miembros a la comisión");
    System.out.println("------------------------------------------------------------");
    
    // Crear ponentes
    Ponente ponente1 = new Ponente(
        "P001", "Dr. Carlos", "Mendoza", "carlos.mendoza@mit.edu", "555-1001",
        "Arquitectura de Software", "Microservicios y Cloud Computing", "MIT"
    );
    
    Ponente ponente2 = new Ponente(
        "P002", "Dra. Ana", "Silva", "ana.silva@stanford.edu", "555-1002",
        "Inteligencia Artificial", "Machine Learning en Producción", "Stanford"
    );

    // Crear docentes
    Docente docente1 = new Docente(
        "D001", "Dr. Luis", "Paredes", "luis.paredes@unsa.edu.pe", "555-2001",
        "UNSA", "Ingeniería de Software"
    );

    Docente docente2 = new Docente(
        "D002", "Mg. Maria", "Torres", "maria.torres@unsa.edu.pe", "555-2002",
        "UNSA", "Bases de Datos"
    );

    // Crear estudiantes
    Estudiante estudiante1 = new Estudiante(
        "E001", "Juan", "Pérez", "juan.perez@unsa.edu.pe", "555-3001",
        "UNSA", "Ingeniería de Sistemas"
    );

    Estudiante estudiante2 = new Estudiante(
        "E002", "Sofia", "Ramirez", "sofia.ramirez@unsa.edu.pe", "555-3002",
        "UNSA", "Ciencias de la Computación"
    );

    // Crear público general
    PublicoGeneral publico1 = new PublicoGeneral(
        "PG001", "Roberto", "Gomez", "roberto.gomez@gmail.com", "555-4001",
        "Desarrollador de Software"
    );

    // Agregar a la comisión
    comision.agregarMiembro(ponente1);
    comision.agregarMiembro(ponente2);
    comision.agregarMiembro(docente1);
    comision.agregarMiembro(docente2);
    comision.agregarMiembro(estudiante1);
    comision.agregarMiembro(estudiante2);
    comision.agregarMiembro(publico1);

    // ========== PASO 3: MOSTRAR MIEMBROS ==========
    System.out.println("\n>>> PASO 3: Mostrando todos los miembros");
    System.out.println("------------------------------------------------------------");
    comision.mostrarMiembros();

    // ========== PASO 4: DEMOSTRAR COVARIANZA ==========
    System.out.println("\n>>> PASO 4: Demostrando COVARIANZA (? extends)");
    System.out.println("------------------------------------------------------------");
    
    // Covarianza: List<? extends Persona> - Solo lectura
    List<? extends Persona> miembrosLectura = comision.obtenerMiembros();
    
    System.out.println("\nRecorriendo lista covariante (solo lectura):");
    for (Persona p : miembrosLectura) {
      System.out.println("  - " + p.obtenerTipo() + ": " + p.getNombreCompleto());
    }
    
    // NO SE PUEDE: miembrosLectura.add(new Estudiante(...)); // Error de compilación
    System.out.println("\n⚠ Nota: No se puede agregar elementos a una lista covariante");
    System.out.println("   Razón: El compilador no sabe el tipo exacto, solo que es subtipo de Persona");

    // ========== PASO 5: DEMOSTRAR CONTRAVARIANZA ==========
    System.out.println("\n\n>>> PASO 5: Demostrando CONTRAVARIANZA (? super)");
    System.out.println("------------------------------------------------------------");
    
    // Lista que acepta Participante o cualquier supertipo
    List<Persona> listaParticipantes = new ArrayList<>();
    
    // Contravarianza: List<? super Participante> - Solo escritura
    comision.registrarParticipantes(listaParticipantes);
    
    System.out.println("\nParticipantes registrados en lista contravariante:");
    for (Persona p : listaParticipantes) {
      System.out.println("  - " + p.obtenerTipo() + ": " + p.getNombreCompleto());
    }

    // ========== PASO 6: USAR MÉTODOS GENÉRICOS ==========
    System.out.println("\n\n>>> PASO 6: Usando métodos genéricos con filtrado");
    System.out.println("------------------------------------------------------------");
    
    List<Ponente> ponentes = comision.obtenerMiembrosPorTipo(Ponente.class);
    System.out.println("\nPonentes encontrados:");
    for (Ponente p : ponentes) {
      System.out.println("  - " + p.getNombreCompleto() + " - Tema: " + p.getTema());
    }

    List<Estudiante> estudiantes = comision.obtenerMiembrosPorTipo(Estudiante.class);
    System.out.println("\nEstudiantes encontrados:");
    for (Estudiante e : estudiantes) {
      System.out.println("  - " + e.getNombreCompleto() + " - Carrera: " + e.getUniversidad());
    }

    // ========== PASO 7: PROCESAMIENTO CON FUNCIÓN CONTRAVARIANTE ==========
    System.out.println("\n\n>>> PASO 7: Procesamiento con función contravariante");
    System.out.println("------------------------------------------------------------");
    
    // Procesador que acepta Persona o cualquier supertipo
    ComisionOrganizadora.Procesador<Persona> procesador = persona -> {
      System.out.println("  ✓ Procesando: " + persona.obtenerTipo() + " - " + 
                         persona.getNombreCompleto());
    };
    
    comision.procesarMiembrosCon(procesador);

    // ========== PASO 8: INTEGRACIÓN CON EVENTO E INSCRIPCIONES ==========
    System.out.println("\n\n>>> PASO 8: Creando evento e inscripciones");
    System.out.println("------------------------------------------------------------");
    
    Evento seminario = new Evento(
        "Seminario Internacional de Ingeniería de Software 2024",
        new Date(),
        "Auditorio Principal - UNSA"
    );

    // Registrar algunos participantes en el evento
    seminario.registrarAsistente(estudiante1);
    seminario.registrarAsistente(estudiante2);
    seminario.registrarAsistente(docente1);
    seminario.registrarAsistente(ponente1);

    System.out.println("\n");
    seminario.iniciarEvento();

    // Demostrar covarianza en Evento
    List<? extends Participante> participantesEvento = seminario.obtenerParticipantes();
    System.out.println("\n[COVARIANZA EN EVENTO] Participantes registrados:");
    for (Participante p : participantesEvento) {
      System.out.println("  - " + p.getNombreCompleto() + " (" + p.obtenerTipo() + ")");
    }

    // ========== PASO 9: USAR MÉTODOS GENÉRICOS DEL EVENTO ==========
    System.out.println("\n\n>>> PASO 9: Filtrando participantes por tipo en el evento");
    System.out.println("------------------------------------------------------------");
    
    List<Estudiante> estudiantesEvento = seminario.obtenerParticipantesPorTipo(Estudiante.class);
    System.out.println("Estudiantes en el evento: " + estudiantesEvento.size());

    List<Ponente> ponentesEvento = seminario.obtenerParticipantesPorTipo(Ponente.class);
    System.out.println("Ponentes en el evento: " + ponentesEvento.size());

    // ========== PASO 10: CREAR INSCRIPCIONES ==========
    System.out.println("\n\n>>> PASO 10: Creando inscripciones");
    System.out.println("------------------------------------------------------------");
    
    new Inscripcion("INS001", seminario.getNombre(), estudiante1);
    new Inscripcion("INS002", seminario.getNombre(), estudiante2);
    new Inscripcion("INS003", seminario.getNombre(), docente1);

    // Filtrar inscripciones por tipo
    List<Inscripcion> inscripcionesEstudiantes = 
        Inscripcion.filtrarPorTipoParticipante(Estudiante.class);

    System.out.println("\nInscripciones de estudiantes: " + inscripcionesEstudiantes.size());
    Certificado cert1 = new Certificado();
    cert1.generar(ponente1);
    cert1.generar(docente1);
    cert1.generar(estudiante1);
    // ========== RESUMEN FINAL ==========
    System.out.println("\n\n╔═══════════════════════════════════════════════════════════════╗");
    System.out.println("║                        RESUMEN FINAL                          ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    System.out.println("✓ Comisión Organizadora creada con " + comision.getTotalMiembros() + " miembros");
    System.out.println("✓ Evento creado con " + seminario.getTotalParticipantes() + " participantes");
    System.out.println("✓ Total de inscripciones: " + Inscripcion.getTotalInscripciones());
    System.out.println("\n✓ Conceptos demostrados:");
    System.out.println("  • Programación Genérica (<T extends Persona>)");
    System.out.println("  • Covarianza (? extends) - para lectura");
    System.out.println("  • Contravarianza (? super) - para escritura");
    System.out.println("  • Bounded Type Parameters");
    System.out.println("  • Wildcards en genéricos");
    System.out.println("  • Interfaces funcionales genéricas");
    System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
    System.out.println("║              ¡PROGRAMA FINALIZADO EXITOSAMENTE!               ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");
  }
}
