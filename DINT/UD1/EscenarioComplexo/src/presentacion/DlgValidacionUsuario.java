/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import bd.Conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author DAM IES Chan do Monte
 */
public class DlgValidacionUsuario extends javax.swing.JDialog {

    /**
     * Creates new form DlgValidacionUsuario
     */
    public DlgValidacionUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
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

        lblUsuario = new javax.swing.JLabel();
        lblContrasinal = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        pwfContrasinal = new javax.swing.JPasswordField();
        panBotons = new javax.swing.JPanel();
        btnConectar = new javax.swing.JButton();
        btnPechar = new javax.swing.JButton();
        lblIPServidor = new javax.swing.JLabel();
        lblPorto = new javax.swing.JLabel();
        txtIPServidor = new javax.swing.JTextField();
        txtPorto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Validación de usuario");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblUsuario.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        getContentPane().add(lblUsuario, gridBagConstraints);

        lblContrasinal.setText("Contrasinal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(lblContrasinal, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        getContentPane().add(txtUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        getContentPane().add(pwfContrasinal, gridBagConstraints);

        panBotons.setLayout(new javax.swing.BoxLayout(panBotons, javax.swing.BoxLayout.X_AXIS));

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });
        panBotons.add(btnConectar);

        btnPechar.setText("Pechar");
        btnPechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPecharActionPerformed(evt);
            }
        });
        panBotons.add(btnPechar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(panBotons, gridBagConstraints);

        lblIPServidor.setText("IP Servidor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(lblIPServidor, gridBagConstraints);

        lblPorto.setText("Porto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(lblPorto, gridBagConstraints);

        txtIPServidor.setText("localhost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        getContentPane().add(txtIPServidor, gridBagConstraints);

        txtPorto.setText("3306");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        getContentPane().add(txtPorto, gridBagConstraints);

        setSize(new java.awt.Dimension(422, 226));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        // TODO add your handling code here:
        String usuario=txtUsuario.getText().trim();
        if(usuario.compareTo("")==0)
        {
            JOptionPane.showMessageDialog(this, "Debe indicar o usuario para conectarse á base de datos", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String contrasinal=new String(pwfContrasinal.getPassword());        
        
        String ipServidor=txtIPServidor.getText().trim();
        if(ipServidor.compareTo("")==0)
        {
            JOptionPane.showMessageDialog(this, "Debe indicar a dirección do servidor de base de datos", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }  
        
        String porto=txtPorto.getText().trim();
        if(porto.compareTo("")==0)
        {
            JOptionPane.showMessageDialog(this, "Debe indicar o porto de servidor de base de datos", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }          
         
        int resultado=Conexion.conectar(ipServidor, porto, usuario, "clinica", contrasinal);
        if(resultado!=0)
        {
            JOptionPane.showMessageDialog(this, "Ocurreu un erro ao intentar conectar coa base de datos, Erro "+resultado+"\nPóñase en contacto co administrador", "Atención", JOptionPane.WARNING_MESSAGE);
            System.exit(2);
        }        
        conectado=true;
        dispose();
    }//GEN-LAST:event_btnConectarActionPerformed

    private void btnPecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPecharActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnPecharActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if(!conectado) System.exit(0);        
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgValidacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgValidacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgValidacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgValidacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgValidacionUsuario dialog = new DlgValidacionUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnPechar;
    private javax.swing.JLabel lblContrasinal;
    private javax.swing.JLabel lblIPServidor;
    private javax.swing.JLabel lblPorto;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panBotons;
    private javax.swing.JPasswordField pwfContrasinal;
    private javax.swing.JTextField txtIPServidor;
    private javax.swing.JTextField txtPorto;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
    boolean conectado=false;

}
