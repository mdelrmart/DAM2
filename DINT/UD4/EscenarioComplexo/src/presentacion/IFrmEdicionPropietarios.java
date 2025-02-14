/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import bd.ConsultasSQL;
import clases.HeaderRenderer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import clases.Propietario;
import javax.swing.table.JTableHeader;

/**
 *
 * @author usuario
 */
public class IFrmEdicionPropietarios extends javax.swing.JInternalFrame {

    public IFrmEdicionPropietarios() {
        initComponents();
        modeloPropietarios = (DefaultTableModel) tblPropietarios.getModel();
        cargarPropietarios();
        
        tblPropietarios.setAutoCreateRowSorter(true);

        // Personalizar el renderizador del encabezado
        //JTableHeader header = tblPropietarios.getTableHeader();
        //header.setDefaultRenderer(new HeaderRenderer());

        // Configurar el layout del JFrame
        //frame.add(new JScrollPane(table), BorderLayout.CENTER);
        //frame.setVisible(true);
    }

    private void cargarPropietarios() {
        modeloPropietarios.setRowCount(0);
        listaPropietarios.clear();

        List<Propietario> propietarios = ConsultasSQL.recuperarTodolosPropietariosList();

        if (propietarios == null || propietarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar os propietarios. Erro -15. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            modeloPropietarios.setRowCount(0);
        } else {
            for (Propietario propietario : propietarios) {
                listaPropietarios.add(propietario);
                Object[] fila = {
                    propietario.toString(),
                    propietario.getDni(),
                    propietario.getTlf(),
                    propietario.geteMail()
                };
                modeloPropietarios.addRow(fila);
            }
        }
    }

    private void cargarPropietarioEdicion() {
        activarBotones();
        
        int filaSeleccionada = tblPropietarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un propietario da táboa para editar.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        int filaModelo = tblPropietarios.convertRowIndexToModel(filaSeleccionada);
        Propietario propietarioSeleccionado = listaPropietarios.get(filaModelo);

        txtNome.setText(propietarioSeleccionado.getNome());
        txtApelido1.setText(propietarioSeleccionado.getAp1());
        txtApelido2.setText(propietarioSeleccionado.getAp2());
        txtDNI.setText(propietarioSeleccionado.getDni());
        txtEmail.setText(propietarioSeleccionado.geteMail());
        txtTelefono.setText(propietarioSeleccionado.getTlf());
    }

    private void eliminarPropietarioSeleccionado() {
        int filaSeleccionada = tblPropietarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un propietario da táboa para eliminar.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        int filaModelo = tblPropietarios.convertRowIndexToModel(filaSeleccionada);
        Propietario propietarioSeleccionado = listaPropietarios.get(filaModelo);

        // Verificar si el propietario puede ser eliminado
        int resultado = ConsultasSQL.verificarRestriccionesDeEliminacionDePropietario(propietarioSeleccionado.getDni());
        switch (resultado) {
            case 0: // Se puede eliminar
                if (ConsultasSQL.eliminarUnPropietarioDadoSeuDni(propietarioSeleccionado.getDni()) == -1) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ocurreu un erro de base de datos ao eliminar o propietario. Erro -17.",
                            "Atención",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    cargarPropietarios(); // Recargar las citas
                    JOptionPane.showMessageDialog(
                            this,
                            "O propietario foi eliminado con éxito.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    limpiarCampos();
                    desactivarBotones();
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
                        "Non é posible eliminar o propietario seleccionado xa que ten cans asociados.",
                        "Atención",
                        JOptionPane.WARNING_MESSAGE
                );
                break;
        }
    }

