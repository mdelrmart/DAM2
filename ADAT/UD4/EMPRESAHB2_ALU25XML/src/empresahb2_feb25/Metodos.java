package empresahb2_feb25;

import POJOS.Departamento;
import POJOS.Proxecto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Metodos {

    // D.2
    public static void asignarProxectoDepartamento(Proxecto pr, int numDepartamento, Session sesion) {
        Transaction tx = null;

        try {
            Departamento dep = (Departamento) sesion.get(Departamento.class, numDepartamento);

            //Proxecto pr = (Proxecto) sesion.get(Proxecto.class, numDepartamento);

            if (dep != null) {
                if (dep.getProxectos().contains(pr)) {
                    System.out.println("Ya está en el proyecto");
                    return;
                }

                dep.getProxectos().add(pr);
                pr.setDepartamento(dep);

                try {
                    tx = sesion.beginTransaction();

                    sesion.save(pr);

                    tx.commit();
                } catch (HibernateException e) {
                    System.out.println("No ha funcionado " + e.getMessage());
                    if (tx != null) {
                        tx.rollback();
                    }
                }
            }

        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
