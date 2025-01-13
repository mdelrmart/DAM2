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
public class Operacion {
    int codOperacion;
    private String codCan;
    private String descripcion;

    public Operacion(String descripcion, String data) {
        this.descripcion = descripcion;
        this.data = Datas.DataFormatoMySQLYYYY_GUION_MM_GUION_DDAFormatoDia_BARRA_Mes_BARRA_Anho(data);
    }

    public Operacion(String codCan, String descripcion, String data, boolean anestesia, boolean raios, boolean sangue, boolean scaner) {
        this.codCan = codCan;
        this.descripcion = descripcion;
        this.data = data;
        this.anestesia = anestesia;
        this.raios = raios;
        this.sangue = sangue;
        this.scaner = scaner;
    }

    public Operacion(String descripcion, String data, int anestesia, int raios, int sangue, int scaner) {
        this.descripcion = descripcion;
        this.data = data;
        this.anestesia = anestesia!= 0;
        this.raios = raios!=0;
        this.sangue = sangue!=0;
        this.scaner = scaner!=0;
    }
    private String data;    

    public String getCodCan() {
        return codCan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getData() {
        return data;
    }

    public boolean isAnestesia() {
        return anestesia;
    }

    public boolean isRaios() {
        return raios;
    }

    public boolean isSangue() {
        return sangue;
    }

    public boolean isScaner() {
        return scaner;
    }
    private boolean anestesia;
    private boolean raios;
    private boolean sangue;

    @Override
    public String toString() {
        return "Operacion{" + "codOperacion=" + codOperacion + ", codCan=" + codCan + ", descripcion=" + descripcion + ", data=" + data + ", anestesia=" + anestesia + ", raios=" + raios + ", sangue=" + sangue + ", scaner=" + scaner + '}';
    }
    private boolean scaner;
    
}
