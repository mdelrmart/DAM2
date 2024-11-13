/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GridLayout;

import FlowLayout.IntfFlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class GridLayout {
        // TODO code application logic here
        public static void main(String[] args) {
        
        JFrame myFrame = new JFrame("Formulario");
        myFrame.setSize(400, 300);
        //myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new IntfGridLayout());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
