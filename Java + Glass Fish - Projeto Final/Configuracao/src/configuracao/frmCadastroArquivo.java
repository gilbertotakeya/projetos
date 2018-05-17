package configuracao;


import database.Database;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Julia
 */
public class frmCadastroArquivo extends javax.swing.JFrame 
{
    int idConfigCabIncluido = -1;
    String nomeArquivo = "";
    /**
     * Creates new form frmCadastroArquivo
     */
    public frmCadastroArquivo() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    public frmCadastroArquivo(String titulo, String descricao, String Autor,String arquivoApache) 
    {
        initComponents();    
        setExtendedState(MAXIMIZED_BOTH);
        
        txtTitulo.setText(titulo);
        txtDescricao.setText(descricao);
        txtAutor.setText(Autor);
        txtApache.setText(arquivoApache);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblApache = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtApache = new javax.swing.JTextArea();
        lblDescricao1 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        lblDescricao2 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de configuração");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                carregarFormulario(evt);
            }
        });

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/salvar.png"))); // NOI18N
        btnGravar.setText("Salvar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmar_actionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar_actionPerformed(evt);
            }
        });

        lblApache.setText("Arquivo apache");

        lblDescricao.setText("Descrição modificação");

        txtApache.setColumns(20);
        txtApache.setLineWrap(true);
        txtApache.setRows(5);
        txtApache.setText("ServerRoot \"C:/Program Files/Apache Software Foundation/Apache2.2\"\nListen 80\n\nLoadModule actions_module modules/mod_actions.so\nLoadModule alias_module modules/mod_alias.so\nLoadModule asis_module modules/mod_asis.so\nLoadModule auth_basic_module modules/mod_auth_basic.so\nLoadModule authn_default_module modules/mod_authn_default.so\nLoadModule authn_file_module modules/mod_authn_file.so\nLoadModule authz_default_module modules/mod_authz_default.so\nLoadModule authz_groupfile_module modules/mod_authz_groupfile.so\nLoadModule authz_host_module modules/mod_authz_host.so\nLoadModule authz_user_module modules/mod_authz_user.so\nLoadModule autoindex_module modules/mod_autoindex.so\nLoadModule cgi_module modules/mod_cgi.so\nLoadModule dir_module modules/mod_dir.so\nLoadModule env_module modules/mod_env.so\nLoadModule include_module modules/mod_include.so\nLoadModule isapi_module modules/mod_isapi.so\nLoadModule log_config_module modules/mod_log_config.so\nLoadModule mime_module modules/mod_mime.so\nLoadModule negotiation_module modules/mod_negotiation.so\nLoadModule setenvif_module modules/mod_setenvif.so\n\n<IfModule !mpm_netware_module>\n<IfModule !mpm_winnt_module>\nUser daemon\nGroup daemon\n\n</IfModule>\n</IfModule>\n\n\nServerAdmin teste@teste.com\n\n\nDocumentRoot \"C:/Program Files/Apache Software Foundation/Apache2.2/htdocs\"\n\n<Directory />\n    Options FollowSymLinks\n    AllowOverride None\n    Order deny,allow\n    Deny from all\n</Directory>\n\n\n<Directory \"C:/Program Files/Apache Software Foundation/Apache2.2/htdocs\">\n    Options Indexes FollowSymLinks\n\n    AllowOverride None\n\n    Order allow,deny\n    Allow from all\n\n</Directory>\n\n<IfModule dir_module>\n    DirectoryIndex index.html\n</IfModule>\n\n<FilesMatch \"^\\.ht\">\n    Order allow,deny\n    Deny from all\n    Satisfy All\n</FilesMatch>\n\nErrorLog \"logs/error.log\"\n\nLogLevel warn\n\n<IfModule log_config_module>\n    LogFormat \"%h %l %u %t \\\"%r\\\" %>s %b \\\"%{Referer}i\\\" \\\"%{User-Agent}i\\\"\" combined\n    LogFormat \"%h %l %u %t \\\"%r\\\" %>s %b\" common\n\n    <IfModule logio_module>\n      LogFormat \"%h %l %u %t \\\"%r\\\" %>s %b \\\"%{Referer}i\\\" \\\"%{User-Agent}i\\\" %I %O\" combinedio\n    </IfModule>\n\n    CustomLog \"logs/access.log\" common\n\n</IfModule>\n\n<IfModule alias_module>\n\n\n    ScriptAlias /cgi-bin/ \"C:/Program Files/Apache Software Foundation/Apache2.2/cgi-bin/\"\n\n</IfModule>\n\n<IfModule cgid_module>\n</IfModule>\n\n<Directory \"C:/Program Files/Apache Software Foundation/Apache2.2/cgi-bin\">\n    AllowOverride None\n    Options None\n    Order allow,deny\n    Allow from all\n</Directory>\n\nDefaultType text/plain\n\n<IfModule mime_module>\n    TypesConfig conf/mime.types\n    AddType application/x-compress .Z\n    AddType application/x-gzip .gz .tgz\n</IfModule>\n\n<IfModule ssl_module>\nSSLRandomSeed startup builtin\nSSLRandomSeed connect builtin\n</IfModule>\n"); // NOI18N
        txtApache.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(txtApache);

        lblDescricao1.setText("Autor da modificação");

        lblDescricao2.setText("Titulo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricao)
                    .addComponent(jScrollPane1)
                    .addComponent(txtAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescricao)
                            .addComponent(lblApache)
                            .addComponent(lblDescricao1)
                            .addComponent(lblDescricao2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblDescricao2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricao1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApache)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGravar)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carregarFormulario(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_carregarFormulario
        // TODO add your handling code here:
    }//GEN-LAST:event_carregarFormulario

    private void incluirCabecalho() //O que faz aqui?? Resposta: Inclui as informações referente a alteração do arquivo na tabela configcab.  
    {
        Database db = new Database ();
        Connection conn = db.abrirBanco();         
        //titulo, descricao, autor        
        String insert = "insert into configcab (titulo, descricao, autor, nomearquivo, textoarquivo) values (?,?,?,?,?)"; //NOI18N
        
        PreparedStatement stmt;           
        try 
        {
            stmt = conn.prepareStatement(insert, new String[] { "IDCONFIGCAB"} );
            stmt.setString(1, txtTitulo.getText());
            stmt.setString(2, txtDescricao.getText());
            stmt.setString(3, txtAutor.getText());
            stmt.setString(4, nomeArquivo);
            stmt.setString(5, txtApache.getText());

            stmt.execute(); 
            ResultSet rs = stmt.getGeneratedKeys();            
            if (rs.next()) 
            {
                idConfigCabIncluido = rs.getInt(1);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    private void btnConfirmar_actionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmar_actionPerformed
        int gravar = JOptionPane.showConfirmDialog(null,"Gravar?");
        
        if (gravar != 0)
            return;
        
        incluirCabecalho();
        
        if (idConfigCabIncluido == -1)
        {
            return;
        }
        //o que faz aqui? Alimenta a variavel de data com a data atual da máquina.
        Date dataAtual = new Date();
        String dia = String.valueOf(dataAtual.getDay());
        String mes = String.valueOf(dataAtual.getMonth());
        String ano = String.valueOf(dataAtual.getYear());
        String hora = String.valueOf(dataAtual.getHours());
        String min = String.valueOf(dataAtual.getMinutes());
        String seg = String.valueOf(dataAtual.getSeconds());
        String formData = ano + mes + ano + hora + min + seg;
        
        nomeArquivo = "ArquivoApache" + formData + ".txt";//o que faz aqui? Resposta: Salva o arquivo com o nome "ArquivoApache<Ano><Mes><Ano><Hora><Min><Seg>
        File file = new File(nomeArquivo);
        try 
        {
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(writer);
            
            String texto = txtApache.getText() ;  
            String all = "";
            StringTokenizer st = new StringTokenizer(texto,"\n") ;  
            while (st.hasMoreTokens())   
            {  
                 String line = st.nextToken();  
                 buffer.newLine();
                 System.out.println(line);
                 buffer.write(line);                 
                 buffer.flush();
            }
 
            buffer.close();
            writer.close();            
            
            try 
            {
                ArquivoHttp importarArq = new ArquivoHttp(nomeArquivo, idConfigCabIncluido);
                importarArq.lerArquivo();
                
                //Aqui será gravado o cabecalho das alterações. Esse cabeçalho contém a descrição da versão.
            } catch (SQLException ex) {
                Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //Aqui será chamada a classe para leitura do arquivo alterado.
            //Dentro dessa classe, ocorrerá a inclusão.
            //ArquivoHttp arquivoHTTP = new ArquivoHttp();
            
            
            this.setVisible(false);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConfirmar_actionPerformed

    private void btnCancelar_actionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar_actionPerformed
        int fechar = JOptionPane.showConfirmDialog(null,"Fechar?");
        if (fechar != 0)
            return;
        
        //System.exit(0);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelar_actionPerformed

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
            java.util.logging.Logger.getLogger(frmCadastroArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCadastroArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCadastroArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCadastroArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCadastroArquivo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApache;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDescricao1;
    private javax.swing.JLabel lblDescricao2;
    private javax.swing.JTextArea txtApache;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
