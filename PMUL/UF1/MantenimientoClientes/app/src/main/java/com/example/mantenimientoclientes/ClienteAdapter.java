package com.example.mantenimientoclientes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ClienteAdapter extends ArrayAdapter<ClienteEnLista> {
    public ClienteAdapter(Context context, ArrayList<ClienteEnLista> clientes) {
        super(context, 0, clientes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener el cliente actual
        ClienteEnLista cliente = getItem(position);

        // Inflar el layout si es necesario
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Obtener el TextView
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);

        // Usar toString() para establecer el texto
        textView.setText(cliente.toString());

        // Cambiar el color del texto si el cliente es VIP
        if (cliente.isVip()) {
            //textView.setBackgroundColor(Color.RED); // Color rojo para VIP
            textView.setTextColor(Color.rgb(184,35,35));
        }

        return convertView;
    }
}
