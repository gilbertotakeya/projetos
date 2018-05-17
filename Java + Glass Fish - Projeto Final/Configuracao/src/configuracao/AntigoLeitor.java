///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package configuracao;

import database.Database;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane; 



public class AntigoLeitor {
    
/*   public void Verifica(){
 
      boolean exists = (new File("httpd.txt")).exists();  
      if (exists) {  
        System.out.println(("httpd.txt"));
     } else {  
        // File or directory does not exist  
     }  

}*/
     public void Ler() throws FileNotFoundException, IOException{
            /// Manipulação - Ler gravar em arquivos
         Database db = new Database ();
        Connection conn = db.abrirBanco();
        
            FileReader f = new FileReader("httpd.txt");

            BufferedReader in = new BufferedReader(f);

            BufferedWriter p = new BufferedWriter(new FileWriter("httpdNovo.txt",true));
            
            

            // renomear e excluir os arquivos
            File g = new File("httpd.txt");
            File a = new File("httpdNovo.txt");

            
            String atrib = null;
            String valor = null;
            String line = null;
            int lineCount = 0;

            while(lineCount != 484)//verificar o tamanho do vetor tem q ser dinamico
            {
                line = in.readLine();
                if(!(line.contains("#")))
                {
                    System.out.println(line);
                    try
                    {
                         p.write(line);
                         p.newLine();
                         p.flush();
                    }
                    catch(Exception e)
                    {
                         System.out.println(e.getMessage());
                    }

                    for(int i = 0;i<= line.trim().length();i++)
                    {
                         try
                         {
                             line.substring(i);
                             //System.out.print(line.substring(i,i+1)+"-");
                             //if(line.substring(i).equals("\n ")){
                             if(line.charAt(i) == 32)
                             {
                                 atrib = line.substring(0,i);
                                 valor = line.substring(i,line.length());
                                 System.out.println("\nAtributo = "+atrib.toString()+"\nValor = "+valor.toString());

                                 try
                                 {
                                     String insert = "insert into config (atributo, valor) values (?,?)"; //NOI18N
                                     PreparedStatement stmt = conn.prepareStatement(insert);         
                                     stmt.setString(1, atrib);
                                     stmt.setString(2, valor);

                                     stmt.execute();
                                     stmt.close();


                                 } 
                                 catch (Exception ex) 
                                 {
                                     JOptionPane.showMessageDialog(null, "Error: "+
                                     ex.getMessage());
                                     ex.printStackTrace();
                                 }                            
                                 break;
                             }
                         }
                         catch (Exception e)
                         {                     
                             break;
                         }                       
                    }
                 }
                lineCount++;
             }

            //fecha instancias
            f.close();
            p.close();
            in.close();

            try {
                //deleta txt "arquivo.txt"
                
                g.delete();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            // renomeia txt "arquivoNovo.txt" para "arquivo.txt"
            a.renameTo(g);
    }

     public String leitor() {
        try {
            FileReader p = new FileReader("httpd.txt");
            BufferedReader conexao = new BufferedReader(p);
            String linha = null;
            while ((conexao.ready())) {
                linha = linha + "\n"+conexao.readLine();
            }
            conexao.close();
            p.close();
            return linha;
        } catch (Exception e) {
            System.out.println("Erro ao ler: " + e.getMessage());
        }
        return "";
    }
//     
     
     
}

