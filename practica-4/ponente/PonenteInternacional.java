package ponente;

public class PonenteInternacional extends Ponente {
    private String paisOrigen;
    private boolean requiereVisa;

    public PonenteInternacional(String id, String nombre, String apellido, String email, String telefono,
                                String tema, String biografia, String paisOrigen, boolean requiereVisa) {
        super(id, nombre, apellido, email, telefono, tema, biografia);
        this.paisOrigen = paisOrigen;
        this.requiereVisa = requiereVisa;
    }

    @Override
    public void registrar() {
        System.out.println("Ponente Internacional " + getNombreCompleto() +
                " de " + paisOrigen + " registrado.");
    }

    @Override
    public String obtenerTipo() {
        return "Ponente Internacional";
    }

    public void gestionarLogistica() {
        System.out.println("Gestionando logística para " + getNombreCompleto());
        if (requiereVisa) {
            System.out.println("→ Requiere visa para ingresar al país.");
        } else {
            System.out.println("→ No requiere visa.");
        }
    }

    // Getters
    public String getPaisOrigen() {
        return paisOrigen;
    }

    public boolean isRequiereVisa() {
        return requiereVisa;
    }
}
