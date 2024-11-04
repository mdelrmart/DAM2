package EJ2_A3P2UD1;

import java.io.*;

public class EJ2_A3P2UD1 {
    public static void main(String[] args) {
        procesarFichero();
    }

    public static void procesarFichero() {
        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("ficherolog.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("/");

                if (datos.length != 3) {
                    bw.write(linea + " -----> no tiene el formato CURSO/NUMERO/ALUMNO. NO SE PUEDE CREAR EL DIRECTORIO" + "\n");
                    continue;
                }

                File f = new File(datos[0] + "/" + datos[1] + "-" + datos[2]);

                if (!f.exists()) {
                    f.mkdirs();
                    bw.write(linea + " -----> se creo correctamente el directorio" + "\n");
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
