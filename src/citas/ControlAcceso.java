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
public class ControlAcceso {

   private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";
    private static final String USERS_FILE = "usuarios.csv";
    private final Map<String, String> usuarios = new HashMap<>();

    public ControlAcceso() {
        cargarUsuarios();
    }

    public boolean autenticarAdministrador() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nUsuario administrador: ");
        String usuario = scanner.nextLine();
        System.out.print("Contrasena: ");
        String contrasena = scanner.nextLine();

        if (usuario.equals(ADMIN_USER) && contrasena.equals(ADMIN_PASS)) {
            System.out.println("Acceso concedido como Administrador.");
            return true;
        } else {
            System.out.println("Credenciales incorrectas.");
            return false;
        }
    }

    public boolean autenticarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nUsuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contrasena: ");
        String contrasena = scanner.nextLine();

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contrasena)) {
            System.out.println("Acceso concedido como Usuario.");
            return true;
        } else {
            System.out.println("Credenciales incorrectas o usuario no existe.");
            return false;
        }
    }

    public void agregarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNuevo usuario: ");
        String usuario = scanner.nextLine();

        if (usuarios.containsKey(usuario)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        System.out.print("Contrasena: ");
        String contrasena = scanner.nextLine();

        usuarios.put(usuario, contrasena);
        guardarUsuarios();
        System.out.println("Usuario agregado exitosamente.");
    }

    public void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUARIOS ===");
        for (String usuario : usuarios.keySet()) {
            System.out.println("Usuario: " + usuario);
        }
    }

    public void eliminarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nUsuario a eliminar: ");
        String usuario = scanner.nextLine();

        if (usuarios.remove(usuario) != null) {
            guardarUsuarios();
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    usuarios.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe, se creará al guardar el primer usuario
        }
    }

    private void guardarUsuarios() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (Map.Entry<String, String> entry : usuarios.entrySet()) {
                pw.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
}
