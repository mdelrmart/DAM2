package A3UD2_EJ1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MetodosSeccion {

    public static List<Node> librosSeccion(String seccion, Document doc) {
        List<Node> libros = new ArrayList<>();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        try {
            XPathExpression expr = xpath.compile(String.format("//copia[@estado = '%s']/libros/libro", seccion));
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            if (nodes.getLength() == 0) {
                System.out.println("La sección introducida no existe.");
            } else {
                for (int i = 0; i < nodes.getLength(); i++) {
                    libros.add(nodes.item(i));
                }
                return libros;
            }
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        return libros;
    }

//    public static boolean existeSeccion(String seccion, Document doc) {
//        XPathFactory xpf = XPathFactory.newInstance();
//        XPath xpath = xpf.newXPath();
//
//        try {
//            XPathExpression expr = xpath.compile(String.format("//seccion[@nombre = '%s']", seccion));
//            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//
//            if (nodes.getLength() == 0) {
//                return false;
//            }
//
//        } catch (XPathExpressionException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }

    public static List<Node> obtenerTodasSecciones(Document doc) {
        List<Node> secciones = new ArrayList<>();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        try {
            XPathExpression expr = xpath.compile("//seccion");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                secciones.add(nodes.item(i));
            }

        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        return secciones;
    }

    public static void seccionesOrdenadasInversamente(List<Node> secciones) {
        secciones.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                int numLibros1 = o1.getFirstChild().getChildNodes().getLength();
                int numLibros2 = o2.getFirstChild().getChildNodes().getLength();

                return numLibros1 - numLibros2;
            }
        }.reversed().thenComparing(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                String nombre1 = o1.getAttributes().getNamedItem("nombre").getNodeValue();
                String nombre2 = o2.getAttributes().getNamedItem("nombre").getNodeValue();

                return nombre1.compareTo(nombre2);
            }
        }));
    }

    public static void visualizarSeccion(Node seccion) {
        String nombre = seccion.getAttributes().getNamedItem("nombre").getNodeValue();
        int numero = seccion.getFirstChild().getChildNodes().getLength();
        System.out.println("Sección: " + nombre);
        System.out.println("Número de Libros: " + numero);
        System.out.println("-------------------------------------------");
    }

    public static void existeSeccion(Node seccion) {

    }
}
