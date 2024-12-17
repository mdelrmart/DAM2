package com.example.examenpmul1ev;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;

public class AsistenteBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "alertas.db";
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
        String sqlCreate = "CREATE TABLE registros (id INTEGER PRIMARY KEY AUTOINCREMENT, alerta TEXT, valida BOOLEAN)";
        bd.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE registros");

        // Crear tabla
        String sqlCreate = "CREATE TABLE registros (id INTEGER PRIMARY KEY AUTOINCREMENT, alerta TEXT, valida BOOLEAN)";
        bd.execSQL(sqlCreate);
    }

    public void insertarRegistro(String text, boolean isValid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("alerta", text);
        values.put("valida", isValid ? 1 : 0);
        db.insert("registros", null, values);
    }

    // Obtenemos los registros de la base de datos
    /*public ArrayList<Registro> obtenerRegistros(SQLiteDatabase db) {
        ArrayList<Registro> listaRegistros = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT alerta,valida FROM registros", null);

        if (c.moveToFirst()) {
            do {
                String registro = c.getString(1);
                int registro2 = c.getInt(2);
                listaRegistros.add(new Registro(registro,registro2));
            } while (c.moveToNext());
            c.close();
        }
        return listaRegistros;
    }*/

    public ArrayList<Registro> obtenerRegistros(SQLiteDatabase db) {
        ArrayList<Registro> registros = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT alerta,valida FROM registros", null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int column1Index = cursor.getColumnIndex("alerta");
                    int column2Index = cursor.getColumnIndex("valida");

                    if (column1Index != -1 && column2Index != -1) {
                        String column1 = cursor.getString(column1Index);
                        int column2 = cursor.getInt(column2Index);

                        registros.add(new Registro(column1.toString(), column2));
                    }
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return registros;
    }
}

