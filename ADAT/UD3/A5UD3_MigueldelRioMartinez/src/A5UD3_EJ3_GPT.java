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

public class A5UD3_EJ3_GPT {
    public static void main(String[] args) {
        eliminarDepartamento("PERSOAL", "TÉCNICO");
    }

    public static void eliminarDepartamento(String departamentoEliminar, String departamentoReasignar) {
        // Verificar existencia de departamentos
        if (existeDepartamento(departamentoEliminar) == 0) {
            System.out.println("No existe el departamento a eliminar indicado.");
            return;
        }

        if (existeDepartamento(departamentoReasignar) == 0) {
            System.out.println("No existe el departamento a reasignar indicado.");
            return;
        }

        int numDepEliminar = obtenerNumDepartamento(departamentoEliminar);
        int numDepReasignar = obtenerNumDepartamento(departamentoReasignar);

        List<Empleado> empleadosReasignados = new ArrayList<>();
        List<Proyecto> proyectosReasignados = new ArrayList<>();

        // Visualizar datos del departamento, empleados y proyectos antes de la eliminación
        System.out.println("Departamento a eliminar:");
        visualizarDepartamento(numDepEliminar);
        System.out.println("Empleados en el departamento a eliminar:");
        visualizarEmpleados(numDepEliminar);
        System.out.println("Proyectos controlados por el departamento a eliminar:");
        visualizarProyectos(numDepEliminar);

        try (Connection conexion = Conexion.obtenerConexion()) {
            conexion.setAutoCommit(false);

            // Actualizar los proyectos
            String sqlSelectProyectos = "SELECT Num_proxecto, Nome_proxecto FROM PROXECTO WHERE Num_departamento_controla = ?";
            try (PreparedStatement selectStmt = conexion.prepareStatement(sqlSelectProyectos, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                selectStmt.setInt(1, numDepEliminar);
                ResultSet rsProyectos = selectStmt.executeQuery();

                while (rsProyectos.next()) {
                    Proyecto proyecto = new Proyecto(
                            rsProyectos.getInt("Num_proxecto"),
                            rsProyectos.getString("Nome_proxecto"),
                            numDepReasignar
                    );
                    proyectosReasignados.add(proyecto);
                }
            }

            String sqlUpdateProyectos = "UPDATE PROXECTO SET Num_departamento_controla = ? WHERE Num_departamento_controla = ?";
            try (PreparedStatement updateStmt = conexion.prepareStatement(sqlUpdateProyectos)) {
                updateStmt.setInt(1, numDepReasignar);
                updateStmt.setInt(2, numDepEliminar);
                updateStmt.executeUpdate();
            }

            // Actualizar los empleados
            String sqlSelectEmpleados = "SELECT NSS, Nome, Apelido_1, Apelido_2 FROM EMPREGADO WHERE Num_departamento_pertenece = ?";
            try (PreparedStatement selectStmt = conexion.prepareStatement(sqlSelectEmpleados, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                selectStmt.setInt(1, numDepEliminar);
                ResultSet rsEmpleados = selectStmt.executeQuery();

                while (rsEmpleados.next()) {
                    Empleado empleado = new Empleado(
                            rsEmpleados.getString("NSS"),
                            rsEmpleados.getString("Nome"),
                            rsEmpleados.getString("Apelido_1"),
                            rsEmpleados.getString("Apelido_2"),
                            numDepReasignar
                    );
                    empleadosReasignados.add(empleado);
                }
            }

            String sqlUpdateEmpleados = "UPDATE EMPREGADO SET Num_departamento_pertenece = ? WHERE Num_departamento_pertenece = ?";
            try (PreparedStatement updateStmt = conexion.prepareStatement(sqlUpdateEmpleados)) {
                updateStmt.setInt(1, numDepReasignar);
                updateStmt.setInt(2, numDepEliminar);
                updateStmt.executeUpdate();
            }

            // Eliminar el departamento
            String sqlEliminarDepartamento = "DELETE FROM DEPARTAMENTO WHERE Num_departamento = ?";
            try (PreparedStatement deleteStmt = conexion.prepareStatement(sqlEliminarDepartamento)) {
                deleteStmt.setInt(1, numDepEliminar);
                deleteStmt.executeUpdate();
            }

            conexion.commit();
            System.out.println("Actualización completada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error durante la actualización o eliminación: " + e.getMessage());
            e.printStackTrace();
        }


        // Crear y guardar archivo JSON
        Departamento departamentoEliminado = new Departamento(numDepEliminar, departamentoEliminar);
        Actualizaciones actualizaciones = new Actualizaciones(empleadosReasignados, proyectosReasignados, departamentoEliminado);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("Actualizaciones.json")) {
            gson.toJson(actualizaciones, writer);
            System.out.println("Archivo JSON creado correctamente: Actualizaciones.json");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static void visualizarDepartamento(int numDepartamento) {
        String sql = "SELECT * FROM DEPARTAMENTO WHERE Num_departamento = " + numDepartamento;
        try (Connection conexion = Conexion.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("Número: " + rs.getInt("Num_departamento") + ", Nombre: " + rs.getString("Nome_departamento"));
            } else {
                System.out.println("Departamento no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al visualizar departamento: " + e.getMessage());
        }
    }

    public static void visualizarEmpleados(int numDepartamento) {
        String sql = "SELECT * FROM EMPREGADO WHERE Num_departamento_pertenece = " + numDepartamento;
        try (Connection conexion = Conexion.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("NSS: " + rs.getString("NSS") + ", Nombre: " + rs.getString("Nome") +
                        ", Apellidos: " + rs.getString("Apelido_1") + " " + rs.getString("Apelido_2"));
            }
        } catch (SQLException e) {
            System.err.println("Error al visualizar empleados: " + e.getMessage());
        }
    }

    public static void visualizarProyectos(int numDepartamento) {
        String sql = "SELECT * FROM PROXECTO WHERE Num_departamento_controla = " + numDepartamento;
        try (Connection conexion = Conexion.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Número: " + rs.getInt("Num_proxecto") + ", Nombre: " + rs.getString("Nome_proxecto"));
            }
        } catch (SQLException e) {
            System.err.println("Error al visualizar proyectos: " + e.getMessage());
        }
    }

    public static int existeDepartamento(String departamento) {
        String sql = "SELECT Nome_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, departamento);
            ResultSet rs = sentencia.executeQuery();

            return rs.next() ? 1 : 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int obtenerNumDepartamento(String departamento) {
        try (Connection conexion = Conexion.obtenerConexion()) {
            String procedimiento = "{CALL pr_ObtenerNumDepartamento(?, ?)}";
            CallableStatement callableStatement = conexion.prepareCall(procedimiento);

            callableStatement.setString(1, departamento);
            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
            callableStatement.execute();

            return callableStatement.getInt(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
