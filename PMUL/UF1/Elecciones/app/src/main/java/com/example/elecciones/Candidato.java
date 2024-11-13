package com.example.elecciones;

public class Candidato {
    private int codCandidato;
    private String nombre;

    public Candidato(int codCandidato, String nombre) {
        this.codCandidato = codCandidato;
        this.nombre = nombre;
    }

    public int getCodCandidato() {
        return codCandidato;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
