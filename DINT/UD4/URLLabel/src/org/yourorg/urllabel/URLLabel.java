/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.yourorg.urllabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel del Rio
 */

public class URLLabel extends JLabel implements Serializable {
    private String url;
    private String navegador;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }
    
    public void irURL() {
        try {
            Runtime.getRuntime().exec(navegador + " " + url);
        } catch (IOException e) {
            if (JOptionPane.showConfirmDialog(
                this,
                "Non foi posible abrir a URL no navegador " + navegador + "\n¿Quere abrila no navegador por defecto?",
                "Información",
                JOptionPane.YES_NO_OPTION
            ) == JOptionPane.YES_OPTION) {
                irURLDefecto();
            }
        }
    }
    
    public void irURLDefecto() {
        try {
            Runtime.getRuntime().exec("xdg-open " + url);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Non foi posible abrir a URL.\n" + e.getMessage());
        }
    }
    
    public URLLabel() {
        setText("Clic aqui");
        setForeground(Color.BLUE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                irURL();
            }
        });
    }
    
}
