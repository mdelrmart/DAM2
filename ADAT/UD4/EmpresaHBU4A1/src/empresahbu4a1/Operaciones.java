/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahbu4a1;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Departamento;
import pojos.Empregado;

/**
 *
 * @author usuario
 */
public class Operaciones {

    public static Session iniciarSesion() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        return sesion;
    }

    public static void cerrarSesion(Session sesion) {
        if (sesion != null) {
            sesion.close();
        }
    }

    public static <T> void insertarObjeto(T objeto) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = sesion.beginTransaction();
            sesion.save(objeto);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void getDepartamento(int numDepartamento) {
        try {
            Session session = iniciarSesion();
            Departamento departamento = (Departamento) session.load(Departamento.class, numDepartamento);
            System.out.println(departamento.getNumDepartamento());
            System.out.println(departamento.getNomeDepartamento());
        } catch (Exception e) {
            System.out.println("No se ha encontrado el departamento " + numDepartamento);
        }

    }

    public static void getEmpleado(String nss) {
        try {
            Session session = iniciarSesion();
            Empregado empregado = (Empregado) session.load(Empregado.class, nss);
            System.out.println(empregado.getNss());
            System.out.println(empregado.getNome() + " " + empregado.getApelido1() + " " + empregado.getApelido2());
        } catch (Exception e) {
            System.out.println("No se ha encontrado el empleado " + nss);
        }

    }

    public static void borrarDepartamento(int numDepartamento, Session sesion) {
        Transaction tx = null;

        Departamento dep = new Departamento(numDepartamento, "");

        try {
            tx = sesion.beginTransaction();
            sesion.delete(dep);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void borrarEmpleado(String nss, Session sesion) {
        Transaction tx = null;

        //Departamento dep = new Departamento(numDepartamento, "");

        Empregado emp = new Empregado(nss, "", "");

        try {
            tx = sesion.beginTransaction();
            sesion.delete(emp);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void cambiarNombreDepartamento(int numDepartamento, String nuevoNombre, Session sesion) {
        Transaction tx = null;

        try {
            tx = sesion.beginTransaction();
            Departamento dep = (Departamento) sesion.get(Departamento.class, numDepartamento);
            dep.setNomeDepartamento(nuevoNombre);
            //sesion.update(dep);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void agregarTelefonoEmpleado(String nss, String telefono, Session sesion) {
        Transaction tx = null;

        try {
            Empregado emp = (Empregado) sesion.get(Empregado.class, nss);
            
            if (emp != null) {
                Set<String> telefonos = emp.getTelefonos();
                telefonos.add(telefono);
                
                // No es necesario porque cojo una referencia al set 
                //emp.setTelefonos(telefonos);

                try {
                    tx = sesion.beginTransaction();
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

    public static void eliminarTelefonoEmpleado(String nss, String telefono, Session sesion) {
        Transaction tx = null;

        try {
            Empregado emp = (Empregado) sesion.get(Empregado.class, nss);

            if (emp != null) {
                Set<String> telefonos = emp.getTelefonos();
                telefonos.remove(telefono);

                // No es necesario porque cojo una referencia al set
                //emp.setTelefonos(telefonos);

                try {
                    tx = sesion.beginTransaction();
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

    //--------------------------------------------------------------------------

    public static void insertarEmpleado(Empregado emp, Session sesion) {
        Transaction tx = null;

        try {
            tx = sesion.beginTransaction();
            sesion.save(emp);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void insertarDepartamento(Departamento dep, Session sesion) {
        Transaction tx = null;

        try {
            tx = sesion.beginTransaction();
            sesion.save(dep);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static void insertarDepartamentoNombre(String nombre, Session sesion) {
        Transaction tx = null;

        Departamento dep = new Departamento(0, nombre);

        try {
            tx = sesion.beginTransaction();
            sesion.save(dep);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("No ha funcionado " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    //--------------------------------------------------------------------------
}
