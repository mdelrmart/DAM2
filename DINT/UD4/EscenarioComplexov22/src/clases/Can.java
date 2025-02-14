/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author DAM IES Chan do Monte
 */
public class Can {
    private String chip;
    private String nome;
    private int codRaza;
    private String dniPropietario;    

    public Can(String chip, String nome, int codRaza, String dniPropietario) {
        this.chip = chip;
        this.nome = nome;
        this.codRaza = codRaza;
        this.dniPropietario = dniPropietario;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getNombre() {
        return nome;
    }

    public void setNombre(String nome) {
        this.nome = nome;
    }

    public int getCodRaza() {
        return codRaza;
    }

    public void setCodRaza(int codRaza) {
        this.codRaza = codRaza;
    }

    public String getDniPropietario() {
        return dniPropietario;
    }

    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }

    @Override
    public String toString() {
        return nome;
    }

    
}
