package com.example.elecciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database {
    private static Database instance;

    public static Database getInstance() {
        if (instance == null) throw new IllegalStateException("No se ha creado la conexion con la base de datos");

        return instance;
    }

    public static void init(Context context)
    {
        instance = new Database(context);
    }

    private final SQLiteDatabase conexion;

    private Database(Context context) {
        conexion = new AsistenteBD(context).getWritableDatabase();
    }

    public SQLiteDatabase getConexion() {
        return conexion;
    }
}
