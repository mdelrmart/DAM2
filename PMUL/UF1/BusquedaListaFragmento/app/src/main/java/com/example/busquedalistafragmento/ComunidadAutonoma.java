package com.example.busquedalistafragmento;

public class ComunidadAutonoma {
    private int codComunidad;
    private String nombre;

    public ComunidadAutonoma(int codComunidad, String nombre) {
        this.codComunidad = codComunidad;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodComunidad() {
        return codComunidad;
    }

    public void setCodComunidad(int codComunidad) {
        this.codComunidad = codComunidad;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
