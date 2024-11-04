package EJ4_A3P1UD1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;

public class EJ4_A3P1UD1 {
    public static void main(String[] args) {
        leerFichero();
    }

    public static void leerFichero() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("Mezcla.dat"))) {

            int numeroPrevio = dis.readInt();
            int contador = 1;

            while (true) {
                int numeroActual = dis.readInt();

                if (numeroActual != numeroPrevio) {
                    System.out.println("El número " + numeroPrevio + " se repite " + contador + " veces");
                    contador = 1;
                    numeroPrevio = numeroActual;
                }
                else {
                    contador++;
                }
            }
        } catch (EOFException e) {
            System.out.println("Final del fichero alcanzado.");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
