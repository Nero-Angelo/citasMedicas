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
public class Pacientes {

    private static final String FILE_NAME = "pacientes.csv";
    private final Map<String, Paciente> pacientes = new HashMap<>();

    public void agregarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== AGREGAR NUEVO PACIENTE ===");

        System.out.print("ID unico: ");
        String id = scanner.nextLine();

        if (pacientes.containsKey(id)) {
            System.out.println("Ya existe un paciente con ese ID.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        pacientes.put(id, new Paciente(id, nombre));
        guardarEnArchivo();
        System.out.println("Paciente agregado exitosamente.");
    }

    public void listarPacientes() {
        System.out.println("\n=== LISTA DE PACIENTES ===");
        for (Paciente paciente : pacientes.values()) {
            System.out.println(paciente);
        }
    }

    public void eliminarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nID del paciente a eliminar: ");
        String id = scanner.nextLine();

        if (pacientes.remove(id) != null) {
            guardarEnArchivo();
            System.out.println("Paciente eliminado exitosamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public boolean existePaciente(String id) {
        return pacientes.containsKey(id);
    }

    public void cargarDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    pacientes.put(partes[0], new Paciente(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe, se creará al guardar
        }
    }

    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Paciente paciente : pacientes.values()) {
                pw.println(paciente.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pacientes: " + e.getMessage());
        }
    }
}
