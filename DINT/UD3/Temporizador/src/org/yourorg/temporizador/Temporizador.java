/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.yourorg.temporizador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author usuario
 */
public class Temporizador extends JLabel implements Serializable {

    private PropertyChangeSupport propertySupport;
    private Timer timer;
    private int tempo;

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
        setText(Integer.toString(tempo));
        repaint();
    }

    private OnFinalContaAtras listener;

    public interface OnFinalContaAtras {
        void FinalContaAtras();
    }

    public void setOnFinalContaAtrasListener(OnFinalContaAtras listener) {
        this.listener = listener;
    }

    public Temporizador() {
        propertySupport = new PropertyChangeSupport(this);

        ActionListener objecto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getTempo() <= 0) {
                    timer.stop();
                    if (listener != null) {  // Verificación del listener
                        listener.FinalContaAtras();
                    }
                } else {
                    setTempo(getTempo() - 1);
                }
            }
        };

        timer = new Timer(1000, objecto);
    }

    public void comeza() {
        timer.start();
    }
}
