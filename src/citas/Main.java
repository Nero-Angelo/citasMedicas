package citas;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Nelo Angelo
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ControlAcceso controlAcceso = new ControlAcceso();
    private static final Doctores doctores = new Doctores();
    private static final Pacientes pacientes = new Pacientes();
    private static final Citas citas = new Citas();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        cargarDatos();
        mostrarMenuAcceso();
    }

    private static void cargarDatos() {
        doctores.cargarDesdeArchivo();
        pacientes.cargarDesdeArchivo();
        citas.cargarDesdeArchivo();
    }

    private static void guardarDatos() {
        doctores.guardarEnArchivo();
        pacientes.guardarEnArchivo();
        citas.guardarEnArchivo();
    }

    private static void mostrarMenuAcceso() {
        while (true) {
            System.out.println("\n=== SISTEMA DE CITAS MEDICAS ===");
            System.out.println("1. Iniciar sesion como Administrador");
            System.out.println("2. Iniciar sesion como Usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n================================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (controlAcceso.autenticarAdministrador()) {
                        menuAdministrador();
                    }
                    break;
                case 2:
                    if (controlAcceso.autenticarUsuario()) {
                        menuUsuario();
                    }
                    break;
                case 3:
                    guardarDatos();
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuAdministrador() {
        while (true) {
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1. Gestionar Doctores");
            System.out.println("2. Gestionar Pacientes");
            System.out.println("3. Gestionar Citas");
            System.out.println("4. Gestionar Usuarios");
            System.out.println("5. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n==========================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuGestionDoctores();
                    break;
                case 2:
                    menuGestionPacientes();
                    break;
                case 3:
                    menuGestionCitas();
                    break;
                case 4:
                    menuGestionUsuarios();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuUsuario() {
        while (true) {
            System.out.println("\n=== MENU USUARIO ===");
            System.out.println("1. Gestionar Doctores");
            System.out.println("2. Gestionar Pacientes");
            System.out.println("3. Gestionar Citas");
            System.out.println("4. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n====================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuUsuarioDoctores();
                    break;
                case 2:
                    menuUsuarioPacientes();
                    break;
                case 3:
                    menuUsuarioCitas();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    //METODO AGREGADO +++++++++++++++++
    private static void menuUsuarioDoctores() {
        while (true) {
            System.out.println("\n=== GESTION DE DOCTORES (USUARIO) ===");
            System.out.println("1. Agregar Doctor");
            System.out.println("2. Listar Doctores");
            System.out.println("3. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n=====================================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    doctores.agregarDoctor();
                    break;
                case 2:
                    doctores.listarDoctores();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuUsuarioPacientes() {
        while (true) {
            System.out.println("\n=== GESTION DE PACIENTES (USUARIO) ===");
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n======================================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    pacientes.agregarPaciente();
                    break;
                case 2:
                    pacientes.listarPacientes();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuUsuarioCitas() {
        while (true) {
            System.out.println("\n=== GESTION DE CITAS (USUARIO) ===");
            System.out.println("1. Agregar Cita");
            System.out.println("2. Listar Citas");
            System.out.println("3. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n==================================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearCita();
                    break;
                case 2:
                    citas.listarCitas();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuGestionDoctores() {
        while (true) {
            System.out.println("\n=== GESTION DE DOCTORES ===");
            System.out.println("1. Agregar Doctor");
            System.out.println("2. Listar Doctores");
            System.out.println("3. Eliminar Doctor");
            System.out.println("4. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n===========================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    doctores.agregarDoctor();
                    break;
                case 2:
                    doctores.listarDoctores();
                    break;
                case 3:
                    doctores.eliminarDoctor();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuGestionPacientes() {
        while (true) {
            System.out.println("\n=== GESTION DE PACIENTES ===");
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Volver al menú anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n============================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    pacientes.agregarPaciente();
                    break;
                case 2:
                    pacientes.listarPacientes();
                    break;
                case 3:
                    pacientes.eliminarPaciente();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuGestionCitas() {
        while (true) {
            System.out.println("\n=== GESTION DE CITAS ===");
            System.out.println("1. Agregar Cita");
            System.out.println("2. Listar Citas");
            System.out.println("3. Eliminar Cita");
            System.out.println("4. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n========================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearCita();
                    break;
                case 2:
                    citas.listarCitas();
                    break;
                case 3:
                    citas.eliminarCita();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void menuGestionUsuarios() {
        while (true) {
            System.out.println("\n=== GESTION DE USUARIOS ===");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Eliminar Usuario");
            System.out.println("4. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");
            System.out.println("\n===========================");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    controlAcceso.agregarUsuario();
                    break;
                case 2:
                    controlAcceso.listarUsuarios();
                    break;
                case 3:
                    controlAcceso.eliminarUsuario();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }


    private static void crearCita() {
        System.out.println("\n=== CREAR NUEVA CITA ===");

        // Mostrar doctores disponibles
        System.out.println("\nDoctores disponibles:");
        doctores.listarDoctores();
        System.out.print("Ingrese el ID del doctor: ");
        String idDoctor = scanner.nextLine();

        // Verificar si el doctor existe
        if (!doctores.existeDoctor(idDoctor)) {
            System.out.println("Doctor no encontrado.");
            return;
        }

        // Mostrar pacientes disponibles
        System.out.println("\nPacientes disponibles:");
        pacientes.listarPacientes();
        System.out.print("Ingrese el ID del paciente: ");
        String idPaciente = scanner.nextLine();

        // Verificar si el paciente existe
        if (!pacientes.existePaciente(idPaciente)) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        // Solicitar fecha y hora
        System.out.print("Ingrese la fecha y hora de la cita (dd/MM/yyyy HH:mm): ");
        String fechaHoraStr = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date fechaHora = sdf.parse(fechaHoraStr);

            // Solicitar motivo
            System.out.print("Ingrese el motivo de la cita: ");
            String motivo = scanner.nextLine();

            // Crear la cita
            citas.agregarCita(idDoctor, idPaciente, fechaHora, motivo);
            System.out.println("Cita creada exitosamente.");
        } catch (ParseException e) {
            System.out.println("Formato de fecha y hora inválido. Use dd/MM/yyyy HH:mm");
        }
    }
}
