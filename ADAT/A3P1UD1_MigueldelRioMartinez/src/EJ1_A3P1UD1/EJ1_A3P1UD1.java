package EJ1_A3P1UD1;

import java.io.*;
import java.util.Scanner;

public class EJ1_A3P1UD1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del archivo:");
        String archivo = sc.nextLine();

        File f = new File(archivo + ".dat");

        generarFichero(f);

        visualizarFichero(f);
    }

    private static void generarFichero(File f) {

        if (f.exists()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("El fichero existe, ¿reemplazar contenido o cancelar operación? (true/false)");

            if (!sc.nextBoolean()) return;
        }

        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(f)))
        {
            int[] numeros = generarNumeros();

            for (int numero : numeros) {
                writer.writeInt(numero);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
        } catch (IOException e) {
            System.out.println("No se pudo abrir el archivo.");
        }
    }

    public static void visualizarFichero(File f) {
        try (DataInputStream reader = new DataInputStream(new FileInputStream(f)))
        {
            while (true) {
                System.out.println("Leído el numero: " + reader.readInt());;
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo.");
        } catch (EOFException e) {
            System.out.println("Final del fichero alcanzado.");
        } catch (IOException e) {
            System.out.println("No se pudo abrir el archivo.");
        }
    }

    public static int[] generarNumeros() {
        int[] buffer = new int[150];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (int) (Math.random() * 61 + 20);
        }

        return buffer;
    }
}
