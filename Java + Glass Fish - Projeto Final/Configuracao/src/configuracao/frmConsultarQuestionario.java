/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracao;

import database.Database;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ana Julia
 *///o que faz aqui? Faz a consulta de resposta dos questionarios.
public class frmConsultarQuestionario extends javax.swing.JFrame {

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

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
    private void carregarInformacoesItens() {
        String sqlItens = "SELECT QUESTIONARIORESPOSTACAB.IDQUESTIONARIORESPOSTA,\n"
                + "       QUESTIONARIORESPOSTACAB.TITULO,\n"
                + "       QUESTIONARIORESPOSTACAB.AUTOR,\n"
                + "       QUESTIONARIORESPOSTACAB.DATAHORAINCLUSAO\n"
                + "  FROM QUESTIONARIORESPOSTACAB ";
        Database db = new Database();
        Connection conn = db.abrirBanco();
        Statement sta;
        try {
            sta = conn.createStatement();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) dgvRepositorio.getModel();

            int linha = dgvRepositorio.getSelectedRow();
            Object x = dtm.getValueAt(linha, 0);
            String str = x.toString();
			//o que faz aqui? Traz as respostas do questionario respondido.
            String sql = "SELECT QUESTIONARIORESPOSTAITEM.IDQUESTIONARIORESPOSTAITEM,\n"
                    + "       QUESTIONARIORESPOSTAITEM.IDQUESTIONARIORESPOSTA,\n"
                    + "       QUESTIONARIORESPOSTAITEM.IDQUESTIONARIO,\n"
                    + "       QUESTIONARIO.PERGUNTA,\n"
                    + "       QUESTIONARIORESPOSTAITEM.RESPOSTA,\n"
                    + "       QUESTIONARIO.DICA\n"
                    + "  FROM QUESTIONARIORESPOSTAITEM\n"
                    + "  INNER JOIN QUESTIONARIO ON QUESTIONARIO.IDQUESTIONARIO = QUESTIONARIORESPOSTAITEM.IDQUESTIONARIO\n"
                    + "  WHERE QUESTIONARIORESPOSTAITEM.IDQUESTIONARIORESPOSTA = " + str;

            // getting the data back
            ResultSet res = sta.executeQuery(sql);
            dgvItensResposta.setModel(buildTableModel(res));

            res.close();

            sta.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmConsultarQuestionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarInformacoesGrid() {
        try {
            Database db = new Database();
            Connection conn = db.abrirBanco();

            Statement sta = conn.createStatement();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) dgvRepositorio.getModel();

            String sql = "SELECT QUESTIONARIORESPOSTACAB.IDQUESTIONARIORESPOSTA,\n"
                    + "       QUESTIONARIORESPOSTACAB.TITULO,\n"
                    + "       QUESTIONARIORESPOSTACAB.AUTOR,\n"
                    + "       QUESTIONARIORESPOSTACAB.DATAHORAINCLUSAO\n"
                    + "  FROM QUESTIONARIORESPOSTACAB ";

            // getting the data back
            ResultSet res = sta.executeQuery(sql);
            dgvRepositorio.setModel(buildTableModel(res));
            res.close();

            sta.close();
            conn.close();
            dgvRepositorio.getSelectionModel().addListSelectionListener(
                    new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent event) {
                            carregarInformacoesItens();
                        }
                    });

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

