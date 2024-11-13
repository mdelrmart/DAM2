/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package traballadores;

/**
 *
 * @author Miguel del Río
 */
public class Traballador {

    private final String dni;
    private final String nome;
    private final String apelido1;
    private final String apelido2;
    private final String provincia;
    private final String profesion;

    public Traballador(String dni, String nome, String apelido1, String apelido2, String provincia, String profesion) {
        this.dni = dni;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.provincia = provincia;
        this.profesion = profesion;
    }

    public String getDni() {
        return dni;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido1() {
        return apelido1;
    }

    public String getApelido2() {
        return apelido2;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getProfesion() {
        return profesion;
    }

}
