import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("Menu:");
            System.out.println("1. Introducir evento");
            System.out.println("2. Consultar disponibilidad");
            System.out.println("3. Eliminar evento");
            System.out.println("4. Visualizar datos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    introducirEvento();
                    break;
                case 2:
                    insertarAsistenteEvento();
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

    // 1
    public static void introducirEvento() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Introduzca el nombre del evento: ");
            String nombre = scanner.nextLine();

            // Si el nombre está vacío, salimos del bucle
            if (nombre.equals("")) {
                break;
            }

            System.out.print("Introduzca el número máximo de asistentes: ");
            int maxAsistentes = scanner.nextInt();

            Operaciones.insertarEvento(nombre, maxAsistentes);
        }
    }

    // 2
    public static void insertarAsistenteEvento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el número de personas que asistirán al evento: ");
        int asistentes = scanner.nextInt();

        //Operaciones.consultarDisponibilidad(asistentes);

        // Si no hay disponibilidad, salimos
        if (!Operaciones.consultarDisponibilidad(asistentes)) {
            return;
        }

        System.out.println("Introduzca el código del evento al que desea agregar asistentes: ");
        int codEvento = scanner.nextInt();

        int contadorAsistentes = 0;

        scanner.nextLine();

        while (true) {
            if (contadorAsistentes >= asistentes) {
                System.out.println("Se han agregado todos los asistentes.");
                break;
            }

            System.out.println("Introduzca el nombre del asistente: ");
            String nombre = scanner.nextLine();

            if (nombre.equals("")) {
                break;
            }

            Operaciones.insertarAsistenteEvento(codEvento, nombre);
            contadorAsistentes++;
        }

    }

    // 3
    public static void eliminarEvento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el nombre del evento a eliminar: ");
        String nombre = scanner.nextLine();

        Operaciones.eliminarEvento(nombre);
    }

    // 4
    public static void visualizarDatos() {
        Operaciones.visualizarDatos();
    }

}