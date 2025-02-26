package empresahb2_feb25;

import POJOS.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Metodos {

    // D.1
    public static void anhadirEmpleadoADepartamento(String nss, int numDepartamento, Session sesion) {
        Transaction tx = null;

        Departamento departamento = (Departamento) sesion.get(Departamento.class, numDepartamento);

        if (departamento != null) {
            Empregado empregado = (Empregado) sesion.get(Empregado.class, nss);
            if (empregado != null) {
                empregado.setDepartamento(departamento);
                departamento.getEmpregados().add(empregado);
                try {
                    tx = sesion.beginTransaction();
                    tx.commit();
                    System.out.println("Empleado añadido.");
                } catch (HibernateException e) {
                    if (tx != null) {
                        e.printStackTrace();
                        tx.rollback();
                    }
                }
            } else {
                System.out.println("No existe el empleado con el nss:" + nss);
                System.out.println("No se ha podidio añadir el empleado.");
            }
        } else {
            System.out.println("No existe el departamento número:" + numDepartamento);
            System.out.println("No se ha podidio añadir el empleado.");
        }
    }

    public static void anhadirSupervisor(String nss, String nssSupervisor, Session sesion) {
        Transaction tx = null;

        Empregado empregado = (Empregado) sesion.get(Empregado.class, nss);

        if (empregado != null) {
            Empregado empregadoSupervisor = (Empregado) sesion.get(Empregado.class, nssSupervisor);
            if (empregadoSupervisor != null) {
                empregado.setSupervisor(empregadoSupervisor);
                empregadoSupervisor.getSupervisados().add(empregado);
                try {
                    tx = sesion.beginTransaction();
                    tx.commit();
                    System.out.println("Supervisor añadido.");
                } catch (HibernateException e) {
                    if (tx != null) {
                        e.printStackTrace();
                        tx.rollback();
                    }
                }
            } else {
                System.out.println("No existe el empleado con el nss:" + nssSupervisor);
                System.out.println("No se ha podidio añadir el supervisor.");
            }
        } else {
            System.out.println("No existe el empregado con el nss:" + nss);
            System.out.println("No se ha podidio añadir el supervisor.");
        }
    }

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
                pr.setDepartamentoControla(dep);

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

    // D.3
    public static void insertarEmpregadoEnProxecto(int numProxecto, String nssEmpregado, int horas, Session sesion) {
        Transaction tx = null;

        Empregado empregado = (Empregado) sesion.get(Empregado.class, nssEmpregado);
        Proxecto proxecto = (Proxecto) sesion.get(Proxecto.class, numProxecto);

        if (empregado == null || proxecto == null) {
            System.out.println("No hay empleado o proyecto.");
            return;
        }

        EmpregadoProxectoId empregadoProxectoId = new EmpregadoProxectoId(nssEmpregado, numProxecto);
        EmpregadoProxecto empregadoProxecto = new EmpregadoProxecto(empregadoProxectoId, horas, empregado, proxecto);

        empregado.getProxectos().add(empregadoProxecto);
        proxecto.getEmpregados().add(empregadoProxecto);

        try {
            tx = sesion.beginTransaction();
            sesion.save(empregadoProxecto);
            tx.commit();
            System.out.println("Empregado asignado al proyecto.");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
    }

    // D.4
    public static void addVehiculo(String nssEmpregado, Vehiculo vehiculo, Session sesion) {
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Empregado empr = (Empregado) sesion.get(Empregado.class, nssEmpregado);
            if (empr == null) {
                System.out.println("El empleado no existe.");
            } else {
                Vehiculo vehi = empr.getVehiculo();
                if (vehi != null) {
                    vehi.setNss(nssEmpregado);
                    vehi.setModelo(vehiculo.getModelo());
                    vehi.setMarca(vehiculo.getMarca());
                    vehi.setMatricula(vehiculo.getMatricula());
                    vehi.setDataCompra(vehiculo.getDataCompra());

                    System.out.println("Vehículo modificado.");
                } else {
                    empr.setVehiculo(vehiculo);
                    vehiculo.setEmpregado(empr);
                    System.out.println("Vehículo insertado correctamente.");
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

    }

    // D.6
    public static void insertarEmpregadoAEdicionCurso(String nssEmpregado, EdicionId edicionId, Session sesion) {
        Transaction tx = null;

        Empregado empregado = (Empregado) sesion.get(Empregado.class, nssEmpregado);
        Edicion edicion = (Edicion) sesion.get(Edicion.class, edicionId);

        if (empregado == null || edicion == null) {
            System.out.println("No hay empleado o edición.");
            return;
        }
        edicion.getEmpregados().add(empregado);
        empregado.getCursos().add(edicion);

        try {
            tx = sesion.beginTransaction();
            tx.commit();
            System.out.println("Empregado asignado a la edición.");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error.");
        } finally {
            sesion.close();
        }
    }

}