    private void editarPropietarioSeleccionado() {
        int filaSeleccionada = tblPropietarios.getSelectedRow();
        
        // No se debería llegar a esta condición ya que se controla antes
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un propietario da táboa para editar.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String dni = txtDNI.getText();
        String nome = txtNome.getText();
        String ap1 = txtApelido1.getText();
        String ap2 = txtApelido2.getText();
        String tel = txtTelefono.getText();
        String email = txtEmail.getText();

        if (dni.isBlank() || nome.isBlank() || ap1.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Non se cubriron todos os campos obligatorios.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int filaModelo = tblPropietarios.convertRowIndexToModel(filaSeleccionada);
        Propietario propietarioSeleccionado = listaPropietarios.get(filaModelo);

        Propietario pr = new Propietario(dni, nome, ap1, ap2, tel, email);

        if (ConsultasSQL.actualizarPropietario(propietarioSeleccionado.getDni(), pr) == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocurreu un erro de base de datos ao editar o propietario. Erro -17.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            cargarPropietarios();

            JOptionPane.showMessageDialog(
                    this,
                    "O propietario foi editado con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();
            desactivarBotones();
        }
    }

    private void limpiarCampos() {
        txtDNI.setText("");
        txtNome.setText("");
        txtApelido1.setText("");
        txtApelido2.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
    
    private void activarBotones() {
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    private void desactivarBotones() {
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
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

        rbtGrpOrdenacion = new javax.swing.ButtonGroup();
        pnlDatos = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblApelido1 = new javax.swing.JLabel();
        lblApelido2 = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtApelido1 = new javax.swing.JTextField();
        txtApelido2 = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        scrpnlPropietarios = new javax.swing.JScrollPane();
        tblPropietarios = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listado de propietarios");
        setMinimumSize(new java.awt.Dimension(800, 610));
        setPreferredSize(new java.awt.Dimension(800, 610));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos do propietario"));
        pnlDatos.setLayout(new java.awt.GridBagLayout());

        lblNome.setText("Nome *");
        lblNome.setToolTipText("Obrigatorio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(lblNome, gridBagConstraints);

        lblApelido1.setText("Primer apelido *");
        lblApelido1.setToolTipText("Obrigatorio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(lblApelido1, gridBagConstraints);

        lblApelido2.setText("Segundo apelido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(lblApelido2, gridBagConstraints);

        lblTelefono.setText("Teléfono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(lblTelefono, gridBagConstraints);

        lblDNI.setText("DNI *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(lblDNI, gridBagConstraints);

        lblEmail.setText("E-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(lblEmail, gridBagConstraints);

        txtNome.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtNomeInputMethodTextChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(txtNome, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(txtApelido1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(txtApelido2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(txtTelefono, gridBagConstraints);

        txtDNI.setEnabled(false);
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(txtDNI, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(txtEmail, gridBagConstraints);

        btnEliminar.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 0, 51));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/x.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Elimina o propietario da base de datos");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        pnlDatos.add(btnEliminar, gridBagConstraints);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pencil.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setToolTipText("Edita a información do propietario na base de datos");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        pnlDatos.add(btnEditar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(pnlDatos, gridBagConstraints);

        pnlListado.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de propietarios"));
        pnlListado.setLayout(new java.awt.GridBagLayout());

        scrpnlPropietarios.setPreferredSize(new java.awt.Dimension(200, 200));

        tblPropietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "DNI", "Teléfono", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPropietarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPropietariosMouseClicked(evt);
            }
        });
        scrpnlPropietarios.setViewportView(tblPropietarios);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlListado.add(scrpnlPropietarios, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(pnlListado, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (txtDNI.getText().isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un propietario da táboa para eliminar.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de editar o propietario seleccionado?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            editarPropietarioSeleccionado();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        XestorDeXanelas.pecharIFrmEdicionPropietarios();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (txtDNI.getText().isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un propietario da táboa para eliminar.",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar o propietario co DNI " + txtDNI.getText() + "?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            eliminarPropietarioSeleccionado();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void tblPropietariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPropietariosMouseClicked
        // TODO add your handling code here:
        cargarPropietarioEdicion();
    }//GEN-LAST:event_tblPropietariosMouseClicked

    private void txtNomeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtNomeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeInputMethodTextChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel lblApelido1;
    private javax.swing.JLabel lblApelido2;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlListado;
    private javax.swing.ButtonGroup rbtGrpOrdenacion;
    private javax.swing.JScrollPane scrpnlPropietarios;
    private javax.swing.JTable tblPropietarios;
    private javax.swing.JTextField txtApelido1;
    private javax.swing.JTextField txtApelido2;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel modeloPropietarios;
    private List<Propietario> listaPropietarios = new ArrayList<>();

}
