package com.example.elecciones;

import static com.example.elecciones.DAO.UsuarioDAO.*;
import static com.example.elecciones.Utiles.NifOk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.elecciones.DAO.AsistenteBD;
import com.example.elecciones.DAO.Database;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    AsistenteBD asistenteBD;

    private EditText usuario;
    private EditText contrasenha;

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Database.init(this);

        usuario = findViewById(R.id.usuario);
        contrasenha = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ocultar teclado si está desplegado
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow((getCurrentFocus()).getWindowToken(), 0);
                } catch (Exception ignored) {
                }

                String usuarioString = usuario.getText().toString().toUpperCase();
                String contrasenhaString = contrasenha.getText().toString();

                // Comprobamos si vienen vacios los campos de usuario y contraseña
                if (usuarioString.isBlank() || contrasenhaString.isBlank()) {
                    mensajeSnackbar("No se ha introducido el usuario y/o contraseña");
                    return;
                }

                // Comprobamos si el DNI es válido
                if (!NifOk(usuarioString)) {
                    mensajeSnackbar("El NIF no es válido");
                    return;
                }

                // Consultamos en la BBDD si ese usuario ya ha votado y no permitimos el acceso
                if (getHaVotado(usuarioString)) {
                    mensajeSnackbarAceptar("Ya has votado, no se puede acceder.");
                    return;
                }

                // Consultamos en la BBDD si los datos del usuario son correctos y accedemos a la votación
                if (comprobarContrasenha(usuarioString, contrasenhaString)) {
                    Intent intentVotacion = new Intent(MainActivity.this, Votacion.class);
                    intentVotacion.putExtra("usuario", usuarioString);
                    startActivity(intentVotacion);

                } else {
                    mensajeSnackbarAceptar("El usuario (NIF) y/o contraseña no son correctos");
                    contrasenha.setText("");
                    return;
                }

                usuario.setText("");
                contrasenha.setText("");
            }
        });
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