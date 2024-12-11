package com.example.busquedalistafragmento;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BusquedaFragmento extends Fragment {

    private RecyclerView recyclerView;
    private EditText txtBusqueda;
    private BusquedaAdapter adaptadorBusqueda;
    private List<String> lista;

    public BusquedaFragmento() {

    }

    public BusquedaFragmento(List<String> lista) {
        this.lista = lista;
    }


    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.busqueda_fragmento, container, false);

        // Inicializamos las vistas
        recyclerView = view.findViewById(R.id.recyclerView);
        txtBusqueda = view.findViewById(R.id.searchEditText);

        // Configurar lista y adaptador con listener
        adaptadorBusqueda = new BusquedaAdapter(lista, item -> {
            if (getActivity() instanceof BusquedaFragmento.OnItemSelectedListener) {
                // Llama a la interfaz personalizada
                ((BusquedaFragmento.OnItemSelectedListener) getActivity()).onItemSelected(item);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorBusqueda);

        // Configurar el filtro de búsqueda
        txtBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarLista(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    // Método para filtrar la lista
    private void filtrarLista(String filtro) {
        List<String> listaFiltrada = new ArrayList<>();
        for (String elemento : lista) {
            if (elemento.toLowerCase().startsWith(filtro.toLowerCase())) {
                listaFiltrada.add(elemento);
            }
        }
        adaptadorBusqueda.updateList(listaFiltrada);
    }

}