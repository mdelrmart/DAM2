package com.example.elecciones;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BotonClicsLimitados extends Fragment {

    private String textoBoton;
    private int limiteClics;
    private int clicsActuales = 0;
    private OnClickListener onClickListener;

    private Button btnClicsLimitados;

    // Constructor vacio
    public BotonClicsLimitados() {

    }

    // Constructor para inicializar el limite de clics y el texto del boton
    public BotonClicsLimitados(int limiteClics, String textoBoton) {
        this.limiteClics = limiteClics;
        this.textoBoton = textoBoton;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verificamos que la actividad implemente la interfaz
        if (context instanceof OnClickListener) {
            onClickListener = (OnClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar OnClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.boton_clics_limitados, container, false);

        btnClicsLimitados = view.findViewById(R.id.btnClicsLimitados);

        btnClicsLimitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manejarClic();
            }
        });

        // Establecemos el texto del botón al crearlo
        btnClicsLimitados.setText(textoBoton + " " + clicsActuales +"/" + limiteClics);

        return view;
    }

    private void manejarClic() {
        if (onClickListener != null) {
            boolean clicRegistrado = onClickListener.onClickAttempt();
            if (clicRegistrado) {
                clicsActuales++;

                // Actualizamos el texto del botón al clicarlo
                btnClicsLimitados.setText(textoBoton + " " + clicsActuales +"/" + limiteClics);

                if (clicsActuales >= limiteClics) {
                    btnClicsLimitados.setEnabled(false);
                    //Snackbar.make(requireView(), "Límite de clics alcanzado", Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }

    // Interfaz para comunicarnos con la actividad
    public interface OnClickListener {
        boolean onClickAttempt();
    }
}