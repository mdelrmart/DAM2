/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JCheckBox;

/**
 *
 * @author usuario
 */
public class Vacinacion {
    String nome;
    boolean tetano;
    boolean hepatite;
    boolean sarampelo;

    public Vacinacion(String nome) {
        this.nome = nome;
    } 

    public void setTetano(boolean tetano) {
        this.tetano = tetano;
    }

    public void setHepatite(boolean hepatite) {
        this.hepatite = hepatite;
    }

    public void setSarampelo(boolean sarampelo) {
        this.sarampelo = sarampelo;
    }
}
