package com.example.fragmentotelefonos;

import java.util.ArrayList;

// Clase Operadora: Gestiona la conexión entre diferentes teléfonos
public class Operadora {
    ArrayList<Telefono> telefonos; // Lista de teléfonos gestionados por la operadora

    // Constructor: Recibe la lista de teléfonos
    public Operadora(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    // Comprueba si un teléfono existe en la lista
    private boolean comprobarTelefono(Telefono telefono) {
        for (Telefono tel : telefonos) {
            if (tel.equals(telefono)) {
                return true;
            }
        }
        return false;
    }

    // Método para realizar una llamada entre teléfonos
    public void llamar(Telefono telefonoOrigen, String numTelefonoDestino) {
        Telefono telefonoDestino = new Telefono(numTelefonoDestino);

        if (comprobarTelefono(telefonoDestino)) {
            int indice = telefonos.indexOf(telefonoDestino);
            telefonoDestino = telefonos.get(indice);

            if (!telefonoDestino.isOcupado()) {
                System.out.println(telefonoOrigen + " llamando a " + telefonoDestino);

                telefonoOrigen.setOcupado();
                telefonoDestino.setOcupado();

                telefonoOrigen.recibirLlamada(null);
                telefonoDestino.recibirLlamada(telefonoOrigen.getTelefono());
            } else {
                System.out.println("El teléfono está ocupado");
            }
        } else {
            System.out.println("Llamando a telefono externo");
            telefonoOrigen.setOcupado();
            telefonoOrigen.recibirLlamada(null);
        }
    }

    // Método para colgar una llamada
    public void colgar(String numTelefonoDestino) {
        Telefono telefonoDestino = new Telefono(numTelefonoDestino);
        int indice = telefonos.indexOf(telefonoDestino);
        if (indice != -1) {
            telefonoDestino = telefonos.get(indice);
            telefonoDestino.setLibre(); // Marca el teléfono como libre
            telefonoDestino.recibirColgar(); // Notifica al listener
            System.out.println("Colgando a " + numTelefonoDestino);
        }
    }
}
