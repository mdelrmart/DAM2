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
    
    public static boolean podeseAbrirIFrmXestionVacinacions()
    {
        if(iFrmXestionVacinacionsAberto)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static void abrirIFrmXestionVacinacions()
    {
       iFrmXestionVacinacionsAberto=true; 
    }
    
    public static void pecharIFrmXestionVacinacions()
    {
       iFrmXestionVacinacionsAberto=false; 
    }        
}
