/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apachehttpdeditor.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author FABIO
 */
public class Database {
   
    private String host = "localhost"; //NOI18N
    private String banco = "apache"; //NOI18N

    private String user = "apache"; //NOI18N
    private String senha = "config"; //NOI18N

   private Connection conn = null;
   private String dbPath = "C:\\Users\\Gilberto\\Documents\\NetBeansProjects\\Configuracao";

  
    public Connection abrirBanco()
    {
        Properties props = new Properties();
        props.put("create", "false"); //NOI18N
        props.put("user", user); //NOI18N
        props.put("password", senha); //NOI18N
        try 
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); 

            conn = DriverManager.getConnection("jdbc:derby:"+dbPath+"/Apache",props); //NOI18N

            conn.createStatement().execute("set schema APACHE"); 
            
        } 
        catch(Exception e)
        {
                //System.out.println("erro:");
                e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Erro ao conectar");
            
        }
        return conn;
    }
}
