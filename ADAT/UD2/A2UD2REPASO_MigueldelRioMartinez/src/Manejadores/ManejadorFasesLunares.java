package Manejadores;

import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Element;
import utilidades.xml.XMLDOMUtility;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

public class ManejadorFasesLunares extends DefaultHandler {
    /*
     * Etiquetas del elemento Noticia
     * Añadir una variable por cada elemento que contenga Actor
     */
    private boolean raiz = false, title = false, luasEstado = false, luasData = false;
    private StringBuilder output = new StringBuilder();
    int contador = 0;

    String fechaHora = "";

    Element luna = null;

    XMLDOMUtility dom = new XMLDOMUtility();

    public ManejadorFasesLunares() throws ParserConfigurationException {

    }

    /* Cabecera del documento */
    @Override
    public void startDocument() {
        dom.createNewXML("Lunas");
        try {
            dom.addAttribute("Lunas", "Descripcion", "Fases Lunares");
            dom.addAttribute("Lunas", "Servicio", "MeteoGalicia");
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    /*Comprobamos el tipo de elemento, como el elemento título está en la cabecera del documento y en sucesivas entradas,
      añadimos un contador para diferenciar el primero del resto,
      cada vez que nos encontramos con un elemento item, asumimos que es una nueva entrada y aumentamos el contador
    */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("item")) {
            contador++;
        }
        if (qName.equalsIgnoreCase("title")) {
            if (contador < 1) {
                raiz = true;
            } else {
                title = true;

                // Creamos elemento Luna
                luna = dom.getDoc().createElement("Luna");
                luna.setIdAttribute("Estado", true);
            }
        }
        if (qName.equalsIgnoreCase("Luas:data")) {
            luasData = true;
        }
        if (qName.equalsIgnoreCase("Luas:estado")) {
            luasEstado = true;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        if (raiz) {
            output.append(new String(ch, start, length));
            String descripcion = new String(ch, start, length);

            raiz = false;
        } else if (title && !raiz) {
            String fase = new String(ch, start, length);
            String[] fasePartes = fase.split(" ");
            StringBuilder sb = new StringBuilder();
            sb.append(fasePartes[0]).append(" ").append(fasePartes[1]);

            output.append(sb).append("\n");
            title = false;

            try {
                dom.addElement("/Lunas", luna);
                dom.addAttribute("/Lunas/Luna[" + contador + "]", "Estado", String.valueOf(sb));
            } catch (XPathExpressionException e) {
                throw new RuntimeException(e);
            }
        } else if (luasData) {
            fechaHora = new String(ch, start, length);
            luasData = false;
        } else if (luasEstado) {
            String estado = new String(ch, start, length);

            try {
                dom.addAttribute("Lunas","Estado", estado);

                String[] luasDataPartes = fechaHora.split(" ");
                String fecha = luasDataPartes[0];
                String hora = luasDataPartes[1];

                Node fechaNodo = dom.getDoc().createElement("Fecha");
                Node horaNodo = dom.getDoc().createElement("Hora");

                fechaNodo.setTextContent(fecha);
                horaNodo.setTextContent(hora);

                dom.addElement("/Lunas/Luna[" + contador + "]", fechaNodo);
                dom.addElement("/Lunas/Luna[" + contador + "]", horaNodo);

            } catch (XPathExpressionException e) {
                throw new RuntimeException(e);
            }

            luasEstado = false;
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
        try {
            dom.writeXML("Archivo.xml");
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
