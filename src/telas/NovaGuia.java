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
import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import static mondrian.olap.Category.Dimension;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
       String idGui = "SELECT max(GUI_ID) from tbGuias";
       
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
        String idSec = "SELECT min(SEC_ID) from tbSecretaria";
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
                + " SecretariaId)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        
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
            
           
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
            java.util.Date data = formatador.parse(txtData.getText());
          
            pst.setDate(7, new java.sql.Date(data.getTime()));
            
            
            Integer idParSec = Integer.parseInt(idS) + cbSecretaria.getSelectedIndex();
            System.out.println("id adf= " +idParSec.toString());
            pst.setString(8, idParSec.toString());
            
            //mostra no campo o valor gerado 
            
            txtValServidor.setText(vS.toString());
            txtValPatronal.setText(vP.toString());
            txtValSuplementar.setText(vSp.toString());
            txtTotalPatronal.setText(vTp.toString());
            txtTotalGeral.setText(vTg.toString());
            txtNumero.setText(num.toString());
          
            
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
                
            }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
    
    private void editar(){
        String sql = "UPDATE tbGuias SET "
                + "GUI_NUMERO = ?, "
                + "GUI_SECRETARIA = ?, "
                + "GUI_BASE = ?, "
                + "GUI_VALOR_SERVIDOR = ?, "
                + "GUI_VALOR_PATRONAL = ?, "
                + "GUI_VALOR_SUPLEMENTAR = ?, "
                + "GUI_OBSERVACAO = ?, "
                + "GUI_DATA_CADASTRO = ? "
                + "WHERE GUI_ID = ?";
        
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtNumero.getText());
            pst.setString(2, cbSecretaria.getSelectedItem().toString());
            pst.setString(3, txtBaseCalculo.getText());
            
            pst.setString(4, txtValServidor.getText());
            pst.setString(5, txtValPatronal.getText());
            pst.setString(6, txtValSuplementar.getText());
            pst.setString(7, txtObservacao.getText());
            
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
            String str = txtData.getText();  
            java.util.Date data = formatador.parse(str);
          
            pst.setDate(8, new java.sql.Date(data.getTime()));
            pst.setString(9, txtId.getText());
            
            int salvo = pst.executeUpdate();
            if(salvo >0){
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso.");
                txtId.setText(null);
                txtNumero.setText(null);
                cbSecretaria.setSelectedItem(null);
                txtBaseCalculo.setText(null);
                txtValPatronal.setText(null);
                txtVencimento.setText(null);
                txtValSuplementar.setText(null);
                txtObservacao.setText(null);
                txtData.setText(null);
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
                   txtValServidor.setText(null);
                   txtValPatronal.setText(null);
                   txtVencimento.setText(null);
                   txtValSuplementar.setText(null);
                   txtObservacao.setText(null);
                   txtTotalGeral.setText(null);
                   txtTotalPatronal.setText(null);
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
        txtBaseCalculo.setText(null);
        cbSecretaria.setSelectedItem(null);
        txtValPatronal.setText(null);
        txtVencimento.setText(null);
        txtValSuplementar.setText(null);
        txtTotalPatronal.setText(null);
        txtTotalGeral.setText(null);
        txtObservacao.setText(null);
        //txtData.setText(null);
        btnSalvar.setEnabled(true);
}
    
    private void imprimir(){
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão?",
                "Atenção", JOptionPane.YES_NO_OPTION);
           
        if(confirma == JOptionPane.YES_OPTION){
            
           HashMap filtro = new HashMap();
           filtro.put("NUM", Integer.parseInt(txtNumero.getText()));
            System.out.println(filtro);
            try{
                JasperPrint print = JasperFillManager.fillReport(
                       "/home/carlos/NetBeansProjects/Grcp/src/relatorios/Grcp.jasper"
                        ,filtro,connection);
                
                JasperViewer.viewReport(print, false);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                 System.out.println(e);
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
                + " SETOR.SET_NOME"
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
               
              cbSetor.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void setarCombos(String id){
        String sql = "SELECT"
               
                + " SETOR.SET_NOME"
                + " FROM tbSecretaria AS SEC"
                + " INNER JOIN tbSetor AS SETOR"
                + " ON (SEC.SEC_ID = SETOR.SecretariaId)"
                + " WHERE SEC_ID = ?";
        
                 try {
           
         
            pst = connection.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
     
                while(rs.next()){
                   
                    cbSetor.addItem(rs.getString(1));
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtValSuplementar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtObservacao = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        txtNumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtValServidor = new javax.swing.JTextField();
        txtVencimento = new javax.swing.JFormattedTextField();
        txtValPatronal = new javax.swing.JFormattedTextField();
        txtBaseCalculo = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalGeral = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTotalPatronal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        cbSecretaria = new javax.swing.JComboBox<>();
        cbSetor = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Cadastro");
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

        jLabel1.setText("Nº:");

        jLabel2.setText("Base de Cálculo: ");

        txtId.setEnabled(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor do Servidor:");

        jLabel4.setText("Valor Patronal:");

        jLabel5.setText("Valor Suplementar:");

        txtValSuplementar.setEnabled(false);
        txtValSuplementar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValSuplementarActionPerformed(evt);
            }
        });

        jLabel6.setText("Secretaria:");

        jLabel7.setText("Observação:");

        txtObservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacaoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-salvar-24.png"))); // NOI18N
        btnSalvar.setMnemonic('g');
        btnSalvar.setText("Gerar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconsVisualizar.png"))); // NOI18N
        jButton3.setMnemonic('p');
        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        txtNumero.setEnabled(false);
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jLabel8.setText("ID:");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconsAdicionar.png"))); // NOI18N
        btnNovo.setMnemonic('n');
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel9.setText("Vencimento:");

        txtValServidor.setEnabled(false);
        txtValServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValServidorActionPerformed(evt);
            }
        });

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

        jLabel10.setText("Total Patronal:");

        txtTotalGeral.setEnabled(false);
        txtTotalGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGeralActionPerformed(evt);
            }
        });

        jLabel11.setText("Total Geral:");

        txtTotalPatronal.setEnabled(false);
        txtTotalPatronal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPatronalActionPerformed(evt);
            }
        });

        jLabel12.setText("Data de Cadastro:");

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

        jLabel13.setText("Setor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNumero)
                                    .addComponent(txtTotalPatronal, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBaseCalculo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(txtValPatronal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtValServidor)
                                            .addComponent(txtTotalGeral)
                                            .addComponent(txtValSuplementar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSetor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSecretaria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 118, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTotalPatronal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(cbSecretaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtBaseCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(txtValPatronal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(36, 36, 36))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtValServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtValSuplementar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(txtTotalGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        setBounds(300, 100, 718, 435);
    }// </editor-fold>//GEN-END:initComponents

    private void txtObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpaCampos();
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

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void txtVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVencimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimentoActionPerformed

    private void cbSecretariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSecretariaActionPerformed

    
   
       
    }//GEN-LAST:event_cbSecretariaActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void cbSecretariaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbSecretariaKeyPressed
        
    }//GEN-LAST:event_cbSecretariaKeyPressed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        setarComboboxSecretaria();   
    }//GEN-LAST:event_formInternalFrameOpened

    private void cbSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSetorActionPerformed
          
    }//GEN-LAST:event_cbSetorActionPerformed

    private void cbSecretariaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbSecretariaFocusGained
        cbSetor.removeAllItems();

    }//GEN-LAST:event_cbSecretariaFocusGained

    private void cbSetorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbSetorFocusLost
        
    }//GEN-LAST:event_cbSetorFocusLost

    private void cbSetorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbSetorFocusGained
        setarComboboxSetor();
    }//GEN-LAST:event_cbSetorFocusGained

    private void cbSecretariaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSecretariaMouseClicked
       
    }//GEN-LAST:event_cbSecretariaMouseClicked

    private void cbSecretariaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSecretariaItemStateChanged
      
    }//GEN-LAST:event_cbSecretariaItemStateChanged

    private void cbSecretariaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSecretariaMousePressed
        
    }//GEN-LAST:event_cbSecretariaMousePressed

    private void cbSecretariaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cbSecretariaComponentShown
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbSecretariaComponentShown

    private void cbSecretariaHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_cbSecretariaHierarchyChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbSecretariaHierarchyChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAlterar;
    public static javax.swing.JButton btnExcluir;
    public static javax.swing.JButton btnNovo;
    public static javax.swing.JButton btnSalvar;
    public static javax.swing.JComboBox<String> cbSecretaria;
    public static javax.swing.JComboBox<String> cbSetor;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JFormattedTextField txtBaseCalculo;
    public static javax.swing.JFormattedTextField txtData;
    public static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtObservacao;
    public static javax.swing.JTextField txtTotalGeral;
    public static javax.swing.JTextField txtTotalPatronal;
    public static javax.swing.JFormattedTextField txtValPatronal;
    public static javax.swing.JTextField txtValServidor;
    public static javax.swing.JTextField txtValSuplementar;
    public static javax.swing.JFormattedTextField txtVencimento;
    // End of variables declaration//GEN-END:variables
}
