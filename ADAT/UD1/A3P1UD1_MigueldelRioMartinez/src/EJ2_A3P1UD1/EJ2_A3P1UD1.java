package EJ2_A3P1UD1;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EJ2_A3P1UD1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del archivo:");
        String nombre = sc.nextLine();

        File archivo = new File(nombre + ".dat");

        generarFichero(archivo, inputNumeros());
        visualizarFichero(archivo);
    }

    public static List<Integer> inputNumeros() {

        boolean exit = false;

        List<Integer> numeros = new ArrayList<>();

        while (!exit) {
            try
            {
                int numero = new Scanner(System.in).nextInt();

                if (numeros.isEmpty())
                {
                    numeros.add(numero);
                    continue;
                }

                int ultimoNumero = numeros.get(numeros.size() - 1);

                if (ultimoNumero > numero) {
                    System.out.println("Introduce un numero mayor o igual al anterior ");
                }
                
                numeros.add(numero);
            } catch (InputMismatchException e) {
                exit = true;

            }
        }

        return numeros;
    }

    public static void generarFichero(File f, List<Integer> numeros) {
        if (f.exists()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("El fichero existe, ¿quieres reemplazar?");

            if (!sc.nextBoolean()) {
                return;
            }
        }

        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(f))) {

            for (int numero : numeros) {
                writer.writeInt(numero);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visualizarFichero(File f) {
        try (DataInputStream reader = new DataInputStream(new FileInputStream(f))){
            while (true) {
                System.out.println("Numero leído: " + reader.readInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el fichero.");
        } catch (EOFException e) {
            System.out.println("Final del fichero alcanzado.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }

}
