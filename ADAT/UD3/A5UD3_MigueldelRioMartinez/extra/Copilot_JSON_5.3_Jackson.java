import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertarVehiculos {

    public static void main(String[] args) {
        insertarVehiculosDesdeJson("InsertarVehiculos.json");
    }

    public static void insertarVehiculosDesdeJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            JsonNode vehiculosNode = rootNode.path("vehiculos");

            for (JsonNode vehiculoNode : vehiculosNode) {
                String matricula = vehiculoNode.path("matricula").asText();
                String marca = vehiculoNode.path("marca").asText();
                String modelo = vehiculoNode.path("modelo").asText();
                char tipo = vehiculoNode.path("tipo").asText().charAt(0);

                if (comprobarMatricula(matricula) == 0) {
                    if (vehiculoNode.has("vehiculoPropio")) {
                        JsonNode vehiculoPropioNode = vehiculoNode.path("vehiculoPropio");
                        Date fechaCompra = Date.valueOf(LocalDate.parse(vehiculoPropioNode.path("fechaCompra").asText()));
                        double precio = vehiculoPropioNode.path("precio").asDouble();
                        VehiculoPropio vehiculoPropio = new VehiculoPropio(matricula, marca, modelo, tipo, fechaCompra, precio);
                        VehiculoDAO.insertarVehiculo(vehiculoPropio);
                    } else if (vehiculoNode.has("vehiculoRenting")) {
                        JsonNode vehiculoRentingNode = vehiculoNode.path("vehiculoRenting");
                        Date fechaInicio = Date.valueOf(LocalDate.parse(vehiculoRentingNode.path("fechaInicio").asText()));
                        double precioMensual = vehiculoRentingNode.path("precioMensual").asDouble();
                        int meses = vehiculoRentingNode.path("meses").asInt();
                        VehiculoRenting vehiculoRenting = new VehiculoRenting(matricula, marca, modelo, tipo, fechaInicio, precioMensual, meses);
                        VehiculoDAO.insertarVehiculo(vehiculoRenting);
                    }
                } else {
                    System.out.println("El vehículo con matrícula " + matricula + " ya existe en la base de datos.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int comprobarMatricula(String matricula) {
        String sql = """
                    SELECT Matricula FROM VEHICULOS WHERE Matricula=?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, matricula);

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
}