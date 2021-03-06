/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dao.ModuloConexao;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import static telas.FormDeResumo.btnVisualizar;

/**
 *
 * @author carlos
 */
public class FormDeResumo extends javax.swing.JInternalFrame {

    /**
     * Creates new form FormDeRaltorioSecretaria
     */
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public FormDeResumo() {
        initComponents();
         //setar curso
         connection = ModuloConexao.conector();
       javax.swing.SwingUtilities.invokeLater(new Runnable() { 
           public void run() { txtResCom.requestFocusInWindow(); }
       });
         
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnVisualizar = new javax.swing.JButton();
        txtResCom = new javax.swing.JFormattedTextField();
        cbSecretaria = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Resumos");
        setToolTipText("");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setText("Secretaria");

        jLabel3.setText("Competencia");

        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconsVisualizar.png"))); // NOI18N
        btnVisualizar.setMnemonic('n');
        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });
        btnVisualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnVisualizarKeyPressed(evt);
            }
        });

        try {
            txtResCom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cbSecretaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVisualizar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtResCom, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSecretaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(119, 119, 119)))
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtResCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbSecretaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addComponent(btnVisualizar)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setBounds(480, 150, 410, 256);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
       
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão?",
                    "Atenção",
                    JOptionPane.YES_NO_OPTION);
           
       if(confirma == JOptionPane.YES_OPTION){
           HashMap filtro = new HashMap();
           filtro.put("SEC", cbSecretaria.getSelectedItem().toString());
           filtro.put("MES", txtResCom.getText().substring(0, 2));
           filtro.put("ANO", txtResCom.getText().substring(3, 7));
           try{
               InputStream rep = getClass().getResourceAsStream("GrcpResumos.jasper");
               Connection connection = ModuloConexao.conector();
               JasperPrint print = JasperFillManager.fillReport(
                       rep,
                       filtro,
                       connection);
               JasperViewer.viewReport(print, false);       
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, e);
           }
       }
    }//GEN-LAST:event_btnVisualizarActionPerformed

    public void setarComboboxSecretaria(){
      
       
        String sql = "SELECT *FROM tbSecretaria";
       
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
               cbSecretaria.addItem(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }
    private void btnVisualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnVisualizarKeyPressed
        btnVisualizar.setMnemonic(KeyEvent.VK_ENTER); //TECLA DE ATALHO
    }//GEN-LAST:event_btnVisualizarKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        setarComboboxSecretaria();
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cbSecretaria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JFormattedTextField txtResCom;
    // End of variables declaration//GEN-END:variables
}
