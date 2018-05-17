/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andersonz
 */
public class Enviar extends Thread{
    
    String mensagem;
    
    public Enviar(String msg){
    mensagem = msg;
    }
    public void run(){
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        try {
            while(true){
            carai2(d.readLine());
            
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carai2(String msg) throws UnknownHostException, IOException{
        
        int porta = 6868;
        InetAddress ipGrupo = null;
        MulticastSocket s = null;
        
        try{
        
            ipGrupo = InetAddress.getByName("224.225.226.227");
            s = new MulticastSocket(porta);
            s.joinGroup(ipGrupo);
        }catch(SocketException e){
            
            System.out.println("Erro SocketException: "+e.getMessage());
        
        }catch(UnknownHostException c){
        System.out.println("Erro UnknownHostException: "+c.getMessage());
        }
        
        
        DatagramPacket dtgrm = new DatagramPacket(msg.getBytes(), msg.length(), ipGrupo, porta);
        
        
        try{
            s.send(dtgrm);
        }catch(IOException i){
        
            System.out.println("Erro IOException: "+i.getMessage());
        
        }
        
        try{
            
            s.leaveGroup(ipGrupo);
            if(s != null){
            s.close();
            }}catch(IOException k){
            
                System.out.println("Erro IOException2: "+k.getMessage());
            }
        }
             
       
    
}
