/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author DAM 2 
 */
public class Vacina {
    private int codVacina;

    public Vacina(int codVacina, String nomeVacina, int numTotalDoses) {
        this.codVacina = codVacina;
        this.nomeVacina = nomeVacina;
        this.numTotalDoses = numTotalDoses;
    }
    private String nomeVacina;

    @Override
    public String toString() {
        return getNomeVacina();
    }
    private int numTotalDoses;

    /**
     * @return the codVacina
     */
    public int getCodVacina() {
        return codVacina;
    }

    /**
     * @param codVacina the codVacina to set
     */
    public void setCodVacina(int codVacina) {
        this.codVacina = codVacina;
    }

    /**
     * @return the nomeVacina
     */
    public String getNomeVacina() {
        return nomeVacina;
    }

    /**
     * @param nomeVacina the nomeVacina to set
     */
    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    /**
     * @return the numTotalDoses
     */
    public int getNumTotalDoses() {
        return numTotalDoses;
    }

    /**
     * @param numTotalDoses the numTotalDoses to set
     */
    public void setNumTotalDoses(int numTotalDoses) {
        this.numTotalDoses = numTotalDoses;
    }
    
}
