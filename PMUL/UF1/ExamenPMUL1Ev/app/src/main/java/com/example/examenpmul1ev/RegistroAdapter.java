package com.example.examenpmul1ev;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RegistroAdapter extends ArrayAdapter<Registro> {
    public RegistroAdapter(Context context, ArrayList<Registro> registros) {
        super(context, 0, registros);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Registro registro = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Lookup view for data population
        TextView textView = convertView.findViewById(android.R.id.text1);

        // Populate the data into the template view using the data object
        textView.setText(registro.getAlerta());

        if (registro.isValid() == 1) {
            textView.setBackgroundColor(Color.rgb(255,0,0));
        } else {
            textView.setBackgroundColor(Color.rgb(0,255,0));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
