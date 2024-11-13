/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author DAM 2 
 */
public class ListadoOperacion {
    int codOperacion;
    String propietario;
    String can;
    String data;
    String descripcion;
 
    public ListadoOperacion(int codOperacion, String nombrePropietario, String ap1Propietario, String ap2Propietario, 
            String can, String data, String descripcion) {
        this.codOperacion=codOperacion;
        this.propietario = nombrePropietario+ " "+ ap1Propietario+" "+ap2Propietario;
        this.can = can;
        this.data = utilidades.Datas.DataFormatoMySQLYYYY_GUION_MM_GUION_DDAFormatoDia_BARRA_Mes_BARRA_Anho(data);
        this.descripcion = descripcion;
    }    

    public int getCodOperacion() {
        return codOperacion;
    }

    @Override
    public String toString() {
        return propietario;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getCan() {
        return can;
    }

    public String getData() {
        return data;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
    
}
