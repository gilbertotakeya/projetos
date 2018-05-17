/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracao;

import database.Database;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ana Julia
 */
public class frmConsultar extends javax.swing.JFrame 
{
    private int quantidade_clicar = 2;//o que faz aqui? Resposta: Váriavel utilizada para identificar a quantidade de cliques realizado pelo usuário. Sempre começa com 2, ao chegar no 0 ela chama a comparação.
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();
//o que faz aqui? Aqui ele transforma os dados em uma tabela virtual. É extremamente mais rápido que utilizar while para inserir em um grid.
        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
    /**
     * Creates new form frmConsultar
     */
    private void carregarColunasDataGrid()
    {
        /*
        dgvRepositorio.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] { },
        new String [] {//aqui adiciona-se as colunas e seus respectivos nomes
        "ID", "Titulo", "Autor","Descricao","Data"
        }
        ));*/
    }
    
    private void carregarInformacoesGrid()
    {
        try 
        {
            Database db = new Database ();
            Connection conn = db.abrirBanco(); 

            Statement sta = conn.createStatement();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)dgvRepositorio.getModel();
            
            // getting the data back
            ResultSet res = sta.executeQuery("SELECT IDCONFIGCAB, TITULO, DESCRICAO, AUTOR, DATAINCLUSAO FROM CONFIGCAB where dataexclusao is null");
            dgvRepositorio.setModel(buildTableModel(res));
            //JTable table = new JTable(buildTableModel(res));
            // table;
            //JOptionPane.showMessageDialog(null, new JScrollPane(table));
            /*
            while (res.next()) 
            {
                //lembre-se um "" para cada coluna na tabela
                dtm.addRow(new Object[]{
                                res.getInt("IDCONFIGCAB"),
                                res.getString("TITULO"),
                                res.getString("DESCRICAO"),
                                res.getString("AUTOR"),
                                res.getDate("DATAINCLUSAO")});
            }*/
            
            res.close();

            sta.close();
            conn.close();  
            
            dgvRepositorio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            dgvRepositorio.getColumnModel().getColumn(0).setPreferredWidth(100);
            dgvRepositorio.getColumnModel().getColumn(1).setPreferredWidth(160);
            dgvRepositorio.getColumnModel().getColumn(2).setPreferredWidth(160);
            dgvRepositorio.getColumnModel().getColumn(3).setPreferredWidth(130);
            dgvRepositorio.getColumnModel().getColumn(4).setPreferredWidth(120);
        } 
        catch (Exception e) 
        {
            System.err.println("Exception: "+e.getMessage());
        }
    }        
    
    private void removerLinhasTabela()
    {
        try 
        {
            int l = dgvRepositorio.getRowCount();// getSelectedRows();//captura as linhas selecionadas
            //pega o DefaultTableModel da tabela
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)dgvRepositorio.getModel();
            for(int i =0; i < l; i++) 
            {
                dtm.removeRow(0);//remove todas as linhas selecionadas
            }
        }
        catch (Exception e) 
        {
            System.err.println("Exception: "+e.getMessage());
        }        
    }
    
    private void atualizarGrid()
    {
        removerLinhasTabela();
        carregarInformacoesGrid();
    }
        
    public frmConsultar() 
    {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);        
        atualizarGrid();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dgvRepositorio = new javax.swing.JTable();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnGerarArquivo = new javax.swing.JButton();
        btnComparar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Repositório de configuração do Apache ");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                frmConsultar_GainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                frmConsultar_Opened(evt);
            }
        });

        dgvRepositorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dgvRepositorio);

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/add.png"))); // NOI18N
        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluir_Click(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/edit.png"))); // NOI18N
        btnAlterar.setActionCommand("Alterar");
        btnAlterar.setLabel("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar_Click(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/logout.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSair_Click(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir_Click(evt);
            }
        });

        btnGerarArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/gerarArquivo.png"))); // NOI18N
        btnGerarArquivo.setText("Gerar arquivo");
        btnGerarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarArquivoActionPerformed(evt);
            }
        });

        btnComparar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/compararRegistros.png"))); // NOI18N
        btnComparar.setText("Comparar");
        btnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnSair)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnComparar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGerarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGerarArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnComparar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluir_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluir_Click
        frmCadastroArquivo dlg = new frmCadastroArquivo();
        dlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dlg.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent ev) 
            {                
                atualizarGrid();
            }
        });        
        dlg.setVisible(true);
    }//GEN-LAST:event_btnIncluir_Click

    private void btnSair_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair_Click
        try 
        { 
            int linha = dgvRepositorio.getSelectedRow();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)dgvRepositorio.getModel();

            Object x = dtm.getValueAt(linha,0); 
            String str = x.toString();
        
            Database db = new Database ();
            Connection conn = db.abrirBanco(); 
            Statement sta = conn.createStatement();
            
            //o que faz aqui? AO sair da tela, ele preenche os textos para realizar a comparação.
			
            
            // getting the data back
            ResultSet res = sta.executeQuery("SELECT titulo, descricao, autor, nomearquivo, textoarquivo FROM CONFIGCAB WHERE IDCONFIGCAB = " + str + " and dataexclusao is null");

            //String arquivo = "";
            while (res.next()) 
            {
                if (Principal.texto1 == null || Principal.texto1.isEmpty()) 
                {
                    Principal.texto1 = res.getString("TEXTOARQUIVO");                
                }                
                else if (Principal.texto2 == null || Principal.texto2.isEmpty()) 
                {
                    Principal.texto2 = res.getString("TEXTOARQUIVO");
                }
                
                break;
            }
                        
            res.close();
            sta.close();
            conn.close();         
        } 
        catch (Exception e) 
        {
            //System.err.println("Exception: "+e.getMessage());
        }                
        
        this.setVisible(false);
    }//GEN-LAST:event_btnSair_Click

    private void btnAlterar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar_Click
        // TODO add your handling code here:
        int linha = dgvRepositorio.getSelectedRow();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)dgvRepositorio.getModel();
        
        Object x = dtm.getValueAt(linha,0); 
        String str = x.toString();
        
        try 
        {
            Database db = new Database ();
            Connection conn = db.abrirBanco(); 
            Statement sta = conn.createStatement();
            
            // getting the data back
            ResultSet res = sta.executeQuery("SELECT titulo, descricao, autor, nomearquivo, textoarquivo FROM CONFIGCAB WHERE IDCONFIGCAB = " + str + " and dataexclusao is null");
            String titulo = "";
            String descricao = "";
            String autor = "";
            String nomearquivo = "";
            String texto = "";
                        
            
            while (res.next()) 
            {
                titulo = res.getString("titulo");
                descricao = res.getString("descricao");
                autor = res.getString("autor");
                nomearquivo = res.getString("nomearquivo");
                texto = res.getString("TEXTOARQUIVO");
                /*
                //lembre-se um "" para cada coluna na tabela
                dtm.addRow(new Object[]{
                    res.getInt("IDCONFIGCAB"),
                                res.getString("TITULO"),
                                res.getString("DESCRICAO"),
                                res.getString("AUTOR"),
                                res.getDate("DATAINCLUSAO")});
                                */
                break;
            }
            
            res.close();

            sta.close();
            conn.close();   
            
            frmCadastroArquivo dlg = new frmCadastroArquivo(titulo, descricao, autor, texto);
            dlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dlg.addWindowListener(new WindowAdapter() 
            {
                public void windowClosing(WindowEvent ev) 
                {
                    atualizarGrid();
                }
            });        
            dlg.setVisible(true);                  
        } 
        catch (Exception e) 
        {
            System.err.println("Exception: "+e.getMessage());
        }                        
        //javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)dgvRepositorio.getModel();
    }//GEN-LAST:event_btnAlterar_Click

    private void frmConsultar_Opened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frmConsultar_Opened
        /*
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        
        // Move the window
        this.setLocation(x, y);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        */
    }//GEN-LAST:event_frmConsultar_Opened

    private void btnExcluir_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir_Click

        int linha = dgvRepositorio.getSelectedRow();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)dgvRepositorio.getModel();
        
        Object x = dtm.getValueAt(linha,0); 
        String str = x.toString();
        
        Database db = new Database ();
        Connection conn = db.abrirBanco();         
        //titulo, descricao, autor        
        String update = "update configcab set dataexclusao = current_date where idconfigcab = " + str; //NOI18N
        
        PreparedStatement stmt;           
        try 
        {
            stmt = conn.prepareStatement(update);            

            stmt.execute(); 
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");
            atualizarGrid();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }//GEN-LAST:event_btnExcluir_Click

    private void frmConsultar_GainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frmConsultar_GainedFocus
        atualizarGrid();
    }//GEN-LAST:event_frmConsultar_GainedFocus

    private void btnGerarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarArquivoActionPerformed
        
        int linha = dgvRepositorio.getSelectedRow();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)dgvRepositorio.getModel();
        
        Object x = dtm.getValueAt(linha,0); 
        String str = x.toString();
        String titulo = "";
        String descricao = "";
        String autor = "";
        String nomearquivo = "";
        String texto = "";    
        
        try 
        {
            Database db = new Database ();
            Connection conn = db.abrirBanco(); 
            Statement sta = conn.createStatement();
            
            // getting the data back
            ResultSet res = sta.executeQuery("SELECT titulo, descricao, autor, nomearquivo, textoarquivo FROM CONFIGCAB WHERE IDCONFIGCAB = " + str + " and dataexclusao is null");
            
            while (res.next()) 
            {
                titulo = res.getString("titulo");
                descricao = res.getString("descricao");
                autor = res.getString("autor");
                nomearquivo = res.getString("nomearquivo");
                texto = res.getString("TEXTOARQUIVO");

                break;
            }
            
            res.close();

            sta.close();
            conn.close();  
        } 
        catch (Exception e) 
        {
            System.err.println("Exception: "+e.getMessage());
        }
                
        Date dataAtual = new Date();
        String dia = String.valueOf(dataAtual.getDay());
        String mes = String.valueOf(dataAtual.getMonth());
        String ano = String.valueOf(dataAtual.getYear());
        String hora = String.valueOf(dataAtual.getHours());
        String min = String.valueOf(dataAtual.getMinutes());
        String seg = String.valueOf(dataAtual.getSeconds());
        String formData = ano + mes + ano + hora + min + seg;
        
        String nomeArquivo = (String) JOptionPane.showInputDialog(null, "Informe o nome e o local para salvar o arquivo de configuração:","Geração do arquivo Apache ", JOptionPane.QUESTION_MESSAGE, null, null, "C:\\Users\\Apache22\\conf\\httpd.conf");
        
        if (nomeArquivo == null) 
        {
            return;
        }
        
        //String nomeArquivo = txtLocalGeracaoArquivo.getText();
        java.io.File file = new java.io.File(nomeArquivo);
        try 
        {
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(writer);            
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
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }//GEN-LAST:event_btnGerarArquivoActionPerformed

    private void btnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompararActionPerformed

        if (quantidade_clicar == 2)
            JOptionPane.showMessageDialog(null, "Na próxima tela selecione o primeiro registro para comparação e clique em sair! Após selecionar e sair, Clique em comparar novamente! ",  "A T E N Ç Ã O", JOptionPane.INFORMATION_MESSAGE);
        else if (quantidade_clicar == 1)
            JOptionPane.showMessageDialog(null, "Na próxima tela selecione o segundo registro para comparação e clique em sair! Após selecionar e sair, Clique em comparar novamente ",  "A T E N Ç Ã O", JOptionPane.INFORMATION_MESSAGE);
        else if (quantidade_clicar == 0)
            JOptionPane.showMessageDialog(null, "Iniciando comparação! ",  "A T E N Ç Ã O", JOptionPane.ERROR_MESSAGE);

        quantidade_clicar --;
        if (Principal.texto1 == null || Principal.texto2 == null)
        {
            frmConsultar dlg = new frmConsultar();
            dlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dlg.setVisible(true);
        }
        else
        {
            String diferente = "";
            String palavra = "";
            Principal.texto1 = Principal.texto1 + " ";
            Principal.texto2 = Principal.texto2 + " ";
            for (int i=0; i < Principal.texto1.length(); i++) 
            {                
                char c = Principal.texto1.charAt(i);
                if (c == ' ' )
                {
                    if (!Principal.texto2.contains(palavra + " "))
                    {
                        diferente += palavra;                        
                    }
                    palavra = "";
                }
                else 
                {
                    palavra += String.valueOf(c);
                }
            }  

            if (diferente != null && !diferente.isEmpty() )
            {
                String nomeArquivo = JOptionPane.showInputDialog(null, "Informe o nome e o local do arquivo para salvar a comparação:","Add New", JOptionPane.QUESTION_MESSAGE);     
                if (nomeArquivo == null)
                    return;

                //String nomeArquivo = txtLocalGeracaoArquivo.getText();
                java.io.File file = new java.io.File(nomeArquivo);
                try 
                {
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter buffer = new BufferedWriter(writer);            
                    String all = "";
                    StringTokenizer st = new StringTokenizer(diferente,"\n") ;  
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
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
                }                  
            }
            else
            {
                JOptionPane.showMessageDialog(null, "registros iguais! ",  "A T E N Ç Ã O", JOptionPane.INFORMATION_MESSAGE);
            }
            
            Principal.texto1 = null;
            Principal.texto2 = null;
            this.quantidade_clicar = 2;
        }
    }//GEN-LAST:event_btnCompararActionPerformed

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
            java.util.logging.Logger.getLogger(frmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmConsultar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnComparar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGerarArquivo;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSair;
    private javax.swing.JTable dgvRepositorio;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
