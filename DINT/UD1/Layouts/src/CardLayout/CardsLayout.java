/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author usuario
 */
public class CardsLayout {
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame myFrame = new JFrame("Formulario");
        myFrame.setSize(800, 150);
        //myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new IntfCardLayout());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
