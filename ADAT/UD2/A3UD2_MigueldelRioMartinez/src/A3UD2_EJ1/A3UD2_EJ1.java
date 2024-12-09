package A3UD2_EJ1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static A3UD2_EJ1.MetodosLibro.*;
import static A3UD2_EJ1.MetodosSeccion.*;

public class A3UD2_EJ1 {
    public static void main(String[] args) {
        // a)

        String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
        String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

        String documentoXML = "BibliotecaInformatica.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

        Document doc = null;

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Cargamos en memoria ("Cargar en memoria" se hace parseando el DocumentBuilder).
            doc = db.parse(new File(documentoXML));

            db.setErrorHandler(new SimpleErrorHandler());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        // b)
        for (Node elemento : librosSeccion("Programación",doc)) {
            //System.out.println(elemento);
            visualizarLibro(elemento);
        }
        // Lo mismo que el código de arriba pero en lambda
        //librosSeccion("Programación",doc).forEach(node -> visualizarLibro(node));
        //librosSeccion("Programación",doc).forEach(A3UD2_EJ1::visualizarLibro);

        // El "for" comentado es igual que el del principio pero el de arriba ya coge automáticamente el tipo de dato
        //for (Node elemento : librosSeccion("Programación")) {

        // c)
        List<Node> secciones = obtenerTodasSecciones(doc);
        seccionesOrdenadasInversamente(secciones);
        secciones.forEach(node -> visualizarSeccion(node));
        // Lo mismo que la lambda de arriba pero más avanzada
        //secciones.forEach(A3UD2_EJ1::visualizarLibro);

        // d)
        modificarEstadoCopia("988-8822222222",3,"Disponible", doc);

        // e)
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Pepa");
        autores.add("Paco");

        anhadirLibro("A562","988-6589856589", 240,"XML Experts", autores,"01-01-2000","Yoquesetio",20.00, 3,"Programación2", doc);
    }

}