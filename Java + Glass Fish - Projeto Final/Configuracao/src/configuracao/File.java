package configuracao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana Julia
 */
public class File {

        private BufferedWriter ArquivoXML;

    public void Write(String Destino, String Texto) {
        try {
            ArquivoXML = new BufferedWriter(new FileWriter(Destino));
            ArquivoXML.write(Texto);
            ArquivoXML.flush();  // Valida o fluxo
            ArquivoXML.close();
        } catch (IOException erro) {
            System.out.println("Erro ao Gravar arquivo: " + erro);
        }
    }
}