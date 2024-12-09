package A3UD2_EJ1;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
    private boolean valid = true;

    @Override
    public void warning(SAXParseException e) throws SAXException {
        valid = false;
        System.out.println("Advertencia: " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        valid = false;
        System.out.println("Error: " + e.getMessage());
        //Podemos lanzar la excepción "e" para que no siga procesando el documento XML
        throw e;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        valid = false;
        System.out.println("Error fatal: " + e.getMessage());
        throw e;
    }

    public boolean isValid() {
        return valid;
    }
}