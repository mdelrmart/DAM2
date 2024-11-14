package com.example.elecciones;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PartidoDAO {

    public static Partido select(int codPartido) {
        SQLiteDatabase conexion = Database.getInstance().getConexion();
        String consulta = "SELECT codPartido, nombre, color FROM partidos WHERE codPartido = ?";
        Cursor datos = conexion.rawQuery(consulta, new String[] {String.valueOf(codPartido)});

        if (datos.moveToFirst()) {
            int codigo = datos.getInt(0);
            String nombre = datos.getString(1);
            int color = datos.getInt(2);

            return new Partido(codigo, nombre, color);
        }

        datos.close();
        return null;
    }

}
