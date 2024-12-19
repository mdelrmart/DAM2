import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

class Metodos {

    static void mostrarMetadatos(Connection con) throws SQLException {
        DatabaseMetaData dbmetadata = con.getMetaData();

        String nombreSXBD = dbmetadata.getDatabaseProductName();
        String numVersion = dbmetadata.getDatabaseProductVersion();
        int numVersionPrincipal = dbmetadata.getDatabaseMajorVersion();
        int numVersionSecundario = dbmetadata.getDatabaseMinorVersion();
        String nombreConector = dbmetadata.getDriverName();
        String numVersionDriver = dbmetadata.getDriverVersion();
        int numVersionPrincipalDriver = dbmetadata.getDriverMajorVersion();
        int numVersionSecundariaDriver = dbmetadata.getDriverMinorVersion();
        String urlBBDD = dbmetadata.getURL();
        String usuarioConectado = dbmetadata.getUserName();
        boolean soloLectura = dbmetadata.isReadOnly();

        System.out.println("Nombre SXBD: " + nombreSXBD);
        System.out.println("Numero de version: " + numVersion);
        System.out.println("Numero de version principal: " + numVersionPrincipal);
        System.out.println("Numero de version secundario: " + numVersionSecundario);
        System.out.println("Nombre del driver: " + nombreConector);
        System.out.println("Numero de version del driver: " + numVersionDriver);
        System.out.println("Numero de version principal del driver: " + numVersionPrincipalDriver);
        System.out.println("Numero de version secundaria del driver: " + numVersionSecundariaDriver);
        System.out.println("URL de la base de datos: " + urlBBDD);
        System.out.println("Usuario conectado: " + usuarioConectado);
        System.out.println("¿Es de solo lectura? " + soloLectura);
    }

    static void mostrarTodasLasTablas(Connection con) throws SQLException {
        DatabaseMetaData md = con.getMetaData();
        ResultSet tablas = md.getTables("BDEmpresa", null, null, null);

        System.out.println("Tablas: ");

        while (tablas.next()) {
            System.out.println("Nombre: " + tablas.getString("TABLE_NAME") +
                    " Tipo: " + tablas.getString("TABLE_TYPE") + " Esquema: " +
                    tablas.getString("TABLE_SCHEM") +
                    " Catalogo: " + tablas.getString("TABLE_CAT"));
        }
    }

    static void mostrarInfoTabla(Connection con, String nombreTabla) throws SQLException {
        DatabaseMetaData md = con.getMetaData();
        ResultSet columnas = md.getColumns("BDEmpresa", null, nombreTabla, null);
        System.out.println("Columnas de la tabla " + nombreTabla);
        while (columnas.next()) {
            System.out.println("Nombre: " + columnas.getString("COLUMN_NAME") + " Tipo de datos: " +
                    columnas.getString("TYPE_NAME") + " Tamaño: " + columnas.getString("COLUMN_SIZE") +
                    " Acepta nulos: " + columnas.getString("IS_NULLABLE"));
        }
    }

    static void mostrarProcedimientos(Connection con) throws SQLException {
        DatabaseMetaData md = con.getMetaData();
        ResultSet procedimientos = md.getProcedures("BDEmpresa", null, null);

        System.out.println("Procedimientos: ");

        while (procedimientos.next()) {
            System.out.println("Nombre: " + procedimientos.getString("PROCEDURE_NAME") + " Tipo: " + procedimientos.getString("PROCEDURE_TYPE"));
        }

    }

    static void obtenerClavePrimaria(Connection con, String bd, String tabla) throws SQLException {
        DatabaseMetaData md = con.getMetaData();
        ResultSet clavesPrimarias = md.getPrimaryKeys(bd, null, tabla);

        System.out.println("Clave primaria de la tabla " + tabla);

        while (clavesPrimarias.next()) {
            System.out.println("Nombre: " + clavesPrimarias.getString("COLUMN_NAME") + " Nombre clave: "
                    + clavesPrimarias.getString("PK_NAME"));
        }
    }

    static void obtenerClavesForaneas(Connection con, String bd, String tabla) throws SQLException {
        DatabaseMetaData md = con.getMetaData();
        ResultSet clavesForaneas = md.getImportedKeys(bd, null, tabla);

        System.out.println("Claves foráneas de la tabla " + tabla);

        while (clavesForaneas.next()) {
            System.out.println("Nombre: " + clavesForaneas.getString("FKCOLUMN_NAME") + " Tabla a la que hace referencia: "
                    + clavesForaneas.getString("PKTABLE_NAME"));
        }
    }

    static void metadatosDeResultSet(Connection con, String consulta) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.println("Numero de columnas recuperadas: " + rsmd.getColumnCount());

        int i = 0;
        while (i < rsmd.getColumnCount()) {
            i++;
            System.out.println("Nombre de la columna: " + rsmd.getColumnName(i) +
                    "  - Tamanho: " + rsmd.getPrecision(i) +
                    " - Admite nulos: " + rsmd.isNullable(i));
        }
    }

}