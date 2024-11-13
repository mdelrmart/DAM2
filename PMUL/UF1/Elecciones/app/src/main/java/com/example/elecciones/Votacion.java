package com.example.elecciones;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.elecciones.databinding.ActivityVotacionBinding;

import java.util.ArrayList;
import java.util.List;

public class Votacion extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityVotacionBinding binding;

    AsistenteBD asistenteBD;

    int numVotosPermitidos = 3;

    private Spinner candidato;
    private Button btnVotar;

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

        // Almacenamos en memoria los candidatos que han sido votados para evitar que se pueda votar al mismo más de una vez, no se puede guardar en la BBDD por seguridad.
        ArrayList<Integer> candidatosVotados = new ArrayList<>();

        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos el objeto candidato seleccionado
                Candidato candidatoSeleccionado = (Candidato) candidato.getSelectedItem();

                int codCandidatoSeleccionado;

                // Si el tamaño del ArrayList es diferente al de numVotosPermitidos
                if (candidatosVotados.size() != numVotosPermitidos) {
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
}