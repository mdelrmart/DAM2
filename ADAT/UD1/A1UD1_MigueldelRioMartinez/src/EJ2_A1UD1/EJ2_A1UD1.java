/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJ2_A1UD1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Miguel del Río
 */
public class EJ2_A1UD1 {
    public static void main(String[] args) {

        try (PrintStream printStream = new PrintStream(".\\Datos.txt"))
        {
            System.out.print("Leer cadena: ");
            String s = Teclado.leer();
            System.out.print("Leer caracter: ");
            char car = Teclado.leerChar();
            System.out.print("Leer numero entero: ");
            int num1 = Teclado.leerInt();
            System.out.print("Leer numero double: ");
            double num2 = Teclado.leerDouble();
            System.out.println("Leer numero positivo: ");
            int num3 = Teclado.leerPositivo();

            printStream.println("                      RESULTADOS");
            printStream.println("                    --------------");
            printStream.println(" cadena: " + s);
            printStream.println(" el caracter tecleado: " + car);
            printStream.println(" entero: " + num1);
            printStream.println(" numero real double: " + num2);
            printStream.println(" entero positivo: " + num3);
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExcepcionEnteroPositivo e) {
            System.err.println("El número no era positivo");
            return;
        }
    }
}
