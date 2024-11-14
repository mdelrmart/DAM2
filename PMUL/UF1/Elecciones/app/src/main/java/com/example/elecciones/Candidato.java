package com.example.elecciones;

public class Candidato {
    private int codCandidato;
    private String nombre;
    private int codPartido;
    private int logoPartido;
    private int colorPartido;
    private String nombrePartido;

    public Candidato(int codCandidato, String nombre, int codPartido, int logoPartido, int colorPartido, String nombrePartido) {
        this.codCandidato = codCandidato;
        this.nombre = nombre;
        this.codPartido = codPartido;
        this.logoPartido = logoPartido;
        this.colorPartido = colorPartido;
        this.nombrePartido = nombrePartido;
    }

    public int getCodCandidato() {
        return codCandidato;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodPartido() {
        return codPartido;
    }

    public int getLogoPartido() {
        return logoPartido;
    }

    public int getColorPartido() {
        return colorPartido;
    }

    public String getNombrePartido() { return nombrePartido; }

    @Override
    public String toString() {
        return nombre;
    }
}
