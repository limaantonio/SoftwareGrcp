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
       String driver = "com.mysql.jdbc.Driver";
       String url = "jdbc:mysql://localhost:3306/bdGrcp";
       String user = "root";
       String password = "Pizz@2000";
       
       try{
           Class.forName(driver);
           conexao = DriverManager.getConnection(url, user, password);
           return conexao;
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Não foi possivel conectar-se o banco de dados.");
           return null;
       }
    }
    
}
