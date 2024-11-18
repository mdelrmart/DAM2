/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A2UD2_EJ3;

import Manejadores.ManejadorNoticias;
import Manejadores.MiErrorHandler;
import java.io.File;
import java.io.FileInputStream;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Miguel
 */
public class A2UD2_EJ3 {
    public static void main(String[] args) {
        String validacion = "http://xml.org/sax/features/validation";
        String namespaces="http://xml.org/sax/features/namespaces";

        MiErrorHandler errorHandler = new MiErrorHandler();
        DefaultHandler handler = new ManejadorNoticias();
        
        try {

            @SuppressWarnings("deprecation")
            XMLReader analizador = XMLReaderFactory.createXMLReader();
        
            // Verificar si tiene soporte para validación
            if (!analizador.getFeature(validacion)) {
                System.out.println("[INFO] Validacion desactivada, activando...");
                analizador.setFeature(validacion, false);
            }else{
                System.out.println("[INFO] Validacion activada.");
            }
            // Verificar si soporta espacios de nombres
            if (!analizador.getFeature(namespaces)) {
                System.out.println("[INFO] Espacio de nombres desactivado, activando...");
                analizador.setFeature(namespaces, false);
            } else {
                System.out.println("[INFO] Espacio de nombres activado.");
            }
    
            analizador.setErrorHandler(errorHandler);
            // Procesar el documento XML
            File inputFile = new File("Noticias.xml");
            FileInputStream inputStream = new FileInputStream(inputFile);
            analizador.setContentHandler(handler);
            analizador.parse(new InputSource(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
