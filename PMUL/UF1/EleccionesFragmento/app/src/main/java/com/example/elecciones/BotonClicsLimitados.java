package com.example.elecciones;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BotonClicsLimitados extends Fragment {
    public boolean mostrarContador = true;
    public boolean desactivarAlFinal = true;
    Button boton;
    int numClics, maxClics;
    String texto;
    OnClickListener listener = null;

    public interface OnClickListener {
        public boolean onClick(int numClic, int maxClics);

        public void ultimoClic();
    }

    public void setOnClickListener(OnClickListener listener, int numClics, String textoBoton) {
        this.listener = listener;
        this.numClics = 0;
        this.maxClics = numClics;
        this.setTexto(textoBoton);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_boton_clics_limitados, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boton = view.findViewById(R.id.btnVotarFrg);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonFrgClic();
            }
        });
    }

    public void botonFrgClic() {
        if (listener != null)
            if (numClics < maxClics)
                if (listener.onClick(numClics + 1, maxClics)) {
                    if ((++numClics == maxClics)) {
                        if (desactivarAlFinal)
                            boton.setEnabled(false);
                        listener.ultimoClic();
                    }
                    setTextoBoton();
                }
    }

    public void setTexto(String texto) {
        this.texto = texto;
        setTextoBoton();
    }

    private void setTextoBoton() {
        String text = this.texto;
        if (mostrarContador) text += String.format(" %d/%d", numClics, maxClics);
        boton.setText(text);
    }
}