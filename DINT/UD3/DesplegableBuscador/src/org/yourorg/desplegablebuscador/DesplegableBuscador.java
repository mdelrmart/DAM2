package org.yourorg.desplegablebuscador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class DesplegableBuscador<T> extends JComboBox<T> implements Serializable {
    private final JComboBox<T> comboBox;
    private boolean filtrado;
    private JTextField txtFiltro;
    private List<T> listaOriginal;

    public DesplegableBuscador() {
        comboBox = new JComboBox<>();
    }

    public DesplegableBuscador(DefaultComboBoxModel<T> m) {
        comboBox = new JComboBox<>(m);
        txtFiltro = new JTextField();
        txtFiltro.addKeyListener(new FilterKeyListener());
        txtFiltro.addActionListener(new FilterActionListener());
    }

    public boolean isFiltrado() {
        return filtrado;
    }

    public void setFiltrado(boolean filtrado) {
        this.filtrado = filtrado;
        txtFiltro.setEnabled(filtrado);
    }

    public void setMasterItemList(List<T> itemList) {
        listaOriginal = new ArrayList<>(itemList);
        filtrarContenido();
    }

    private void filtrarContenido() {
        comboBox.removeAllItems();
        String filterText = txtFiltro.getText().toLowerCase();

        for (T item : listaOriginal) {
            if (item.toString().toLowerCase().contains(filterText)) {
                comboBox.addItem(item);
            }
        }
    }

    private class FilterKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            filtrarContenido();
        }
    }

    private class FilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            filtrarContenido();
        }
    }
}
