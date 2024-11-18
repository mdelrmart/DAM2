/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manejadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorActores extends DefaultHandler {
    private int totalActores = 0;
    /* 
     * Etiquetas del elemento Actor
     * Añadir una variable por cada elemento que contenga Actor 
    */
    private boolean inNome = false, inSexo = false, inDataNacemento = false;
    private StringBuilder output = new StringBuilder();

    @Override
    public void startDocument() {
        output.append("Información de actores\n");
        output.append("==========================\n");
    }

    /*  Si el elemento tiene un atributo y necesitamos capturarlo lo almacenamos 
        para añadir posteriomente al StringBuilder.
        Luego si no contiene un texto, comprobamos los elementos que contiene,
        para posteriormente añadirlos al StringBuilder si existen con su texto. 
    */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        
        if (qName.equalsIgnoreCase("Actor")) {
            totalActores++;
            output.append("\nActor ").append(totalActores).append("\n");
            output.append("**********\n");
            String id = attributes.getValue("id");
            if (id != null) {
                output.append("id:").append(id).append("\n");
            }
        } else if (qName.equalsIgnoreCase("Nome")) {
            inNome = true; //Marcamos que existe el elemento para luego añadir al String "nombreElemento:" + varValor;
        } else if (qName.equalsIgnoreCase("Sexo")) {
            inSexo = true; //igual que el anterior con todos los elementos siguientes. 
        } else if (qName.equalsIgnoreCase("DataNacemento")) {
            inDataNacemento = true;
        }
    }
    /*
        Como anteriormente almacenamos si existian los elementos, ahora si existen añadimos hardcodeado
        el nombre del elemento y leemos con characters el texto que contiene para añadirlo al StringBuilder. 
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        if (inNome) {
            output.append("Nome:").append(new String(ch, start, length)).append("\n");
            inNome = false;
        } else if (inSexo) {
            output.append("Sexo:").append(new String(ch, start, length)).append("\n");
            inSexo = false;
        } else if (inDataNacemento) {
            output.append("DataNacemento:").append(new String(ch, start, length)).append("\n");
            inDataNacemento = false;
        }
    }
    /*
        Si al finalizar un elemento coincide con el que nos interesa cerrar,
        añadimos el cierre de elemento con una linea 
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("Actor")) {
            output.append("--------------------------------\n");
        }
    }
    /*
        Añadimos al final del documento el total de actores o lo que se nos solicite
        con el string builder y lo almacenamos en un documento si es necesario. 
     */
    @Override
    public void endDocument() {
        output.append("\nTotal de actores: ").append(totalActores).append("\n");
        System.out.println(output);

        // Guardar en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Actores.txt"))) {
            writer.write(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}