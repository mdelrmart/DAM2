/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahbu4a1;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import pojos.*;

/**
 *
 * @author Miguel del Río
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
    //    Empregado empregado = new Empregado("52485145F", "Miguel", "Gutierrez");

    //    Departamento departamento = new Departamento(1, "DEPARTAMENTO DE PRUEBA", empregado);
        //Operaciones.insertarObjeto(departamento);
    //    Operaciones.insertarDepartamento(departamento, sesion);


        // Corresponden al ejercicio 1.
        //Operaciones.insertarDepartamentoNombre("COMERCIAL", sesion);

    //    Operaciones.insertarEmpleado(empregado, sesion);
        //Operaciones.insertarObjeto(empregado);
        
    //    Operaciones.getEmpleado("52485145F");
    //    Operaciones.getDepartamento(1);

    //    Operaciones.cambiarNombreDepartamento(1, "FINANCIERO", sesion);

        //Operaciones.borrarDepartamento(1, sesion);

    //    Set<String> telefonos = new HashSet<>();
    //    telefonos.add("695456212");
    //    telefonos.add("635658452");
        
        /*
        Empregado empregado2 = new Empregado("52785452F", "Manuel", "Fernandez", telefonos);
        Operaciones.insertarEmpleado(empregado2, sesion);

        Operaciones.insertarTelefonoEmpleado("52485147F", "986320719", sesion);

        Operaciones.borrarTelefonoEmpleado("52485147F", "698542598", sesion);

        Operaciones.borrarEmpleado("52485145F", sesion);
        */

        // Si le pasa NULL no lo inserta
    //    Telefono tel = new Telefono("movil", "623123457");
    //    OperacionesTelefono.insertarTelefonoEmpleado("52485145F", tel, sesion);

        //OperacionesTelefono.borrarTelefonoEmpleado("52485145F", tel, sesion);

    //    Familiar fam = new Familiar("12345609A", "Paco", "Terranjausen", "Estevez", Date.valueOf(LocalDate.of(2000,5,12)), "Amego", Character.valueOf('M'));
    //    Operaciones.insertarFamiliar("54785128M", fam, sesion);

        //Operaciones.insertarAficion("54785128M","Golf", sesion);

        //Operaciones.insertarLugar(2,"Badalona", sesion);

        //Operaciones.insertarHorasExtras("54785128M", Date.valueOf(LocalDate.of(2025,2,5)),4.0, sesion);

        //Operaciones.visualizasHorasExtrasEmpleado("54785128M", sesion);

    //    Enderezo enderezo = new Enderezo("Montero Rios", "36930","Bueu","Pontevedra");
    //    Empregado emp = new Empregado("54562124N", "Francisco", "Martinez", "Terranjausen", 300.0, Date.valueOf(LocalDate.of(2025,2,5)),'H', enderezo);
    //    Operaciones.insertarEmpleado(emp, sesion);


        //Vehiculo vehiculo = new Vehiculo("54562124N","4565LGC","Volkswagen","Polo", Date.valueOf(LocalDate.of(2020,8,23)), emp);
    //    Vehiculo vehiculo = new Vehiculo("4565LGC","Volkswagen","Polo", Date.valueOf(LocalDate.of(2020,8,23)));
    //    Operaciones.insertarVehiculo("54562124N", vehiculo, sesion);


        Operaciones.visualizarProyectosDpto(1, sesion);
        Operaciones.visualizarProyectosDptoProPlus("FINANCIERO", sesion);

        // Cerramos la sesión
        Operaciones.cerrarSesion(sesion);
        HibernateUtil.getSessionFactory().close();

        // Sin esta línea el programa no finaliza la ejecución, en la versión de Hibernate 4.3.1
        System.exit(0);
    }
    
}