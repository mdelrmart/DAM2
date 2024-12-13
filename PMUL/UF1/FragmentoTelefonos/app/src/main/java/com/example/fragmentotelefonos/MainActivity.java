package com.example.fragmentotelefonos;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import com.example.fragmentotelefonos.DAO.AsistenteBD;

import java.util.ArrayList;

// Clase principal que inicializa la aplicación
public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout; // Contenedor para los fragmentos de teléfonos
    AsistenteBD asistenteBD; // Base de datos para gestionar los teléfonos
    ArrayList<Telefono> telefonos = new ArrayList<>(); // Lista de teléfonos

    onFragmentTelefonoListener getTelefono; // Listener para obtener teléfonos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        asistenteBD = AsistenteBD.getInstance(this); // Inicializa el asistente de base de datos
        EdgeToEdge.enable(this); // Configuración para diseño de borde a borde
        setContentView(R.layout.activity_main); // Asocia el diseño principal

        // Configura los márgenes para evitar superposición con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayout = findViewById(R.id.linearLayout); // Referencia al contenedor de fragmentos

        // Listener para obtener un teléfono por su número
        getTelefono = new onFragmentTelefonoListener() {
            @Override
            public Telefono obtenerTelefono(String telefono) {
                Telefono tel = new Telefono(telefono);
                int indice = telefonos.indexOf(tel);
                if (indice != -1) {
                    tel = telefonos.get(indice);
                }
                return tel;
            }
        };

        telefonos = asistenteBD.obtenerTelefonos(asistenteBD.getReadableDatabase()); // Carga los teléfonos desde la base de datos
        int cantTelefonos = telefonos.size(); // Obtiene la cantidad de teléfonos registrados

        Operadora operadora = new Operadora(telefonos); // Inicializa la operadora con la lista de teléfonos

        // Crea un fragmento para cada teléfono en la lista
        for (int i = 0; i < cantTelefonos; i++) {
            FragmentContainerView containerView = new FragmentContainerView(this); // Contenedor para el fragmento
            containerView.setId(View.generateViewId()); // Genera un ID único para el contenedor
            FragmentTelefono fragmento = new FragmentTelefono(); // Crea una instancia del fragmento

            Telefono telefono = telefonos.get(i); // Obtiene el teléfono actual
            fragmento.setListener(getTelefono, telefono.getTelefono()); // Configura el listener del fragmento

            telefono.setOperadora(operadora); // Asocia la operadora al teléfono
            telefono.setOnTelefonoListener(fragmento); // Asocia el listener del teléfono al fragmento

            linearLayout.addView(containerView); // Agrega el contenedor al diseño principal
            getSupportFragmentManager().beginTransaction().add(containerView.getId(), fragmento).commit(); // Agrega el fragmento al contenedor
        }
    }
}