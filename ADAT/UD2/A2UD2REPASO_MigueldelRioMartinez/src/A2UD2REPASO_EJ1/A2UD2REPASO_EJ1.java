/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A2UD2REPASO_EJ1;

import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * @author Miguel
 */
public class A2UD2REPASO_EJ1 {
    public static void main(String[] args) {
        // a
        System.out.println("a)");

        try (BufferedReader br = new BufferedReader(new FileReader("ModificarFecha.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                boolean encontrado = false;
                String datos[] = linea.split(",");

                if (datos.length != 2) {
                    System.out.println("Error: Datos incorrectos");
                }

                try (InputStream is = new FileInputStream("Actores.xml")) {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

                    dbf.setValidating(true);
                    dbf.setNamespaceAware(true);
                    dbf.setIgnoringComments(true);

                    DocumentBuilder db = dbf.newDocumentBuilder();

                    Document doc = db.parse(is);
                    NodeList nList = doc.getElementsByTagName("Actor");
                    for (int i = 0; i < nList.getLength(); i++) {
                        Node nNode = nList.item(i);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            String id = nNode.getAttributes().getNamedItem("id").getTextContent();

                            if (datos[0].equals(id)) {
                                Node fechaNacimiento = nNode.getLastChild().getPreviousSibling();
                                //Node fechaNacimiento = nNode.getLastChild();
                                fechaNacimiento.setTextContent(datos[1]);
                                System.out.println("Modificada la fecha de nacimiento del actor " + nNode.getFirstChild().getNextSibling().getTextContent() + " a " + fechaNacimiento.getTextContent());
                                encontrado = true;
                                break;
                            }

                        }
                    }

                    try (FileOutputStream fos = new FileOutputStream("Actores.xml")) {
                        TransformerFactory tf = TransformerFactory.newInstance();
                        Transformer transformer = tf.newTransformer();

                        // Mantener DTD en el documento
                        DocumentType docType = doc.getDoctype();
                        if (docType != null) {
                            String systemId = docType.getSystemId();
                            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemId);
                        }

                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(fos);
                        transformer.transform(source, result);
                    } catch (TransformerConfigurationException e) {
                        throw new RuntimeException(e);
                    } catch (TransformerException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (!encontrado) {
                    System.out.println("No existe el actor con ID: " + datos[0]);
                }
            }

        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        //b
        System.out.println("b)");

        try (InputStream is = new FileInputStream("Actores.xml")) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setValidating(true);
            dbf.setNamespaceAware(true);
            dbf.setIgnoringComments(true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            Element actor = doc.createElement("Actor");

            Attr atrib = doc.createAttribute("id");
            atrib.setValue("A32");
            actor.setAttributeNode(atrib);
            actor.setIdAttributeNode(atrib,true);

            Element Nombre = doc.createElement("Nome");
            Text texto = doc.createTextNode("Michael Caine");
            actor.appendChild(Nombre);
            Nombre.appendChild(texto);

            Element Sexo = doc.createElement("Sexo");
            texto = doc.createTextNode("home");
            actor.appendChild(Sexo);
            Sexo.appendChild(texto);

            Element Data = doc.createElement("DataNacemento");
            texto = doc.createTextNode("14/03/1933");
            actor.appendChild(Data);
            Data.appendChild(texto);

            doc.getDocumentElement().appendChild(actor);

            try (FileOutputStream fos = new FileOutputStream("Actores.xml")) {
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();

                // Mantener DTD en el documento
                DocumentType docType = doc.getDoctype();
                if (docType != null) {
                    String systemId = docType.getSystemId();
                    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemId);
                }

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(fos);
                transformer.transform(source, result);
                System.out.println("Actor agregado correctamente.");
            } catch (TransformerConfigurationException e) {
                throw new RuntimeException(e);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        //c
        System.out.println("c)");

        try (InputStream is = new FileInputStream("Actores.xml")) {
            boolean encontrado = false;

            String idActor = "A139";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setValidating(true);
            dbf.setNamespaceAware(true);
            dbf.setIgnoringComments(true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);
            NodeList nList = doc.getElementsByTagName("Actor");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    String id = nNode.getAttributes().getNamedItem("id").getTextContent();

                    if (idActor.equals(id)) {
                        System.out.println("Nombre: " + nNode.getFirstChild().getNextSibling().getTextContent());
                        System.out.println("Fecha Nacimiento: " + nNode.getLastChild().getPreviousSibling().getTextContent());
                        System.out.println("Sexo: " + nNode.getLastChild().getPreviousSibling().getPreviousSibling().getPreviousSibling().getTextContent());

                        encontrado = true;
                        break;
                    }

                }
            }

            if (!encontrado) {
                System.out.println("No existe el actor con ID: " + idActor);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }


    }

}

