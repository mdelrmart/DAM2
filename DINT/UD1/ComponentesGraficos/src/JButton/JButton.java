/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JButton;

import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class JButton {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Formulario");
        myFrame.setSize(600, 400);
        //myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new IntfJButton());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
