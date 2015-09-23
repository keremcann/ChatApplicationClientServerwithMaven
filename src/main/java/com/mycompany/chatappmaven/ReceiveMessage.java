package com.mycompany.chatappmaven;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author can
 */
public class ReceiveMessage extends Thread {
    String message = "";
    DataInputStream dis = null;
    JTextArea txt_area = null;
    
    public ReceiveMessage(DataInputStream d, JTextArea t){
        this.dis = d;
        this.txt_area = t;
    }
    public void run(){
        while(true){
            try {
                message = dis.readUTF();
                txt_area.append("\n"+this.getName()+":"+message);
            } catch (IOException ex) {
                Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
