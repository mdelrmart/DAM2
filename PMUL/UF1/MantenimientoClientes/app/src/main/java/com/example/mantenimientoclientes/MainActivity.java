package com.example.mantenimientoclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.mantenimientoclientes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    AsistenteBD asistenteBD;
    ListView listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fichaCliente = new Intent(view.getContext(), FichaCliente.class);
                startActivity(fichaCliente);
            }
        });

        listaClientes = findViewById(R.id.listaclientes);
        asistenteBD = new AsistenteBD(this);
        poblarLista();
    }

    // Recargamos la lista al volver a la MainActivity después de un finish()
    @Override
    protected void onResume() {
        super.onResume();

        poblarLista();
    }

    public ArrayList<ClienteEnLista> getDatosClientes() {
        ArrayList<ClienteEnLista> datosClientes = new ArrayList<>();
        SQLiteDatabase bd = new AsistenteBD(this).getReadableDatabase();
        Cursor datos = bd.rawQuery("SELECT * FROM clientes ORDER BY apellidos,nombre", null);

        int colIndexCodCliente = datos.getColumnIndex("codCliente");
        int colIndexNombre = datos.getColumnIndex("nombre");
        int colIndexApellidos = datos.getColumnIndex("apellidos");
        int colIndexVIP = datos.getColumnIndex("VIP");

        while(datos.moveToNext()) {
            int codCliente = datos.getInt(colIndexCodCliente);
            String nombre = datos.getString(colIndexNombre);
            String apellidos = datos.getString(colIndexApellidos);
            boolean VIP = datos.getInt(colIndexVIP) == 1;
            datosClientes.add(new ClienteEnLista(codCliente,nombre,apellidos,VIP));
        }

        bd.close();

        return datosClientes;
    }

    public void poblarLista() {
        ArrayList<ClienteEnLista> clientes = getDatosClientes();
        ClienteAdapter adapter = new ClienteAdapter(this, clientes);

        listaClientes.setAdapter(adapter);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClienteEnLista clienteSeleccionado = (ClienteEnLista) listaClientes.getItemAtPosition(i);

                Intent intentFichaCliente = new Intent(MainActivity.this, FichaCliente.class);
                intentFichaCliente.putExtra("codCliente", clienteSeleccionado.getCodCliente());

                startActivity(intentFichaCliente);
            }
        });
    }

    /*
    public void poblarLista() {
        listaClientes.setAdapter(new ArrayAdapter<ClienteEnLista>(this, android.R.layout.simple_list_item_1,getDatosClientes()));

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClienteEnLista clienteSeleccionado = (ClienteEnLista)  listaClientes.getItemAtPosition(i);

                Intent intentFichaCliente = new Intent(MainActivity.this, FichaCliente.class);
                intentFichaCliente.putExtra("codCliente", clienteSeleccionado.getCodCliente());

                startActivity(intentFichaCliente);
            }
        });
    }

    private void poblarLista() {
        Cursor datos = asistenteBD.obtenerDatos();

        ArrayList<String> listaDatos = new ArrayList<>();

        while(datos.moveToNext()) {
            listaDatos.add(datos.getString(2) + ", " + datos.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaDatos);
        listaClientes.setAdapter(adapter);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    */
}