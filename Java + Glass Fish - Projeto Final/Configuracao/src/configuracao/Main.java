/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracao;

import javax.swing.JFrame;

/**
 *
 * @author Ana Julia
 */
public class Main {

    public static void main(String[] args){
         
        //Codigo j = new Codigo();
        //j.setVisible(true);
        Principal dlg = new Principal();
        dlg.setVisible(true);
        dlg.setExtendedState(dlg.getExtendedState() | JFrame.MAXIMIZED_BOTH);       
    }    
}

