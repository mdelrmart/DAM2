package com.example.elecciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AsistenteBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "elecciones.db";
    private static final int VERSION_BD = 1;

    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        // Crear tablas
        String sqlCreateUsuarios = "CREATE TABLE usuarios (nif TEXT PRIMARY KEY, password TEXT, haVotado INTEGER DEFAULT 0)";
        String sqlCreatePartidos = "CREATE TABLE partidos (codPartido INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, color INTEGER)";
        String sqlCreateCandidatos = "CREATE TABLE candidatos (codCandidato INTEGER PRIMARY KEY AUTOINCREMENT, codPartido INTEGER, nombre TEXT, votos INTEGER DEFAULT 0)";

        bd.execSQL(sqlCreateUsuarios);
        bd.execSQL(sqlCreatePartidos);
        bd.execSQL(sqlCreateCandidatos);

        // Insertar datos en la tabla usuarios
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('12345678Z','" + Utiles.generateHash("abc123.") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('87654321X','" + Utiles.generateHash("abc123.") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('55555555K','" + Utiles.generateHash("abc123.") + "')");

        // Insertar datos en la tabla partidos (solo nombre y color)
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PP', -12418845)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PSOE', -1891806)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('Sumar', -65364)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('VOX', -15340231)");

        // Insertar datos en la tabla candidatos (codPartido corresponde al ID del partido)
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'Alberto Nuñez Feijoó')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'José Luis Martínez-Almeida')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'Isabel Díaz Ayuso')");

        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (2, 'Pedro Sánchez')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (2, 'Óscar Puente')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (2, 'Fernando Grande-Marlaska')");

        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (3, 'Yolanda Díaz')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (3, 'Rafael Cofiño')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (3, 'Carlos Martín Urriza')");

        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (4, 'Santiago Abascal')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (4, 'Javier Ortega Smith')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (4, 'Pepa Millán Parro')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE usuarios");
        bd.execSQL("DROP TABLE partidos");
        bd.execSQL("DROP TABLE candidatos");

        // Crear tablas
        String sqlCreateUsuarios = "CREATE TABLE usuarios (nif TEXT PRIMARY KEY, password TEXT, haVotado INTEGER DEFAULT 0)";
        String sqlCreatePartidos = "CREATE TABLE partidos (codPartido INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, color INTEGER)";
        String sqlCreateCandidatos = "CREATE TABLE candidatos (codCandidato INTEGER PRIMARY KEY AUTOINCREMENT, codPartido INTEGER, nombre TEXT, votos INTEGER DEFAULT 0)";

        bd.execSQL(sqlCreateUsuarios);
        bd.execSQL(sqlCreatePartidos);
        bd.execSQL(sqlCreateCandidatos);

        // Insertar datos en la tabla usuarios
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('12345678Z','" + Utiles.generateHash("abc123.") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('87654321X','" + Utiles.generateHash("abc123.") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('55555555K','" + Utiles.generateHash("abc123.") + "')");

        // Insertar datos en la tabla partidos (solo nombre y color)
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PP', -12418845)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PSOE', -1891806)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('Sumar', -65364)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('VOX', -15340231)");

        // Insertar datos en la tabla candidatos (codPartido corresponde al ID del partido)
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'Alberto Nuñez Feijoó')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'José Luis Martínez-Almeida')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'Isabel Díaz Ayuso')");

        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (2, 'Pedro Sánchez')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (2, 'Óscar Puente')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (2, 'Fernando Grande-Marlaska')");

        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (3, 'Yolanda Díaz')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (3, 'Rafael Cofiño')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (3, 'Carlos Martín Urriza')");

        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (4, 'Santiago Abascal')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (4, 'Javier Ortega Smith')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (4, 'Pepa Millán Parro')");
    }
}
