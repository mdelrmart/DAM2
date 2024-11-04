/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EJ1_A3P3UD1;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class EJ1_A3P3UD1 {

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 7) {
            menu();
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    crearArchivo();
                    break;
                case 2:
                    anhadirRegistros();
                    break;
                case 3:
                    System.out.println("Introduzca el número de dorsal a consultar:");
                    int numDorsal = sc.nextInt();
                    consultarRegistro(numDorsal);
                    break;
                case 4:
                    consultarTodosLosRegistros();
                    break;
                case 5:
                    modificarRegistro();
                    break;
                case 6:
                    eliminarRegistro();
                    break;
                case 7:
                    break;
            }

        }
    }

    public static void menu() {
        System.out.println("\n");
        System.out.println("Menú de opciones");
        System.out.println("----------------");
        System.out.println("1.-Crear archivo");
        System.out.println("2.-Añadir registros");
        System.out.println("3.-Consultar un registro");
        System.out.println("4.-Consultar todos los registros");
        System.out.println("5.-Modificar un registro");
        System.out.println("6.-Borrar");
        System.out.println("7.-Salir");
    }

    // 1.-Crear archivo
    public static void crearArchivo() {
        Scanner sc = new Scanner(System.in);

        File f = new File("corredores.dat");

        if (f.exists()) {
            System.out.println("¿Quieres sobreescribir el archivo?");
            boolean sobreescribir = sc.nextBoolean();

            if (!sobreescribir) {
                System.out.println("No se sobreescribirá el archivo. Operación cancelada.");
                return;
            } else {
                f.delete();
            }
        }

        String nombre = "";

        while (!nombre.equals("*")) {
            System.out.println("Nombre del corredor:");
            nombre = sc.next();

            if (nombre.equals("*")) {
                break;
            }

            System.out.println("Dorsal del corredor:");
            int dorsal = sc.nextInt();

            if (Validaciones.comprobarDorsal(dorsal) == 1) {
                System.out.println("El número de dorsal ya existe");
                break;
            }

            System.out.println("Tiempo del corredor:");
            float tiempo = sc.nextFloat();

            Corredor c = new Corredor(dorsal, nombre, tiempo);
            try (FileOutputStream archivoSalida = new FileOutputStream("corredores.dat", true);
                 ObjectOutputStream objectoSalida =
                         f.exists() && f.length() > 0 ? new MiObjectOutputStream(archivoSalida) : new ObjectOutputStream(archivoSalida)) {

                objectoSalida.writeObject(c);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 2.-Añadir registros
    public static void anhadirRegistros() {
        Scanner sc = new Scanner(System.in);

        if (Validaciones.comprobarExistenciaFichero() == 1) return;

        String nombre = "";

        while (!nombre.equals("*")) {
            System.out.println("Nombre del corredor:");
            nombre = sc.next();

            if (nombre.equals("*")) {
                break;
            }

            System.out.println("Dorsal del corredor:");
            int dorsal = sc.nextInt();

            if (Validaciones.comprobarDorsal(dorsal) == 1) {
                System.out.println("El número de dorsal ya existe");
                break;
            }

            System.out.println("Tiempo del corredor:");
            float tiempo = sc.nextFloat();

            Corredor c = new Corredor(dorsal, nombre, tiempo);
            try (FileOutputStream archivoSalida = new FileOutputStream("corredores.dat", true);
                 ObjectOutputStream objectoSalida = new MiObjectOutputStream(archivoSalida)) {

                objectoSalida.writeObject(c);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 3.-Consultar un registro
    public static void consultarRegistro(int numDorsal) {
        if (Validaciones.comprobarExistenciaFichero() == 1) return;

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Introduzca el número de dorsal a consultar:");
        //int numDorsal = sc.nextInt();

        try (FileInputStream archivoEntrada = new FileInputStream("corredores.dat");
             ObjectInputStream objectoEntrada = new ObjectInputStream(archivoEntrada)) {

            while (true) {
                try {
                    Corredor c = (Corredor) objectoEntrada.readObject();
                    if (Objects.equals(c.getDorsal(), numDorsal)) {
                        System.out.println("\n");
                        System.out.println("Nombre: " + c.getNombre());
                        System.out.println("Dorsal: " + c.getDorsal());
                        System.out.println("Tiempo: " + c.getTiempo());
                    }
                }
                catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 4.-Consultar todos los registros
    public static void consultarTodosLosRegistros() {
        if (Validaciones.comprobarExistenciaFichero() == 1) return;

        try (FileInputStream archivoEntrada = new FileInputStream("corredores.dat");
             ObjectInputStream objectoEntrada = new ObjectInputStream(archivoEntrada)) {

            while (true) {
                try {
                    Corredor c = (Corredor) objectoEntrada.readObject();
                    System.out.println("\n");
                    System.out.println("Nombre: " + c.getNombre());
                    System.out.println("Dorsal: " + c.getDorsal());
                    System.out.println("Tiempo: " + c.getTiempo());
                } catch (EOFException e) {
                    // Fin del fichero.
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 5.-Modificar un registro
    public static void modificarRegistro() {
        if (Validaciones.comprobarExistenciaFichero() == 1) return;

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el número de dorsal a modificar:");
        int numDorsal = sc.nextInt();

        consultarRegistro(numDorsal);
        System.out.println("\n");
        System.out.println("Este es el corredor que se va a modificar. ¿Continuar?");

        boolean continuar = sc.nextBoolean();

        if (!continuar) {
            System.out.println("Operación cancelada.");
            return;
        }

        System.out.println("Nombre del corredor:");
        String nombre = sc.next();

        System.out.println("Tiempo del corredor:");
        float tiempo = sc.nextFloat();

        Operaciones.modificarRegistro(numDorsal, nombre, tiempo);
    }

    // 6.-Borrar
    public static void eliminarRegistro() {
        if (Validaciones.comprobarExistenciaFichero() == 1) return;

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el número de dorsal a borrar:");
        int numDorsal = sc.nextInt();

        Operaciones.eliminarRegistro(numDorsal);
    }
}
