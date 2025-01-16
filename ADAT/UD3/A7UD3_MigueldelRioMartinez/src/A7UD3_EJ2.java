import java.sql.*;
import java.time.LocalDate;

public class A7UD3_EJ2 {
    public static void main(String[] args) {
        Familiar familiar = new Familiar("0010010", "0222222", "Francisco", "Augusto", "Terranjausen", Date.valueOf(LocalDate.of(1970,5,1)), "Padre", 'H');
        insertarFamiliarEmpleado(familiar);

        Familiar familiar2 = new Familiar("0110010", "0222222", "Francisco", "Augusto2", "Terranjausen", Date.valueOf(LocalDate.of(1970,5,1)), "Padre", 'H');
        insertarFamiliarEmpleado(familiar2);

        Familiar familiar3 = new Familiar("0010010", "0222232", "Fernando", "Augusto", "Terranjausen", Date.valueOf(LocalDate.of(1970,5,1)), "Padre", 'H');
        insertarFamiliarEmpleado(familiar3);
    }

    public static void insertarFamiliarEmpleado(Familiar familiar) {
        if (existeFamiliarEmpleado(familiar.NSSEmpleado, familiar.nss) == 1) {
            System.out.println("Ya existe el familiar del empleado");
            return;
        }

        int ultimoNumFamiliar = obtenerNumFamiliar(familiar.NSSEmpleado);

        String sql = """
                INSERT INTO EMPREGADO_FAMILIAR (NSS_Empregado, Numero, NSS, Nome, Apelido_1, Apelido_2, Data_nacemento, Parentesco, Sexo)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, familiar.NSSEmpleado);
            sentencia.setInt(2, ultimoNumFamiliar + 1);
            sentencia.setString(3, familiar.nss);
            sentencia.setString(4, familiar.nombre);
            sentencia.setString(5, familiar.apellido1);
            sentencia.setString(6, familiar.apellido2);
            sentencia.setString(7, familiar.fechaNacimiento.toString());
            sentencia.setString(8, familiar.parentesco);
            sentencia.setString(9, String.valueOf(familiar.sexo));

            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int obtenerNumFamiliar(String NSSempleado) {
        try (Connection conexion = Conexion.obtenerConexion()) {
            String funcion = "{? = CALL fn_UltimoNumeroFamiliar(?)}";
            CallableStatement callableStatement = conexion.prepareCall(funcion);

            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, NSSempleado);
            callableStatement.execute();

            return callableStatement.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int existeFamiliarEmpleado(String NSSempleado, String NSSfamiliar) {
        try (Connection conexion = Conexion.obtenerConexion()) {
            String procedimiento = "{CALL pr_ExisteFamiliar(?, ?, ?)}";
            CallableStatement callableStatement = conexion.prepareCall(procedimiento);

            callableStatement.setString(1, NSSempleado);
            callableStatement.setString(2, NSSfamiliar);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.execute();

            // Recupera el valor del parámetro de salida
            int existe = callableStatement.getInt(3);

            if (existe == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
