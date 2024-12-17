// ActivityAlerta.java
package com.example.examenpmul1ev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityAlerta extends AppCompatActivity {

    private AsistenteBD asistenteBD;
    private String enteredText;
    private String detectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);

        asistenteBD = AsistenteBD.getInstance(this);

        detectedText = getIntent().getStringExtra("detectedText");
        enteredText = getIntent().getStringExtra("enteredText");

        TextView detectedTextView = findViewById(R.id.textoEncontrado);
        TextView enteredTextView = findViewById(R.id.enteredTextView);
        detectedTextView.setText(detectedText);
        enteredTextView.setText(enteredText);

        Button buttonYes = findViewById(R.id.buttonYes);
        Button buttonNo = findViewById(R.id.buttonNo);

        buttonYes.setOnClickListener(v -> handleButtonClick(true));
        buttonNo.setOnClickListener(v -> handleButtonClick(false));
    }

    private void handleButtonClick(boolean isValid) {
        asistenteBD.insertarRegistro(enteredText, isValid);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("checkboxValue", isValid ? 1 : 0);
        resultIntent.putExtra("detectedText", detectedText);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}