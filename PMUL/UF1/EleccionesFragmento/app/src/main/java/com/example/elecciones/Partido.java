package com.example.elecciones;

public class Partido {
    int codPartido;
    String nombrePartido;
    int colorPartido;

    // Array de IDs de los logos en drawable, cada posición debe corresponder con el código del partido
    private final int[] logosPartidos = {
            R.drawable.pp, // codPartido = 1
            R.drawable.psoe, // codPartido = 2
            R.drawable.sumar, // codPartido = 3
            R.drawable.vox // codPartido = 4
    };

    public Partido(int codPartido, String nombrePartido, int colorPartido) {
        this.codPartido = codPartido;
        this.nombrePartido = nombrePartido;
        this.colorPartido = colorPartido;
    }

    public int getCodPartido() {
        return codPartido;
    }

    public int getLogoPartido() {
        return logosPartidos[codPartido-1];
    }

    public String getNombrePartido() {
        return nombrePartido;
    }

    public int getColorPartido() {
        return colorPartido;
    }
}
