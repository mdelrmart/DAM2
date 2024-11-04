package EJ3_A3P1UD1;

import java.io.*;

public class EJ3_A3P1UD1 {
    public static void main(String[] args) {
        File f1 = new File("NUM1.dat");
        File f2 = new File("NUM2.dat");
        File output = new File("Mezcla.dat");

        generarFicheroOrdenado(f1);
        generarFicheroOrdenado(f2);

        juntarArchivos(f1, f2, output);
    }

    private static void generarFicheroOrdenado(File f)
    {
        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(f)))
        {
            for (int i = 0; i < 100;) {
                if (Math.random() < 0.33)
                {
                    i++;
                    continue;
                }
                if (Math.random() < 0.40) i++;

                writer.writeInt(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el fichero.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }

    private static void juntarArchivos(File f1, File f2, File output) {
        try (DataInputStream reader1 = new DataInputStream(new FileInputStream(f1));
             DataInputStream reader2 = new DataInputStream(new FileInputStream(f2));
             DataOutputStream writer = new DataOutputStream(new FileOutputStream(output)))
        {
            int num1 = reader1.readInt();
            int num2 = reader2.readInt();

            while (true)
            {
                if (num1 < num2)
                {
                    writer.writeInt(num1);
                    System.out.println(num1);
                    num1 = reader1.readInt();
                }
                else
                {
                    writer.writeInt(num2);
                    System.out.println(num2);
                    num2 = reader2.readInt();
                }
            }

        } catch (EOFException ignored)
        {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
