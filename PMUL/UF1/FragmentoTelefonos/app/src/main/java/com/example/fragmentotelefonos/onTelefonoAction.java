package com.example.fragmentotelefonos;

// Interfaz que define las acciones principales de un teléfono
public interface onTelefonoAction {
    /**
     * Realiza una llamada al número de teléfono destino proporcionado.
     * @param telefonoDestino El número de teléfono de destino como String.
     */
    public void llamar(String telefonoDestino);

    /**
     * Finaliza una llamada activa.
     */
    public void colgar();
}