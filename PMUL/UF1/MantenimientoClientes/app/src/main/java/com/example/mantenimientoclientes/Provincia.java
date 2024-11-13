package com.example.mantenimientoclientes;

public class Provincia {
    private int codProvincia;
    private String nombre;

    public Provincia(int id, String nombre) {
        this.codProvincia = id;
        this.nombre = nombre;
    }

    public int getCodProvincia() {
        return codProvincia;
    }

    public String getNombreProvincia() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre != "" ? nombre : "Selecciona una provincia";
    }
}
