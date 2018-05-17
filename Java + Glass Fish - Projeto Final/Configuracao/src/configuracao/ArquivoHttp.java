/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracao;

import database.Database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * A classe ArquivoHttp representa o arquivo texto http de configuração
 * do apache no disco tendo metodos para abrir, ler, recuperar seu conteudo
 * etc...
 * @author Ana Julia
 */
public class ArquivoHttp 
{

    /**
     * Atributo: arquivoHTTP representa o arquivo texto http de configuração
     * do apache contendo todas as linhas em memoria em tempo de execução
     * ou seja um lista de String, cada linha do arquivo esta dentro
     * de arquivoHTTP.
     */
    private ApacheArquivo linhaIncluir = new ApacheArquivo();
    private List<ApacheArquivo> lista = new LinkedList<ApacheArquivo>();
    private int contadorTagAberta = 0;
    private int contadorTagFechada = 0;
    private List<String> arquivoHTTP = new LinkedList<String>(); //Atributo: arquivoHTTP
    File arquivo;  //atributo classe que trata do arquivo em disco, se ele existe, se é um diretorio etc.
    FileReader fileReander; //Classe de conveniência para leitura de arquivos de carácter.
    BufferedReader buffReander; //atributo responsavel por ler o arquivo bufferidado performace esse e o proposito dele.
    String NomeArquivo = "httpd.txt";
    int sequencialCabecalho = -1;
    public ArquivoHttp() 
    {
        initComponets();
        mostrarArquivoLido();//usa o metodo mostrarArquivoLido e lista a lista arquivoHTTP;
        gravaArquivoemDisco();
    }
    public ArquivoHttp(String nomArquivo, int seq) 
    {
        initComponets();
        NomeArquivo = nomArquivo;
        sequencialCabecalho = seq;
    }
    /**
     * Metodo para inicialização dos atributos e preparar o arquivo http para
     * abrir e ler... 
     */
    private void initComponets() 
    {
        arquivo = new File(NomeArquivo);// busca do path do projeto mas pode mudar !!!!
        if (arquivo.exists()) 
        {
            try 
            {
                buffReander = new BufferedReader(fileReander = new FileReader(arquivo));// prepara o arquivo para abrir, e trabalahr com buffer com ele...
                try {
                    lerArquivo();// aqui realmente ler o arquivo !
                } catch (SQLException ex) {
                    Logger.getLogger(ArquivoHttp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            catch (FileNotFoundException ex) 
            {
                ex.printStackTrace();
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "(O sistema não pode encontrar o arquivo especificado)" + arquivo.getAbsolutePath());
        }
    }

    /**
     * Metodo ler arquivo http idepedente do tamaho e quantidade de linhas...
     * lê do inicio ao fim ! sempre !
     * e automaticamente verifica se cada linha lida contem o caracter # de 
     * comentário...
     */
    public void lerArquivo() throws IOException, SQLException 
    { // quem for usar esse metodo tem que tratar a Exception: IOException
        String linha = "";// cada linha do arquivo...
        String caracter = "";
        Boolean abriuTag = false;
        int contadorTagAberto = 0;
        int contadorTagFechado = 0;
        int contadorLinhas = 0;
        while ((linha = buffReander.readLine()) != null) 
        {
            String linhaAuxiliar = linha;
            linhaAuxiliar = linhaAuxiliar.replaceAll(" ", "");
            
            if (linhaAuxiliar.equals(""))
                continue;
            
            
            if (linha.indexOf("#") == -1) //se nao tiver pode adicionar na lista !
            {                
                //Verifica se tem tag!
                if (linha.indexOf("<") != -1 && linha.indexOf("</") == -1)
                {
                    abriuTag = true;
                    linhaIncluir.campo = linha;
                    contadorTagAberto ++;
                }
                else if(abriuTag) //Inclui os valores dos campos se tiver tag
                {
                    String campo = linhaIncluir.campo;
                    linhaIncluir.valor = linha;                                
                    if (abriuTag && linha.indexOf("</") != -1)
                    {
                        contadorTagFechado ++;
                        if (contadorTagAberto == contadorTagFechado)
                        {
                            contadorTagAberto = 0;
                            contadorTagFechado = 0;
                        }
                    }                
                    linhaIncluir.id = contadorLinhas;
                    incluirItemLista();
                    linhaIncluir.campo = campo;
                }
                else
                {
                    if (!linha.equals(""))
                    {
                        Boolean achouEspaco = false;
                        linhaIncluir.id = contadorLinhas;
                        for (int i = 0; i < linha.length(); i++)
                        {
                            char x = linha.charAt(i);
                            //String x = linha.substring(i, 1);
                            if (x == ' ')
                            {
                                achouEspaco = true;
                            }

                            if (x != ' ' && !achouEspaco) 
                            {
                                linhaIncluir.campo += x;
                            }
                            else if (x != ' ' && achouEspaco) 
                            {
                                linhaIncluir.valor += x;
                            }                        
                        }
                        incluirItemLista();
                    }
                }
                contadorLinhas ++;
                arquivoHTTP.add(linha);// adiciona cada linha a lista de string que representa o arquivo text http.
            }
        }
        
        //Aqui será escrito os dados no banco.
        //O banco está com pau para inclusão
        Database db = new Database ();
        Connection conn = db.abrirBanco();        
        for(ApacheArquivo a:lista)
        {
            String insert = "insert into config (idconfigcab,atributo, valor) values (?,?,?)"; //NOI18N
            PreparedStatement stmt = conn.prepareStatement(insert);           
            stmt.setInt(1, sequencialCabecalho);
            stmt.setString(2, a.campo);
            stmt.setString(3, a.valor);

            stmt.execute();

            System.out.println(a.campo + "-" + a.valor.toString());
        }
        int x = 10;
    }

    /**
     * Metodo para percorrer toda a lista arquivoHTTP
     * lê do inicio ao fim ! sempre ! e pegar uma string dentro com o int x,
     * e mostrar na saida padrao...
     */
    private void mostrarArquivoLido() 
    {
        for (int x = 0; x < arquivoHTTP.size(); x++) 
        {
            System.out.println(arquivoHTTP.get(x));
        }
    }

    /**
     * Metodo para gravar um novo arquivo em disco com base em arquivoHTTP
     */
    private void gravaArquivoemDisco() 
    {
        /*
        File arquivoDisco = new File();
        StringBuffer conteudodetodoarquivo = new StringBuffer();
        for (int x = 0; x < arquivoHTTP.size(); x++) 
        {
            conteudodetodoarquivo.append(arquivoHTTP.get(x) + "\r\n");
        }
        //arquivoDisco.Write(new File(NomeArquivo) + "_novo.txt", conteudodetodoarquivo.toString());
        arquivoDisco.Write(new File(NomeArquivo).getName(), conteudodetodoarquivo.toString());*/
    }

    private void incluirItemLista() 
    {
        lista.add(linhaIncluir);

        linhaIncluir = null;
        linhaIncluir = new ApacheArquivo();
        linhaIncluir.campo = "";
        linhaIncluir.valor = "";
    }
}
