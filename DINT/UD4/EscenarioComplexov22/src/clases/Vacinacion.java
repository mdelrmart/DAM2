/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author DAM IES Chan do Monte
 */
public class Vacinacion {
    private int codVacinacion;
    private String chip;
    private int codVacina;
    private String data;
    private String observacions;

    public int getCodVacinacion() {
        return codVacinacion;
    }

    public void setCodVacinacion(int codVacinacion) {
        this.codVacinacion = codVacinacion;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public int getCodVacina() {
        return codVacina;
    }

    public void setCodVacina(int codVacina) {
        this.codVacina = codVacina;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public Vacinacion(int codVacinacion, String chip, int codVacina, String data, String observacions, int dose) {
        this.codVacinacion = codVacinacion;
        this.chip = chip;
        this.codVacina = codVacina;
        this.data = data;
        this.observacions = observacions;
        this.dose = dose;
    }

    @Override
    public String toString() {
        return observacions;
    }
    private int dose;
}
