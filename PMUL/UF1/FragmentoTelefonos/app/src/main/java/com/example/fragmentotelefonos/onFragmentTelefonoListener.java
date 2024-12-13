package com.example.fragmentotelefonos;

// Interfaz para obtener un objeto Telefono a partir de su número
public interface onFragmentTelefonoListener {
    /**
     * Devuelve el objeto Telefono asociado a un número dado.
     * @param telefono El número de teléfono como String.
     * @return El objeto Telefono correspondiente.
     */
    Telefono obtenerTelefono(String telefono);
}
