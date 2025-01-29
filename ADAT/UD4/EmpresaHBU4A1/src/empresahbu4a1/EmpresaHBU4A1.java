/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahbu4a1;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import pojos.Departamento;
import pojos.Empregado;

/**
 *
 * @author usuario
 */
public class EmpresaHBU4A1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session sesion = Operaciones.iniciarSesion();

        if (sesion != null) {
            System.out.println("La sesión es correcta");
        } else {
            System.exit(-1); 
        }

        //Departamento departamento = new Departamento(1, "DEPARTAMENTO ENTO");
        //Operaciones.insertarObjeto(departamento);
        //Operaciones.insertarDepartamento(departamento, sesion);

        /*
        // Corresponden al ejercicio 1.

        Operaciones.insertarDepartamentoNombre("COMERCIAL", sesion);
        
        Empregado empregado = new Empregado("52485145F", "Miguel", "Gutierrez");
        Operaciones.insertarEmpleado(empregado, sesion);
        //Operaciones.insertarObjeto(empregado);
        
        Operaciones.getEmpleado("52485145F");
        //Operaciones.getDepartamento(1);
        
        Operaciones.borrarDepartamento(1, sesion);
        
        Operaciones.cambiarNombreDepartamento(1, "FINANCIERO", sesion);
        
        Set<String> telefonos = new HashSet<>();
        telefonos.add("695456212");
        telefonos.add("635658452");
        
        Empregado empregado2 = new Empregado("52785452F", "Manuel", "Fernandez", telefonos);
        Operaciones.insertarEmpleado(empregado2, sesion);

        */

        /*
        // Hecho en clase aparte.
        Operaciones.agregarTelefonoEmpleado("52485147F", "986320719", sesion);

        Operaciones.eliminarTelefonoEmpleado("52485147F", "698542598", sesion);

        Operaciones.borrarEmpleado("52485145F", sesion);
        */

        HibernateUtil.getSessionFactory().close();

        // Sin esta línea el programa no finaliza la ejecución
        System.exit(0);
    }
    
}
