// MainActivity.java
package com.example.examenpmul1ev;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ALERTA_REQUEST_CODE = 1;
    LinearLayout linearLayout;
    AsistenteBD asistenteBD;
    TextView textViewCount;
    ListView listaRegistros;

    ArrayList<String> textosPara = new ArrayList<>();
    ArrayList<String> textosAsunto = new ArrayList<>();

    int contador = 0;

    @Override
    protected void onRestart() {
        super.onRestart();
        poblarLista();


        for (String text : textosPara) {
            if (text.contains("@ot.com")) {
                contador++;
            }
        }
        textViewCount.setText("Contador veces detectadas correo @ot.com: " + contador);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        asistenteBD = AsistenteBD.getInstance(this); // Inicializamos el asistente base datos

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayout = findViewById(R.id.linearLayout); // Referencia al contenedor de fragmentos
        textViewCount = findViewById(R.id.textViewCount); // Referencia al TextView de conteo

        String[] textosFragmentos = {"Para", "Asunto", "Cuerpo"};

        ArrayList<ArrayList<String>> textoBuscar = new ArrayList<>();

        textosPara.add("@ot.com");

        textosAsunto.add("ascensor");
        textosAsunto.add("fuego");

        // Añadimos los textos a buscar en el fragmento "Para"
        for (int i = 0; i < textosPara.size(); i++) {
            textoBuscar.add(textosPara);
        }

        // Añadimos los textos a buscar en el fragmento "Asunto"
        for (int i = 0; i < textosAsunto.size(); i++) {
            textoBuscar.add(textosAsunto);
        }

        // Expresión regular para IBAN
        String regexIBAN = "ES\\d{2}\\s?\\d{4}\\s?\\d{4}\\s?\\d{2}\\s?\\d{10}";
        // Expresión regilar para DNI
        String regexDNI = "\\d{8}[a-hj-np-tv-z]";

        listaRegistros = findViewById(R.id.listaRegistros);
        poblarLista(); // Asegúrate de poblar la lista

        // Creamos un fragmento para cada caja de texto
        for (int i = 0; i < 3; i++) {
            FragmentContainerView containerView = new FragmentContainerView(this); // Contenedor para el fragmento
            containerView.setId(View.generateViewId()); // Genera un ID único para el contenedor
            TextoFragment fragmento;

            // Si el campo es el último, se le pasa el regex para "Cuerpo"
            if (i == 2) {
                // Le pasamos la expresión regular para IBAN y DNI para el campo "Cuerpo"
                fragmento = TextoFragment.newInstance(textosFragmentos[i], new ArrayList<>(), regexIBAN + "|" + regexDNI);
            } else {
                // Le pasamos una expresión regular vacía para los otros campos
                fragmento = TextoFragment.newInstance(textosFragmentos[i], textoBuscar.get(i), "");
            }

            linearLayout.addView(containerView); // Agrega el contenedor al diseño principal
            getSupportFragmentManager().beginTransaction().add(containerView.getId(), fragmento).commit(); // Agrega el fragmento al contenedor
        }

        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetDatabaseAndExit();
            }
        });
    }

    private void resetDatabaseAndExit() {
        // Borra la base de datos
        SQLiteDatabase db = asistenteBD.getWritableDatabase();
        asistenteBD.onUpgrade(db, 0, 0);
        Toast.makeText(this, "Hecho", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void poblarLista() {
        ArrayList<Registro> registros = asistenteBD.obtenerRegistros(asistenteBD.getReadableDatabase());
        RegistroAdapter adapter = new RegistroAdapter(this, registros);

        listaRegistros.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALERTA_REQUEST_CODE && resultCode == RESULT_OK) {
            int checkboxValue = data.getIntExtra("checkboxValue", 0);
            String detectedText = data.getStringExtra("detectedText");

            if (checkboxValue == 1) {
                textosPara.remove(detectedText);
                textosAsunto.remove(detectedText);
            }
        }
    }
}