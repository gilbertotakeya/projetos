/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andersonz
 */
public class Chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
            
            Receber c = new Receber();
            c.start();
          
            Enviar EnviarMensagem = new Enviar("Ola como vc esta");
            EnviarMensagem.start();
           
            
       
        
     
    }
}
