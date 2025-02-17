package com.example.fragmentotelefonos;

import java.util.ArrayList;

public class Operadora {
    ArrayList<Telefono> telefonos;

    public Operadora(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    private boolean comprobarTelefono(Telefono telefono) {
     for (Telefono tel : telefonos) {
         if (tel.equals(telefono)) {
             return true;
         }
     }
     return false;
    }

    public void llamar(Telefono telefonoOrigen, String numTelefonoDestino) {
        Telefono telefonoDestino = new Telefono(numTelefonoDestino);

        if (comprobarTelefono(telefonoDestino)) {
            int indice = telefonos.indexOf(telefonoDestino);
            telefonoDestino = telefonos.get(indice);

            if (!telefonoDestino.isOcupado()) {
                System.out.println(telefonoOrigen + " llamando a " + telefonoDestino);
                telefonoOrigen.setOcupado();
                telefonoDestino.setOcupado();
                telefonoDestino.recibirLlamada(telefonoOrigen.getTelefono());
                telefonoOrigen.recibirLlamada(null);
            } else {
                System.out.println("El teléfono está ocupado");
            }
        } else {
            System.out.println("Llamando a telefono externo");
            telefonoOrigen.setOcupado();
            telefonoOrigen.recibirLlamada(null);
        }
    }

    public void colgar(String numTelefonoDestino) {
        Telefono telefonoDestino = new Telefono(numTelefonoDestino);
        int indice = telefonos.indexOf(telefonoDestino);
        if (indice != -1) {
            telefonoDestino = telefonos.get(indice);
            telefonoDestino.setLibre();
            telefonoDestino.recibirColgar();
            System.out.println("Colgando a " + numTelefonoDestino);
        }
    }
}
