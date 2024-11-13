package com.example.mantenimientoclientes;

public class ClienteEnLista {
    int codCliente;
    String nombre;
    String apellidos;
    boolean vip;

    public ClienteEnLista(int codCliente, String nombre, String apellidos, boolean vip) {
        this.codCliente = codCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.vip = vip;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public String toString() {
        return apellidos + ", " +  nombre;
    }
}
