package Clases;

import java.util.Date;

public class Empleado {
    String NSS;
    String nombre;
    String apellido1;
    String apellido2;
    int numDepartamentoPertenece;

    public Empleado(String NSS, String nombre, String apellido1, String apellido2, int numDepartamentoPertenece) {
        this.NSS = NSS;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }
}
