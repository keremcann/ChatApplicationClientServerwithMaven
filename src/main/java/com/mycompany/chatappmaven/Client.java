package com.mycompany.chatappmaven;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author can
 */
public class Client extends javax.swing.JFrame {
    
    Socket server = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    public Client() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_sendMessage = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        area_Message = new javax.swing.JTextArea();
        btn_Send = new javax.swing.JButton();
        btn_connection = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("İstemci");

        txt_sendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sendMessageActionPerformed(evt);
            }
        });

        area_Message.setEditable(false);
        area_Message.setColumns(20);
        area_Message.setLineWrap(true);
        area_Message.setRows(5);
        jScrollPane1.setViewportView(area_Message);

        btn_Send.setText("Gönder");
        btn_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SendActionPerformed(evt);
            }
        });

        btn_connection.setText("Bağlan");
        btn_connection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_connection, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_sendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Send, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_sendMessage)
                    .addComponent(btn_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_connection, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_connectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectionActionPerformed
        try {
            server = new Socket("127.0.0.1", 1234);
            JOptionPane.showMessageDialog(null, "Sunucuya bağlandı");
            dis = new DataInputStream(server.getInputStream());
            dos = new DataOutputStream(server.getOutputStream());
            ReceiveMessage clientThread = new ReceiveMessage(dis, area_Message);
            clientThread.setDaemon(true);
            clientThread.setName("sunucu");
            clientThread.start();
        } catch (UnknownHostException ex) {
            //Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Bağlantı başarısız");
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Hata !");
            //Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_connectionActionPerformed

    private void btn_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SendActionPerformed
        try {
            dos.writeUTF(txt_sendMessage.getText());
            txt_sendMessage.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Hata !");
            //Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_SendActionPerformed

    private void txt_sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sendMessageActionPerformed
        try {
            dos.writeUTF(txt_sendMessage.getText());
            txt_sendMessage.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Hata !");
            //Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_sendMessageActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_Message;
    private javax.swing.JButton btn_Send;
    private javax.swing.JButton btn_connection;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_sendMessage;
    // End of variables declaration//GEN-END:variables
}
