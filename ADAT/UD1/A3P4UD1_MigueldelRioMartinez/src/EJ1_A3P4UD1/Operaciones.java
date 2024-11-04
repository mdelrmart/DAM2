package EJ1_A3P4UD1;

import java.io.*;
import java.util.Scanner;

public class Operaciones {
    public static void modificarRegistro(int numDorsal, String nombre, float tiempo) {
        try (RandomAccessFile archivo = new RandomAccessFile("corredores.dat", "rw")) {
            archivo.seek(numDorsal * 80);
            archivo.writeInt(numDorsal);
            archivo.writeUTF(nombre);
            archivo.writeFloat(tiempo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarRegistro(int numDorsal) {
        try (RandomAccessFile archivo = new RandomAccessFile("corredores.dat", "rw")) {
            archivo.seek(numDorsal * 80);
            archivo.writeInt(0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

