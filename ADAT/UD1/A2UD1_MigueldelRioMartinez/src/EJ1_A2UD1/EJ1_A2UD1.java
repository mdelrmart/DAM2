/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJ1_A2UD1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Miguel del Río
 */
public class EJ1_A2UD1 {
    public static void main(String[] args) {
        System.out.println("Menu de opciones");
        System.out.println("----------------");
        System.out.println("[P] listado por pantalla");
        System.out.println("[D] listado a un fichero");
        System.out.println("----------------");
        System.out.println("Elija opción------------------>:");

        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine().toUpperCase();

        switch (entrada) {
            case "P":
                System.out.println("--- LISTANDO UNIDADES ---");
                listarUnidades();
                break;
            case "D":
                try {
                    System.setOut(guardarEnFichero());
                    listarUnidades();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public static void listarUnidades() {
        File[] unidades = File.listRoots();

        NumberFormat formatoLegible = NumberFormat.getInstance(Locale.getDefault());

        for (File unidad : unidades) {
            System.out.println("Unidad " + unidad);
            System.out.println("   Espacio libre: " + formatoLegible.format(unidad.getFreeSpace()) + " bytes (" + convertirBytesAGibibytes(unidad.getFreeSpace()) + " GB)");
            System.out.println("   Espacio ocupado: " + formatoLegible.format((unidad.getTotalSpace() - unidad.getFreeSpace())) + " bytes (" + convertirBytesAGibibytes(unidad.getTotalSpace() - unidad.getFreeSpace()) + " GB)");
            System.out.println("   Espacio total: " + formatoLegible.format(unidad.getTotalSpace()) + " bytes (" + convertirBytesAGibibytes(unidad.getTotalSpace()) + " GB)");
        }
    }

    public static PrintStream guardarEnFichero() throws FileNotFoundException {
        File file = new File("salida.txt");
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        return ps;
    }

    public static String convertirBytesAGibibytes(long bytes) {
        long BYTES_A_GIBIBYTES = 1024 * 1024 * 1024;
        DecimalFormat df = new DecimalFormat("#.00");
        double resultado = (double) bytes / BYTES_A_GIBIBYTES;
        return df.format(Double.valueOf(resultado));
    }
}
