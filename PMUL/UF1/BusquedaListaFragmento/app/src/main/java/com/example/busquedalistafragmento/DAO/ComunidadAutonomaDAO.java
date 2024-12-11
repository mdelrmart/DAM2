package com.example.busquedalistafragmento.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.busquedalistafragmento.ComunidadAutonoma;

import java.util.ArrayList;
import java.util.List;

public class ComunidadAutonomaDAO {

    public static List<ComunidadAutonoma> obtenerComunidadesAutonomas(){
        List<ComunidadAutonoma> comunidadesAutonomas = new ArrayList<>();
        SQLiteDatabase db = Database.getInstance().getConexion();
        String consulta = "SELECT codComunidad, nombre FROM comunidadesAutonomas";
        Cursor datos =  db.rawQuery(consulta,null);

        if (datos.moveToFirst()) {
            do {
                int codComunidad = datos.getInt(0);
                String nombreComunidad = datos.getString(1);

                comunidadesAutonomas.add(new ComunidadAutonoma(codComunidad, nombreComunidad));
            } while (datos.moveToNext());
        }

        datos.close();

        return comunidadesAutonomas;
    }


}
