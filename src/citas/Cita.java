package citas;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nelo Angelo
 */
public class Cita {

    private final String id;
    private final String idDoctor;
    private final String idPaciente;
    private final Date fechaHora;
    private final String motivo;

    public Cita(String id, String idDoctor, String idPaciente, Date fechaHora, String motivo) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return id + "," + idDoctor + "," + idPaciente + "," + sdf.format(fechaHora) + "," + motivo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Cita [ID: " + id + ", Doctor: " + idDoctor + ", Paciente: " + idPaciente
                + ", Fecha/Hora: " + sdf.format(fechaHora) + ", Motivo: " + motivo + "]";
    }
}
