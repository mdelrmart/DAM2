package EJ1_A3P2UD1;

import java.io.*;

public class EJ1_A3P2UD1 {
    public static void main(String[] args) {
        File f = new File("salida.txt");
        if (f.exists()) {
            f.delete();
        }

        for (String i : args) {
            if (!i.endsWith(".txt")) {
                System.out.println("El archivo" + i + "no es un fichero de texto válido.");
            } else {
                procesarFichero(i);
            }
        }
    }

    public static void procesarFichero(String archivo) {
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo));
             BufferedWriter salida = new BufferedWriter(new FileWriter("salida.txt", true))) {

            int contadorLinea = 0;

            while (entrada.readLine() != null) {
                contadorLinea++;
            }

            String cad = (archivo + " " + contadorLinea);
            salida.write(cad + "\n");

            // Importante cerrar los Buffered si no usamos el try-catch con recursos.
            //salida.close();
            //entrada.close();

        } catch (IOException e) {
            try (BufferedWriter salida = new BufferedWriter(new FileWriter("salida.txt", true))) {
                //salida.write("No se ha encontrado el archivo " + archivo + "\n");
                salida.write("Error en el archivo " + archivo + ": " + e.getMessage() + "\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}