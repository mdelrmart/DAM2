/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manejadores;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

public class ManejadorNoticias extends DefaultHandler {
    /*
     * Etiquetas del elemento Noticia
     * Añadir una variable por cada elemento que contenga Actor
     */
    private boolean item = false, title = false, pubDate = false, creator = false, description = false, category = false;
    private StringBuilder output = new StringBuilder();
    int contador = 0;

    /* Cabecera del documento */
    @Override
    public void startDocument() {
        output.append("Noticias\n");
        output.append("==========================");
    }

    /*Comprobamos el tipo de elemento, como el elemento título esta en la cabecera del documento y en sucesivas entradas,
      añadimos un contador para diferenciar el primero del resto,
      cada vez que nos encontramos con un elemento item, asumimos que es una nueva entrada y aumentamos el contador
    */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("item")) {
            contador++;
            output.append("\n================ NOTICIA " + contador + "================");
        }
        if (qName.equalsIgnoreCase("title")) {
            if (contador < 1) {
                item = true;
            } else {
                title = true;
            }
            output.append("\nTitular: ");
        }
        if (qName.equalsIgnoreCase("description")) {
            output.append("Descripción: ");
            description = true;
        }
        if (qName.equalsIgnoreCase("pubDate")) {
            output.append("Fecha Publicación: ");
            pubDate = true;
        }
        if (qName.equalsIgnoreCase("dc:creator")) {
            output.append("Autor: ");
            creator = true;
        }
        if (qName.equalsIgnoreCase("category")) {
            output.append("/");
            category = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (item) {
            output.append(new String(ch, start, length)).append("\n");
            item = false;
        } else if (description) {
            output.append(new String(ch, start, length)).append("\n");
            description = false;
            if(contador >= 1) {
                output.append("Categoría: ");
            }
        } else if (title && !item) {
            output.append(new String(ch, start, length)).append("\n");
            title = false;
        } else if (pubDate) {
            String fechaOriginal = new String(ch, start, length);
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            SimpleDateFormat formatoSalida = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss", new Locale("es", "ES"));

            try {
                Date fecha = formatoEntrada.parse(fechaOriginal);
                String fechaFormateada = formatoSalida.format(fecha);
                output.append(fechaFormateada + "\n");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            pubDate = false;
        } else if (creator) {
            output.append(new String(ch, start, length)).append("\n");
            creator = false;
        } else if (category) {
            output.append(new String(ch, start, length)).append("");
            category = false;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {

    }

    /*
       Al finalizar el documento lo imprimimos por consola y lo almacenamos en un fichero de texto
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