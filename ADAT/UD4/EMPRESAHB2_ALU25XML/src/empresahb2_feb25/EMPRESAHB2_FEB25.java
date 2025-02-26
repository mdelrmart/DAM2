/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahb2_feb25;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import POJOS.Departamento;
import POJOS.Proxecto;
import org.hibernate.Session;

/**
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

            //ConsultasHB.iniciarSesion();
            //ConsultasHB.mostrarEmpregados();
            //ConsultasHB.mostrarEmpregadosTipo("fijo");
            //ConsultasHB.mostrarEmpleadosNacidosAPartirDe("1970");
            //ConsultasHB.listarEmpleadosDepartamento();

            // D.2
            Proxecto pr = new Proxecto(1, "Proxecto 1", "Mercadona");
            Metodos.asignarProxectoDepartamento(pr, 1, sesion);

            sesion.close();

        } else {
            System.out.println("ERROR");
        }

        System.exit(0);
    }
}
