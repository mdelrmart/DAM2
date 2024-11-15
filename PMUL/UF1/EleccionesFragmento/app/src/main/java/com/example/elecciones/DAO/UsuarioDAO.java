package com.example.elecciones.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.elecciones.Utiles;

public class UsuarioDAO {
    public static boolean comprobarContrasenha(String usuario, String contrasenha) {
        String contrasenhaHash = Utiles.generateHash(contrasenha);

        SQLiteDatabase db = Database.getInstance().getConexion();
        Cursor consulta = db.rawQuery("SELECT * FROM usuarios WHERE nif = " + "'" + usuario + "'", null);

        if (consulta.moveToFirst()) {
            int colIndexContrasenha = consulta.getColumnIndex("password");
            String contrasenhaBBDD = consulta.getString(colIndexContrasenha);

            if (contrasenhaHash.equals(contrasenhaBBDD)) {
                return true;
            }
        }
        consulta.close();

        return false;
    }

    public static boolean getHaVotado(String usuario) {
        SQLiteDatabase db = Database.getInstance().getConexion();
        Cursor consulta = db.rawQuery("SELECT * FROM usuarios WHERE nif = " + "'" + usuario + "' AND haVotado = 1", null);

        if (consulta.moveToFirst()) {
            int colIndexNIF = consulta.getColumnIndex("nif");
            String nifBBDD = consulta.getString(colIndexNIF);

            if (usuario.equals(nifBBDD)) {
                return true;
            }
        }
        consulta.close();

        return false;
    }

    public static void setHaVotado(String nif) {
        SQLiteDatabase bd = Database.getInstance().getConexion();

        // Consulta parametrizada
        bd.execSQL("UPDATE usuarios SET haVotado = 1 WHERE nif = ?", new String[]{nif});
    }

}