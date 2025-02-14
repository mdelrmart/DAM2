/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.yourorg.desplegablebuscador;

/**
 *
 * @author usuario
 */
public class Propietario {

    private String dni;
    private String nome;
    private String ap1;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nombre) {
        this.nome = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return nome + " " + ap1 + " " + ap2;
    }

    public Propietario(String dni, String nome, String ap1, String ap2, String tlf, String eMail) {
        this.dni = dni;
        this.nome = nome;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.tlf = tlf;
        this.eMail = eMail;
    }
    private String ap2;
    private String tlf;
    private String eMail;
}
