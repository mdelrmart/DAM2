import java.sql.*;

public class A7UD3_EJ1 {
    public static void main(String[] args) {
        crearTablaEmpleadoFamiliar();
    }

    public static void crearTablaEmpleadoFamiliar(){
        // Sentencias SQL
        String sqlEmpleadoFamiliar = """
                CREATE TABLE EMPREGADO_FAMILIAR
                (
                
                    NSS_Empregado  VARCHAR(15) NOT NULL,
                    Numero         SMALLINT    NOT NULL,
                    NSS            VARCHAR(15) NOT NULL,
                    Nome           VARCHAR(15) NOT NULL,
                    Apelido_1      VARCHAR(25) NOT NULL,
                    Apelido_2      VARCHAR(25) NULL,
                    Data_nacemento DATE        NULL,
                    Parentesco     VARCHAR(20) NOT NULL,
                    Sexo           CHAR(1)     NOT NULL DEFAULT 'M',
                );
                """;

        String sqlClavePrimaria = """
                ALTER TABLE EMPREGADO_FAMILIAR
                    ADD CONSTRAINT PK_EMPREGADO_FAMILIAR PRIMARY KEY (NSS_Empregado, Numero);
                """;

        String sqlClaveForanea = """
                ALTER TABLE EMPREGADO_FAMILIAR
                    ADD CONSTRAINT FK_EMPREGADO_FAMILIAR FOREIGN KEY (NSS_Empregado) REFERENCES EMPREGADO (NSS);
                """;

        String sqlClaveUnica = """
                ALTER TABLE EMPREGADO_FAMILIAR
                    ADD CONSTRAINT UQ_EMPREGADO_FAMILIAR_NSS UNIQUE (NSS_Empregado, NSS);
                """;

        String sqlChecks = """
                ALTER TABLE EMPREGADO_FAMILIAR
                    ADD CONSTRAINT CHK_SEXO CHECK (Sexo IN ('H', 'M'));
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(sqlEmpleadoFamiliar);
            sentencia.executeUpdate(sqlClavePrimaria);
            sentencia.executeUpdate(sqlClaveForanea);
            sentencia.executeUpdate(sqlClaveUnica);
            sentencia.executeUpdate(sqlChecks);

            sentencia.executeBatch();

            // Verificar que la tabla se ha creado
            DatabaseMetaData metaData = conexion.getMetaData();
            try (ResultSet rs = metaData.getTables(null, null, "EMPREGADO_FAMILIAR", null)) {
                if (rs.next()) {
                    System.out.println("La tabla EMPREGADO_FAMILIAR se ha creado con exito.");
                } else {
                    System.out.println("Error: La tabla EMPREGADO_FAMILIAR no se ha podido crear.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
