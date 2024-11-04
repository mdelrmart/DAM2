package EJ4_A1UD1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class EJ4_A1UD1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("NumerosPositivos.txt");

        Scanner sc = new Scanner(file);

        ArrayList<Integer> numerosPositivos = new ArrayList<>();

        sc.useDelimiter(";");

        while (sc.hasNext()) {
            numerosPositivos.add(Integer.valueOf(sc.next()));
        }

        numerosPositivos.sort(Comparator.naturalOrder());

        sc.close();

        PrintStream printStream = new PrintStream(new FileOutputStream(file));

        /*
        *  for (Integer item : numerosPositivos)
        *{
        *    printStream.print(item + ";"); // Es lo mismo que usando programacion funcional.
        *}
        */

        String resultado = numerosPositivos.stream() // Integers
                .map(String::valueOf) // Strings
                .collect(Collectors.joining(";"));

        printStream.println(resultado);
    }

}