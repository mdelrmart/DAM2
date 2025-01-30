package empresahbu4a1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Empregado;
import pojos.Telefono;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Miguel del Río
 */
public class OperacionesTelefono {

    public static void insertarTelefonoEmpleado(String nss, Telefono telefono, Session sesion) {
        sesion.beginTransaction();

        try {
            Empregado emp = (Empregado) sesion.get(Empregado.class, nss);

            if (emp != null) {
                Map<String,String> telefonos = emp.getTelefonos();
                telefonos.put(telefono.getNumero(), telefono.getInformacion());

                sesion.getTransaction().commit();
            }

        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        }
    }

    /*
    public static void insertarTelefonoEmpleado(String nss, Telefono telefono, Session sesion) {
        sesion.beginTransaction();

        try {
            Empregado emp = (Empregado) sesion.get(Empregado.class, nss);

            if (emp != null) {
                Set<Telefono> telefonos = emp.getTelefonos();
                telefonos.add(telefono);

                sesion.getTransaction().commit();
            }

        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        }
    }

    public static void borrarTelefonoEmpleado(String nss, Telefono telefono, Session sesion) {
        sesion.beginTransaction();
        try {
            Empregado emp = (Empregado) sesion.get(Empregado.class, nss);

            if (emp != null) {
                Set<Telefono> telefonos = emp.getTelefonos();
                telefonos.remove(telefono);

                sesion.getTransaction().commit();
            }

        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        }
    }
    */

}