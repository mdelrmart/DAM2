package EJ6_A2UD1;

import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class EJ6_A2UD1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //String nivel = "";

        System.out.println("Introduce el directorio:");
        String entrada = sc.nextLine();

        System.out.println("Introduce la subcadena a buscar:");
        String subcadena = sc.nextLine();

        System.out.println("--- LISTANDO EL DIRECTORIO " + entrada);

        try {
            comprobarDirectorio(entrada);
            listarDirectorio(entrada, subcadena, "");
        } catch (EJ4_A2UD1.ExcepcionNoExiste | EJ4_A2UD1.ExcepcionNoEsDirectorio e) {
            System.err.println(e.getMessage());
        }
    }

    public static void comprobarDirectorio(String entrada) throws EJ4_A2UD1.ExcepcionNoExiste, EJ4_A2UD1.ExcepcionNoEsDirectorio {
        File input = new File(entrada);

        if (!input.exists()) throw new ExcepcionNoExiste("La ruta especificada no existe.");
        if (!input.isDirectory()) throw new ExcepcionNoEsDirectorio("La ruta no es un directorio.");
    }

    public static void listarDirectorio(String entrada, String subcadena, String nivel) {
        File dir = new File(entrada);

        File[] files = dir.listFiles(new Filtro(subcadena));

        if (files == null) {
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (File f : files) {
            Date ultimaModificacion = new Date(f.lastModified());

            if (f.isDirectory()) {
                System.out.println(nivel + "-|" + f.getName() + " <DIR> " + convertirBytesAKibibytes(f.length()) + " Kbytes " + dateFormat.format(ultimaModificacion));
                listarDirectorio(f.getAbsolutePath(), subcadena ,nivel + "---");
            }

            if (f.isFile()) {
                //if (f.getName().contains(subcadena)) {
                    System.out.println(nivel + "-|" + f.getName() + " <FICHERO> " + convertirBytesAKibibytes(f.length()) + " Kbytes " + dateFormat.format(ultimaModificacion));
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