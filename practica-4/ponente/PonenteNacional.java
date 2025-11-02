package ponente;

import evento.Evento;

public class PonenteNacional extends Ponente {
    private String ciudad;
    private String institucion;

    public PonenteNacional(String id, String nombre, String apellido, String email, String telefono,
                           String tema, String biografia, String ciudad, String institucion) {
        super(id, nombre, apellido, email, telefono, tema, biografia);
        this.ciudad = ciudad;
        this.institucion = institucion;
    }

    @Override
    public void registrar() {
        System.out.println("Ponente Nacional " + getNombreCompleto() + " registrado.");
    }

    @Override
    public String obtenerTipo() {
        return "Ponente Nacional";
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getInstitucion() {
        return institucion;
    }

    // Mantiene la relación con Evento heredada de Ponente
    @Override
    public void agregarEvento(Evento e) {
        super.agregarEvento(e); // Usa la lógica de agregarEvento de Ponente
    }
}
