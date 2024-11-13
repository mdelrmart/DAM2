/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GridBagLayout;

import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class GridBagLayout {
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame myFrame = new JFrame("Formulario");
        myFrame.setSize(600, 400);
        //myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new IntfGridBagLayout());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
