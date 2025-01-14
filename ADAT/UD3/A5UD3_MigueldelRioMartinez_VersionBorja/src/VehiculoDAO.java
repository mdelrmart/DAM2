import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO {

    public static int insertarVehiculo(Vehiculo vehiculo, Connection conn) throws SQLException {
        String sql = """
                INSERT INTO VEHICULOS (Matricula, Marca, Modelo, Combustible)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement sentencia = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, vehiculo.matricula);
            sentencia.setString(2, vehiculo.marca);
            sentencia.setString(3, vehiculo.modelo);
            sentencia.setString(4, String.valueOf(vehiculo.combustible));

            sentencia.executeUpdate();
            ResultSet rs1 = sentencia.getGeneratedKeys();
            rs1.next();
            return rs1.getInt(1);
        }
    }

    public static int insertarVehiculo(Vehiculo vehiculo) {
        Connection conn = null;
        try {
            conn = Conexion.obtenerConexion();
            conn.setAutoCommit(false);
            int generatedKey = insertarVehiculo(vehiculo, conn);
            conn.commit();
            return generatedKey;
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.close();
                return -1;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void insertarVehiculo(VehiculoPropio vehiculoPropio) {
        String sql = """
                INSERT INTO VEHICULOS_PROPIOS (codVehiculo, fechaCompra, precio)
                VALUES (?, ?, ?)
                """;
        Connection conexion = null;
        try {
            conexion = Conexion.obtenerConexion();

            try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
                conexion.setAutoCommit(false);

                int codVehiculo = insertarVehiculo((Vehiculo) vehiculoPropio, conexion);

                sentencia.setInt(1, codVehiculo);
                sentencia.setString(2, vehiculoPropio.fechaCompra.toString());
                sentencia.setDouble(3, vehiculoPropio.precio);

                sentencia.executeUpdate();
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarVehiculo(VehiculoRenting vehiculoRenting) {
        int codVehiculo = insertarVehiculo((Vehiculo) vehiculoRenting);

        String sql = """
                INSERT INTO VEHICULOS_RENTING (codVehiculo, fechaInicio, mesesAlquilado, precio)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, codVehiculo);
            sentencia.setString(2, vehiculoRenting.fechaInicio.toString());
            sentencia.setDouble(3, vehiculoRenting.precio);
            sentencia.setInt(4, vehiculoRenting.mesesAlquilado);

            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
