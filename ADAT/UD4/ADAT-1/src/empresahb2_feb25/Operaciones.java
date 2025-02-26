/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresahb2_feb25;

import POJOS.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author usuario
 */
public class Operaciones {
    public static void conectarHibernate() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {
            System.out.println("Conexión realizada con éxito");
            sesion.close();
        } else {
            System.out.println("ERROR");
        }
    }

    //1. Metodo para añadir un curso y una edición
    public static void anhadirCursoYEdicion(Curso curso, Edicion edicion, Session sesion) {
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            // Guardar el curso
            sesion.save(curso);

            // Guardar la edición del curso
            sesion.save(edicion);

            tx.commit();
            System.out.println("Curso y edición añadidos correctamente.");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al añadir el curso y la edición.");
        }
    }

    //2. Metodo para añadir un nuevo familiar al empleado
    public static void anhadirFamiliar(String nssEmpregado, Familiar familiar, Session sesion) {
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            // Obtener el empleado
            Empregado empregado = (Empregado) sesion.get(Empregado.class, nssEmpregado);
            if (empregado == null) {
                System.out.println("No existe el empleado con el NSS: " + nssEmpregado);
                return;
            }

            // Añadir el familiar al empleado
            empregado.getFamiliares().add(familiar);

            // Guardar el empleado (esto guardará también los familiares, ya que está mapeado como componente (composite-element)
            sesion.saveOrUpdate(empregado);

            tx.commit();
            System.out.println("Familiar añadido correctamente.");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al añadir el familiar.");
        }
    }

    //4
    public static void borrarEmpregadoDeProxecto(String nssEmpregado, int numProxecto, Session sesion) {
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            // Obtener la clave compuesta del empleado en el proyecto
            EmpregadoProxectoId id = new EmpregadoProxectoId(nssEmpregado, numProxecto);

            // Obtener el objeto EmpregadoProxecto
            EmpregadoProxecto empregadoProxecto = (EmpregadoProxecto) sesion.get(EmpregadoProxecto.class, id);
            if (empregadoProxecto == null) {
                System.out.println("No existe el empleado en el proyecto con NSS: " + nssEmpregado + " y número de proyecto: " + numProxecto);
                return;
            }

            // Borrar el objeto EmpregadoProxecto
            sesion.delete(empregadoProxecto);

            tx.commit();
            System.out.println("Empleado borrado del proyecto correctamente.");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al borrar el empleado del proyecto.");
        }
    }
    
}
