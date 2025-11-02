package evento;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import persona.Persona;

public class Certificado<T extends Persona> {
    private String id; // ID del certificado
    private T per; // Cualquier objeto que herede de Persona
    private Date fechaEmision; // Se coloca la hora de la emisión del certificado
    private String certificado; //Almacena el contenido del certificado para evitar que se genere varias veces

    // Se usa el tipo ? extends para evitar agregar el tipo de objeto que no corresponda
    private static List<Certificado<? extends Persona>> certificados= new ArrayList<>();//Lista global de certificados emitidos

    public Certificado(T p){
        this.id = "CER-" + p.getId();
        this.fechaEmision = new Date(System.currentTimeMillis());
        this.per = p;
        this.generar();
        certificados.add(this);
    };
    
    // Se genera el certificado
    public void generar() {
        if(certificado == null) {//Evita que se vuelva a generar
            certificado = "Fecha de Emisión: " + fechaEmision + "\n"+ "- Certificado " + id + " otorgado a " + per.getNombreCompleto() + " por su participacion como " + per.obtenerTipo();
        }
        System.out.println(certificado + "/n");
    }

    //Se envia al email
    public void enviarPorEmail() { 
        if(certificado == null) {//Verifica si el certificado fue generado y si no lo genera
            generar();
        }
        System.out.println("Enviando certificado " + id + " a " + per.getEmail());
    }
    
    //Envia los objetos certificados
    public static List<Certificado<? extends Persona>> getCertificados() {
        return certificados;
    }

    //Envia todos los certificados por email
    //Se usa covarianza ya que se recorren certificados de cualquier subtipo de Persona

    public static void enviarTodosPorEmail() {
        System.out.println("\nEnviando todos los certificados\n");
        for (Certificado<? extends Persona> cert : certificados) {
            cert.enviarPorEmail();
        }
    }

}
