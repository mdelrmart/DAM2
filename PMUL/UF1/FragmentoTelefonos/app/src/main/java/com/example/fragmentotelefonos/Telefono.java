package com.example.fragmentotelefonos;

import java.util.Objects;

public class Telefono implements onTelefonoAction {
    private String telefono;
    private String numOrigen;
    private String numDestino;
    private boolean ocupado;
    private Operadora operadora;
    private onTelefonoListener listener;

    public Telefono(String telefono) {
        this.telefono = telefono;
        ocupado = false;
    }

    public String getTelefono() {
        return telefono;
    }


    public void setOperadora(Operadora operadora) {
        this.operadora = operadora;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado() {
        this.ocupado = true;
    }

    public void setLibre() {
        this.ocupado = false;
    }

    public void setOnTelefonoListener(onTelefonoListener listener) {
        this.listener = listener;
    }

    public void recibirLlamada(String numOrigen) {
        ocupado = true;

        if (listener != null) {
            listener.recibirLlamada(numOrigen);
        }

        this.numOrigen = numOrigen;
    }

    public void recibirColgar() {
        if (listener != null) {
            listener.recibirColgada();
        }

    }


    @Override
    public void llamar(String telefonoDestino) {
        operadora.llamar(this, telefonoDestino);
    }

    @Override
    public void colgar() {
        if (numDestino != null) {
            operadora.colgar(numDestino);
            numDestino = null;
            listener.recibirColgada();

        } else if (numOrigen != null) {
            operadora.colgar(numOrigen);
            numOrigen = null;
            listener.recibirColgada();

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefono telefono1 = (Telefono) o;
        return Objects.equals(telefono, telefono1.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(telefono);
    }
}