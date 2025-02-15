package com.example.elecciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CandidatoAdapter extends ArrayAdapter<Candidato> {

    public CandidatoAdapter(Context context, List<Candidato> candidatos) {
        super(context, 0, candidatos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return crearVista(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return crearVista(position, convertView, parent);
    }

    private View crearVista(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item_candidato, parent, false);
        }

        Candidato candidato = getItem(position);
        ImageView logoPartido = convertView.findViewById(R.id.logoPartido);
        TextView nombreCandidato = convertView.findViewById(R.id.nombreCandidato);
        TextView nombrePartido = convertView.findViewById(R.id.nombrePartido);

        if (candidato == null) return convertView;

        nombreCandidato.setText(candidato.getNombre()); // Establece el nombre del candidato

        Partido partido = candidato.getPartido();

        if (partido == null) return convertView;

        logoPartido.setImageResource(partido.getLogoPartido()); // Establece el logo del partido
        nombrePartido.setText(partido.getNombrePartido()); // Establece el nombre del partido
        nombrePartido.setTextColor(partido.getColorPartido()); // Establece el color del texto del candidato

        return convertView;
    }
}