import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 5) {
            System.out.println("\n");
            System.out.println("Menu:");
            System.out.println("1. Introducir evento");
            System.out.println("2. Consultar disponibilidad");
            System.out.println("3. Eliminar evento");
            System.out.println("4. Visualizar datos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    introducirEvento();
                    break;
                case 2:
                    consultarDisponibilidad();
                    break;
                case 3:
                    eliminarEvento();
                    break;
                case 4:
                    visualizarDatos();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }

        scanner.close();
    }

    public static void introducirEvento() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Introduzca el nombre del evento: ");
            String nombre = scanner.nextLine();

            // If the user enters an empty string, we break the loop
            if (nombre.equals("")) {
                break;
            }

            System.out.print("Introduzca el número máximo de asistentes: ");
            int maxAsistentes = scanner.nextInt();

            Operaciones.insertarEvento(nombre, maxAsistentes);
        }
    }

    public static void eliminarEvento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el nombre del evento a eliminar: ");
        String nombre = scanner.nextLine();

        Operaciones.eliminarEvento(nombre);
    }

    public static void visualizarDatos() {
        Operaciones.visualizarDatos();
    }

    public static void consultarDisponibilidad() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el número de personas que asistirán al evento: ");
        int sitios = scanner.nextInt();

        Operaciones.consultarDisponibilidad(sitios);

    }
}