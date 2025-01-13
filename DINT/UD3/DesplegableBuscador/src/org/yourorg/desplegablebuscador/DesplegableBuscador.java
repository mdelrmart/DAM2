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


public class DesplegableBuscador extends JComboBox<Object> implements Serializable {

    private final JComboBox comboBox;
    private boolean filtrado;
    private JTextField txtFiltro;
    private List<String> listaOriginal;

    public DesplegableBuscador() {
        comboBox = new JComboBox();
    }
      

    public DesplegableBuscador(DefaultComboBoxModel m) {
        comboBox = new DesplegableBuscador(m);
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
    
    public void setMasterItemList(List<String> itemList) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            itemList.add((String)comboBox.getModel().getElementAt(i));
        }
        listaOriginal = new ArrayList<>(itemList);
        filtrarContenido();
    }

    private void filtrarContenido() {
        comboBox.removeAllItems();
        String filterText = txtFiltro.getText().toLowerCase();

        for (String item : listaOriginal) {
            if (item.toLowerCase().contains(filterText)) {
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
