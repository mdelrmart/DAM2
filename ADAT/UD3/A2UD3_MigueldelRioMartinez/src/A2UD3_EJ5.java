import java.sql.*;

public class A2UD3_EJ5 {
    public static void main(String[] args) {
        // a)
        cambiarDomicilio("0010010","Calle de prueba", 45, "1-A", 36930, "Bueu");

        // b)
        System.out.println(informacionProxectos(2));
        System.out.print("\n");

        // c)
        departamentoControlaNumProyectosMayorIgualQue(2);
        System.out.print("\n");

        // d)
        consultarNumEmpleadosDepartamento("INFORMÁTICA");
    }

    public static void cambiarDomicilio(String NSSempleado, String calle, int numero, String numPiso, int codPostal, String localidad) {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://locahost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenha)) {
            // Prepara la llamada al procedimiento almacenado
            String procedimiento = "{CALL pr_CambioDomicilio(?, ?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(procedimiento);

            // Establece los parámetros de entrada
            callableStatement.setString(1, NSSempleado);

            callableStatement.setString(2, calle);
            callableStatement.setInt(3, numero);
            callableStatement.setString(4, numPiso);
            callableStatement.setInt(5, codPostal);
            callableStatement.setString(6, localidad);

            // Ejecuta el procedimiento almacenado
            callableStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Proxecto informacionProxectos(int numProyecto) {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenha)) {
            // Prepara la llamada al procedimiento almacenado
            String procedimiento = "{CALL pr_DatosProxectos(?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(procedimiento);

            // Establece los parámetros de entrada
            callableStatement.setInt(1, numProyecto);

            // Parametros de salida
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            // Ejecutamos el procedimiento almacenado
            callableStatement.execute();

            String p_nomeProxecto = callableStatement.getString(2);
            String p_Lugar = callableStatement.getString(3);
            int p_Num_departamento_controla = callableStatement.getInt(4);

            Proxecto proxecto = new Proxecto(numProyecto, p_nomeProxecto, p_Lugar, p_Num_departamento_controla);
            return proxecto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void departamentoControlaNumProyectosMayorIgualQue(int numProyectos) {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        ResultSet rs = null;

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenha)) {
            // Prepara la llamada al procedimiento almacenado
            String procedimiento = "{CALL pr_DepartControlaProxec(?)}";
            CallableStatement callableStatement = connection.prepareCall(procedimiento);

            // Establece los parámetros de entrada
            callableStatement.setInt(1, numProyectos);

            // Ejecutamos el procedimiento almacenado
            rs = callableStatement.executeQuery();

            // Iteramos sobre el ResultSet para procesar todas las filas
            while (rs.next()) {
                int p_numDepartamento = rs.getInt("Num_departamento");
                String p_nomeDepartamento = rs.getString("Nome_departamento");
                String p_NSSdirige = rs.getString("NSS_dirige");
                String p_dataDireccion = rs.getString("Data_direccion");

                System.out.println(p_numDepartamento + " " + p_nomeDepartamento + " " + p_NSSdirige + " " + p_dataDireccion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consultarNumEmpleadosDepartamento(String nomeDepartamento) {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenha)) {
            // Prepara la llamada al procedimiento almacenado
            String funcion = "{? = CALL fn_nEmpDepart(?)}";
            CallableStatement callableStatement = connection.prepareCall(funcion);

            // Registramos el parámetro de salida para el valor de retorno de la función
            callableStatement.registerOutParameter(1, Types.INTEGER);

            // Establece el parámetro de entrada
            callableStatement.setString(2, nomeDepartamento);

            // Ejecutamos la función y obtenemos el valor de retorno
            callableStatement.execute();
            int numEmpleados = callableStatement.getInt(1);
            System.out.println("Número de empleados en el departamento " + nomeDepartamento + ": " + numEmpleados);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
