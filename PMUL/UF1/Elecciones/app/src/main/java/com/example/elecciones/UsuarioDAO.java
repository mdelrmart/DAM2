package com.example.elecciones;

import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {

    public static void setHaVotado(String nif) {
        SQLiteDatabase bd = Database.getInstance().getConexion();

        // Consulta parametrizada
        bd.execSQL("UPDATE usuarios SET haVotado = 1 WHERE nif = ?", new String[]{nif});
    }

}