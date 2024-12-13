package com.example.fragmentotelefonos;

import java.util.Objects;

// Clase Telefono: Representa un teléfono con funcionalidades de llamada y estado.
public class Telefono implements onTelefonoAction {
    private String telefono; // Número del teléfono
    private String numOrigen; // Número del que se recibe la llamada
    private String numDestino; // Número al que se realiza la llamada
    private boolean ocupado; // Indica si el teléfono está ocupado
    private Operadora operadora; // Operadora asociada al teléfono
    private onTelefonoListener listener; // Listener para gestionar eventos de la interfaz

    // Constructor: Inicializa el teléfono con su número y lo marca como libre.
    public Telefono(String telefono) {
        this.telefono = telefono;
        ocupado = false;
    }

    // Getter del número de teléfono
    public String getTelefono() {
        return telefono;
    }

    // Setter para asociar una operadora al teléfono
    public void setOperadora(Operadora operadora) {
        this.operadora = operadora;
    }

    // Devuelve si el teléfono está ocupado
    public boolean isOcupado() {
        return ocupado;
    }

    // Marca el teléfono como ocupado
    public void setOcupado() {
        this.ocupado = true;
    }

    // Marca el teléfono como libre
    public void setLibre() {
        this.ocupado = false;
    }

    // Asocia un listener para gestionar eventos
    public void setOnTelefonoListener(onTelefonoListener listener) {
        this.listener = listener;
    }

    // Método para recibir una llamada, marcando el teléfono como ocupado y notificando al listener
    public void recibirLlamada(String numOrigen) {
        ocupado = true;
        if (listener != null) {
            listener.recibirLlamada(numOrigen);
        }
        this.numOrigen = numOrigen;
    }

    // Método para colgar una llamada y notificar al listener
    public void recibirColgar() {
        if (listener != null) {
            listener.recibirColgada();
        }
    }

    // Llama a otro teléfono a través de la operadora
    @Override
    public void llamar(String telefonoDestino) {

        // Comprobamos que el número de destino no sea el mismo que el de origen
        // (no se puede llamar a uno mismo)
        if (telefonoDestino.equals(getTelefono())) {
            System.out.println("No se puede llamar al mismo número desde el cual iniciamos la llamada");
            return;
        }

        operadora.llamar(this, telefonoDestino);
    }

    // Modificación: Ahora libera el estado del teléfono al colgar
    @Override
    public void colgar() {
        if (numDestino != null) {
            operadora.colgar(numDestino); // Colgar el destino
            numDestino = null;
        }
        if (numOrigen != null) {
            operadora.colgar(numOrigen); // Colgar el origen
            numOrigen = null;
        }
        setLibre(); // Liberar este teléfono
        if (listener != null) {
            listener.recibirColgada();
        }
    }

    // Métodos equals y hashCode para comparar teléfonos por su número
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