package com.example.mantenimientoclientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AsistenteBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "clientes.db";
    private static final int VERSION_BD = 8;
    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateProvincias = "CREATE TABLE provincias (codProvincia INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT)";
        String sqlCreateClientes= "CREATE TABLE clientes (codCliente INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT,NIF TEXT,codProvincia INTEGER, VIP INTEGER, latitud REAL, longitud REAL)";
        db.execSQL(sqlCreateProvincias);
        db.execSQL(sqlCreateClientes);
        ContentValues cv = new ContentValues();
        cv.put("nombre", "A Coruña"); db.insert("provincias", null, cv);
        cv.put("nombre", "Lugo"); db.insert("provincias", null, cv);
        cv.put("nombre", "Ourense"); db.insert("provincias", null, cv);
        cv.put("nombre", "Pontevedra"); db.insert("provincias", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE clientes");
        db.execSQL("DROP TABLE provincias");

        String sqlCreateProvincias = "CREATE TABLE provincias (codProvincia INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT)";
        String sqlCreateClientes= "CREATE TABLE clientes (codCliente INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT,NIF TEXT,codProvincia INTEGER, VIP INTEGER, latitud REAL, longitud REAL)";
        db.execSQL(sqlCreateProvincias);
        db.execSQL(sqlCreateClientes);
        ContentValues cv = new ContentValues();
        cv.put("nombre", "A Coruña"); db.insert("provincias", null, cv);
        cv.put("nombre", "Lugo"); db.insert("provincias", null, cv);
        cv.put("nombre", "Ourense"); db.insert("provincias", null, cv);
        cv.put("nombre", "Pontevedra"); db.insert("provincias", null, cv);
    }

    public boolean anhadirDatos(String nombre, String apellidos, String dni, int codProvincia, int vip, String latitud, String longitud) {
        SQLiteDatabase bd = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("apellidos", apellidos);
        contentValues.put("NIF", dni);
        contentValues.put("codProvincia", codProvincia);
        contentValues.put("VIP", vip);
        contentValues.put("latitud", latitud);
        contentValues.put("longitud", longitud);

        long resultado = bd.insert("clientes", null, contentValues);

        if (resultado == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean modificarDatos(int codCliente, String nombre, String apellidos, String dni, int codProvincia, int vip, String latitud, String longitud) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String where = "codCliente = " + codCliente;

        contentValues.put("nombre", nombre);
        contentValues.put("apellidos", apellidos);
        contentValues.put("NIF", dni);
        contentValues.put("codProvincia", codProvincia);
        contentValues.put("VIP", vip);
        contentValues.put("latitud", latitud);
        contentValues.put("longitud", longitud);

        long resultado = bd.update("clientes", contentValues, where,null);

        if (resultado == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public List<Provincia> obtenerProvincias() {
        List<Provincia> provincias = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM provincias";
        Cursor datos = db.rawQuery(consulta, null);

        //Agregamos a la lista un objeto Provincia vacío
        provincias.add(0,null);

        if (datos.moveToFirst()) {
            do {
                int codProvincia = datos.getInt(0);
                String nombreProvincia = datos.getString(1);
                provincias.add(new Provincia(codProvincia, nombreProvincia));
            } while (datos.moveToNext());
        }
        datos.close();
        db.close();

        return provincias;
    }

    /*
    public Cursor obtenerDatos() {
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM CLIENTES";
        Cursor datos = db.rawQuery(consulta, null);
        return datos;
    }

   public Cursor obtenerCodCliente(String nombre) {
        SQLiteDatabase db= this.getWritableDatabase();
        String consulta = "SELECT codCliente FROM CLIENTE WHERE codCliente=" + nombre;
        Cursor datos = db.rawQuery(consulta, null);
        return datos;
    }
    */
}
