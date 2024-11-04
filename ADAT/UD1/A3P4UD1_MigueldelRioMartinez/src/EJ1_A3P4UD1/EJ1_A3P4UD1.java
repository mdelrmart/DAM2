/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EJ1_A3P4UD1;

import java.io.*;
import java.util.Scanner;

public class EJ1_A3P4UD1 {

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 6) {
            menu();
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    anhadirRegistros();
                    break;
                case 2:
                    System.out.println("Introduzca el número de dorsal a consultar:");
                    int numDorsal = sc.nextInt();
                    consultarRegistro(numDorsal);
                    break;
                case 3:
                    consultarTodosLosRegistros();
                    break;
                case 4:
                    modificarRegistro();
                    break;
                case 5:
                    eliminarRegistro();
                    break;
                default:
                    break;
            }

        }
    }

    public static void menu() {
        System.out.println("\n");
        System.out.println("Menú de opciones");
        System.out.println("----------------");
        System.out.println("1.-Añadir registros");
        System.out.println("2.-Consultar un registro");
        System.out.println("3.-Consultar todos los registros");
        System.out.println("4.-Modificar un registro");
        System.out.println("5.-Borrar");
        System.out.println("6.-Salir");
    }

    // 1.-Añadir registros
    public static void anhadirRegistros() {
        Scanner sc = new Scanner(System.in);

        String nombre = "";

        while (!nombre.equals("*")) {
            System.out.println("Nombre del corredor:");
            nombre = sc.next();

            if (nombre.equals("*")) {
                break;
            }

            System.out.println("Dorsal del corredor:");
            int dorsal = sc.nextInt();


            if (Validaciones.comprobarDorsal(dorsal) == -1) {
                System.out.println("El número de dorsal ya existe");
                break;
            }

            System.out.println("Tiempo del corredor:");
            float tiempo = sc.nextFloat();

            Corredor c = new Corredor(dorsal, nombre, tiempo);
            try (RandomAccessFile archivo = new RandomAccessFile("corredores.dat", "rw")) {

                archivo.seek(c.getDorsal() * 80);
                archivo.writeInt(c.getDorsal());
                archivo.writeUTF(c.getNombre());
                archivo.writeFloat(c.getTiempo());

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 2.-Consultar un registro
    public static int consultarRegistro(int numDorsal) {
        try (RandomAccessFile archivo = new RandomAccessFile("corredores.dat", "r")) {
            archivo.seek(numDorsal * 80);

            if (archivo.readInt() != 0) {
                //Volvemos a hacer el seek para volver a la posición inicial.
                archivo.seek(numDorsal * 80);
                System.out.println("Dorsal: " + archivo.readInt());
                System.out.println("Nombre: " + archivo.readUTF());
                System.out.println("Tiempo: " + archivo.readFloat());
            }
            else {
                System.out.println("No existe el corredor.");
                return -1;
            }
        } catch (EOFException e) {
            return -1;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // 3.-Consultar todos los registros
    public static void consultarTodosLosRegistros() {

        try (RandomAccessFile archivo = new RandomAccessFile("corredores.dat", "r")) {
            for (int i = 0; i <= archivo.length() / 80; i++) {
                archivo.seek(i * 80);

                if (archivo.readInt() != 0) {
                    //Volvemos a hacer el seek para volver a la posición inicial.
                    archivo.seek(i * 80);
                    System.out.println("Dorsal: " + archivo.readInt());
                    System.out.println("Nombre: " + archivo.readUTF());
                    System.out.println("Tiempo: " + archivo.readFloat());
                    System.out.println("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 4.-Modificar un registro
    public static void modificarRegistro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el número de dorsal a modificar:");
        int numDorsal = sc.nextInt();

        if (consultarRegistro(numDorsal) == -1) {
            return;
        }

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

    // 5.-Borrar
    public static void eliminarRegistro() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el número de dorsal a borrar:");
        int numDorsal = sc.nextInt();

        Operaciones.eliminarRegistro(numDorsal);
    }
}
