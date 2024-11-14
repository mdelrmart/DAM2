package com.example.elecciones;

public class Candidato {
    private int codCandidato;
    private String nombre;
    private int codPartido;
    private int logoPartido;

    public Candidato(int codCandidato, String nombre, int codPartido, int logoPartido) {
        this.codCandidato = codCandidato;
        this.nombre = nombre;
        this.codPartido = codPartido;
        this.logoPartido = logoPartido;
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

    @Override
    public String toString() {
        return nombre;
    }
}
