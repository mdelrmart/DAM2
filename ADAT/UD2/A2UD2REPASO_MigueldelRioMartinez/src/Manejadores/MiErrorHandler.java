package Manejadores;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MiErrorHandler extends DefaultHandler {
    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Warning: ");
        printInfo(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("Error: ");
        printInfo(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Fatal Error: ");
        printInfo(e);
    }

    private void printInfo(SAXParseException e) {
        System.out.println("   Line: " + e.getLineNumber());
        System.out.println("   Column: " + e.getColumnNumber());
        System.out.println("   Message: " + e.getMessage());
        System.out.println("   Public ID: " + e.getPublicId());
        System.out.println("   System ID: " + e.getSystemId());
    }

}
