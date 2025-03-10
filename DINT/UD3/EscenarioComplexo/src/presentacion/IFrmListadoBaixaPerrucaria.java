/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import bd.ConsultasSQL;
import bd.ConsultasSQLPerrucaria;
import clases.Vacina;
import clases.Vacinacion;
import clases.CitaPerrucaria;
import clases.ListadoPerrucaria;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.Datas;

/**
 *
 * @author usuario
 */
public class IFrmListadoBaixaPerrucaria extends javax.swing.JInternalFrame {

    /**
     * Creates new form IFrmListadoBaixaPerrucaria
     */
    public IFrmListadoBaixaPerrucaria() {
        initComponents();
        modeloResultados = (DefaultTableModel) tblResultados.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlBusqueda = new javax.swing.JPanel();
        chkData = new javax.swing.JCheckBox();
        lblDesde = new javax.swing.JLabel();
        txtDesde = new javax.swing.JTextField();
        lblAta = new javax.swing.JLabel();
        txtAta = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlResultados = new javax.swing.JPanel();
        scpResultados = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        pnlBotones = new javax.swing.JPanel();
        btnPechar = new javax.swing.JButton();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Baixa e listado citas perrucaría");
        setMinimumSize(new java.awt.Dimension(700, 450));
        setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(700, 450));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Búsqueda de citas"));
        pnlBusqueda.setToolTipText("");
        pnlBusqueda.setLayout(new java.awt.GridBagLayout());

