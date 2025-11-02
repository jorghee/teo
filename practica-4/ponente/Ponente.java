package ponente;

import persona.Persona;
import evento.Evento;
import java.util.ArrayList;
import java.util.List;

public class Ponente extends Persona {
    protected String tema;
    protected String biografia;
    protected List<Evento> eventos; 

    public Ponente(String id, String nombre, String apellido, String email, String telefono,
                   String tema, String biografia) {
        super(id, nombre, apellido, email, telefono);
        this.tema = tema;
        this.biografia = biografia;
        this.eventos = new ArrayList<>();
    }

    @Override
    public void registrar() {
        System.out.println("Ponente " + getNombreCompleto() + " registrado.");
    }

    @Override
    public String obtenerTipo() {
        return "Ponente";
    }

    public void prepararPresentacion() {
        System.out.println(getNombreCompleto() + " está preparando la presentación sobre: " + tema);
    }

    public void realizarExposicion() {
        System.out.println(getNombreCompleto() + " realiza su exposición.");
    }

    public String getTema() {
        return tema;
    }

    // ================= RELACIÓN CON EVENTO =================
    public void agregarEvento(Evento e) {
        if (!eventos.contains(e)) {
            eventos.add(e);
            // Agregar este ponente como participante del evento si aún no está
            if (!e.obtenerParticipantes().contains(this)) {
                e.registrarAsistente(this);
            }
        }
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
