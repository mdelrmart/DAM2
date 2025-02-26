/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahb2_feb25;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import POJOS.*;
import org.hibernate.Session;

/**
 *
 * @author usuario
 */
public class EMPRESAHB2_FEB25 {
    /**
     * @param args the command line arguments
     */
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        if (sesion != null) {
            System.out.println("Conexión realizada con éxito");
            //sesion.close();

        } else {
            System.out.println("ERROR");
        }

        //D.1
        //Metodos.anhadirEmpleadoADepartamento("0010010", 2, sesion);
        //Metodos.anhadirSupervisor("0010010", "5000000", sesion);

        //D.2
        //Proxecto pr = new Proxecto(20, "Proyecto TOP SECRET", "Vigo");
        //Metodos.asignarProxectoDepartamento(pr, 2, sesion);

        //D.3
        //Metodos.insertarEmpregadoEnProxecto(9, "0010010", 20000, sesion);

        //D.4
        //Vehiculo vehiculo = new Vehiculo("1234ABC", "FORD", "FOCUS", sdf.parse("01/01/2020"));
        //Metodos.addVehiculo("3338883", vehiculo, sesion);

        //D.6
        //Metodos.insertarEmpregadoAEdicionCurso("2525252", new EdicionId(1,1), sesion);



        //1
        // No funciona por temas de clave foránea y herencia
        //Curso curso = new Curso(301, "Curso de Java", 100);
        //Edicion edicion = new Edicion(new EdicionId(301, 1), Date.valueOf(LocalDate.of(2021, 5, 12)), "PONTEVEDRA", "0999900");
        //Operaciones.anhadirCursoYEdicion(curso, edicion, sesion);

        //2
        //Familiar fam = new Familiar("12345609A", "Paco", "Terranjausen", "Estevez", Date.valueOf(LocalDate.of(2000,5,12)), "Amego", Character.valueOf('M'));
        //Operaciones.anhadirFamiliar("0999900", fam, sesion);


        //4
        //Operaciones.borrarEmpregadoDeProxecto("0010010", 8, sesion);


        System.exit(0);
    }
}
