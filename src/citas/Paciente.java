package citas;

/**
 *
 * @author Nelo Angelo
 */
public class Paciente {

    private final String id;
    private final String nombre;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String toCSV() {
        return id + "," + nombre;
    }

    @Override
    public String toString() {
        return "Paciente [ID: " + id + ", Nombre: " + nombre + "]";
    }
}
