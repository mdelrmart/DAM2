/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manejadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorMareas extends DefaultHandler {
    /*
     * Etiquetas del elemento Actor
     * Añadir una variable por cada elemento que contenga Actor
     */
    private boolean pubDate = false, nomePorto = false;
    private StringBuilder output = new StringBuilder();

    @Override
    public void startDocument() {
        output.append("==========================\n");
        output.append("Táboa Mareas\n");
        output.append("==========================\n");
    }

    /*  Si el elemento tiene un atributo y necesitamos capturarlo lo almacenamos 
        para añadir posteriomente al StringBuilder.
        Luego si no contiene un texto, comprobamos los elementos que contiene,
        para posteriormente añadirlos al StringBuilder si existen con su texto. 
    */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("pubDate")) {
            output.append("Fecha de la predicción: ");
            pubDate = true;
        }
        if (qName.equalsIgnoreCase("Mareas:nomePorto")) {
            output.append("       Puerto: ");
            nomePorto = true;
        }
        if (qName.equalsIgnoreCase("Mareas:mareas")) {
            output.append("estado: " + attributes.getValue("estado"))
                    .append("            hora: " + attributes.getValue("hora"))
                    .append("            altura: " + attributes.getValue("altura"))
                    .append("\n");
        }
    }

    /*
        Como anteriormente almacenamos si existian los elementos, ahora si existen añadimos hardcodeado
        el nombre del elemento y leemos con characters el texto que contiene para añadirlo al StringBuilder. 
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        if (pubDate) {
            String fechaOriginal = new String(ch, start, length);
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            SimpleDateFormat formatoSalida = new SimpleDateFormat("EEEE, dd MMM yyyy", new Locale("es", "ES"));

            try {
                Date fecha = formatoEntrada.parse(fechaOriginal);
                String fechaFormateada = formatoSalida.format(fecha);
                output.append(fechaFormateada);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            pubDate = false;
        } else if (nomePorto) {
            output.append(new String(ch, start, length).toUpperCase()).append("\n");
            nomePorto = false;
        }
    }

    /*
        Si al finalizar un elemento coincide con el que nos interesa cerrar,
        añadimos el cierre de elemento con una linea 
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("item")) {
            output.append("\n==========================\n");
        }
    }

    /*
        Añadimos al final del documento el total de actores o lo que se nos solicite
        con el string builder y lo almacenamos en un documento si es necesario. 
     */
    @Override
    public void endDocument() {
        System.out.println(output);

        // Guardar en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Actores.txt"))) {
            writer.write(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}