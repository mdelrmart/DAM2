package com.example.busquedalistafragmento;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.busquedalistafragmento.DAO.ComunidadAutonomaDAO;
import com.example.busquedalistafragmento.DAO.Database;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BusquedaFragmento.OnItemSelectedListener {

    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Database.init(this);

        txtResultado = findViewById(R.id.txtResultado);

        BusquedaFragmento fragment = new BusquedaFragmento(listaStringComunidades());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frgBusqueda, fragment)
                .commit();
    }

    @Override
    public void onItemSelected(String elemento) {
        // Aquí manejamos el elemento clicado
        txtResultado.setText(elemento);
    }

    // Este método lo pasamos al constructor del fragmento para que pueble la lista.
    public static List<String> listaStringComunidades() {
        List<ComunidadAutonoma> comunidadesAutonomas = ComunidadAutonomaDAO.obtenerComunidadesAutonomas();

        List<String> comunidadesLista = new ArrayList<>();

        for (ComunidadAutonoma comunidadAutonoma : comunidadesAutonomas) {
            comunidadesLista.add(comunidadAutonoma.getNombre());
        }

        return comunidadesLista;
    }
}
