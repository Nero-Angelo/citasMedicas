package citas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Nelo Angelo
 */
public class Doctores {

    private static final String FILE_NAME = "doctores.csv";
    private final Map<String, Doctor> doctores = new HashMap<>();
    
    
    //Creacion de metodo para agregar doctores
    public void agregarDoctor() {       //METODOS
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== AGREGAR NUEVO DOCTOR ===");

        System.out.print("ID Unico: ");
        String id = scanner.nextLine();

        if (doctores.containsKey(id)) {
            System.out.println("Ya existe un doctor con ese ID.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        doctores.put(id, new Doctor(id, nombre, especialidad));
        guardarEnArchivo();
        System.out.println("Doctor agregado exitosamente.");
    }
    //creacion de metodo para listar los doctores 
    public void listarDoctores() {
        System.out.println("\n=== LISTA DE DOCTORES ===");
        for (Doctor doctor : doctores.values()) {
            System.out.println(doctor);
        }
    }
    //Creacion de metodo para eliminar los doctores
    public void eliminarDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nID del doctor a eliminar: ");
        String id = scanner.nextLine();

        if (doctores.remove(id) != null) {
            guardarEnArchivo();
            System.out.println("Doctor eliminado exitosamente.");
        } else {
            System.out.println("Doctor no encontrado.");
        }
    }

    public boolean existeDoctor(String id) {
        return doctores.containsKey(id);
    }

    public void cargarDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    doctores.put(partes[0], new Doctor(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe, se creará al guardar
        }
    }

    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Doctor doctor : doctores.values()) {
                pw.println(doctor.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar doctores: " + e.getMessage());
        }
    }
}
