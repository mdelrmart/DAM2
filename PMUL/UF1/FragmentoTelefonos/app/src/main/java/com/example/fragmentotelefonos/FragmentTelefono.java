package com.example.fragmentotelefonos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

// Fragmento que representa la interfaz de un teléfono
public class FragmentTelefono extends Fragment implements onTelefonoListener {

    private TextView textView; // Muestra el número del teléfono
    private EditText editText; // Campo de texto para ingresar el número a llamar
    private ImageButton imageButton; // Botón para realizar o colgar una llamada

    private String numTelefono; // Número del teléfono asociado
    private onFragmentTelefonoListener listener; // Listener para obtener el teléfono

    Telefono telefono; // Instancia del teléfono gestionado

    // Asocia un listener y el número del teléfono
    public void setListener(onFragmentTelefonoListener listener, String numTelefono) {
        this.listener = listener;
        this.numTelefono = numTelefono;
    }

    public FragmentTelefono() {
        // Constructor vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_telefono, container, false); // Infla el diseño del fragmento
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.textView);
        editText = view.findViewById(R.id.editText);
        imageButton = view.findViewById(R.id.imageButton);

        telefono = listener.obtenerTelefono(numTelefono); // Obtiene el teléfono asociado
        textView.setText(telefono.getTelefono()); // Muestra el número del teléfono

        // Configura el botón para llamar o colgar
        imageButton.setOnClickListener(v -> {
            if (!telefono.isOcupado()) {
                telefono.llamar(editText.getText().toString());
            } else {
                telefono.colgar();
            }
        });
    }

    // Implementación de los métodos del listener
    @Override
    public void recibirLlamada(String telefonoOrigen) {
        editText.setEnabled(false); // Deshabilita la edición durante la llamada
        editText.setText(telefonoOrigen); // Muestra el número del origen
    }

    @Override
    public void recibirColgada() {
        editText.setEnabled(true); // Habilita la edición tras colgar
        editText.setText(""); // Limpia el campo de texto
    }
}