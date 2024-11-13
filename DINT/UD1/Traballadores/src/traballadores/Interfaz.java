/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package traballadores;

import javax.swing.JFrame;

/**
 *
 * @author Miguel del Río
 */
public class Interfaz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Traballadores");
        myFrame.setSize(1200, 600);
        //myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new IntfTraballadores());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
