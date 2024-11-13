/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JCheckBox2;

import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class JCheckBox2 {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Formulario");
        myFrame.setSize(400, 220);
        //myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new InftJCheckBox2());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
