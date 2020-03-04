/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dao.ModuloConexao;
import entidades.Guia;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import static mondrian.olap.Category.Dimension;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sourceforge.barbecue.env.Environment;
import static telas.FormDeRaltorio.txtRelCom;

/**
 *
 * @author carlos
 */
public class NovaGuia extends javax.swing.JInternalFrame {

    /**
     * Creates new form NovaGuia
     */
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Pesquisa pesquisa = new Pesquisa();
   
    public NovaGuia() {
        initComponents();
        connection = ModuloConexao.conector();
         //setar curso
       javax.swing.SwingUtilities.invokeLater(new Runnable() { 
           public void run() { txtData.requestFocusInWindow(); }
       });
        
    }
    
   
    
    public int retornaId() throws SQLException{
        String res ;
        
        String sql = "SELECT COUNT(GUI_ID) FROM tbGuias";
        PreparedStatement p1 = connection.prepareStatement(sql);
        
        ResultSet r1 = p1.executeQuery();
        if(r1.next()){
            return r1.getInt(1);
        }
        return 0;
        
        
        
    }
  
    private void salvar(){
        
       String baseConvertido = txtBaseCalculo.getText().replace("R$", "")
                        .replace(".", "").replace(",", ".").replace(" ", "");
       
       Double base = Double.parseDouble(baseConvertido);
       Double vS = base * 11 /100;
       Double vP = base * 14 / 100;
       Double vSp = base * 4 / 100;
       Double vTp = vP + vSp;
       Double vTg = vS + vTp;
       
       String id = null;
       String idGui = "SELECT count(GUI_ID) from tbGuias";
       
        try {
            pst = connection.prepareStatement(idGui);
            rs = pst.executeQuery();
           
            if(rs.next()){
                id =  rs.getString(1);
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
           // System.out.println(id);
           
        }
        
        String idS = null;
        String idSec = "SELECT COUNT(SET_ID) from tbSetor";
        try {
            pst = connection.prepareStatement(idSec);
            rs = pst.executeQuery();
            
            if(rs.next()){
                idS =  rs.getString(1);       
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
           
           // System.out.println(id);
        }
        String sql = "INSERT INTO tbGuias ("
                + " GUI_NUMERO,"
                + " GUI_BASE,"
                + " GUI_VALOR_SERVIDOR,"
                + " GUI_VALOR_PATRONAL,"
                + " GUI_VALOR_SUPLEMENTAR,"
                + " GUI_OBSERVACAO,"
                + " GUI_DATA_CADASTRO,"
                + " GUI_DATA_VENCIMENTO,"
                + " SetorId)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        
        System.out.println(txtBaseCalculo.getText().replace("R$", "")
                        .replace(".", "").replace(",", ".").replace(" ", ""));
        
        
        try{
            
            Integer num = Integer.parseInt(id) + 1;
            pst = connection.prepareStatement(sql);
            pst.setString(1, num.toString());
           // if(txtNumero i)
            
            pst.setString(2, txtBaseCalculo.getText().replace("R$", "")
                        .replace(".", "").replace(",", ".").replace(" ", ""));
            pst.setString(3, vS.toString());
            pst.setString(4, vP.toString());
            pst.setString(5, vSp.toString());
            pst.setString(6, txtObservacao.getText());
            
           
//            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
//            java.util.Date data = formatador.parse(txtData.getText());
//            pst.setDate(7, new java.sql.Date(data.getTime()));
           // java.util.Date data2 = formatador.parse(txtVencimento.getText());
            //pst.setDate(8, new java.sql.Date(data.getTime()));
            
            pst.setString(7, txtData.getText());
            pst.setString(8, txtVencimento.getText());
            
            
            Integer idParSec = Integer.parseInt(idS) + cbSetor.getSelectedIndex();
            System.out.println("id adf= " +idParSec.toString());
            pst.setString(9, idParSec.toString());
            
          
            
            //mostra no campo o valor gerado 
            
            txtValServidor.setText(vS.toString());
            txtValPatronal.setText(vP.toString());
            txtValSuplementar.setText(vSp.toString());
            txtTotalPatronal.setText(vTp.toString());
            txtTotalGeral.setText(vTg.toString());
            txtNumero.setText(num.toString());
            txtOrgao.setText(cbSecretaria.toString()+" + "+cbSetor.toString());
            txtId.setText(num.toString());
          
            if(txtBaseCalculo.getText().isEmpty() || txtData.getText().isEmpty()
                    || txtOrgao.getText().isEmpty() || txtVencimento.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
                
            }else{
                int resposta = JOptionPane.showConfirmDialog(null,
                    "Deseja salvar esses dados?",
                    "Atenção",
                    JOptionPane.YES_NO_OPTION);
            
                int salvo = 0;

                if(resposta == JOptionPane.YES_OPTION){
                    salvo = pst.executeUpdate();
                }

                if(salvo >0){
                    JOptionPane.showMessageDialog(null, "Dados salvo com sucesso.");
                    connection.close();
                }
            }
            
            
           
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
          //  System.out.println(e);
           
        }
    }
    
    
    private void editar(){
        String idS = null;
        String idSec = "SELECT min(SET_ID) from tbSetor";
        try {
            pst = connection.prepareStatement(idSec);
            rs = pst.executeQuery();
            
            if(rs.next()){
                idS =  rs.getString(1);       
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
           
           // System.out.println(id);
        }
        
        String sql = "UPDATE tbGuias SET "
                + "GUI_NUMERO = ?, "
                + "GUI_BASE = ?, "
                + "GUI_VALOR_SERVIDOR = ?, "
                + "GUI_VALOR_PATRONAL = ?, "
                + "GUI_VALOR_SUPLEMENTAR = ?, "
                + "GUI_OBSERVACAO = ?, "
                + "GUI_DATA_CADASTRO = ?, "
                + "SetorId = ? "
                + "WHERE GUI_ID = ?";
        
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtNumero.getText());
            pst.setString(2, txtBaseCalculo.getText().replace("R$", "")
                        .replace(".", "").replace(",", ".").replace(" ", ""));
            pst.setString(3, txtValServidor.getText());
            pst.setString(4, txtValPatronal.getText());
            pst.setString(5, txtValSuplementar.getText());
            pst.setString(6, txtObservacao.getText());
            
//            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
//            String str = txtData.getText();  
//            java.util.Date data = formatador.parse(str);
//          
//            pst.setDate(7, new java.sql.Date(data.getTime()));
//            
            Integer idParSec = Integer.parseInt(idS) + cbSetor.getSelectedIndex();
           System.out.println("id adf= " +idParSec.toString());
           
           pst.setString(7, txtData.toString());
            pst.setString(8, idParSec.toString());
            
            txtOrgao.setText(cbSecretaria.getSelectedItem().toString()+" + "+cbSetor.getSelectedItem().toString());
           
            
            pst.setString(9,txtId.getText());
            
            int salvo = pst.executeUpdate();
            if(salvo >0){
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso.");
                txtId.setText(null);
                txtNumero.setText(null);
                
                txtBaseCalculo.setText(null);
                txtValPatronal.setText(null);
                txtVencimento.setText(null);
                txtValSuplementar.setText(null);
                txtObservacao.setText(null);
                txtData.setText(null);
                cbSecretaria.setSelectedItem(null);
                cbSetor.setSelectedItem(null);
                btnSalvar.setEnabled(true);   
            }
           
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
            
        }
    }
    private void excluir(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja "
                + "excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if(confirma == JOptionPane.YES_OPTION){
            String slq = "DELETE FROM tbGuias WHERE GUI_ID = ?";
            try{
               pst = connection.prepareStatement(slq);
               pst.setString(1,txtId.getText());
               
               int apagado = pst.executeUpdate();
               if(apagado > 0){
                   JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
                   txtId.setText(null);
                   txtNumero.setText(null);
                   txtBaseCalculo.setText(null);
                   cbSecretaria.setSelectedItem(null);
                   cbSetor.setSelectedItem(null);
                   txtValServidor.setText(null);
                   txtValPatronal.setText(null);
                   txtVencimento.setText(null);
                   txtValSuplementar.setText(null);
                   txtObservacao.setText(null);
                   txtData.setText(null);
                   txtTotalGeral.setText(null);
                   txtTotalPatronal.setText(null);
                   txtOrgao.setText(null);
                   btnSalvar.setEnabled(true);
               }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                
            }
        }
        
    }
    
