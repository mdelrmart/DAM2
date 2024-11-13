package com.example.elecciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SpinnerCandidato extends ArrayAdapter<Candidato> {
    private int[] images; // Array de imágenes
    List<Candidato> candidatos;

    public SpinnerCandidato(Context context, List<Candidato> candidatos, int[] images) {
        super(context, R.layout.spinner_candidato, candidatos);
        this.candidatos = candidatos;
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(super.getContext());
        if (convertView == null) {
            //convertView = View.inflate(getContext(), R.layout.spinner_candidato, parent);
            convertView = layoutInflater.inflate(R.layout.spinner_candidato, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);


            String nombre = getItem(position).getNombre();
            viewHolder.textView.setText(nombre);

            viewHolder.imageView.setImageResource(images[getItem(position).getCodPartido()-1]);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public static class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.logo);
            textView = view.findViewById(R.id.nombre);
        }

    }
}
