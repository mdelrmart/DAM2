package com.example.elecciones;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                String usuarioString = usuario.getText().toString().toUpperCase();
                String contrasenhaString = contrasenha.getText().toString();

                // Comprobamos si vienen vacios los campos de usuario y contraseña
                if (usuarioString.isBlank() || contrasenhaString.isBlank()) {
                    mensajeSnackbar("No se ha introducido el usuario y/o contraseña");
                    return;
                }

                // Consultamos en la BBDD si ese usuario ya ha votado y no permitimos el acceso
                if (getHaVotado(usuarioString)) {
                    mensajeSnackbarAceptar("Ya has votado, no se puede acceder.");
                    return;
                }

                // Consultamos en la BBDD si los datos del usuario son correctos y accedemos a la votación
                if (comprobarContrasenha(usuarioString,contrasenhaString)) {
                    Intent intentVotacion = new Intent(MainActivity.this, Votacion.class);
                    intentVotacion.putExtra("usuario", usuarioString);
                    startActivity(intentVotacion);

                    // Un poco chapuza, finalizamos el intent actual para no guardar los datos en los campos
                    // TODO mejorar ?
                    finish();
                }
                else {
                    mensajeSnackbarAceptar("El usuario y/o contraseña no son correctos");
                }
            }
        });
    }

    //region *** Operaciones BBDD ***
    public boolean comprobarContrasenha(String usuario, String contrasenha) {
        String contrasenhaHash = Utiles.generateHash(contrasenha);

        SQLiteDatabase bd = new AsistenteBD(this).getReadableDatabase();
        Cursor consulta = bd.rawQuery("SELECT * FROM usuarios WHERE nif = " + "'" + usuario + "'", null);

        if (consulta.moveToFirst()) {
            int colIndexContrasenha = consulta.getColumnIndex("password");
            String contrasenhaBBDD = consulta.getString(colIndexContrasenha);

            if (contrasenhaHash.equals(contrasenhaBBDD)) {
                return true;
            }
        }
        consulta.close();
        bd.close();
        return false;
    }

    public boolean getHaVotado(String usuario) {
        SQLiteDatabase bd = new AsistenteBD(this).getReadableDatabase();
        Cursor consulta = bd.rawQuery("SELECT * FROM usuarios WHERE nif = " + "'" + usuario + "' AND haVotado = 1", null);

        if (consulta.moveToFirst()) {
            int colIndexNIF = consulta.getColumnIndex("nif");
            String nifBBDD = consulta.getString(colIndexNIF);

            if (usuario.equals(nifBBDD)) {
                return true;
            }
        }
        consulta.close();
        bd.close();
        return false;
    }
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