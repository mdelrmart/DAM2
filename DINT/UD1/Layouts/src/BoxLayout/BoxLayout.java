/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoxLayout;

import javax.swing.JFrame;

/**
 *
 * @author Miguel del Río
 */
public class BoxLayout {
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame myFrame = new JFrame("Formulario");
        myFrame.setSize(500, 300);
        //myFrame.setResizable(false);
        //myFrame.setLocation(200, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setContentPane(new InftBoxLayout());
        myFrame.setVisible(true);    
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
