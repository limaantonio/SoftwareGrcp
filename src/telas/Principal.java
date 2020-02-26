/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dao.ModuloConexao;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import javax.swing.JInternalFrame;
import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author carlos
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    Connection connection = null;
    public Principal() {  
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        connection = ModuloConexao.conector();
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/background.jpg"));
        final Image image = icon.getImage();
        desktop = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }

        };
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnBfNovo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnBfPesquisa = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnResumo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemMenuData = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);

        desktop.setBackground(java.awt.Color.black);
        desktop.setForeground(new java.awt.Color(115, 196, 223));
        desktop.setAutoscrolls(true);
        desktop.setPreferredSize(new java.awt.Dimension(640, 480));
        desktop.setRequestFocusEnabled(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jSeparator3.setPreferredSize(new java.awt.Dimension(45, 45));
        jToolBar1.add(jSeparator3);

        btnBfNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconNovo.png"))); // NOI18N
        btnBfNovo.setFocusable(false);
        btnBfNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBfNovo.setPreferredSize(new java.awt.Dimension(45, 45));
        btnBfNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBfNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBfNovoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBfNovo);

        jSeparator4.setPreferredSize(new java.awt.Dimension(45, 45));
        jToolBar1.add(jSeparator4);

        btnBfPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-letras-miúdas-24.png"))); // NOI18N
        btnBfPesquisa.setFocusable(false);
        btnBfPesquisa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBfPesquisa.setPreferredSize(new java.awt.Dimension(45, 45));
        btnBfPesquisa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBfPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBfPesquisaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBfPesquisa);
        jToolBar1.add(jSeparator5);

        btnResumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-teste-passado-24.png"))); // NOI18N
        btnResumo.setFocusable(false);
        btnResumo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResumo.setPreferredSize(new java.awt.Dimension(45, 45));
        btnResumo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnResumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnResumo);

        menuArquivo.setText("Arquivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Cadastro");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuArquivo.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Banco de Dados");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuArquivo.add(jMenuItem2);

        jMenuBar1.add(menuArquivo);

        jMenu2.setText("Relatórios");

        jMenuItem5.setText("Guias");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem3.setText("Resumos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Configuração");

        itemMenuData.setText("Data");
        itemMenuData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuDataActionPerformed(evt);
            }
        });
        jMenu1.add(itemMenuData);

        jMenuItem6.setText("Aliquotas");
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 1365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        NovaGuia novaGuia = new NovaGuia();
        novaGuia.setVisible(true);
        
        
        
        
        desktop.add(novaGuia);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       BancoDeDados bancoDeDados = new BancoDeDados();
       bancoDeDados.setVisible(true);
       bancoDeDados.setSize(desktop.getSize().width, desktop.getSize().height);
       bancoDeDados.setLocation(desktop.getPosition(this), desktop.getPosition(this));
       desktop.add(bancoDeDados);
       
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnBfPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBfPesquisaActionPerformed
       BancoDeDados bancoDeDados = new BancoDeDados();
       bancoDeDados.setVisible(true);
       bancoDeDados.setSize(desktop.getSize().width, desktop.getSize().height);
       bancoDeDados.setLocation(desktop.getPosition(this), desktop.getPosition(this));
       desktop.add(bancoDeDados);
    }//GEN-LAST:event_btnBfPesquisaActionPerformed

    private void btnBfNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBfNovoActionPerformed
      NovaGuia novaGuia = new NovaGuia();
       novaGuia.setVisible(true);
       desktop.add(novaGuia);
    }//GEN-LAST:event_btnBfNovoActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
      FormDeResumo form = new FormDeResumo();
      form.setVisible(true);
      desktop.add(form);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
      FormDeRaltorio form = new FormDeRaltorio();
      form.setVisible(true);
      desktop.add(form);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void itemMenuDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuDataActionPerformed
        FormConfiguracaoData form = new FormConfiguracaoData();
        form.setVisible(true);
        desktop.add(form);
    }//GEN-LAST:event_itemMenuDataActionPerformed

    private void btnResumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumoActionPerformed
         FormDeResumo form = new FormDeResumo();
      form.setVisible(true);
      desktop.add(form);
    }//GEN-LAST:event_btnResumoActionPerformed

    
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBfNovo;
    private javax.swing.JButton btnBfPesquisa;
    private javax.swing.JButton btnResumo;
    public static javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem itemMenuData;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu menuArquivo;
    // End of variables declaration//GEN-END:variables
}
