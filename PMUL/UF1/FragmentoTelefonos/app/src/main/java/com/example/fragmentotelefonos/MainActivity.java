package com.example.fragmentotelefonos;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;

import com.example.fragmentotelefonos.DAO.AsistenteBD;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    AsistenteBD asistenteBD;

    ArrayList<Telefono> telefonos = new ArrayList<>();

    onFragmentTelefonoListener getTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        asistenteBD = AsistenteBD.getInstance(this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayout = findViewById(R.id.linearLayout);

        getTelefono = new onFragmentTelefonoListener() {
            @Override
            public Telefono obtenerTelefono(String telefono) {
                Telefono tel = new Telefono(telefono);
                int indice = telefonos.indexOf(tel);
                tel = telefonos.get(indice);
                return tel;
            }
        };

        telefonos = asistenteBD.obtenerTelefonos(asistenteBD.getReadableDatabase());
        int cantTelefonos = telefonos.size();

        Operadora operadora = new Operadora(telefonos);

        for (int i = 0; i < cantTelefonos; i++) {
            FragmentContainerView containerView = new FragmentContainerView(this);
            containerView.setId(View.generateViewId());
            FragmentTelefono fragmento = new FragmentTelefono();

            Telefono telefono = telefonos.get(i);
            fragmento.setListener(getTelefono, telefono.getTelefono());

            telefono.setOperadora(operadora);

            telefono.setOnTelefonoListener(fragmento);


            linearLayout.addView(containerView);
            getSupportFragmentManager().beginTransaction().add(containerView.getId(), fragmento).commit();
        }

    }
}