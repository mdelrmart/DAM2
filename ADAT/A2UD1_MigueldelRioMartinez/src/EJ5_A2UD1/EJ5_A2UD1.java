package EJ5_A2UD1;

import java.io.File;
import java.io.FileFilter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class EJ5_A2UD1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el directorio:");
        String entrada = sc.nextLine();

        System.out.println("Introduce la extensión a buscar:");
        String extension = sc.nextLine();

        System.out.println("--- LISTANDO EL DIRECTORIO " + entrada);

        try {
            comprobarDirectorio(entrada);
            listarDirectorio(entrada, extension);
        } catch (ExcepcionNoExiste | ExcepcionNoEsDirectorio e) {
            System.err.println(e.getMessage());
        }
    }

    public static void comprobarDirectorio(String entrada) throws ExcepcionNoExiste, ExcepcionNoEsDirectorio {
        File input = new File(entrada);

        if (!input.exists()) throw new ExcepcionNoExiste("La ruta especificada no existe.");
        if (!input.isDirectory()) throw new ExcepcionNoEsDirectorio("La ruta no es un directorio.");
    }

    public static void listarDirectorio(String entrada, String extension) {
        File dir = new File(entrada);

        File[] files = dir.listFiles(new Filtro(extension));

        if(files == null) {
            return;
        }

        Arrays.sort(files, (f1, f2) -> f2.getName().compareToIgnoreCase(f1.getName()));

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (File f : files) {
            Date ultimaModificacion = new Date(f.lastModified());

//          if (f.isDirectory()) {
//              System.out.println("-|" + f.getName() + " <DIR> " + convertirBytesAKibibytes(f.length()) + " Kbytes " + dateFormat.format(ultimaModificacion));
//          }

            if (f.isFile()) {
                //if (f.getName().endsWith(extension)) {
                    System.out.println("-|" + f.getName() + " <FICHERO> " + convertirBytesAKibibytes(f.length()) + " Kbytes " + dateFormat.format(ultimaModificacion));

                //}
            }
        }
    }

    public static String convertirBytesAKibibytes(long bytes) {
        long BYTES_A_KIBIBYTES = 1024;
        NumberFormat formatoLegible = NumberFormat.getInstance(Locale.ENGLISH);
        double resultado = (double) bytes / BYTES_A_KIBIBYTES;
        return formatoLegible.format(resultado);
    }
}
