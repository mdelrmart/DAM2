/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import bd.ConsultasSQL;
import clases.Can;
import clases.Propietario;
import clases.Vacina;
import clases.Vacinacion;
import java.awt.event.ItemEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.Datas;

/**
 *
 * @author DAM 2 
 */
public class IFrmXestionVacinacions extends javax.swing.JInternalFrame {

    /**
     * Creates new form IFrmAltaVacunacions
     */
    public IFrmXestionVacinacions() {
        initComponents();
        modeloPropietarios=new DefaultComboBoxModel<Propietario>();
        cmbPropietarios.setModel(modeloPropietarios);    
        modeloCans=new DefaultComboBoxModel<Can>();
        cmbCans.setModel(modeloCans);            
        modeloVacinas=new DefaultComboBoxModel<Vacina>();
        cmbVacinas.setModel(modeloVacinas); 
        modeloVacinacions=(DefaultTableModel)tblVacinacions.getModel();        
        cargarPropietarios();                
        cargarVacinas();                        
    }
    
    private void cargarPropietarios()
    {
        modeloPropietarios.removeAllElements();
        Vector propietarios=ConsultasSQL.recuperarTodolosPropietarios();
        if(propietarios==null)
        {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar os propietarios. Erro -10. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            return;            
        }
        else
        {
            for(int i=0;i<propietarios.size();i++)
            {
                modeloPropietarios.addElement((Propietario)propietarios.elementAt(i));
            }
        }        
    }        

    private void cargarVacinas()
    {
        modeloVacinas.removeAllElements();
        Vector vacinas=ConsultasSQL.recuperarTodalasVacinas();
        if(vacinas==null)
        {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar as vacinas. Erro -12. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            return;            
        }
        else
        {
            for(int i=0;i<vacinas.size();i++)
            {
                modeloVacinas.addElement((Vacina)vacinas.elementAt(i));
            }
        }        
    }            
    
    private void cargarCans(String dni)
    {
        modeloCans.removeAllElements();
        Vector cans=ConsultasSQL.recuperarTodolosCansDunPropietario(dni);
        if(cans==null)
        {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar os cans do propietario. Erro -11. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            return;            
        }
        else
        {
            for(int i=0;i<cans.size();i++)
            {
                modeloCans.addElement((Can)cans.elementAt(i));
            }
        }        
    }            
    
    private void cargarVacinacions()
    {
        modeloVacinacions.setRowCount(0);
        Vector vacinacions=ConsultasSQL.recuperarTodalasVacinacionsDunCan(modeloCans.getElementAt(cmbCans.getSelectedIndex()).getChip());
        if(vacinacions==null)
        {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar as vacinacións do can. Erro -14. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            modeloVacinacions.setRowCount(0);
            return;            
        }
        else
        {
            for(int i=0;i<vacinacions.size();i++)
            {
                //Recuperar info da vacuna
                Vector vacina=ConsultasSQL.recuperarUnhaVacunaDadoSeuCodigo(((Vacinacion)vacinacions.elementAt(i)).getCodVacina());
                if(vacina==null || vacina.size()==0)
                {
                    JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao recuperar as vacinacións do can. Erro -15. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
                    modeloVacinacions.setRowCount(0);
                    return;            
                }                
                else
                {
                    int filaActual=modeloVacinacions.getRowCount()+1;
                    modeloVacinacions.setRowCount(filaActual);
                    modeloVacinacions.setValueAt(vacina.elementAt(0), filaActual-1, 0);
                    modeloVacinacions.setValueAt(((Vacina)vacina.elementAt(0)).getNumTotalDoses(), filaActual-1, 1);
                    modeloVacinacions.setValueAt(((Vacinacion)vacinacions.elementAt(i)).getDose(), filaActual-1, 2);
                    modeloVacinacions.setValueAt(Datas.DataFormatoMySQLYYYY_GUION_MM_GUION_DDAFormatoDia_BARRA_Mes_BARRA_Anho(((Vacinacion)vacinacions.elementAt(i)).getData()), filaActual-1, 3);                    
                    modeloVacinacions.setValueAt(vacinacions.elementAt(i), filaActual-1, 4);                                    
                }

            }
        } 
        if(modeloVacinacions.getRowCount()>0) habilitarVacinacionsAplicadas();
        else deshabilitarVacinacionsAplicadas();
            
        
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

        lblPropietario = new javax.swing.JLabel();
        lblCan = new javax.swing.JLabel();
        lblChip = new javax.swing.JLabel();
        pnlVacinasAplicadas = new javax.swing.JPanel();
        scpVacinacions = new javax.swing.JScrollPane();
        tblVacinacions = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        pnlAplicarVacina = new javax.swing.JPanel();
        lblVacina = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblObservacions = new javax.swing.JLabel();
        cmbVacinas = new javax.swing.JComboBox();
        txtData = new javax.swing.JTextField();
        txtObservacions = new javax.swing.JTextField();
        btnVacinar = new javax.swing.JButton();
        pnlBotons = new javax.swing.JPanel();
        btnPechar = new javax.swing.JButton();
        cmbPropietarios = new javax.swing.JComboBox();
        cmbCans = new javax.swing.JComboBox();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Xestión de vacinacións");
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

        lblPropietario.setText("Propietario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(lblPropietario, gridBagConstraints);

        lblCan.setText("Can");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        getContentPane().add(lblCan, gridBagConstraints);

        lblChip.setText("CHIP --");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        getContentPane().add(lblChip, gridBagConstraints);

        pnlVacinasAplicadas.setBorder(javax.swing.BorderFactory.createTitledBorder("Vacinas aplicadas"));
        pnlVacinasAplicadas.setLayout(new java.awt.GridBagLayout());

        tblVacinacions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vacina", "Num. total doses", "Dose aplicada", "Data", "Observacións"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVacinacions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scpVacinacions.setViewportView(tblVacinacions);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlVacinasAplicadas.add(scpVacinacions, gridBagConstraints);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        pnlVacinasAplicadas.add(btnEliminar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnlVacinasAplicadas, gridBagConstraints);

        pnlAplicarVacina.setBorder(javax.swing.BorderFactory.createTitledBorder("Aplicar vacina"));
        pnlAplicarVacina.setLayout(new java.awt.GridBagLayout());

        lblVacina.setText("Vacina");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlAplicarVacina.add(lblVacina, gridBagConstraints);

        lblData.setText("Data");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlAplicarVacina.add(lblData, gridBagConstraints);

        lblObservacions.setText("Observacións");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlAplicarVacina.add(lblObservacions, gridBagConstraints);

        cmbVacinas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        pnlAplicarVacina.add(cmbVacinas, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        pnlAplicarVacina.add(txtData, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        pnlAplicarVacina.add(txtObservacions, gridBagConstraints);

        btnVacinar.setText("Vacinar");
        btnVacinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVacinarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        pnlAplicarVacina.add(btnVacinar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(pnlAplicarVacina, gridBagConstraints);

        btnPechar.setText("Pechar");
        btnPechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPecharActionPerformed(evt);
            }
        });
        pnlBotons.add(btnPechar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        getContentPane().add(pnlBotons, gridBagConstraints);

        cmbPropietarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPropietarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPropietariosItemStateChanged(evt);
            }
        });
        cmbPropietarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPropietariosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        getContentPane().add(cmbPropietarios, gridBagConstraints);

