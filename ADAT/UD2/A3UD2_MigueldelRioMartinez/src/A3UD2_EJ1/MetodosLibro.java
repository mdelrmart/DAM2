package A3UD2_EJ1;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

import static A3UD2_EJ1.MetodosSeccion.*;

public class MetodosLibro {
    public static void visualizarLibro(Node libro) {
        if (!libro.getNodeName().equals("libro")) throw new IllegalArgumentException("El nodo no es un libro.");

        System.out.println("ID: " + libro.getAttributes().getNamedItem("ID").getTextContent());
        System.out.println("ISBN: " + libro.getAttributes().getNamedItem("isbn").getTextContent());
        System.out.println("Titulo: " + libro.getAttributes().getNamedItem("titulo").getTextContent());
        System.out.println("Número de páginas: " + libro.getAttributes().getNamedItem("numero_de_paginas").getTextContent());

        //System.out.println("Autores: " + libro.getFirstChild().getNextSibling().getTextContent().replace(" ", "").replace("\n", ", "));

        Node autores = libro.getFirstChild();
        StringJoiner joiner = new StringJoiner(", ");
        System.out.print("Autores: [");
        //System.out.println(autores.getNodeName());
        for (int i = 0; i < autores.getChildNodes().getLength(); i++) {
            Node autor = autores.getChildNodes().item(i);
            joiner.add(autor.getTextContent());
            //System.out.print(autor.getTextContent() + ", ");
        }
        System.out.println(joiner.toString() + "]");

        System.out.println("Fecha de Edición: " + libro.getChildNodes().item(1).getTextContent());
        System.out.println("Editorial: " + libro.getChildNodes().item(2).getTextContent());
        System.out.println("Precio: " + libro.getChildNodes().item(3).getTextContent());

        Node copias = libro.getChildNodes().item(4);
        for (int i = 0; i < copias.getChildNodes().getLength(); i++) {
            System.out.print("{" + copiaLibroToString(copias.getChildNodes().item(i)) + "}");
        }

        System.out.println("\n-------------------------------------------");
    }

    public static String copiaLibroToString(Node copia) {
        if (!copia.getNodeName().equals("copia")) throw new IllegalArgumentException("El nodo no es una copia.");

        StringBuilder builder = new StringBuilder();
        builder.append("Número de copia: ").append(copia.getAttributes().getNamedItem("numero").getNodeValue());
        builder.append(", Estado: ").append(copia.getAttributes().getNamedItem("estado").getNodeValue());
        return builder.toString();
    }

    public static void modificarEstadoCopia(String isbn, int numCopia, String estado, Document doc) {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        try {
            XPathExpression expr = xpath.compile(String.format("//libro[@isbn='%s']/copias/copia[@numero = '%d']", isbn, numCopia));
            Element elementoCopia = (Element) expr.evaluate(doc, XPathConstants.NODE);

            if (elementoCopia != null) {
                elementoCopia.setAttribute("estado", estado);
            } else {
                System.out.println("El número de copia no existe");
            }

        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        try (FileOutputStream fos = new FileOutputStream("BibliotecaInformatica.xml")) {
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void anhadirLibro(String idLibro, String isbn, int numPaginas, String titulo, ArrayList<String> autores, String fechaEdicion, String editorial, double precio, int copias, String seccion, Document doc) {
        // Creamos el elemento principal "libro" con sus atributos
        Element libro = doc.createElement("libro");
        libro.setAttribute("ID", idLibro);
        libro.setAttribute("isbn", isbn);
        libro.setAttribute("numero_de_paginas", Integer.toString(numPaginas));
        libro.setAttribute("titulo", titulo);

        // Creamos el elemento "autores" y añadimos cada autor de la lista
        Element autoresXML = doc.createElement("autores");
        for (String autor : autores) {
            Element autorXML = doc.createElement("autor");
            autorXML.setTextContent(autor);
            autoresXML.appendChild(autorXML);
        }
        libro.appendChild(autoresXML);

        // Creamos y añadimos el elemento "fechaEdicion"
        Element fechaEdicionXML = doc.createElement("fechaEdicion");
        fechaEdicionXML.setTextContent(fechaEdicion);
        libro.appendChild(fechaEdicionXML);

        // Creamos y añadimos el elemento "editorial"
        Element editorialXML = doc.createElement("editorial");
        editorialXML.setTextContent(editorial);
        libro.appendChild(editorialXML);

        // Creamos y añadimos el elemento "precio"
        Element precioXML = doc.createElement("precio");
        precioXML.setTextContent(Double.toString(precio));
        libro.appendChild(precioXML);

        // Creamos el elemento "copias" y añadimos las copias con estado "Disponible"
        Element copiasXML = doc.createElement("copias");
        for (int i = 1; i <= copias; i++) {
            Element copiaXML = doc.createElement("copia");
            copiaXML.setAttribute("estado", "Disponible");
            copiaXML.setAttribute("numero", Integer.toString(i));
            copiasXML.appendChild(copiaXML);
        }
        libro.appendChild(copiasXML);

        // Usamos XPath para buscar la sección en el documento
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        try {
            // Buscamos el elemento raíz "secciones"
            XPathExpression seccionesExpr = xpath.compile("//secciones");
            Node seccionesNode = (Node) seccionesExpr.evaluate(doc, XPathConstants.NODE);

            // Buscamos la sección especificada dentro de "secciones"
            XPathExpression seccionExpr = xpath.compile(String.format("//secciones/seccion[@nombre='%s']", seccion));
            Node seccionNode = (Node) seccionExpr.evaluate(doc, XPathConstants.NODE);

            // Si la sección no existe, la creamos y la añadimos a "secciones"
            if (seccionNode == null) {
                Element seccionXML = doc.createElement("seccion");
                seccionXML.setAttribute("nombre", seccion);

                // Creamos el contenedor "libros" y lo agregamos a la nueva sección
                Element librosXML = doc.createElement("libros");
                seccionXML.appendChild(librosXML);
                librosXML.appendChild(libro);

                // Añadimos la nueva sección al nodo "secciones"
                seccionesNode.appendChild(seccionXML);
            } else {
                // Si la sección existe, añadimos el libro al nodo "libros" dentro de esa sección
                Element librosXML = (Element) ((Element) seccionNode).getElementsByTagName("libros").item(0);
                librosXML.appendChild(libro);
            }

        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        // Guardamos el documento actualizado
        try (FileOutputStream fos = new FileOutputStream("BibliotecaInformatica.xml")) {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);
        } catch (TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}