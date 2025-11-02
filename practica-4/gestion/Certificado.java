package gestion;

import java.sql.Date;

public class Certificado {
    private String id;
    private String nombreParticipante;
    private String tipoParticipacion;
    private Date fechaEmision;

    public Certificado(String id, String nomp, String tp, Date fecha) {
        this.id = id;
        this.nombreParticipante = nomp;
        this.tipoParticipacion = tp;
        this.fechaEmision = fecha;
    }
}
