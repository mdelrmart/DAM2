package com.example.elecciones;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.elecciones.DAO.AsistenteBD;
import com.example.elecciones.DAO.CandidatoDAO;
import com.example.elecciones.DAO.UsuarioDAO;
import com.example.elecciones.databinding.ActivityVotacionBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Votacion extends AppCompatActivity implements BotonClicsLimitados.OnClickListener {
    private AppBarConfiguration appBarConfiguration;
    private ActivityVotacionBinding binding;

    AsistenteBD asistenteBD;

    int numVotosMaxPermitidos = 3;

    private ListView lvResultados;
    private Spinner spCandidato;

    Bundle usuarioPasado;
    String usuarioObtenido;

    // Almacenamos en memoria los candidatos que han sido votados para evitar que se pueda votar
    // al mismo más de una vez, no se puede guardar en la BBDD por secreto electoral.
    ArrayList<Integer> candidatosVotados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);

        // Obtenemos usuario y lo ponemos en la cabecera
        usuarioPasado = getIntent().getExtras();
        usuarioObtenido = usuarioPasado.getString("usuario");

        spCandidato = findViewById(R.id.candidato);
        lvResultados = findViewById(R.id.listaResultados);

        // Configuramos el fragmento con el límite de clics y el texto
        BotonClicsLimitados fragment = new BotonClicsLimitados(numVotosMaxPermitidos,"Votar");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.click_button, fragment)
                .commit();

        cargarCandidatosEnSpinner();
    }

    @Override
    public boolean onClickAttempt() {
        Candidato candidatoSeleccionado = (Candidato) spCandidato.getSelectedItem();

        // Nos aseguramos de que el candidato no sea -1
        if (candidatoSeleccionado.getCodCandidato() == -1) {
            mensajeSnackbar("Selecciona un candidato para votar");
            return false;
        }

        // Si el método registrarVoto devuelve falso (porque se está votando a un candidato ya votado), devolvemos false al fragment para no contar el clic
        if (registrarVoto(candidatoSeleccionado) == false) {
            return false;
        }

        guardarVoto(usuarioObtenido);
        return true;
    }

    private boolean registrarVoto(Candidato candidatoSeleccionado) {
        // Obtenemos el código del candidato y lo añadimos a la lista
        int codCandidatoSeleccionado = candidatoSeleccionado.getCodCandidato();

        // Verificamos si el candidato ya ha sido votado comprobando si el objecto que hemos seleccionado está presente en el ArrayList
        if (candidatosVotados.contains(codCandidatoSeleccionado)) {
            mensajeSnackbarAceptar("No se puede votar al mismo candidato más de una vez.");
            return false;
        }

        candidatosVotados.add(codCandidatoSeleccionado);

        // Volvemos a cargar los candidatos para desmarcar el seleccionado anteriormente
        cargarCandidatosEnSpinner();

        return true;
    }

    private void guardarVoto(String usuario) {
        if (candidatosVotados.size() == numVotosMaxPermitidos) {
            mensajeSnackbar("Votación finalizada");

            // Registrar los votos en la BBDD y marcar como ha votado
            for (int codCandidato : candidatosVotados) {
                CandidatoDAO.anhadirVotoCandidato(codCandidato);
            }

            UsuarioDAO.setHaVotado(usuario);

            listarVotos();
        }
    }

    private void listarVotos() {
        List<Candidato> candidatos = CandidatoDAO.obtenerCandidatos();

        ArrayList<String> candidatosConVotos = new ArrayList<>();

        for (Candidato candidato : candidatos) {
            // Evitamos el primer elemento "Selecciona un candidato" y los candidatos con cero votos
            if (candidato.getCodCandidato() != -1 && candidato.getVotos() != 0) {
                candidatosConVotos.add(candidato.getNombre() + " - " + candidato.getVotos() + " votos");
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, candidatosConVotos);

        lvResultados.setAdapter(adapter);
    }

    private void cargarCandidatosEnSpinner() {
        // Obtenemos los candidatos de la BBDD
        List<Candidato> candidatos = CandidatoDAO.obtenerCandidatos();

        // Crear un adaptador personalizado que muestra logo y nombre
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