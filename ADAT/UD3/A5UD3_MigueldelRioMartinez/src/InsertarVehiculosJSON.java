import Clases.VehiculoPropio;
import Clases.VehiculoRenting;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class InsertarVehiculosJSON {

    public static void insertarVehiculosDesdeJson(String filePath) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            JsonObject objectoRaiz = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray arrayVehiculos = objectoRaiz.getAsJsonArray("vehiculos");

            // Vamos recorriendo el array de vehículos hasta que no haya más elementos y los vamos insertando en la base de datos
            for (int i = 0; i < arrayVehiculos.size(); i++) {
                JsonObject vehiculo = arrayVehiculos.get(i).getAsJsonObject();

                String matricula = vehiculo.get("matricula").getAsString();
                String marca = vehiculo.get("marca").getAsString();
                String modelo = vehiculo.get("modelo").getAsString();
                char tipo = vehiculo.get("tipo").getAsString().charAt(0);

                // Si la matricula no está en la base de datos, insertamos el vehículo
                if (A5UD3_EJ2.comprobarMatricula(matricula) == 0) {
                    // Si el objecto vehiculo tiene la propiedad "vehiculoPropio", lo insertamos como un vehículo propio
                    if (vehiculo.has("vehiculoPropio")) {
                        JsonObject vehiculoPropio = vehiculo.getAsJsonObject("vehiculoPropio");

                        Date fechaCompra = Date.valueOf(LocalDate.parse(vehiculoPropio.get("fechaCompra").getAsString()));
                        double precio = vehiculoPropio.get("precio").getAsDouble();

                        VehiculoPropio v = new VehiculoPropio(matricula, marca, modelo, tipo, fechaCompra, precio);
                        VehiculoDAO.insertarVehiculo(v);
                    // En caso contrario, si el objecto vehiculo tiene la propiedad "vehiculoRenting", lo insertamos como un vehículo renting
                    } else if (vehiculo.has("vehiculoRenting")) {
                        JsonObject vehiculoRenting = vehiculo.getAsJsonObject("vehiculoRenting");

                        Date fechaInicio = Date.valueOf(LocalDate.parse(vehiculoRenting.get("fechaInicio").getAsString()));
                        double precioMensual = vehiculoRenting.get("precioMensual").getAsDouble();
                        int meses = vehiculoRenting.get("meses").getAsInt();

                        VehiculoRenting v = new VehiculoRenting(matricula, marca, modelo, tipo, fechaInicio, precioMensual, meses);
                        VehiculoDAO.insertarVehiculo(v);
                    }
                } else {
                    // Si la matrícula está en la base de datos, mostramos el siguiente mensaje
                    System.out.println("El vehículo con matrícula " + matricula + " ya existe en la base de datos.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}