/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

/**
 *
 * @author DAM IES Chan do Monte
 */
public class XestorDeXanelas {
    
    static boolean iFrmXestionVacinacionsAberto=false;   
    static boolean iFrmImprimirFacturasAberto=false;  
    static boolean iFrmListadoPropietariosAberto=false;
    static boolean iFrmDatosPropietarioAberto=false;
    
    public static boolean podeseAbrirIFrmXestionVacinacions()
    {
        return !iFrmXestionVacinacionsAberto;
    }
    
    public static boolean podeseAbrirIFrmImprimirFacturas()
    {
        return !iFrmImprimirFacturasAberto;
    }
    
    public static void abrirIFrmXestionVacinacions()
    {
       iFrmXestionVacinacionsAberto=true; 
    }
    
    public static void pecharIFrmXestionVacinacions()
    {
       iFrmXestionVacinacionsAberto=false; 
    }        
    
    public static void abrirIFrmImprimirFacturas()
    {
       iFrmImprimirFacturasAberto=true; 
    }
    
    public static void pecharIFrmImprimirFacturas()
    {
       iFrmImprimirFacturasAberto=false; 
    } 
    
        public static boolean podeseAbrirIFrmListadoPropietarios() {
        return !iFrmListadoPropietariosAberto;
    }

    public static void abrirIFrmListadoPropietarios() {
        iFrmListadoPropietariosAberto = true;
    }
    
    public static void pecharIFrmListadoPropietariosAberto(){
       iFrmListadoPropietariosAberto=false; 
    } 
    
    
    public static boolean podeseAbrirIFrmDatosPropietario() {
        return !iFrmDatosPropietarioAberto;
    }
        
    public static void abrirIFrmDatosPropietario() {
        iFrmDatosPropietarioAberto = true;
    }
            
    public static void pecharFrmDatosPropietarioAberto(){
       iFrmDatosPropietarioAberto=false; 
    } 
}
