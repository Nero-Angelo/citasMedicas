package citas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Nelo Angelo
 */
public class Citas {

    private static final String FILE_NAME = "citas.csv";
    private final Map<String, Cita> citas = new HashMap<>();
    private int contadorId = 1;

    public void agregarCita(String idDoctor, String idPaciente, Date fechaHora, String motivo) {
        String id = "C" + contadorId++;
        citas.put(id, new Cita(id, idDoctor, idPaciente, fechaHora, motivo));
        guardarEnArchivo();
    }

    public void listarCitas() {
        System.out.println("\n=== LISTA DE CITAS ===");
        for (Cita cita : citas.values()) {
            System.out.println(cita);
        }
    }

    public void eliminarCita() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nID de la cita a eliminar: ");
        String id = scanner.nextLine();

        if (citas.remove(id) != null) {
            guardarEnArchivo();
            System.out.println("Cita eliminada exitosamente.");
        } else {
            System.out.println("Cita no encontrada.");
        }
    }

    public void cargarDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        Date fechaHora = sdf.parse(partes[3]);

                        citas.put(partes[0], new Cita(partes[0], partes[1], partes[2], fechaHora, partes[4]));

                        // Actualizar contador de IDs
                        int num = Integer.parseInt(partes[0].substring(1));
                        if (num >= contadorId) {
                            contadorId = num + 1;
                        }
                    } catch (NumberFormatException | ParseException e) {
                        System.out.println("Error al parsear fecha: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe, se creará al guardar
        }
    }

    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Cita cita : citas.values()) {
                pw.println(cita.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar citas: " + e.getMessage());
        }
    }
}
