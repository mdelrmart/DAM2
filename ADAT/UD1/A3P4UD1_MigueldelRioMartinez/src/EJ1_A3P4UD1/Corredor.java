package EJ1_A3P4UD1;

import java.io.Serializable;

public class Corredor implements Serializable {
    int dorsal;
    String nombre;
    float tiempo;

    public Corredor(int dorsal, String nombre, float tiempo) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Dorsal: " + dorsal + "\n" +
                "Tiempo: " + tiempo + "\n\n";
    }
}

