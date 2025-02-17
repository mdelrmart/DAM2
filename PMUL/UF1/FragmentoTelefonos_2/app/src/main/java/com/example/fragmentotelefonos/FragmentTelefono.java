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

public class FragmentTelefono extends Fragment implements onTelefonoListener {

    private TextView textView;
    private EditText editText;
    private ImageButton imageButton;

    private String numTelefono;

    private onFragmentTelefonoListener listener;


    Telefono telefono;


    public void setListener(onFragmentTelefonoListener listener, String numTelefono) {
        this.listener = listener;
        this.numTelefono = numTelefono;

    }

    public FragmentTelefono() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_telefono, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.textView);
        editText = view.findViewById(R.id.editText);
        imageButton = view.findViewById(R.id.imageButton);


        telefono = listener.obtenerTelefono(numTelefono);
        textView.setText(telefono.getTelefono());

        imageButton.setOnClickListener(v -> {

            if (!telefono.isOcupado()) {
                telefono.llamar(editText.getText().toString());
            }
            else {
                telefono.colgar();
            }

        });

    }

    @Override
    public void recibirLlamada(String telefonoOrigen) {
        editText.setEnabled(false);
        editText.setText(telefonoOrigen);
    }

    @Override
    public void recibirColgada() {
        editText.setEnabled(true);
        editText.setText("");

    }
}