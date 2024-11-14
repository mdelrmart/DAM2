package com.example.elecciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AsistenteBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "elecciones.db";
    private static final int VERSION_BD = 7;
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
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('53614816E','" + Utiles.generateHash("abc123") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('52492165R','" + Utiles.generateHash("abc123.") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('12345678Z','" + Utiles.generateHash("abc123,") + "')");

        // Insertar datos en la tabla partidos (solo nombre y color)
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PP', 1)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PSOE', 2)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('Sumar', 3)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('VOX', 4)");

        // Insertar datos en la tabla candidatos (codPartido corresponde al ID del partido)
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'Alberto Nuñez Feijoó')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'José Luis Martínez-Almeida Navasqüés')");
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
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('53614816E','" + Utiles.generateHash("abc123") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('52492165R','" + Utiles.generateHash("abc123.") + "')");
        bd.execSQL("INSERT INTO usuarios (nif, password) VALUES('12345678Z','" + Utiles.generateHash("abc123.,") + "')");

        // Insertar datos en la tabla partidos (solo nombre y color)
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PP', 1)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('PSOE', 2)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('Sumar', 3)");
        bd.execSQL("INSERT INTO partidos (nombre, color) VALUES ('VOX', 4)");

        // Insertar datos en la tabla candidatos (codPartido corresponde al ID del partido)
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'Alberto Nuñez Feijoó')");
        bd.execSQL("INSERT INTO candidatos (codPartido, nombre) VALUES (1, 'José Luis Martínez-Almeida Navasqüés')");
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

    public void anhadirVotoCandidato(int codCandidato) {
        SQLiteDatabase bd = this.getWritableDatabase();

        // Consulta parametrizada
        bd.execSQL("UPDATE candidatos SET votos = votos + 1 WHERE codCandidato = ?", new String[]{String.valueOf(codCandidato)});
    }

    public void setHaVotado(String nif) {
        SQLiteDatabase bd = this.getWritableDatabase();

        // Consulta parametrizada
        bd.execSQL("UPDATE usuarios SET haVotado = 1 WHERE nif = ?", new String[]{nif});
    }

    // Array de IDs de los logos en drawable, cada posición debe corresponder con el código del partido
    private final int[] logosPartidos = {
            R.drawable.pp, // codPartido = 1
            R.drawable.psoe, // codPartido = 2
            R.drawable.sumar, // codPartido = 3
            R.drawable.vox // codPartido = 4
    };

    public List<Candidato> obtenerCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM candidatos";
        Cursor datos = db.rawQuery(consulta, null);

        candidatos.add(0, new Candidato(-1,"Selecciona un candidato",0,0));

        if (datos.moveToFirst()) {
            do {
                int codCandidato = datos.getInt(0);
                String nombreCandidato = datos.getString(2);
                int codPartido = datos.getInt(1);

                // Asignamos el logo correspondiente usando el código del partido
                // Restamos 1 ya que los índices de los array empiezan en el valor 0
                int logoPartido = logosPartidos[codPartido - 1];
                candidatos.add(new Candidato(codCandidato, nombreCandidato, codPartido, logoPartido));
            } while (datos.moveToNext());
        }
        datos.close();
        db.close();

        return candidatos;
    }

}
