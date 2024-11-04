/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJ2_A1UD1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Miguel del Río
 */
public class Teclado {
    
    public static String leer() throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String lectura = entrada.readLine();
        
        return lectura;
    }
    
    public static char leerChar() throws IOException {
        BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
        String lectura = entrada.readLine();
        
        char lecturaChar = lectura.charAt(0);
        
        return lecturaChar;
    }

    public static int leerInt() throws IOException {
        BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
        String lectura = entrada.readLine();
       
        Integer lecturaInt = Integer.valueOf(lectura);
        
        return lecturaInt;
    }
    
    public static double leerDouble() throws IOException {
        BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
        String lectura = entrada.readLine();
        
        Double lecturaDouble = Double.valueOf(lectura);
        
        //PrintStream printStream  = new PrintStream(System.out, true);
        //printStream.println(" numero real double: " + lecturaDouble);
        
        return lecturaDouble;
    }
    
    public static int leerPositivo() throws IOException, ExcepcionEnteroPositivo {
        BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
        boolean esPositivo = false;
        
        int lecturaInteger = 0;
        
        while (!esPositivo) {
            String lectura = entrada.readLine();
            lecturaInteger = Integer.parseInt(lectura);
            
            if(!esPositivo(lecturaInteger)) {
                throw new ExcepcionEnteroPositivo();
            }
            else {
                esPositivo = true;
            }
           
        }
        return lecturaInteger;
    } 

    public static boolean esPositivo(int num){
        return num > 0;
    }
}
