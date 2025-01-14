import Clases.Vehiculo;
import Clases.VehiculoPropio;
import Clases.VehiculoRenting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO {

    public static int insertarVehiculo(Vehiculo vehiculo) {
        String sql = """
                INSERT INTO VEHICULOS (Matricula, Marca, Modelo, Combustible)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, vehiculo.matricula);
            sentencia.setString(2, vehiculo.marca);
            sentencia.setString(3, vehiculo.modelo);
            sentencia.setString(4, String.valueOf(vehiculo.combustible));

            sentencia.executeUpdate();
            ResultSet rs1 = sentencia.getGeneratedKeys();
            rs1.next();
            return rs1.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarVehiculo(VehiculoPropio vehiculoPropio) {
        int codVehiculo = insertarVehiculo((Vehiculo) vehiculoPropio);

        String sql = """
                INSERT INTO VEHICULOS_PROPIOS (codVehiculo, fechaCompra, precio)
                VALUES (?, ?, ?)
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, codVehiculo);
            sentencia.setString(2, vehiculoPropio.fechaCompra.toString());
            sentencia.setDouble(3, vehiculoPropio.precio);

            sentencia.executeUpdate();
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
