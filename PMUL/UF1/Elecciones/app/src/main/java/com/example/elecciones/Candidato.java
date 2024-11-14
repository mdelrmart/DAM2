package com.example.elecciones;

public class Candidato {
    private int codCandidato;
    private String nombre;
    private int codPartido;

    public Candidato(int codCandidato, String nombre, int codPartido) {
        this.codCandidato = codCandidato;
        this.nombre = nombre;
        this.codPartido = codPartido;
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

    public Partido getPartido()
    {
        return PartidoDAO.select(codPartido);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
