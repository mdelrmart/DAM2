package com.example.fragmentotelefonos;

// Interfaz para manejar eventos relacionados con las llamadas en un teléfono
public interface onTelefonoListener {
    /**
     * Maneja el evento de recepción de una llamada.
     * @param telefonoOrigen El número de teléfono del origen de la llamada como String.
     */
    public void recibirLlamada(String telefonoOrigen);

    /**
     * Maneja el evento de finalización de una llamada.
     */
    public void recibirColgada();
}