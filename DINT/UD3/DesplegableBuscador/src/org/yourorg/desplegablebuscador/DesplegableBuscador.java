package org.yourorg.desplegablebuscador;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DesplegableBuscador<E> extends JTextField {
    private JPopupMenu popupMenu;
    private List<E> originalItems;
    private List<E> filteredItems;
    public DesplegableBuscador(){}
    
    public DesplegableBuscador(List<E> items) {
        super();
        this.originalItems = new ArrayList<>(items);
        this.filteredItems = new ArrayList<>();

        // Crear el menú desplegable
        popupMenu = new JPopupMenu();

        // Añadir un listener al texto para filtrar las opciones
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar(getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar(getText());
            }
        });

        // Configurar el comportamiento al perder el foco
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                popupMenu.setVisible(false);
            }
        });
    }

    private void filtrar(String text) {
        // Vaciar las opciones filtradas
        filteredItems.clear();
        popupMenu.removeAll();

        // Filtrar los elementos que coincidan con el texto
        for (E item : originalItems) {
            if (item.toString().toLowerCase().contains(text.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        // Si hay elementos que coinciden, mostrarlos en el menú desplegable
        if (!filteredItems.isEmpty()) {
            for (E item : filteredItems) {
                JMenuItem menuItem = new JMenuItem(item.toString());
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setText(item.toString());
                        popupMenu.setVisible(false);
                    }
                });
                popupMenu.add(menuItem);
            }

            // Mostrar el menú desplegable debajo del JTextField
            popupMenu.show(this, 0, this.getHeight());
        } else {
            popupMenu.setVisible(false);
        }
    }
}
