package com.example.mantenimientoclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FichaCliente extends AppCompatActivity {

    AsistenteBD asistenteBD;

    private EditText nombre;
    private EditText apellidos;
    private EditText dni;
    private Spinner provincia;
    private CheckBox vip;
    private EditText latitud;
    private EditText longitud;

    private Button btnMapa;
    private Button btnCancelar;
    private Button btnGuardar;

    Bundle codClientePasado;

    int idProvincia = -1;

    boolean dniCorrecto = false;

    boolean modoEdicion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_ficha_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        dni = findViewById(R.id.dni);
        provincia = findViewById(R.id.provincia);
        vip = findViewById(R.id.vip);
        latitud = findViewById(R.id.latitud);
        longitud = findViewById(R.id.longitud);

        btnMapa = findViewById(R.id.btnMapa);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnGuardar = findViewById(R.id.btnGuardar);

        asistenteBD = new AsistenteBD(this);

        // Obtenemos codCliente
        codClientePasado = getIntent().getExtras();
        int codClienteObtenido;

        // Si codCliente es distinto de null, estamos en modo edición
        if (codClientePasado != null) {
            modoEdicion = true;
            codClienteObtenido = codClientePasado.getInt("codCliente");
            getDatosCliente(codClienteObtenido);
            dniCorrecto = true;
            // Cargamos la provincia
            cargarProvinciasEnSpinnerEdicion();
        } else {
            cargarProvinciasEnSpinner();
        }

        // Comprobamos dinámicamente el DNI
        Pattern patternDNI = Pattern.compile("^[0-9]{8}[A-z]$");

        dni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Matcher matcherDNI = patternDNI.matcher(dni.getText().toString());

                if (!matcherDNI.find()) {
                    dni.setTextColor(Color.RED);
                    dniCorrecto = false;
                } else {
                    dni.setTextColor(Color.BLACK);
                    dniCorrecto = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lat = latitud.getText().toString();
                String lon = longitud.getText().toString();

                if (!lat.isBlank() || !lon.isBlank()) {
                    Uri mapaUri = Uri.parse("https://maps.google.com/maps/search/" + lat + "," + lon);
                    Intent intent = new Intent(Intent.ACTION_VIEW, mapaUri);
                    startActivity(intent);
                } else {
                    mensajeSnackbarAceptar("Falta la latitud y/o longitud. No se puede abrir el mapa.");
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int codClienteInt;

                String nombreString = nombre.getText().toString();
                String apellidosString = apellidos.getText().toString();
                String dniString = dni.getText().toString().toUpperCase();

                if (nombreString.isBlank() || apellidosString.isBlank() || dniString.isBlank()) {
                    mensajeToast("No se han rellenado todos los campos obligatorios");
                    return;
                }

                // Obtenemos el objeto provincia seleccionado
                Provincia provinciaSeleccionada = (Provincia) provincia.getSelectedItem();
                // Por defecto la provincia seleccionada es -1
                int codProvinciaSeleccionada = -1;

                // Si la provinciaSeleccionada no es null obtenemos el codProvincia
                if (provinciaSeleccionada != null) {
                    codProvinciaSeleccionada = provinciaSeleccionada.getCodProvincia();
                }

                int vipBoolean = vip.isChecked() ? 1 : 0;
                String latitudString = latitud.getText().toString();
                String longitudString = longitud.getText().toString();

                if (dniCorrecto == false) {
                    mensajeToast("El DNI no contiene el formato correcto.");
                    return;
                }

                if (modoEdicion) {
                    codClienteInt = codClientePasado.getInt("codCliente");
                    boolean exito = modificarDatosBD(codClienteInt, nombreString, apellidosString, dniString, codProvinciaSeleccionada, vipBoolean, latitudString, longitudString);
                    if (exito) {
                        finish();
                    }
                } else {
                    boolean exito = anhadirDatosBD(nombreString, apellidosString, dniString, codProvinciaSeleccionada, vipBoolean, latitudString, longitudString);
                    if (exito) {
                        finish();
                    }
                }
            }
        });
    }

    // region *** Manejo de las provincias ***

    // Cargar provincias en spinner en modo inserción
    private void cargarProvinciasEnSpinner() {
        // Obtener todos los nombres de la base de datos
        List<Provincia> provincias = asistenteBD.obtenerProvincias();

        // Crear un ArrayAdapter usando la lista de nombres y un layout predefinido para el Spinner
        ArrayAdapter<Provincia> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provincias);

        // Especificar el layout para cuando el menú del Spinner esté desplegado
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        provincia.setAdapter(adapter);
    }

    private void cargarProvinciasEnSpinnerEdicion() {
        // Cargamos las provincias en el Spinner
        cargarProvinciasEnSpinner();

        // Obtenemos el codProvincia de la provincia guardada
        int codProvinciaGuardada = idProvincia;

        // Verificamos que se encontró un codProvincia distinto de -1, si es asi la seleccionamos en el Spinner
        // En caso contrario, no seleccionamos nada y se queda el Spinner cargado de forma normal
        if (codProvinciaGuardada != -1) {
            seleccionarProvinciaEnSpinner(codProvinciaGuardada);
        }
    }

    // Cargar provincias en spinner en modo edición (desde BBDD)
    private void seleccionarProvinciaEnSpinner(int idProvinciaGuardada) {
        // Recorremos los elementos del Spinner para encontrar el que coincide con el codProvincia
        for (int i = 0; i < provincia.getCount(); i++) {
            Provincia provinciaItem = (Provincia) provincia.getItemAtPosition(i);

            if (provinciaItem != null && provinciaItem.getCodProvincia() == idProvinciaGuardada) {
                // Seleccionamos el elemento en el Spinner
                provincia.setSelection(i);
                break;
            }
        }
    }


    //endregion

    // region *** Operaciones BBDD ***
    public void getDatosCliente(int codClienteObtenido) {
        SQLiteDatabase bd = new AsistenteBD(this).getReadableDatabase();
        Cursor consulta = bd.rawQuery("SELECT * FROM clientes WHERE codCliente =" + codClienteObtenido, null);

        if (consulta.moveToFirst()) {
            //int colIndexCodCliente = consulta.getColumnIndex("codCliente");
            int colIndexNombre = consulta.getColumnIndex("nombre");
            int colIndexApellidos = consulta.getColumnIndex("apellidos");
            int colIndexDNI = consulta.getColumnIndex("NIF");
            int colIndexVIP = consulta.getColumnIndex("VIP");
            int colIndexProvincia = consulta.getColumnIndex("codProvincia");
            int colIndexLatitud = consulta.getColumnIndex("latitud");
            int colIndexLongitud = consulta.getColumnIndex("longitud");

            //int codClienteBBDD = consulta.getInt(colIndexCodCliente);
            String nombreBBDD = consulta.getString(colIndexNombre);
            String apellidosBBDD = consulta.getString(colIndexApellidos);
            String dniBBDD = consulta.getString(colIndexDNI);
            boolean VIPBBDD = consulta.getInt(colIndexVIP) == 1;
            int provinciaBBDD = consulta.getInt(colIndexProvincia);
            String latitudBBDD = consulta.getString(colIndexLatitud);
            String longitudBBDD = consulta.getString(colIndexLongitud);

            if (colIndexNombre == -1 || colIndexApellidos == -1 || colIndexDNI == -1 ||
                    colIndexVIP == -1 || colIndexLatitud == -1 || colIndexLongitud == -1) {
                mensajeToast("Error en la estructura de la tabla");
                return;
            }

            nombre.setText(nombreBBDD);
            apellidos.setText(apellidosBBDD);
            dni.setText(dniBBDD);
            vip.setChecked(VIPBBDD);
            idProvincia = provinciaBBDD;
            latitud.setText(latitudBBDD);
            longitud.setText(longitudBBDD);
        }

        bd.close();
    }

    public boolean consultarDniClienteInsercion(String dniCliente) {
        SQLiteDatabase bd = new AsistenteBD(this).getReadableDatabase();
        Cursor consulta = bd.rawQuery("SELECT NIF FROM clientes WHERE NIF = " + "'" + dniCliente + "'", null);

        if (consulta.moveToFirst()) {
            int colIndexDNI = consulta.getColumnIndex("NIF");
            String dniBBDD = consulta.getString(colIndexDNI);

            if (dniCliente.equals(dniBBDD)) {
                return true;
            }
        }
        consulta.close();
        bd.close();
        return false;
    }

    public int consultarDniClienteEdicion(int codCliente, String dniCliente) {
        SQLiteDatabase bd = new AsistenteBD(this).getReadableDatabase();
        Cursor consulta = bd.rawQuery("SELECT codCliente, NIF FROM clientes WHERE NIF = " + "'" + dniCliente + "'", null);

        if (consulta.moveToFirst()) {
            int colIndexcodCliente = consulta.getColumnIndex("codCliente");
            int colIndexDNI = consulta.getColumnIndex("NIF");

            int codClienteBBDD = consulta.getInt(colIndexcodCliente);
            String dniBBDD = consulta.getString(colIndexDNI);

            if (codCliente == codClienteBBDD && dniCliente.equals(dniBBDD)) {
                return 0;
            }

            if (codCliente != codClienteBBDD && dniCliente.equals(dniBBDD)) {
                return 1;
            }
        }
        consulta.close();
        bd.close();
        return -1;
    }

    public boolean anhadirDatosBD(String nombreString, String apellidosString, String dniString, int provinciaInt, int vipBoolean, String latitudString, String longitudString) {
        boolean exito = false;

        if (consultarDniClienteInsercion(dniString)) {
            mensajeSnackbarAceptar("El DNI introducido pertenece a otro cliente");
            return exito;
        }

        boolean datosInsertados = asistenteBD.anhadirDatos(nombreString, apellidosString, dniString, provinciaInt, vipBoolean, latitudString, longitudString);

        if (datosInsertados) {
            mensajeToast("Datos guardados correctamente");
            exito = true;
        } else {
            mensajeToast("Se ha producido un error al guardar los datos en la base de datos");
        }
        return exito;
    }

    public boolean modificarDatosBD(int codClienteInt, String nombreString, String apellidosString, String dniString, int provinciaInt, int vipBoolean, String latitudString, String longitudString) {
        boolean exito = false;

        switch (consultarDniClienteEdicion(codClienteInt, dniString)) {
            case 0:
            case -1:
                boolean datosModificados = asistenteBD.modificarDatos(codClienteInt, nombreString, apellidosString, dniString, provinciaInt, vipBoolean, latitudString, longitudString);

                if (datosModificados) {
                    mensajeToast("Datos modificados correctamente");
                    exito = true;
                } else {
                    mensajeToast("Se ha producido un error al modificar los datos en la base de datos");
                }
                break;
            case 1:
                mensajeSnackbarAceptar("El DNI introducido pertenece a otro cliente");
                break;
            default:
                mensajeToast("No se ha podido comprobar la existencia del DNI en la BBDD");
                break;
        }
        return exito;
    }
    //endregion

    //region *** Métodos para sacar mensajes por pantalla ***
    private void mensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private void mensajeSnackbar(String mensaje) {
        Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG).show();
    }

    private void mensajeSnackbarAceptar(String mensaje) {
        //Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG).show();
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
        snackbar.show();
    }
    //endregion
}