            dgvRepositorio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            dgvRepositorio.getColumnModel().getColumn(0).setPreferredWidth(172);//30
            dgvRepositorio.getColumnModel().getColumn(1).setPreferredWidth(180);//400
            dgvRepositorio.getColumnModel().getColumn(2).setPreferredWidth(160);//400
            dgvRepositorio.getColumnModel().getColumn(3).setPreferredWidth(150);//350
            //dgvRepositorio.getColumnModel().getColumn(4).setPreferredWidth(80);
        } catch (Exception e) {
            //System.err.println("carregarInformacoesGrid - Exception: "+e.getMessage());
        }
    }

    private void atualizarGrid() {
        carregarInformacoesGrid();
    }

    public frmConsultarQuestionario() {
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
        jScrollPane2 = new javax.swing.JScrollPane();
        dgvItensResposta = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnComparar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta e preenchimento de Cenários");
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

        dgvItensResposta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(dgvItensResposta);

        jLabel1.setText("  Itens de resposta");

        btnComparar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/configuracao/comparar.png"))); // NOI18N
        btnComparar.setText("Comparar");
        btnComparar.setToolTipText("");
        btnComparar.setName("btnComparar"); // NOI18N
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
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnSair)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(btnComparar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir)
                    .addComponent(btnIncluir)
                    .addComponent(btnSair)
                    .addComponent(btnComparar)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluir_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluir_Click
        /*
         frmCadastroArquivo dlg = new frmCadastroArquivo();
         dlg.setVisible(true);
         atualizarGrid();
         */
        frmQuestionarioDinamico dlg = new frmQuestionarioDinamico();
        dlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                atualizarGrid();
            }
        });
        dlg.setVisible(true);
    }//GEN-LAST:event_btnIncluir_Click

    private void btnSair_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair_Click

        this.setVisible(false);
    }//GEN-LAST:event_btnSair_Click

    private void btnAlterar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar_Click
        // TODO add your handling code here:
        try {
            int linha = dgvRepositorio.getSelectedRow();
            javax.swing.table.DefaultTableModel dtmCab = (javax.swing.table.DefaultTableModel) dgvRepositorio.getModel();

            int linhaItem = dgvItensResposta.getSelectedRow();
            javax.swing.table.DefaultTableModel dtmItem = (javax.swing.table.DefaultTableModel) dgvItensResposta.getModel();

            frmQuestionarioDinamicoAlterar dlg = new frmQuestionarioDinamicoAlterar();
            dlg.idQuestionarioRespostaItem = Integer.parseInt(dtmItem.getValueAt(linhaItem, 0).toString());

            dlg.titulo = String.valueOf(dtmCab.getValueAt(linha, 1).toString());

            Object autor = dtmCab.getValueAt(linha, 2);
            if (autor != null) {
                dlg.autor = String.valueOf(dtmCab.getValueAt(linha, 2).toString());
            }

            Object resposta = dtmItem.getValueAt(linhaItem, 4);
            if (resposta != null) {
                dlg.resposta = String.valueOf(dtmItem.getValueAt(linhaItem, 4).toString());
            }

            Object dica = dtmItem.getValueAt(linhaItem, 5);
            if (dica != null) {
                dlg.dica = String.valueOf(dtmItem.getValueAt(linhaItem, 5).toString());
            }

            dlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dlg.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent ev) {
                    carregarInformacoesItens();
                }
            });
            dlg.setVisible(true);
            dlg.atualizarTela();
        } catch (Exception e) {
            //System.err.println("btnAlterar_Click - Exception: "+e.getMessage());
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

        int linha = dgvItensResposta.getSelectedRow();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) dgvItensResposta.getModel();

        Object x = dtm.getValueAt(linha, 0);
        String str = x.toString();

        Database db = new Database();
        Connection conn = db.abrirBanco();
        //titulo, descricao, autor        
        String delete = "delete from questionariorespostaitem where idquestionariorespostaitem = " + str; //NOI18N

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(delete);

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            carregarInformacoesItens();
        } catch (SQLException ex) {
            Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluir_Click

    private void frmConsultar_GainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frmConsultar_GainedFocus
        atualizarGrid();
    }//GEN-LAST:event_frmConsultar_GainedFocus

    private void btnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompararActionPerformed
        try {
            String arquivoPadrao = dadosArquivo();
            frmCadastroArquivo dlg = new frmCadastroArquivo("", "", "", arquivoPadrao);
            dlg.setVisible(true);
        } catch (Exception e) {
            System.err.println("Comparar string - Exception: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCompararActionPerformed

    public String dadosArquivo() {
        String arquivoPadrao = null;
        try {
            int linha = dgvRepositorio.getSelectedRow();
            javax.swing.table.DefaultTableModel dtmCab = (javax.swing.table.DefaultTableModel) dgvRepositorio.getModel();
            Object idRespostaCab = dtmCab.getValueAt(linha, 0);

            Database db = new Database();
            Connection conn = db.abrirBanco();

            Statement sta = conn.createStatement();

            //Verificação das respostas com a resposta padrão.
            String sql = " SELECT QUESTIONARIORESPOSTAITEM.IDQUESTIONARIORESPOSTAITEM,\n"
                    + "        QUESTIONARIORESPOSTAITEM.IDQUESTIONARIORESPOSTA,\n"
                    + "        QUESTIONARIORESPOSTAITEM.IDQUESTIONARIO,\n"
                    + "        QUESTIONARIORESPOSTAITEM.RESPOSTA,\n"
                    + "        QUESTIONARIO.RESPOSTAPADRAO,\n"
                    + "        QUESTIONARIO.TAG,\n"
                    + "        QUESTIONARIO.TAGSAIDA,\n"
                    + "        QUESTIONARIO.PERGUNTA,\n"
                    + "        QUESTIONARIO.DICA\n"
                    + " FROM   QUESTIONARIORESPOSTAITEM\n"
                    + " INNER JOIN QUESTIONARIO ON QUESTIONARIO.IDQUESTIONARIO = QUESTIONARIORESPOSTAITEM.IDQUESTIONARIO\n"
                    + " WHERE QUESTIONARIORESPOSTAITEM.IDQUESTIONARIORESPOSTA = " + idRespostaCab.toString();

            // getting the data back
            ResultSet res = sta.executeQuery(sql);
            String tagsDiferentes = "";
            String mensagemTagDiferentes = "";
            while (res.next()) {
                //lembre-se um "" para cada coluna na tabela
                //percorrer a resposta data
                // checar se é igual a resposta padrao
                String tag = res.getString("tag") + " ";
                tag = tag.toUpperCase();

                String dica = res.getString("dica") + " ";
                dica = dica.toUpperCase();

                String resposta = res.getString("resposta") + " ";
                resposta = resposta.toUpperCase();

                String respostaPadrao = res.getString("respostapadrao") + " ";
                respostaPadrao = respostaPadrao.toUpperCase();

                String pergunta = res.getString("pergunta");
                pergunta = pergunta.toUpperCase();

                String palavra = "";
                for (int i = 0; i < resposta.length(); i++) {
                    char c = resposta.charAt(i);
                    if (c == ' ') {
                        //Se a palavra não estiver na resposta padrão
                        if (!respostaPadrao.contains(palavra + " ")) {
                            //Se a tag não tiver sido inclusa na tag
                            if (!tagsDiferentes.contains("_" + tag + "-")) {
                                String strTemp = pergunta + " RESPOSTA: " + palavra + " DICA: " + dica + "\n";
                                mensagemTagDiferentes += strTemp;
                                tagsDiferentes += "_" + tag + "-";
                                //Incluo a tag na opÃ§Ã£o de tags diferentes.
                            }
                            palavra = null;
                        }
                    } else {
                        palavra += String.valueOf(c);
                    }
                }
            }

            //GravaÃ§Ã£o das respostas diferentes do valor padrÃ£o.
            String nomeArquivo = JOptionPane.showInputDialog(null, "Informe o nome e o local do arquivo para salvar a comparação:", "Add New", JOptionPane.QUESTION_MESSAGE);
            if (nomeArquivo == null) {
                return null;
            }

            //String nomeArquivo = txtLocalGeracaoArquivo.getText();
            java.io.File file = new java.io.File(nomeArquivo);
            try {
                FileWriter writer = new FileWriter(file);
                BufferedWriter buffer = new BufferedWriter(writer);
                String all = "";
                StringTokenizer st = new StringTokenizer(mensagemTagDiferentes, "\n");
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    buffer.newLine();
                    System.out.println(line);
                    buffer.write(line);
                    buffer.flush();
                }

                buffer.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(frmCadastroArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }

            //CriaÃ§Ã£o das configuraÃ§Ãµes do arquivo padrÃ£o para revisÃ£o e salvamento.
            Database db2 = new Database();
            Connection conn2 = db.abrirBanco();
            conn2.setAutoCommit(false);
            Statement sta2 = conn.createStatement();
            String sqlArqPadrao = "SELECT IDARQUIVOPADRAO, UPPER(TAGENTRADA) as TAGENTRADA, UPPER(TAGSAIDA) as TAGSAIDA, PADRAO FROM ARQUIVOPADRAO";
            ResultSet resPadrao = sta2.executeQuery(sqlArqPadrao);

			//modo dinamico onde faz a identificação das linhas pra descobrir o que Atibuto e valor e onde foi feito o tratamento das tag
            res.close();
            while (resPadrao.next()) {
                String tagPadrao = resPadrao.getString("TAGENTRADA") + " ";
                tagPadrao = tagPadrao.toUpperCase();
                String tagSaida = resPadrao.getString("TAGSAIDA") + " ";

                if (tagsDiferentes.contains("_" + tagPadrao + "-")) {
                    String sqlFormaArquivo = sql + " AND UPPER(QUESTIONARIO.TAG) = '" + tagPadrao + "'";
                    ResultSet resTemp = sta.executeQuery(sqlFormaArquivo);
                    while (resTemp.next()) {
                        String resposta = resTemp.getString("resposta") + " ";
                        resposta = resposta.toUpperCase();

                        arquivoPadrao += tagPadrao + resposta + tagSaida + "\n";
                    }
                    resTemp.close();
                } else {
                    String xyz = resPadrao.getString("PADRAO");
                    arquivoPadrao += xyz + (xyz == null ? "" : "\n");
                }
            }

            sta.close();
            conn.close();//ConexÃ£o principal  
            sta2.close();
            conn2.close();//ConexÃ£o auxiliar.            
        } catch (Exception e) {
            System.err.println("Comparar string - Exception: " + e.getMessage());
        }
        return arquivoPadrao;
    }

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
            java.util.logging.Logger.getLogger(frmConsultarQuestionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmConsultarQuestionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmConsultarQuestionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmConsultarQuestionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmConsultarQuestionario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnComparar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSair;
    private javax.swing.JTable dgvItensResposta;
    private javax.swing.JTable dgvRepositorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
