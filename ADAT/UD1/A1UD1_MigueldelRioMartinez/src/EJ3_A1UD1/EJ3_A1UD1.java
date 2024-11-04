/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJ3_A1UD1;

import java.io.*;
import java.util.Scanner;

/**
 * @author Miguel del Río
 */
public class EJ3_A1UD1 {

    private static PrintStream writer = null;

    public static void main(String[] args) throws IOException, ExcepcionEnteroPositivo {

        abrirFichero();
        System.out.println("Introduce el número de enteros positivos para grabar en fichero:");

        int num;
        num = leerPositivo();

        for (int i = 1; i <= num; i++) {
            System.out.print("número " + i + ":");
            grabarFichero(obtenerPositivo());
        }

        cerrarFichero();
        leerFichero();
    }

    private static void abrirFichero() throws FileNotFoundException {
        File file = new File(".\\NumerosPositivos.txt");
        writer = new PrintStream(new FileOutputStream(file));
    }

    private static void cerrarFichero() {
        writer.close();
        writer = null;
    }

    private static void leerFichero() {
        File file = new File(".\\NumerosPositivos.txt");

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int obtenerPositivo() {
        try {
            return leerPositivo();
        } catch (ExcepcionEnteroPositivo e) {
            System.out.println("No es un número positivo");
            return obtenerPositivo();
        }
    }

    private static int leerPositivo() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if (num < 0) throw new ExcepcionEnteroPositivo();

        return num;
    }

    private static void grabarFichero(int leerPositivo) {
        writer.print(leerPositivo);
        writer.print(";");
    }
}
