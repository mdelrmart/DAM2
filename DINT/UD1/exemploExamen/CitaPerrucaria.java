/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import utilidades.Datas;

/**
 *
 * @author DAM2
 */
public class CitaPerrucaria {
    private int codCita;
    private String codCan;
    private String data;
    private int hora;

    public CitaPerrucaria(String data, int hora) {
        this.data = Datas.DataFormatoMySQLYYYY_GUION_MM_GUION_DDAFormatoDia_BARRA_Mes_BARRA_Anho(data);
        this.hora = hora;
    }

    /**
     * @return the codCita
     */
    public int getCodCita() {
        return codCita;
    }

    public CitaPerrucaria(String codCan, String data, int hora) {
        this.codCan = codCan;
        this.data = data;
        this.hora = hora;
    }

    public CitaPerrucaria(int hora) {
        this.hora = hora;
    }

    /**
     * @param codCita the codCita to set
     */
    public void setCodCita(int codCita) {
        this.codCita = codCita;
    }

    /**
     * @return the codCan
     */
    public String getCodCan() {
        return codCan;
    }

    /**
     * @param codCan the codCan to set
     */
    public void setCodCan(String codCan) {
        this.codCan = codCan;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the hora
     */
    public int getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(int hora) {
        this.hora = hora;
    }
    
    public String getHora00() {
        return hora+".00h";
    }
    
    
}