        chkData.setText("Buscar por data");
        chkData.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkDataStateChanged(evt);
            }
        });
        chkData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDataActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        pnlBusqueda.add(chkData, gridBagConstraints);

        lblDesde.setText("Desde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        pnlBusqueda.add(lblDesde, gridBagConstraints);

        txtDesde.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        pnlBusqueda.add(txtDesde, gridBagConstraints);

        lblAta.setText("Ata");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        pnlBusqueda.add(lblAta, gridBagConstraints);

        txtAta.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        pnlBusqueda.add(txtAta, gridBagConstraints);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        pnlBusqueda.add(btnBuscar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(pnlBusqueda, gridBagConstraints);

        pnlResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados da búsqueda"));
        pnlResultados.setMinimumSize(new java.awt.Dimension(372, 208));
        pnlResultados.setPreferredSize(new java.awt.Dimension(372, 300));
        pnlResultados.setLayout(new java.awt.GridBagLayout());

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PROPIETARIO", "CAN", "DATA", "HORA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scpResultados.setViewportView(tblResultados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlResultados.add(scpResultados, gridBagConstraints);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 5, 20);
        pnlResultados.add(btnEliminar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(pnlResultados, gridBagConstraints);

        pnlBotones.setLayout(new java.awt.GridBagLayout());

        btnPechar.setText("Pechar");
        btnPechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPecharActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlBotones.add(btnPechar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(pnlBotones, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDataActionPerformed
        // TODO add your handling code here:
        if (chkData.isSelected()) {
            txtDesde.setEnabled(true);
            txtAta.setEnabled(true);
        } else {
            txtDesde.setEnabled(false);
            txtAta.setEnabled(false);

            txtDesde.setText("");
            txtAta.setText("");
        }
    }//GEN-LAST:event_chkDataActionPerformed

    private void chkDataStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkDataStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDataStateChanged

    private void btnPecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPecharActionPerformed
        // TODO add your handling code here:
        XestorDeXanelas.pecharIFrmBaixaListadoPerrucaria();
        dispose();
    }//GEN-LAST:event_btnPecharActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        // TODO COMPROBAR EL DATO DEL CAMPO
        if (chkData.isSelected()) {
            if (!txtDesde.getText().isBlank() && !txtAta.getText().isBlank()) {
                cargarCitasFecha();
            } else {
                JOptionPane.showMessageDialog(this, "O formato da data non é correcto ou os campos están baleiros.", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            cargarCitas();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarCitaSeleccionada();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cargarCitas() {
        modeloResultados.setRowCount(0);
        listaCitas.clear();
        
        //Recuperar info das citas
        List<ListadoPerrucaria> citas = ConsultasSQLPerrucaria.recuperarTodaAsCitasDePerrucariaList();

        if (citas == null || citas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar as citas da perrucaría do can. Erro -15. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            modeloResultados.setRowCount(0);
            return;
        } else {
            for (ListadoPerrucaria cita : citas) {
                listaCitas.add(cita);
                Object[] fila = {
                    cita.getPropietario(),
                    cita.getCan(),
                    cita.getData(),
                    cita.getHora()
                };
                modeloResultados.addRow(fila);
            }
        }
    }

    private void cargarCitasFecha() {
        modeloResultados.setRowCount(0);

        String fechaInicial = Datas.Data_Dia_BARRA_Mes_BARRA_AnhoAFormatoMySQLYYYY_GUION_MM_GUION_DD(txtDesde.getText());
        String fechaFinal = Datas.Data_Dia_BARRA_Mes_BARRA_AnhoAFormatoMySQLYYYY_GUION_MM_GUION_DD(txtAta.getText());

        //Recuperar info das citas
        List<ListadoPerrucaria> citas = ConsultasSQLPerrucaria.recuperarTodaAsCitasDePerrucariaEntreDuasDatasList(fechaInicial, fechaFinal);

        if (citas == null) {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar as citas da perrucaría do can. Erro -15. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            modeloResultados.setRowCount(0);
            return;
        } else if (citas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Non se atoparon citas para o rango de datas indicado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            modeloResultados.setRowCount(0);
            return;
        } else {
            for (ListadoPerrucaria cita : citas) {
                Object[] fila = {
                    cita.getPropietario(),
                    cita.getCan(),
                    cita.getData(),
                    cita.getHora()
                };
                modeloResultados.addRow(fila);
            }

            /*for (int i = 0; i < citas.size(); i++) {
                int filaActual = modeloResultados.getRowCount() + 1;
                modeloResultados.setRowCount(filaActual);
                modeloResultados.setValueAt(citas.get(i), filaActual - 1, 0);
                modeloResultados.setValueAt(citas.elementAt(2), filaActual - 1, 1);
                modeloResultados.setValueAt(citas.elementAt(3), filaActual - 1, 2);
                modeloResultados.setValueAt(citas.elementAt(4), filaActual - 1, 3);

                //modeloResultados.setValueAt(((CitaPerrucaria) citas.elementAt(0)).getCodCan(), filaActual - 1, 1);
                //modeloResultados.setValueAt(((CitaPerrucaria) citas.elementAt(i)).getData(), filaActual - 1, 2);
                //modeloResultados.setValueAt(Datas.DataFormatoMySQLYYYY_GUION_MM_GUION_DDAFormatoDia_BARRA_Mes_BARRA_Anho(((CitaPerrucaria) citas.elementAt(i)).getData()), filaActual - 1, 3);
                //modeloResultados.setValueAt(citas.elementAt(i), filaActual - 1, 4);
            }*/
        }

        /*if (modeloResultados.getRowCount() > 0) {
            habilitarVacinacionsAplicadas();
        } else {
            deshabilitarVacinacionsAplicadas();
        }*/
    }

    private void eliminarCitaSeleccionada() {
        int filaSeleccionada = tblResultados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar unha cita para eliminar.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Obtener la cita seleccionada desde la lista
        ListadoPerrucaria citaSeleccionada = listaCitas.get(filaSeleccionada);

        // Verificar si la cita puede ser eliminada
        //int resultado = ConsultasSQLPerrucaria.verificarRestriccionesDeEliminacionDeCita(citaSeleccionada.getCodCita());
        int resultado = 0;
        switch (resultado) {
            case 0: // Se puede eliminar
                if (ConsultasSQLPerrucaria.eliminarUnhaCitaDadoSeuCodigo(citaSeleccionada.getCodCita()) == -1) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ocurreu un erro de base de datos ao eliminar a cita. Erro -17.",
                            "Atención",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    cargarCitas(); // Recargar las citas
                    JOptionPane.showMessageDialog(
                            this,
                            "A cita foi eliminada con éxito.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                break;

            case -1: // Error en la verificación
                JOptionPane.showMessageDialog(
                        this,
                        "Ocurreu un erro de base de datos ao verificar a eliminación. Erro -18.",
                        "Atención",
                        JOptionPane.WARNING_MESSAGE
                );
                break;

            default: // No se puede eliminar debido a restricciones
                JOptionPane.showMessageDialog(
                        this,
                        "Non é posible eliminar a cita seleccionada debido a restriccións relacionadas.",
                        "Atención",
                        JOptionPane.WARNING_MESSAGE
                );
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnPechar;
    private javax.swing.JCheckBox chkData;
    private javax.swing.JLabel lblAta;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlResultados;
    private javax.swing.JScrollPane scpResultados;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtAta;
    private javax.swing.JTextField txtDesde;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel modeloResultados;
    private List<ListadoPerrucaria> listaCitas = new ArrayList<>();

}
