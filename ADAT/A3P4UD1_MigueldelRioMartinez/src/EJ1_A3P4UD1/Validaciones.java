package EJ1_A3P4UD1;

import java.io.*;

public class Validaciones {
    public static int comprobarDorsal(int dorsal) {
        try (RandomAccessFile archivo = new RandomAccessFile("corredores.dat", "r")) {
            archivo.seek(dorsal * 80);
            if (archivo.readInt() == dorsal) {
                return -1;
            }
        } catch (FileNotFoundException e) {

        } catch (EOFException e) {
            return 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}