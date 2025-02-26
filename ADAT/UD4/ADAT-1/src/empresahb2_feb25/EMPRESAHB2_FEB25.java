/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahb2_feb25;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import POJOS.Edicion;
import POJOS.EdicionId;
import POJOS.Proxecto;
import POJOS.Vehiculo;
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

        System.exit(0);
    }
}
