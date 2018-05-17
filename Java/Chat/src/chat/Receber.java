/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andersonz
 */
public class Receber extends Thread {
      int porta = 6868;
        InetAddress ipGrupo = null;
        MulticastSocket s = null;
        
        public Receber(){
        
        }
        
    public void run(){
        try {
            carai();
            
        } catch (IOException ex) {
            Logger.getLogger(Receber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void carai()throws IOException{
    
      
       
        try{
        
            ipGrupo = InetAddress.getByName("224.225.226.227");
            s = new MulticastSocket(porta);
            s.joinGroup(ipGrupo);
        }catch(SocketException e){
            
            System.out.println("Erro SocketException: "+e.getMessage());
        
        }catch(UnknownHostException c){
        System.out.println("Erro UnknownHostException: "+c.getMessage());
        }
        
        byte[] buf = new byte[1512];
        
        while(true){
        DatagramPacket recebido = new DatagramPacket(buf, buf.length);
        
        try{
        s.setSoTimeout(120000);
        s.receive(recebido);
        }catch(SocketTimeoutException e){
        System.out.println("Erro SocketTimeoutException: "+e.getMessage());
        break;
        }catch(IOException e){
        System.out.println("Erro IOException: "+e.getMessage());
        }
        
        String str = new String(recebido.getData());
        
        System.out.println("(" + recebido.getAddress().getHostAddress()+ " : "+recebido.getPort() +") << "+str.trim());
        }
        
    }
}
