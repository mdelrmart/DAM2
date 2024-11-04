package EJ1_A3P3UD1;

import java.io.*;

public class Operaciones {
    public static void modificarRegistro(int numDorsal, String nombre, float tiempo) {
        // Creamos nuevo fichero donde ponemos todos los registros que ya tenemos excepto el que
        // vamos a modificar.

        File f = new File("corredores_2.dat");
        File f2 = new File("corredores.dat");

        try (FileInputStream archivoEntrada = new FileInputStream("corredores.dat");
             ObjectInputStream objectoEntrada = new ObjectInputStream(archivoEntrada)) {
            while (true) {
                try {
                    Corredor c = (Corredor) objectoEntrada.readObject();

                    if (numDorsal != c.getDorsal()) {
                        try (FileOutputStream archivoSalida = new FileOutputStream("corredores_2.dat", true);
                             ObjectOutputStream objectoSalida =
                                     f.exists() && f.length() > 0 ? new MiObjectOutputStream(archivoSalida) : new ObjectOutputStream(archivoSalida)) {

                            objectoSalida.writeObject(c);

                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (EOFException e) {
                    // Fin del fichero.
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Añadimos el nuevo registro modificado al final del nuevo archivo.
        Corredor c = new Corredor(numDorsal, nombre, tiempo);

        try (FileOutputStream archivoSalida = new FileOutputStream("corredores_2.dat", true);
             ObjectOutputStream objectoSalida = new MiObjectOutputStream(archivoSalida)) {

            objectoSalida.writeObject(c);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        f2.delete();
        f.renameTo(new File("corredores.dat"));
    }

    public static void eliminarRegistro(int numDorsal) {
        // Creamos nuevo fichero donde ponemos todos los registros que ya tenemos excepto el que
        // vamos a borrar.

        File f = new File("corredores_2.dat");
        File f2 = new File("corredores.dat");

        try (FileInputStream archivoEntrada = new FileInputStream("corredores.dat");
             ObjectInputStream objectoEntrada = new ObjectInputStream(archivoEntrada)) {
            while (true) {
                try {
                    Corredor c = (Corredor) objectoEntrada.readObject();

                    if (numDorsal != c.getDorsal()) {
                        try (FileOutputStream archivoSalida = new FileOutputStream("corredores_2.dat", true);
                             ObjectOutputStream objectoSalida =
                                     f.exists() && f.length() > 0 ? new MiObjectOutputStream(archivoSalida) : new ObjectOutputStream(archivoSalida)) {

                            objectoSalida.writeObject(c);

                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (EOFException e) {
                    // Fin del fichero.
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        f2.delete();
        f.renameTo(new File("corredores.dat"));
    }
}
