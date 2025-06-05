package citas;

/**
 *
 * @author Nelo Angelo
 */
public class Doctor {

    private final String id;
    private final String nombre;
    private final String especialidad;

    //CONSTRUCTOR DE LA CLASE PARA INSTANCIAR
    public Doctor(String id, String nombre, String especialidad) {  
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    //METODO
    public String toCSV() {
        return id + "," + nombre + "," + especialidad;
    }
    
    //METODO QUE RETORNA 
    @Override
    public String toString() {
        return "Doctor [ID: " + id + ", Nombre: " + nombre + ", Especialidad: " + especialidad + "]";
    }
}
