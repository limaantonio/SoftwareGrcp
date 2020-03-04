/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class ModuloConexao {
    
    public static java.sql.Connection conector (){
        java.sql.Connection conexao = null;
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:banco/bdGrcp.db";
       
       try{
           Class.forName(driver);
           conexao = DriverManager.getConnection(url);
           
           return conexao;
           
       }catch(Exception e){
           e.printStackTrace();
             JOptionPane.showMessageDialog(null, e);
           return null;
       }
    }
    
}
