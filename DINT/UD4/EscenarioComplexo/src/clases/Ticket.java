/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author usuario
 */
public class Ticket {
    public static void main(String[] args) throws FileNotFoundException, JRException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica" , "root", "abc123.");
        
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("WHERE", "where f.id_factura = 13");
        
        JasperPrint print = JasperFillManager.fillReport(new FileInputStream(new File("src/informes/report1.jasper")), parametros, conn);
        
    }
}