package com.example.fragmentotelefonos.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fragmentotelefonos.Telefono;

import java.util.ArrayList;

public class AsistenteBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "telefonos.db";
    private static final int VERSION_BD = 1;
    private static AsistenteBD instance;

    private AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    public static AsistenteBD getInstance(Context context) {
        if (instance == null) {
            instance = new AsistenteBD(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        // Crear tabla
        String sqlCreateTelefonos = "CREATE TABLE telefonos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT)";
        bd.execSQL(sqlCreateTelefonos);

        // Insertar datos en la tabla comunidades autonomas
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Jose', '33')");
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Fernando', '44')");
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Francisco', '55')");
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Manuel', '66')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE telefonos");

        // Crear tabla
        String sqlCreateTelefonos = "CREATE TABLE telefonos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT)";
        bd.execSQL(sqlCreateTelefonos);

        // Insertar datos en la tabla comunidades autonomas
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Jose', '33')");
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Fernando', '44')");
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Francisco', '55')");
        bd.execSQL("INSERT INTO telefonos (nombre, telefono) VALUES ('Manuel', '66')");
    }


    public ArrayList<Telefono> obtenerTelefonos(SQLiteDatabase db) {
        ArrayList<Telefono> listaTelefonos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT nombre, telefono FROM telefonos", null);

        if (c.moveToFirst()) {
            do {
                String telefono = c.getString(1);
                listaTelefonos.add(new Telefono(telefono));
            } while (c.moveToNext());
            c.close();
        }
        return listaTelefonos;
    }
}
