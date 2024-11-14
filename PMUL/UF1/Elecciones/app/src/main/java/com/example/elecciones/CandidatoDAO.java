package com.example.elecciones;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {
    public static void anhadirVotoCandidato(int codCandidato) {
        SQLiteDatabase bd = Database.getInstance().getConexion();

        // Consulta parametrizada
        bd.execSQL("UPDATE candidatos SET votos = votos + 1 WHERE codCandidato = ?", new String[]{String.valueOf(codCandidato)});
    }

    public static List<Candidato> obtenerCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();
        SQLiteDatabase db = Database.getInstance().getConexion();
        String consulta = "SELECT codCandidato, nombre, codPartido, votos FROM candidatos";
        Cursor datos = db.rawQuery(consulta, null);

        candidatos.add(0, new Candidato(-1,"Selecciona un candidato",0,0));

        if (datos.moveToFirst()) {
            do {
                int codCandidato = datos.getInt(0);
                String nombreCandidato = datos.getString(1);
                int codPartido = datos.getInt(2);
                int votos = datos.getInt(3);

                candidatos.add(new Candidato(codCandidato, nombreCandidato, codPartido, votos));
            } while (datos.moveToNext());
        }

        datos.close();

        return candidatos;
    }

    // Otra forma de recuperar los votos
    public static List<Integer> getVotosDeCandidatos() {
        List<Integer> votos = new ArrayList<>();

        for (Candidato candidato : obtenerCandidatos()) {
            votos.add(candidato.getVotos());
        }

        return votos;
    }

}
