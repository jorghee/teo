package gestion;

import java.sql.Date;

import participante.Participante;
import participante.Ponente;
import participante.PublicoGeneral;
import participante.*;
import persona.Persona;

public class Certificado {
    private String id;
    private Persona participante;
    private String tipo;
    private Date fechaEmision;    

    public Certificado(){};

    public static void generar(Persona p) {
        if (p instanceof Ponente) {
            System.out.println("Certificado de Ponente para " + p.getNombre());
        }else if (p instanceof Participante) {
            generar((Participante) p);
        }else {
            System.out.println("Participante desconocido" + p.getNombre());
        }
    }

    private static void generar(Participante p) {
        if (p instanceof Ponente) {
            System.out.println("Certificado de Ponente para " + p.getNombre());
        } else if (p instanceof Docente) {
            System.out.println("Certificado de Docente para " + p.getNombre());
        } else if (p instanceof Estudiante) {
            System.out.println("Certificado de Estudiante para " + p.getNombre());
        } else {
            System.out.println("Certificado de Asistencia para " + p.getNombre());
        } 
    }
}
