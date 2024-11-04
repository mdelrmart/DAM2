package EJ1_A3P3UD1;

import java.io.*;

public class Validaciones {
    public static int comprobarDorsal(int dorsal) {
        try (FileInputStream archivoEntrada = new FileInputStream("corredores.dat");
             ObjectInputStream objectoEntrada = new ObjectInputStream(archivoEntrada)) {

            while (true) {
                try {
                    Corredor c = (Corredor) objectoEntrada.readObject();
                    if (c.getDorsal() == dorsal) {
                        return 1;
                    }
                }
                catch (EOFException e) {
                    // Fin del fichero.
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            //System.out.println("El archivo no existe todavía");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static int comprobarExistenciaFichero() {
        File f = new File("corredores.dat");

        if (!f.exists()) {
            System.out.println("El archivo no existe. No se puede continuar");
            return 1;
        }
        return 0;
    }
}
