/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresahb2_feb25;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;

public class ConsultasHB {
    static Session sesion = null;
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void iniciarSesion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
    }

    public static void cerrarSesion() {
        sesion.close();
    }

    public static void mostrarEmpregados() {
        List<Object[]> results = sesion.createQuery("""
                select e.nss, concat(e.apelido1, ' ', isnull (e.apelido2,''), ', ', e.nome) as nome_completo, e.departamento.nomeDepartamento,
                case type(e)
                    when Empregadofixo then 'fijo'
                    when Empregadotemporal then 'temporal'
                    end as tipo_empregado,
                e.telefonos.size
                from Empregado e
                order by e.apelido1, e.apelido2, e.nome
                """).list();

        // Encabezado con alineación
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-15s %-10s %-5s%n",
                "NSS", "Nombre completo", "Departamento", "Tipo", "Num. de teléfonos");
        System.out.println("---------------------------------------------------------------------------------------------");

        // Imprimir filas de empleados
        for (Object[] campos : results) {
            System.out.printf("%-10s %-30s %-15s %-10s %-5s%n",
                    campos[0], campos[1], campos[2], campos[3], campos[4]);
        }

        System.out.println("---------------------------------------------------------------------------------------------");
    }

    public static void mostrarEmpregadosTipo(String tipo) {
        if (tipo.equals("fijo")) {
            tipo = "Empregadofixo";
        } else if (tipo.equals("temporal")) {
            tipo = "Empregadotemporal";
        } else {
            System.out.println("Tipo de empleado no válido");
            return;
        }

        //sesion.beginTransaction();

        List<Object[]> results = sesion.createQuery("""
                select e.nss, concat(e.apelido1, ' ', isnull (e.apelido2,''), ', ', e.nome) as nome_completo, e.departamento.nomeDepartamento,
                       case type(e)
                           when Empregadofixo then 'fijo'
                           when Empregadotemporal then 'temporal'
                           end as tipo_empregado,
                       e.telefonos.size
                from Empregado e """
                + " where e.class in (" + tipo + ")"
                + "order by e.apelido1, e.apelido2, e.nome").list();

        // Encabezado con alineación
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-15s %-10s %-5s%n",
                "NSS", "Nombre completo", "Departamento", "Tipo", "Num. de teléfonos");
        System.out.println("---------------------------------------------------------------------------------------------");

        // Imprimir filas de empleados
        for (Object[] campos : results) {
            System.out.printf("%-10s %-30s %-15s %-10s %-5s%n",
                    campos[0], campos[1], campos[2], campos[3], campos[4]);
        }

        System.out.println("---------------------------------------------------------------------------------------------");

        //sesion.getTransaction().commit();
    }

    public static void mostrarEmpleadosNacidosAPartirDe(String anho) {
        //sesion.beginTransaction();

        List<Object[]> results = sesion.createQuery("""
                select e.nss, concat(e.apelido1, ' ', isnull (e.apelido2,''), ', ', e.nome) as nome_completo, e.departamento.nomeDepartamento, 
                e.dataNacemento
                from Empregado e """
                + " where year(e.dataNacemento) > (" + anho + ")"
                + "order by e.apelido1, e.apelido2, e.nome").list();

        // Encabezado con alineación
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-15s %-10s%n",
                "NSS", "Nombre completo", "Departamento", "Fecha Nacimiento");
        System.out.println("---------------------------------------------------------------------------------------------");

        // Imprimir filas de empleados
        for (Object[] campos : results) {
            System.out.printf("%-10s %-30s %-15s %-10s%n",
                    campos[0], campos[1], campos[2], campos[3]);
        }

        System.out.println("---------------------------------------------------------------------------------------------");

        //sesion.getTransaction().commit();

    }

    public static void listarEmpleadosDepartamento() {
        //sesion.beginTransaction();

        List<Object[]> results = sesion.createQuery("""
                select d.numDepartamento, d.nomeDepartamento, d.empregados.size as num_empleados
                from Departamento d
                order by num_empleados desc
                """).list();

        // Encabezado con alineación
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-15s%n",
                "Número", "Nombre", "Núm. de empleados");
        System.out.println("---------------------------------------------------------------------------------------------");

        // Imprimir filas de empleados
        for (Object[] campos : results) {
            System.out.printf("%-10s %-30s %-15s%n",
                    campos[0], campos[1], campos[2]);
        }

        System.out.println("---------------------------------------------------------------------------------------------");

        //sesion.getTransaction().commit();
    }
}