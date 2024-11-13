/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author DAM 2 
 */
public class Raza {
    private int codRaza;
    private String descripcion;

    public int getCodRaza() {
        return codRaza;
    }

    public void setCodRaza(int codRaza) {
        this.codRaza = codRaza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    public Raza(int codRaza, String descripcion) {
        this.codRaza = codRaza;
        this.descripcion = descripcion;
    }
    
}
