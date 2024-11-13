/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 *
 * @author DAM 2 
 */
public class Datas {
    public static boolean isData_Dia_BARRA_Mes_BARRA_Anho_Valida(String data)
    {
        if(data==null || data.length()!=10 || data.charAt(2)!='/' ||data.charAt(5)!='/') return false;
        
        int dia=new Integer(data.substring(0, 2)).intValue();
        int mes=new Integer(data.substring(3, 5)).intValue();
        int anho=new Integer(data.substring(6)).intValue();
        
        switch(mes)
        {
            case    1:
            case    3:   
            case    5:
            case    7:
            case    8:
            case    10:
            case    12: //31 dias
                        if(dia<1 || dia >31) return false;
                        break;
            case    4:
            case    6:
            case    9:
            case    11: //30 dias
                        if(dia<1 || dia >30) return false;
                        break;
            case    2:  //febrero
                        GregorianCalendar calendario=new GregorianCalendar();
                        if(calendario.isLeapYear(anho))
                        {
                            if(dia<1 || dia >29) return false;
                        }
                        else
                        {
                            if(dia<1 || dia >28) return false;                            
                        }
                        break;
            default:    return false;
                

        }
        
        return true;
        
    }
    
    public static String Data_Dia_BARRA_Mes_BARRA_AnhoAFormatoMySQLYYYY_GUION_MM_GUION_DD(String data)
    {
        String dia=data.substring(0, 2);
        String mes=data.substring(3, 5);
        String anho=data.substring(6);
        return anho+"-"+mes+"-"+dia;
    }
    public static String DataFormatoMySQLYYYY_GUION_MM_GUION_DDAFormatoDia_BARRA_Mes_BARRA_Anho(String data)
    {
        String dia=data.substring(8);
        String mes=data.substring(5, 7);
        String anho=data.substring(0, 4);
        return dia+"/"+mes+"/"+anho;
    } 
    public static int compararDatasEnFormatoDia_BARRA_Mes_BARRA_Anho(String dataInicial, String dataFinal)
    {
        int dia1=new Integer(dataInicial.substring(0, 2)).intValue();
        int mes1=new Integer(dataInicial.substring(3, 5)).intValue();
        int anho1=new Integer(dataInicial.substring(6)).intValue();        
        int dia2=new Integer(dataFinal.substring(0, 2)).intValue();
        int mes2=new Integer(dataFinal.substring(3, 5)).intValue();
        int anho2=new Integer(dataFinal.substring(6)).intValue();   
        GregorianCalendar gcData1=new GregorianCalendar(anho1, mes1, dia1);
        GregorianCalendar gcData2=new GregorianCalendar(anho2, mes2, dia2);
        if(gcData1.before(gcData2))
        {
            return 1;
        }
        else
        {
            if(gcData2.before(gcData1))
            {
                return 3;
            }
            else
            {
                return 2;
            }
        }
        
    }
}
