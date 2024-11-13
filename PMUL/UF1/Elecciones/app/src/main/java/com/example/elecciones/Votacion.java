package com.example.elecciones;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.elecciones.databinding.ActivityVotacionBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Votacion extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityVotacionBinding binding;

    AsistenteBD asistenteBD;

    int numVotosMaxPermitidos = 3;

    private Spinner candidato;

    private Button btnVotar;

    Bundle usuarioPasado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVotacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        candidato =  findViewById(R.id.candidato);
        btnVotar = findViewById(R.id.btnVotar);

        asistenteBD = new AsistenteBD(this);

        cargarCandidatosEnSpinner();

        // Obtenemos usuario y lo ponemos en la cabecera, comprobando antes que no sea null
        usuarioPasado = getIntent().getExtras();
        String usuarioObtenido = usuarioPasado.getString("usuario");

        if (getSupportActionBar() != null) {
            if (usuarioPasado != null) getSupportActionBar().setTitle("Elecciones - " + usuarioObtenido);
        }

        // Almacenamos en memoria los candidatos que han sido votados para evitar que se pueda votar al mismo más de una vez,
        // no se puede guardar en la BBDD por secreto electoral.
        ArrayList<Integer> candidatosVotados = new ArrayList<>();

        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos el objeto candidato seleccionado
                Candidato candidatoSeleccionado = (Candidato) candidato.getSelectedItem();

                int codCandidatoSeleccionado;

                // Si el tamaño del ArrayList es diferente al de numVotosPermitidos
                if (candidatosVotados.size() != numVotosMaxPermitidos) {
                    // Si el candidato seleccionado no es null obtenemos el codCandidato y lo añadimos al ArrayList
                    if (candidatoSeleccionado != null) {
                        codCandidatoSeleccionado = candidatoSeleccionado.getCodCandidato();
                        candidatosVotados.add(codCandidatoSeleccionado);
                    }
                } else {
                    // Al llegar al limite de numVotosPermitidos vamos añadiendolos a la BBDD
                    for (int i = 0; i < candidatosVotados.size(); i++) {
                        asistenteBD.anhadirVotoCandidato(candidatosVotados.get(i));
                    }
                    //asistenteBD.anhadirUsuarioHaVotado(codCandidatoSeleccionado);
                    asistenteBD.setHaVotado(usuarioObtenido);
                    asistenteBD.close();
                }
            }
        });

    }

    private void cargarCandidatosEnSpinner() {
        // Obtener todos los nombres de la base de datos
        List<Candidato> candidatos = asistenteBD.obtenerCandidatos();

        // Crear un ArrayAdapter usando la lista de nombres y un layout predefinido para el Spinner
        ArrayAdapter<Candidato> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, candidatos);

        // Especificar el layout para cuando el menú del Spinner esté desplegado
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        candidato.setAdapter(adapter);
    }

    //region *** Operaciones BBDD ***

    //endregion

    //region *** Métodos para sacar mensajes por pantalla ***
    private void mensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private void mensajeSnackbar(String mensaje) {
        Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG).show();
    }

    private void mensajeSnackbarAceptar(String mensaje) {
        //Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG).show();
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
        snackbar.show();
    }
    //endregion
}