import Clases.Actualizaciones;
import Clases.Departamento;
import Clases.Empleado;
import Clases.Proyecto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class A5UD3_EJ3_JSON {
    public static void main(String[] args) {
        //eliminarDepartamento("INNOVACIÓN", "CONTABILIDADE");
         System.out.println(existeMatricula("1234Abc"));
    }

    public static void eliminarDepartamento(String departamentoEliminar, String departamentoReasignar) {
        if (existeDepartamento(departamentoEliminar) == 0) {
            System.out.println("No existe el departamento a eliminar indicado");
            return;
        }

        if (existeDepartamento(departamentoReasignar) == 0) {
            System.out.println("No existe el departamento a reasignar indicado");
            return;
        }

        int numDepEliminar = obtenerNumDepartamentoProcedimiento(departamentoEliminar);
        int numDepReasignar = obtenerNumDepartamentoProcedimiento(departamentoReasignar);

        List<Empleado> empleadosReasignados = new ArrayList<>();
        List<Proyecto> proyectosReasignados = new ArrayList<>();

        // Cambiamos el departamento que controla los proyectos
        String sqlUpdateProxecto = """
                UPDATE PROXECTO
                SET Num_departamento_controla = ?
                WHERE Num_departamento_controla = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sqlUpdateProxecto)) {

            sentencia.setInt(1, numDepReasignar);
            sentencia.setInt(2, numDepEliminar);

            sentencia.executeUpdate();

            // Recopilamos información de los proyectos reasignados
            String sqlSelectProyectos = """
                    SELECT Num_proxecto, Nome_proxecto
                    FROM PROXECTO
                    WHERE Num_departamento_controla = ?
                    """;

            try (PreparedStatement selectSentencia = conexion.prepareStatement(sqlSelectProyectos)) {
                selectSentencia.setInt(1, numDepReasignar);
                ResultSet rs = selectSentencia.executeQuery();

                while (rs.next()) {
                    Proyecto proyecto = new Proyecto(rs.getInt("Num_proxecto"), rs.getString("Nome_proxecto"), numDepReasignar);
                    proyectosReasignados.add(proyecto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Cambiamos los empleados de departamento
        String sqlUpdateEmpregado = """
                UPDATE EMPREGADO
                SET Num_departamento_pertenece = ?
                WHERE Num_departamento_pertenece = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sqlUpdateEmpregado)) {

            sentencia.setInt(1, numDepReasignar);
            sentencia.setInt(2, numDepEliminar);

            sentencia.executeUpdate();

            // Recopilamos información de los empleados reasignados
            String sqlSelectEmpleados = """
                    SELECT NSS, Nome, Apelido_1, Apelido_2
                    FROM EMPREGADO
                    WHERE Num_departamento_pertenece = ?
                    """;

            try (PreparedStatement selectSentencia = conexion.prepareStatement(sqlSelectEmpleados)) {
                selectSentencia.setInt(1, numDepReasignar);
                ResultSet rs = selectSentencia.executeQuery();

                while (rs.next()) {
                    Empleado empleado = new Empleado(rs.getString("NSS"), rs.getString("Nome"), rs.getString("Apelido_1"), rs.getString("Apelido_2"), numDepReasignar);
                    empleadosReasignados.add(empleado);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Eliminamos el departamento
        String sqlEliminarDepartamento = """
                DELETE FROM DEPARTAMENTO
                WHERE Num_departamento = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sqlEliminarDepartamento)) {

            sentencia.setInt(1, numDepEliminar);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Crear el objeto JSON y escribirlo en un archivo
        Departamento departamentoEliminado = new Departamento(numDepEliminar, departamentoEliminar);
        Actualizaciones actualizaciones = new Actualizaciones(empleadosReasignados, proyectosReasignados, departamentoEliminado);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("Actualizaciones.json")) {
            gson.toJson(actualizaciones, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int existeDepartamento(String departamento) {
        String sql = """
                    SELECT Nome_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, departamento);

            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int obtenerNumDepartamento(String departamento) {
        String sql = """
                    SELECT Num_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, departamento);

            ResultSet rs = sentencia.executeQuery();

            // Mover el cursor a la primera fila y comprobar si hay resultados
            if (rs.next()) {
                return rs.getInt("Num_departamento");
            } else {
                // Devolvemos -1 si no se encuentra el departamento
                System.out.println("No se ha encontrado el departamento " + departamento);
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int obtenerNumDepartamentoProcedimiento(String departamento) {
        try (Connection conexion = Conexion.obtenerConexion()) {
            // Prepara la llamada al procedimiento almacenado
            String procedimiento = "{CALL pr_ObtenerNumDepartamento(?, ?)}";
            CallableStatement callableStatement = conexion.prepareCall(procedimiento);

            // Establece el parámetro de entrada
            callableStatement.setString(1, departamento);

            // Registra el parámetro de salida
            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

            // Ejecuta el procedimiento almacenado
            callableStatement.execute();

            // Recupera el valor del parámetro de salida
            int numDepartamento = callableStatement.getInt(2);

            // Procesa el resultado
            if (numDepartamento == -1) {
                return -1;
            } else {
                return numDepartamento;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int existeMatricula(String matricula) {
        try (Connection conexion = Conexion.obtenerConexion()) {
            String funcion = "{? = CALL fn_ComprobarMatricula(?)}";
            CallableStatement callableStatement = conexion.prepareCall(funcion);

            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.setString(2,matricula);

            callableStatement.execute();

            return callableStatement.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}