    private void limpaCampos(){
        txtId.setText(null);
        txtNumero.setText(null);
        //txtBaseCalculo.setText(null);
        cbSecretaria.setSelectedItem(null);
        txtValPatronal.setText(null);
        txtValServidor.setText(null);
        txtVencimento.setText(null);
        txtData.setText(null);
        txtBaseCalculo.setText(null);
        txtValSuplementar.setText(null);
        txtTotalPatronal.setText(null);
        txtTotalGeral.setText(null);
        txtObservacao.setText(null);
        txtOrgao.setText(null);
        cbSetor.setSelectedItem(null);
        //txtData.setText(null);
        btnSalvar.setEnabled(true);
}
    
    private void imprimir(){
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão?",
                "Atenção", JOptionPane.YES_NO_OPTION);
           
        if(confirma == JOptionPane.YES_OPTION){
            
           HashMap filtro = new HashMap();
           filtro.put("ID", Integer.parseInt(txtNumero.getText()));
           
           InputStream rep = getClass().getResourceAsStream("Grcp.jasper");
           
           System.out.println( txtData.getText().substring(7, 10));
                   
           System.out.println(filtro);
            try{
                JasperPrint print = JasperFillManager.fillReport(
                       rep
                        ,filtro,connection);
                
                JasperViewer.viewReport(print, false);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                //System.out.println(e);
               
            }
        } 
    }
    
    public void setarComboboxSetor(){
        
        String minId = null;
        String idSec = "SELECT min(SEC_ID) from tbSecretaria";
        
         try {
            pst = connection.prepareStatement(idSec);
            rs = pst.executeQuery();
           
            if(rs.next()){
                minId =  rs.getString(1);
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
           
           // System.out.println(id);
        }
         
        String sql = "SELECT"
                + " SETOR.SET_NOME, SET_TIPO"
                + " FROM tbSecretaria AS SEC"
                + " INNER JOIN tbSetor AS SETOR"
                + " ON (SEC.SEC_ID = SETOR.SecretariaId)"
                + " WHERE SEC_ID = ?";
   
        try {
            Integer i = Integer.parseInt(minId.toString()) + cbSecretaria.getSelectedIndex();
            
            pst = connection.prepareStatement(sql);
            pst.setString(1, i.toString());
            rs = pst.executeQuery();
     
            while(rs.next()){
               
              cbSetor.addItem(rs.getString(1)+" + "+rs.getString(2));
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
            
        }
    }
    
   
    
    
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        txtObservacao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtVencimento = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtBaseCalculo = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        cbSecretaria = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbSetor = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtOrgao = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtValServidor = new javax.swing.JTextField();
        txtTotalGeral = new javax.swing.JTextField();
        txtValPatronal = new javax.swing.JFormattedTextField();
        txtTotalPatronal = new javax.swing.JTextField();
        txtValSuplementar = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setClosable(true);
        setTitle("Cadastro");
        setPreferredSize(new java.awt.Dimension(800, 500));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Gerais"));
        jPanel6.setName(""); // NOI18N

        txtObservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacaoActionPerformed(evt);
            }
        });

        jLabel7.setText("Observação:");

        jLabel8.setText("ID:");

        txtId.setEnabled(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel1.setText("Nº:");

        txtNumero.setEnabled(false);
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jLabel12.setText("*Data de Cadastro:");

        try {
            txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataActionPerformed(evt);
            }
        });

        jLabel9.setText("*Vencimento:");

        try {
            txtVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVencimentoActionPerformed(evt);
            }
        });

        jLabel2.setText("*Base de Cálculo: ");

        txtBaseCalculo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtBaseCalculo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBaseCalculoFocusLost(evt);
            }
        });
        txtBaseCalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBaseCalculoActionPerformed(evt);
            }
        });

        jLabel6.setText("*Secretaria:");

        cbSecretaria.setFocusCycleRoot(true);
        cbSecretaria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSecretariaItemStateChanged(evt);
            }
        });
        cbSecretaria.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                cbSecretariaHierarchyChanged(evt);
            }
        });
        cbSecretaria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbSecretariaFocusGained(evt);
            }
        });
        cbSecretaria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbSecretariaMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbSecretariaMouseClicked(evt);
            }
        });
        cbSecretaria.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                cbSecretariaComponentShown(evt);
            }
        });
        cbSecretaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSecretariaActionPerformed(evt);
            }
        });
        cbSecretaria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbSecretariaKeyPressed(evt);
            }
        });

        jLabel13.setText("*Setor:");

        cbSetor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbSetorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbSetorFocusLost(evt);
            }
        });
        cbSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSetorActionPerformed(evt);
            }
        });

        jLabel14.setText("Órgão Público:");

        txtOrgao.setEnabled(false);
        txtOrgao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrgaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtObservacao)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbSecretaria, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSetor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtOrgao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBaseCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addGap(13, 13, 13)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrgao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtBaseCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbSecretaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7)
                            .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores"));

        jLabel5.setText("Valor Suplementar:");

        jLabel3.setText("Valor do Servidor:");

        jLabel10.setText("Total Patronal:");

        jLabel4.setText("Valor Patronal:");

        jLabel11.setText("Total Geral:");

        txtValServidor.setEnabled(false);
        txtValServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValServidorActionPerformed(evt);
            }
        });

        txtTotalGeral.setEnabled(false);
        txtTotalGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGeralActionPerformed(evt);
            }
        });

        txtValPatronal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtValPatronal.setEnabled(false);
        txtValPatronal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValPatronalFocusLost(evt);
            }
        });
        txtValPatronal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValPatronalActionPerformed(evt);
            }
        });

        txtTotalPatronal.setEnabled(false);
        txtTotalPatronal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPatronalActionPerformed(evt);
            }
        });

        txtValSuplementar.setEnabled(false);
        txtValSuplementar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValSuplementarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtValServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addComponent(txtTotalGeral))
                    .addComponent(txtValPatronal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalPatronal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValSuplementar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtValServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtValPatronal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtValSuplementar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTotalGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtTotalPatronal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-salvar-24.png"))); // NOI18N
        btnSalvar.setMnemonic('g');
        btnSalvar.setText("Gerar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-excluir-arquivo-24.png"))); // NOI18N
        btnExcluir.setMnemonic('e');
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-editar-arquivo-24.png"))); // NOI18N
        btnAlterar.setMnemonic('a');
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconsAdicionar.png"))); // NOI18N
        btnNovo.setMnemonic('n');
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconsVisualizar.png"))); // NOI18N
        jButton3.setMnemonic('p');
        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconsImpressão-24.png"))); // NOI18N
        jButton2.setMnemonic('i');
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnNovo))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(31, 31, 31))
        );

        setBounds(300, 100, 787, 492);
    }// </editor-fold>//GEN-END:initComponents

    private void txtObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        connection = ModuloConexao.conector();
        imprimir();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Principal.desktop.add(pesquisa);
        pesquisa.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
       excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        editar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void txtValSuplementarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValSuplementarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValSuplementarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
        cbSecretaria.setEnabled(false);
        cbSetor.setEnabled(false);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpaCampos();
        connection = ModuloConexao.conector();
        cbSecretaria.setEnabled(true);
        cbSetor.setEnabled(true);
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtValServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValServidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValServidorActionPerformed

    private void txtValPatronalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValPatronalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValPatronalActionPerformed

    private void txtValPatronalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValPatronalFocusLost
   
    }//GEN-LAST:event_txtValPatronalFocusLost

    private void txtBaseCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBaseCalculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBaseCalculoActionPerformed

    private void txtBaseCalculoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBaseCalculoFocusLost
        String sv = txtBaseCalculo.getText();
        String vsf = sv.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        BigDecimal valor = new BigDecimal(vsf);
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String valorFormatado = nf.format(valor);
        txtBaseCalculo.setText(valorFormatado);
        
       
    }//GEN-LAST:event_txtBaseCalculoFocusLost

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
       
    }//GEN-LAST:event_formMouseExited

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
      
    }//GEN-LAST:event_formInternalFrameClosing

    private void txtTotalGeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalGeralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGeralActionPerformed

    private void txtTotalPatronalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPatronalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPatronalActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        cbSecretaria.transferFocus();
        setarComboboxSecretaria();   
    }//GEN-LAST:event_formInternalFrameOpened

    private void cbSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSetorActionPerformed

    }//GEN-LAST:event_cbSetorActionPerformed

    private void cbSetorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbSetorFocusLost

    }//GEN-LAST:event_cbSetorFocusLost

    private void cbSetorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbSetorFocusGained
        setarComboboxSetor();
    }//GEN-LAST:event_cbSetorFocusGained

    private void cbSecretariaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbSecretariaKeyPressed

    }//GEN-LAST:event_cbSecretariaKeyPressed

    private void cbSecretariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSecretariaActionPerformed

    }//GEN-LAST:event_cbSecretariaActionPerformed

    private void cbSecretariaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cbSecretariaComponentShown
        // TODO add your handling code here:

    }//GEN-LAST:event_cbSecretariaComponentShown

    private void cbSecretariaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSecretariaMouseClicked

    }//GEN-LAST:event_cbSecretariaMouseClicked

    private void cbSecretariaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSecretariaMousePressed

    }//GEN-LAST:event_cbSecretariaMousePressed

    private void cbSecretariaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbSecretariaFocusGained
        cbSetor.removeAllItems();
    }//GEN-LAST:event_cbSecretariaFocusGained

    private void cbSecretariaHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_cbSecretariaHierarchyChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbSecretariaHierarchyChanged

    private void cbSecretariaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSecretariaItemStateChanged

    }//GEN-LAST:event_cbSecretariaItemStateChanged

    private void txtVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVencimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimentoActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtOrgaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrgaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrgaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAlterar;
    public static javax.swing.JButton btnExcluir;
    public static javax.swing.JButton btnNovo;
    public static javax.swing.JButton btnSalvar;
    public static javax.swing.JComboBox<String> cbSecretaria;
    public static javax.swing.JComboBox<String> cbSetor;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JFrame jFrame1;
    public static javax.swing.JFrame jFrame2;
    public static javax.swing.JFrame jFrame3;
    public static javax.swing.JFrame jFrame4;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel14;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel6;
    public static javax.swing.JPanel jPanel7;
    public static javax.swing.JFormattedTextField txtBaseCalculo;
    public static javax.swing.JFormattedTextField txtData;
    public static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtObservacao;
    public static javax.swing.JTextField txtOrgao;
    public static javax.swing.JTextField txtTotalGeral;
    public static javax.swing.JTextField txtTotalPatronal;
    public static javax.swing.JFormattedTextField txtValPatronal;
    public static javax.swing.JTextField txtValServidor;
    public static javax.swing.JTextField txtValSuplementar;
    public static javax.swing.JFormattedTextField txtVencimento;
    // End of variables declaration//GEN-END:variables
}
