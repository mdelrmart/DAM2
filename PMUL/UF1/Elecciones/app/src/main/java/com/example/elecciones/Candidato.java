package com.example.elecciones;

public class Candidato {
    private int codCandidato;
    private String nombre;
    private int codPartido;
    private int votos;

    public Candidato(int codCandidato, String nombre, int codPartido, int votos) {
        this.codCandidato = codCandidato;
        this.nombre = nombre;
        this.codPartido = codPartido;
        this.votos = votos;
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

    public Partido getPartido() {
        return PartidoDAO.select(codPartido);
    }

    public int getVotos() {
        return votos;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
