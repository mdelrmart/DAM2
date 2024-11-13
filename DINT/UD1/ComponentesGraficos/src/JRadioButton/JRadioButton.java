/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JRadioButton;

import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class JRadioButton {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Formulario");
        myFrame.setSize(410, 350);
        myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new IntfJRadioButton());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
