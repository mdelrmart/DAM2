package com.example.busquedalistafragmento.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AsistenteBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "comunidadesAutonomas.db";
    private static final int VERSION_BD = 1;

    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        // Crear tabla
        String sqlCreateComunidadesAutonomas = "CREATE TABLE comunidadesAutonomas (codComunidad INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
        bd.execSQL(sqlCreateComunidadesAutonomas);

        // Insertar datos en la tabla comunidades autonomas
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Andalucía')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Aragón')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Asturias')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Cantabria')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Castilla-La Mancha')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Castilla y León')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Cataluña')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Extremadura')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Galicia')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Islas Baleares')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Islas Canarias')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('La Rioja')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Madrid')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Murcia')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Navarra')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('País Vasco')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Valencia')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Ceuta')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Melilla')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE comunidadesAutonomas");

        // Crear tabla
        String sqlCreateComunidadesAutonomas = "CREATE TABLE comunidadesAutonomas (codComunidad INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
        bd.execSQL(sqlCreateComunidadesAutonomas);

        // Insertar datos en la tabla comunidades autonomas
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Andalucía')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Aragón')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Asturias')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Cantabria')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Castilla-La Mancha')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Castilla y León')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Cataluña')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Extremadura')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Galicia')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Islas Baleares')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Islas Canarias')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('La Rioja')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Madrid')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Murcia')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Navarra')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('País Vasco')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Valencia')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Ceuta')");
        bd.execSQL("INSERT INTO comunidadesAutonomas (nombre) VALUES ('Melilla')");
    }

}
