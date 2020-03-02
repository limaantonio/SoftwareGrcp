/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dao.ModuloConexao;
import entidades.Guia;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.mozilla.javascript.tools.debugger.GuiCallback;
import static telas.NovaGuia.btnSalvar;
import static telas.NovaGuia.txtBaseCalculo;
import static telas.NovaGuia.txtId;
import static telas.NovaGuia.txtNumero;

import static telas.NovaGuia.cbSecretaria;
import static telas.NovaGuia.cbSetor;
import static telas.NovaGuia.txtData;
import static telas.NovaGuia.txtObservacao;
import static telas.NovaGuia.txtOrgao;
import static telas.NovaGuia.txtTotalGeral;
import static telas.NovaGuia.txtTotalPatronal;
import static telas.NovaGuia.txtValPatronal;
import static telas.NovaGuia.txtValServidor;
import static telas.NovaGuia.txtValSuplementar;
import static telas.NovaGuia.txtVencimento;

/**
 *
 * @author carlos
 */
public class Pesquisa extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pesquisa
     */
    
    
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Pesquisa() {
        initComponents();
        connection = ModuloConexao.conector();
         btOk.setMnemonic(KeyEvent.VK_ENTER); //TECLA DE ATALHO
         tbCancelar.setMnemonic(KeyEvent.VK_ESCAPE);
         
    }
    
    public void pesquisar(){
        
        String sql = "SELECT GUIAS.GUI_ID, GUI_NUMERO, GUI_BASE,"
                    + " GUI_VALOR_SERVIDOR, GUI_VALOR_PATRONAL,"
                    + " GUI_VALOR_SUPLEMENTAR, GUI_OBSERVACAO, GUI_DATA_CADASTRO,"
                    + " SEC.SEC_NOME, SET_NOME, SET_TIPO"
                    + " FROM tbGuias AS GUIAS"
                    + " INNER JOIN tbSetor AS SETOR"
                    + " ON (SETOR.SET_ID = SetorId)"
                    + " INNER JOIN tbSecretaria AS SEC"
                    + " ON (SEC.SEC_ID = SETOR.SecretariaId)"
                    + " where GUIAS.GUI_NUMERO LIKE ?";
         
        

        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            
            tbGuias.setModel(DbUtils.resultSetToTableModel(rs));
   
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
              
        int setar = tbGuias.getSelectedRow();
     
    }
    
    public void setarC() throws ParseException{
        int setar =  tbGuias.getSelectedRow();
        Double v1, v2, v3;
        v1 = Double.parseDouble(tbGuias.getModel().getValueAt(setar, 5).toString());
        v2 = Double.parseDouble(tbGuias.getModel().getValueAt(setar, 4).toString());
        v3 = Double.parseDouble(tbGuias.getModel().getValueAt(setar, 3).toString());
       
        txtId.setText(tbGuias.getModel().getValueAt(setar, 0).toString());
        txtNumero.setText(tbGuias.getModel().getValueAt(setar, 1).toString());
       
        txtBaseCalculo.setText(tbGuias.getModel().getValueAt(setar, 2).toString());
        //txtVencimento.setText(tbGuias.getModel().getValueAt(setar, 4).toString());
        txtValServidor.setText(tbGuias.getModel().getValueAt(setar, 3).toString());
        txtValPatronal.setText(tbGuias.getModel().getValueAt(setar, 4).toString());
        txtValSuplementar.setText(tbGuias.getModel().getValueAt(setar, 5).toString());
        txtObservacao.setText(tbGuias.getModel().getValueAt(setar, 6).toString());
        
        SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(Date.valueOf(tbGuias.getModel().getValueAt(setar, 7).toString()));
        txtData.setText(data.toString());
        
        String data1 = sdf.format(Date.valueOf(tbGuias.getModel().getValueAt(setar, 7).toString()));
        txtVencimento.setText(data.toString());
       
        cbSecretaria.setSelectedItem(tbGuias.getModel().getValueAt(setar, 8).toString());

        txtOrgao.setText(tbGuias.getModel().getValueAt(setar, 8).toString()
                        + ", " + tbGuias.getModel().getValueAt(setar, 9).toString());
        
        System.out.println(tbGuias.getModel().getValueAt(setar, 9).toString());
        
        v1 = v1 + v2;
        v3 = v3 + v1;
        
        txtTotalPatronal.setText(v1.toString());
        txtTotalGeral.setText(v3.toString());
        //nao permitir usuario cadastrar novamente o mesmo item
        btnSalvar.setEnabled(false);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbGuias = new javax.swing.JTable();
        btOk = new javax.swing.JButton();
        tbCancelar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Consulta de Guias");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        tbGuias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Número", "Base de Cálculo", "Valor Servidor", "Valor Patronal", "Total Patronal", "Total Geral", "Observação", "Data de Cadastro", "Secretaria", "Setor", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGuias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGuiasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbGuias);
        if (tbGuias.getColumnModel().getColumnCount() > 0) {
            tbGuias.getColumnModel().getColumn(0).setMinWidth(5);
            tbGuias.getColumnModel().getColumn(0).setPreferredWidth(5);
            tbGuias.getColumnModel().getColumn(1).setMinWidth(8);
            tbGuias.getColumnModel().getColumn(1).setPreferredWidth(8);
        }

        btOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-ok-24.png"))); // NOI18N
        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });

        tbCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-cancelar-24.png"))); // NOI18N
        tbCancelar.setText("Fechar");
        tbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Informe o número da Guia:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btOk, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(tbCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btOk)
                    .addComponent(tbCancelar)
                    .addComponent(jLabel1)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(375, 200, 851, 240);
    }// </editor-fold>//GEN-END:initComponents

    private void tbGuiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGuiasMouseClicked
        try {
            setarC();
        } catch (ParseException ex) {
            Logger.getLogger(Pesquisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbGuiasMouseClicked

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        pesquisar();
       

    }//GEN-LAST:event_btOkActionPerformed

    private void tbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbCancelarActionPerformed
        // TODO add your handling code here:
         this.dispose();
       
    }//GEN-LAST:event_tbCancelarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    
         
       
        
    }//GEN-LAST:event_formKeyPressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
       
    }//GEN-LAST:event_formKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton tbCancelar;
    private javax.swing.JTable tbGuias;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
