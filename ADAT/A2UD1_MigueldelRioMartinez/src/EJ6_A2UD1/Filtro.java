package EJ6_A2UD1;

import java.io.File;
import java.io.FilenameFilter;

public class Filtro implements FilenameFilter {
    String subcadena;

    Filtro (String subcadena) {
        this.subcadena = subcadena;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.contains(subcadena);
    }
}
