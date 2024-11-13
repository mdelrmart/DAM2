/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multixanela;

import java.util.Vector;

/**
 *
 * @author usuario
 */
public class XestorDeXanelas {

    static private int numXanelasDatosPersoais = 0;
    static private int numXanelasDatosAcademicos = 0;

    static private Vector xanelasDatosPersoais = new Vector();

    public static void cerrarXanelasDatosPersoais() {
        numXanelasDatosPersoais--;
        //System.out.println("Número de ventanas de datos persoais abiertas: " + numXanelasDatosAcademicos);
    }

    public static boolean abrirXanelasDatosPersoais() {
        if (numXanelasDatosPersoais < 5) {
            numXanelasDatosPersoais++;
            return true;
        } else {
            return false;
        }
    }

    public static void cerrarXanelasDatosAcademicos() {
        numXanelasDatosAcademicos--;
        //System.out.println("Número de ventanas de datos académicos abiertas: " + numXanelasDatosAcademicos);
    }

    public static boolean abrirXanelasDatosAcademicos() {
        if (numXanelasDatosAcademicos < 2) {
            numXanelasDatosAcademicos++;
            return true;
        } else {
            return false;
        }
    }

    public static void engadirXanelaDatosPersoais(DlgDatosPersoais xanela) {
        xanelasDatosPersoais.add(xanela);
    }

    public static void eliminarXanelaDatosPersoais(DlgDatosPersoais xanela) {
        for (int i = 0; i < xanelasDatosPersoais.size(); i++) {
            if (xanelasDatosPersoais.elementAt(i) == xanela) {
                xanelasDatosPersoais.removeElementAt(i);
                break;
            }
        }
        System.out.println(xanelasDatosPersoais.size());
    }

    public static Vector recuperarXanelasDatosPersoais() {
        return xanelasDatosPersoais;
    }

}
