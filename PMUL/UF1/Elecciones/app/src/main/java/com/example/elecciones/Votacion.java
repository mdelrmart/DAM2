package com.example.elecciones;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

    // Almacenamos en memoria los candidatos que han sido votados para evitar que se pueda votar al mismo más de una vez,
    // no se puede guardar en la BBDD por secreto electoral.
    ArrayList<Integer> candidatosVotados = new ArrayList<>();

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

        btnVotar.setText("Votar 0/" + numVotosMaxPermitidos);

        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos el objeto candidato seleccionado
                Candidato candidatoSeleccionado = (Candidato) candidato.getSelectedItem();

                // Si el candidatoSeleccionado es null no seguimos
                if (candidatoSeleccionado == null) {
                    return;
                }

                // Agregamos el voto y comprobamos el límite
                registrarVoto(candidatoSeleccionado);
                comprobarLimiteVotos(usuarioObtenido);
            }
        });

    }

    private void registrarVoto(Candidato candidatoSeleccionado) {
        // Obtenemos el código del candidato y lo añadimos a la lista
        int codCandidatoSeleccionado = candidatoSeleccionado.getCodCandidato();

        // Verificamos si el candidato ya ha sido votado comprobando si el objecto que hemos seleccionado está presente en el ArrayList
        if (candidatosVotados.contains(codCandidatoSeleccionado)) {
            mensajeSnackbarAceptar("No se puede votar al mismo candidato más de una vez.");
            return;
        }

        candidatosVotados.add(codCandidatoSeleccionado);

        // Actualizamos el texto del botón para ir mostrando el progreso
        btnVotar.setText("Votar " + candidatosVotados.size() + "/" + numVotosMaxPermitidos);

        // Volvemos a cargar los candidatos para quitar los que ya votamos
        cargarCandidatosEnSpinner();
    }

    private void comprobarLimiteVotos(String usuario) {
        if (candidatosVotados.size() == numVotosMaxPermitidos) {
            // Deshabilitamos el botón
            btnVotar.setEnabled(false);

            System.out.println("Límite de votos alcanzado");

            // Registrar los votos en la BBDD y marcar como ha votado
            for (int codCandidato : candidatosVotados) {
                asistenteBD.anhadirVotoCandidato(codCandidato);
            }
            asistenteBD.setHaVotado(usuario);
            asistenteBD.close();
        }
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