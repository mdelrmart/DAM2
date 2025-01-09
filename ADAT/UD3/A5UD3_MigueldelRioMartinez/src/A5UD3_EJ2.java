import java.sql.*;

public class A5UD3_EJ2 {
    public static void main(String[] args) {
        //crearTablas();
        comprobarMatricula("AAAA");
    }

    public static void crearTablas() {
        String sqlVehiculos = """                
                CREATE TABLE VEHICULOS
                (
                    codVehiculo INT IDENTITY (1,1) NOT NULL,
                    Matricula   CHAR(8)            NOT NULL,
                    Marca       VARCHAR(25)        NOT NULL,
                    Modelo      VARCHAR(15)        NOT NULL,
                    Combustible CHAR(1)            NOT NULL
                )
                
                ALTER TABLE VEHICULOS
                    ADD CONSTRAINT PK_Vehiculos_codVehiculo PRIMARY KEY (codVehiculo)
                
                ALTER TABLE VEHICULOS
                    ADD CONSTRAINT UQ_Vehiculos_Combustible UNIQUE (Matricula)
                
                ALTER TABLE VEHICULOS
                    ADD CONSTRAINT CK_Vehiculos_Matricula CHECK (Matricula LIKE '[0-9][0-9][0-9][0-9] [A-Z][A-Z][A-Z]')
                
                ALTER TABLE VEHICULOS
                    ADD CONSTRAINT CK_Vehiculos_Combustible CHECK (Combustible IN ('G', 'D'))
                """;

        String sqlVehiculosRenting = """
                CREATE TABLE VEHICULOS_RENTING
                (
                    codVehiculo    INT              NOT NULL,
                    fechaInicio    DATE             NOT NULL,
                    mesesAlquilado TINYINT          NOT NULL,
                    precio         DOUBLE PRECISION NOT NULL
                )
                
                ALTER TABLE VEHICULOS_RENTING
                    ADD CONSTRAINT PK_VehiculosRenting_codVehiculo PRIMARY KEY (codVehiculo)
                
                ALTER TABLE VEHICULOS_RENTING
                    ADD CONSTRAINT FK_VehiculosRenting_codVehiculo FOREIGN KEY (codVehiculo) REFERENCES VEHICULOS (codVehiculo)
                """;

        String sqlVehiculosPropios = """
                CREATE TABLE VEHICULOS_PROPIOS
                (
                    codVehiculo INT              NOT NULL,
                    fechaCompra DATE             NOT NULL,
                    precio      DOUBLE PRECISION NOT NULL
                )
                
                ALTER TABLE VEHICULOS_PROPIOS
                    ADD CONSTRAINT PK_VehiculosPropios_codVehiculo PRIMARY KEY (codVehiculo)
                
                ALTER TABLE VEHICULOS_PROPIOS
                    ADD CONSTRAINT FK_VehiculosPropios_codVehiculo FOREIGN KEY (codVehiculo) REFERENCES VEHICULOS (codVehiculo)
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             Statement sentencia = conexion.createStatement()) {

            sentencia.addBatch("DROP TABLE IF EXISTS VEHICULOS_RENTING");
            sentencia.addBatch("DROP TABLE IF EXISTS VEHICULOS_PROPIOS");
            sentencia.addBatch("DROP TABLE IF EXISTS VEHICULOS");
            sentencia.addBatch(sqlVehiculos);
            sentencia.addBatch(sqlVehiculosPropios);
            sentencia.addBatch(sqlVehiculosRenting);

            sentencia.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarVehiculo() {

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