/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import utilidades.Datas;

/**
 *
 * @author DAM 2 
 */
public class VacinacionHistorial {
    String data;
    String vacina;
    int dose;

    public VacinacionHistorial(String data, String vacina, int dose) {
        this.data = Datas.DataFormatoMySQLYYYY_GUION_MM_GUION_DDAFormatoDia_BARRA_Mes_BARRA_Anho(data);
        this.vacina = vacina;
        this.dose = dose;
    }

    public String getData() {
        return data;
    }

    public String getVacina() {
        return vacina;
    }

    public int getDose() {
        return dose;
    }
    
    

    
    
}
