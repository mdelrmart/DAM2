/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EJ1_A1UD1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Miguel del Río
 */
public class EJ1_A1UD1 {

    public static void main(String[] args) throws IOException {

        String num1_input = "0";
        String num2_input = "0";
        
        BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
        
        boolean num1_esCorrecto = false;
        
        while (!num1_esCorrecto) {
            System.out.println("Introduce el primer número:");
            num1_input = entrada.readLine();

            if(!esEntero(num1_input)) {
                System.out.println("El número no es un número entero. Inténtalo de nuevo.");
            }
            else {
                num1_esCorrecto = true;
            }
        }
        
        boolean num2_esCorrecto = false;
        
        while (!num2_esCorrecto) {
            System.out.println("Introduce el segundo número:");
            num2_input = entrada.readLine();

            if(!esEntero(num2_input)) {
                System.out.println("El número no es un entero. Inténtalo de nuevo.");
            }
            else {
                num2_esCorrecto = true;
            }
        }
        
        System.out.println("\n");
        
        int num1 = Integer.parseInt (num1_input);
        int num2 = Integer.parseInt (num2_input);

        int suma = num1+num2;
        System.out.println("El resultado de la suma es: " + suma);

        int resta = num1-num2;
        System.out.println("El resultado de la resta es: " + resta);

        int multiplicacion = num1*num2;
        System.out.println("El resultado de la multiplicación es: " + multiplicacion);

        try {
            int division = num1/num2;
            System.out.println("El resultado de la división entera es: " + division);

            float division_real = (float) num1 / num2;
            System.out.println("El resultado de la división real es: " + division_real);

            int resto = num1 % num2;
            System.out.println("El resultado del resto es: " + resto);
        } catch (ArithmeticException e) {
            System.out.println("No se puede dividir entre cero.");
        }
        
    }
    
    public static boolean esEntero(String str) { 
        try {
            Integer.valueOf(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    
}
