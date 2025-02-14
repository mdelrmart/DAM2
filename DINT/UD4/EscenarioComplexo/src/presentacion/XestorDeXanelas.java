/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

/**
 *
 * @author DAM 2
 */
public class XestorDeXanelas {

    static boolean iFrmXestionVacinacionsAberto = false;
    static boolean iFrmAltaPerrucariaAberto = false;
    static boolean iFrmBaixaListadoPerrucariaAberto = false;
    static boolean iFrmAltaOperacionsAberto = false;
    static boolean iFrmBaixaListadoOperacionsAberto = false;
    static boolean iFrmImprimirFacturasAberto = false;
    static boolean iFrmNovaCitaPerrucariaAberto = false;
    
    //
    static boolean iFrmEdicionPropietariosAberto = false;

    public static boolean podeseAbrirEdicionPropietarios() {
        if (iFrmEdicionPropietariosAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static void abrirIFrmEdicionPropietarios() {
        iFrmEdicionPropietariosAberto = true;
    }

    public static void pecharIFrmEdicionPropietarios() {
        iFrmEdicionPropietariosAberto = false;
    }
    //

    public static boolean podeseAbrirIFrmBaixaListadoPerrucaria() {
        if (iFrmBaixaListadoPerrucariaAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean podeseAbrirIFrmBaixaListadoOperacions() {
        if (iFrmBaixaListadoOperacionsAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean podeseAbrirIFrmAltaOperacions() {
        if (iFrmAltaOperacionsAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean podeseAbrirIFrmXestionVacinacions() {
        if (iFrmXestionVacinacionsAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean podeseAbrirIFrmAltaPerrucaria() {
        if (iFrmAltaPerrucariaAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean podeseAbrirIFrmNovaCitaPerrucaria() {
        if (iFrmNovaCitaPerrucariaAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static void abrirIFrmNovaCitaPerrucaria() {
        iFrmNovaCitaPerrucariaAberto = true;
    }

    public static void pecharIFrmNovaCitaPerrucaria() {
        iFrmNovaCitaPerrucariaAberto = false;
    }

    public static void abrirIFrmXestionVacinacions() {
        iFrmXestionVacinacionsAberto = true;
    }

    public static void pecharIFrmXestionVacinacions() {
        iFrmXestionVacinacionsAberto = false;
    }

    public static void abrirIFrmAltaOperacions() {
        iFrmAltaOperacionsAberto = true;
    }

    public static void abrirIFrmAltaPerrucaria() {
        iFrmAltaPerrucariaAberto = true;
    }

    public static void abrirIFrmBaixaListadoPerrucaria() {
        iFrmBaixaListadoPerrucariaAberto = true;
    }

    public static void abrirIFrmBaixaListadoOperacions() {
        iFrmBaixaListadoOperacionsAberto = true;
    }

    public static void pecharIFrmAltaOperacions() {
        iFrmAltaOperacionsAberto = false;
    }

    public static void pecharIFrmAltaPerrucaria() {
        iFrmAltaPerrucariaAberto = false;
    }

    public static void pecharIFrmBaixaListadoPerrucaria() {
        iFrmBaixaListadoPerrucariaAberto = false;
    }

    public static void pecharIFrmBaixaListadoOperacions() {
        iFrmBaixaListadoOperacionsAberto = false;
    }

    public static boolean podeseAbrirIFrmImprimirFacturas() {
        if (iFrmImprimirFacturasAberto) {
            return false;
        } else {
            return true;
        }
    }

    public static void abrirIFrmImprimirFacturas() {
        iFrmImprimirFacturasAberto = true;
    }

    public static void pecharIFrmImprimirFacturas() {
        iFrmImprimirFacturasAberto = false;
    }

}
