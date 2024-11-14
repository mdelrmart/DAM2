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

    private Spinner spCandidato;

    private Button btnVotar;

    Bundle usuarioPasado;

    // Arrays de imágenes y textos
    int[] logos = {R.drawable.pp, R.drawable.psoe, R.drawable.sumar, R.drawable.vox};

    // Almacenamos en memoria los candidatos que han sido votados para evitar que se pueda votar al mismo más de una vez,
    // no se puede guardar en la BBDD por secreto electoral.
    ArrayList<Integer> candidatosVotados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVotacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        spCandidato = findViewById(R.id.candidato);
        btnVotar = findViewById(R.id.btnVotar);

        asistenteBD = new AsistenteBD(this);

        cargarCandidatosEnSpinner();

        // Obtenemos usuario y lo ponemos en la cabecera, comprobando antes que no sea null
        usuarioPasado = getIntent().getExtras();
        String usuarioObtenido = usuarioPasado.getString("usuario");

        if (getSupportActionBar() != null) {
            if (usuarioPasado != null)
                getSupportActionBar().setTitle("Elecciones - " + usuarioObtenido);
        }

        btnVotar.setText("Votar 0/" + numVotosMaxPermitidos);

        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos el objeto candidato seleccionado
                Candidato candidatoSeleccionado = (Candidato) spCandidato.getSelectedItem();

                // Si el codCandidato del candidatoSeleccionado es -1 no seguimos
                if (candidatoSeleccionado.getCodCandidato() == -1) {
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

        // Crear un adaptador personalizado para mostrar logo y nombre
        CandidatoAdapter adapter = new CandidatoAdapter(this, candidatos);

        // Asignar el adaptador al Spinner
        spCandidato.setAdapter(adapter);
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