        cmbCans.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCans.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCansItemStateChanged(evt);
            }
        });
        cmbCans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCansActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        getContentPane().add(cmbCans, gridBagConstraints);

        setBounds(0, 0, 671, 408);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPecharActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnPecharActionPerformed

    private void cmbPropietariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPropietariosItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            String dniPropietario=modeloPropietarios.getElementAt(cmbPropietarios.getSelectedIndex()).getDni();
            lblChip.setText("CHIP --");
            deshabilitarVacinacionsAplicadas();
            deshabilitarAltaVacinas();
            cargarCans(dniPropietario);
        }        
    }//GEN-LAST:event_cmbPropietariosItemStateChanged

    private void deshabilitarVacinacionsAplicadas()
    {
        modeloVacinacions.setRowCount(0);
        tblVacinacions.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    private void habilitarVacinacionsAplicadas()
    {
        tblVacinacions.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    private void deshabilitarAltaVacinas()
    {
        cmbVacinas.setEnabled(false);
        txtData.setEnabled(false);
        txtObservacions.setEnabled(false);
        btnVacinar.setEnabled(false);
    }
    
    private void habilitarAltaVacinas()
    {
        cmbVacinas.setEnabled(true);
        txtData.setEnabled(true);
        txtObservacions.setEnabled(true);
        btnVacinar.setEnabled(true);
    }    
    private void cmbCansItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCansItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            //System.out.println("holaaaa");
            String chip=modeloCans.getElementAt(cmbCans.getSelectedIndex()).getChip();
            lblChip.setText("CHIP "+chip);
            habilitarAltaVacinas();
            cargarVacinacions();
        }                
    }//GEN-LAST:event_cmbCansItemStateChanged

    private void btnVacinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVacinarActionPerformed
        // TODO add your handling code here:
        if(cmbVacinas.getSelectedIndex()==-1)
        {
            JOptionPane.showMessageDialog(this, "Debe indicar a vacina que desexa aplicar", "Atención", JOptionPane.WARNING_MESSAGE);
            return;            
        }
        
        String dataVacina=txtData.getText().trim();
        if(dataVacina.compareTo("")==0)
        {
            JOptionPane.showMessageDialog(this, "Debe indicar a data de aplicación da vacina", "Atención", JOptionPane.WARNING_MESSAGE);
            return;            
        }        
        
        if(!Datas.isData_Dia_BARRA_Mes_BARRA_Anho_Valida(dataVacina))
        {
            JOptionPane.showMessageDialog(this, "Data de vacinación non válida (dd/mm/aaaa)", "Atención", JOptionPane.WARNING_MESSAGE);
            return;            
        }                
        dataVacina=Datas.Data_Dia_BARRA_Mes_BARRA_AnhoAFormatoMySQLYYYY_GUION_MM_GUION_DD(dataVacina);
        String observacionsVacina=txtObservacions.getText().trim();
        
        //Calcular cantas doses leva postas da vacina
        int codVacina=modeloVacinas.getElementAt(cmbVacinas.getSelectedIndex()).getCodVacina();
        int ultimaDose=ConsultasSQL.recuperarUltimaDoseDunhaVacinaParaOCan(modeloCans.getElementAt(cmbCans.getSelectedIndex()).getChip(), codVacina);
        if(ultimaDose==-1)
        {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos as doses das vacinas dun can. Erro -13. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            return;                        
        }
        if(ultimaDose<modeloVacinas.getElementAt(cmbVacinas.getSelectedIndex()).getNumTotalDoses())
        {
            ultimaDose++;
            Vacinacion vacinacion=new Vacinacion(0,modeloCans.getElementAt(cmbCans.getSelectedIndex()).getChip(), codVacina, dataVacina, observacionsVacina, ultimaDose);
            if(ConsultasSQL.insertarVacunacion(vacinacion)==-1)
            {
                JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao insertar a vacinación do can. Erro -16. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
                return;                                        
            }
            else
            {
                cargarVacinacions();    
                cmbVacinas.setSelectedIndex(-1);
                txtData.setText("");
                txtObservacions.setText("");
                JOptionPane.showMessageDialog(this, "Vacinación engadida", "Éxito", JOptionPane.INFORMATION_MESSAGE);                
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Non é posible vacinar ao can de " + modeloVacinas.getElementAt(cmbVacinas.getSelectedIndex()).getNomeVacina().toUpperCase()+ ". Xa foron administradas tódalas doses desta vacina", "Atención", JOptionPane.WARNING_MESSAGE);
            return;                                                    
        }
  
    }//GEN-LAST:event_btnVacinarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(tblVacinacions.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(this, "Debe indicar a vacinación que desexa eliminar", "Atención", JOptionPane.WARNING_MESSAGE);
            return;                        
        }
        
        switch(ConsultasSQL.existeVacinacionDeDoseSuperior(((Vacina)modeloVacinacions.getValueAt(tblVacinacions.getSelectedRow(), 0)).getCodVacina(), ((Vacinacion)modeloVacinacions.getValueAt(tblVacinacions.getSelectedRow(), 4)).getDose(),modeloCans.getElementAt(cmbCans.getSelectedIndex()).getChip()))
        {
            case 0: if(ConsultasSQL.eliminarUnhaVacinacionDadoSeuCodigo(((Vacinacion)modeloVacinacions.getValueAt(tblVacinacions.getSelectedRow(), 4)).getCodVacinacion())==-1)
                    {
                        JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao eliminar unha vacinación dun can. Erro -17. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
                        return;                                                    
                    } 
                    else
                    {
                        cargarVacinacions();
                        JOptionPane.showMessageDialog(this, "Vacinación eliminada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
            case -1:JOptionPane.showMessageDialog(this, "Ocurreu un erro de base de datos ao eliminar unha vacinación dun can. Erro -18. Póñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
                    return;                                                                    
            default:JOptionPane.showMessageDialog(this, "Non é posible eliminar a dose " +((Vacinacion)modeloVacinacions.getValueAt(tblVacinacions.getSelectedRow(), 4)).getDose()+" da vacinación seleccionada sen antes eliminar as doses posteriores", "Atención", JOptionPane.WARNING_MESSAGE);
                    return;                                                    
        }
  
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        XestorDeXanelas.pecharIFrmXestionVacinacions();
    }//GEN-LAST:event_formInternalFrameClosed

    private void cmbPropietariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPropietariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPropietariosActionPerformed

    private void cmbCansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCansActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnPechar;
    private javax.swing.JButton btnVacinar;
    private javax.swing.JComboBox cmbCans;
    private javax.swing.JComboBox cmbPropietarios;
    private javax.swing.JComboBox cmbVacinas;
    private javax.swing.JLabel lblCan;
    private javax.swing.JLabel lblChip;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblObservacions;
    private javax.swing.JLabel lblPropietario;
    private javax.swing.JLabel lblVacina;
    private javax.swing.JPanel pnlAplicarVacina;
    private javax.swing.JPanel pnlBotons;
    private javax.swing.JPanel pnlVacinasAplicadas;
    private javax.swing.JScrollPane scpVacinacions;
    private javax.swing.JTable tblVacinacions;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtObservacions;
    // End of variables declaration//GEN-END:variables
    private javax.swing.DefaultComboBoxModel<Propietario> modeloPropietarios;    
    private javax.swing.DefaultComboBoxModel<Can> modeloCans;        
    private javax.swing.DefaultComboBoxModel<Vacina> modeloVacinas;  
    private DefaultTableModel modeloVacinacions;
}
