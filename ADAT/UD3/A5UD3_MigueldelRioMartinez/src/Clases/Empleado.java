package Clases;

import java.util.Date;

public class Empleado {
    String NSS;
    String nombre;
    String apellido1;
    String apellido2;
    String calle;
    int numeroCalle;
    String piso;
    String codPostal;
    Date fechaNacimiento;
    float salario;
    char sexo;
    String nssSupervisa;
    int numDepartamentoPertenece;

    public Empleado(String NSS, String nombre, String apellido1, String apellido2, String calle, int numeroCalle, String piso, String codPostal, Date fechaNacimiento, float salario, char sexo, String nssSupervisa, int numDepartamentoPertenece) {
        this.NSS = NSS;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.piso = piso;
        this.codPostal = codPostal;
        this.fechaNacimiento = fechaNacimiento;
        this.salario = salario;
        this.sexo = sexo;
        this.nssSupervisa = nssSupervisa;
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }

    public Empleado(String NSS, String nombre, String apellido1, String apellido2, int numDepartamentoPertenece) {
        this.NSS = NSS;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }
}
