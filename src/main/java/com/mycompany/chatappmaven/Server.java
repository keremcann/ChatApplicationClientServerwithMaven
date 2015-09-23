package com.mycompany.chatappmaven;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author can
 */
public class Server extends javax.swing.JFrame {

    ServerSocket server = null;
    Socket client = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    
    public Server() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        area_message = new javax.swing.JTextArea();
        txt_sendMessage = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_online = new javax.swing.JTable();
        btn_onlineUsers = new javax.swing.JButton();
        btn_startServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sunucu");

        area_message.setEditable(false);
        area_message.setColumns(20);
        area_message.setRows(5);
        jScrollPane2.setViewportView(area_message);

        txt_sendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sendMessageActionPerformed(evt);
            }
        });

        btn_send.setText("Gönder");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        tbl_online.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Çevirimiçi"
            }
        ));
        jScrollPane3.setViewportView(tbl_online);

        btn_onlineUsers.setText("Çevirimiçi");
        btn_onlineUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_onlineUsersActionPerformed(evt);
            }
        });

        btn_startServer.setText("Server Başlat");
        btn_startServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_sendMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_onlineUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_startServer, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_onlineUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_send, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(txt_sendMessage)
                    .addComponent(btn_startServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed

        String message = txt_sendMessage.getText();
        try {
            dos.writeUTF(message);
            txt_sendMessage.setText("");
        } catch (IOException ex) {
            //Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Mesaj gönderilemedi");
        }
    }//GEN-LAST:event_btn_sendActionPerformed

    private void btn_onlineUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_onlineUsersActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root",""); //Mysql sunucusuna bağlandık
            Statement st = (Statement) con.createStatement();
            try (ResultSet rs = st.executeQuery("Select kullanici_adi from users")) { //Veritabanındaki tabloya bağlandık
                int colcount = rs.getMetaData().getColumnCount(); //Veritabanındaki tabloda kaç tane sütun var?
                DefaultTableModel tm = new DefaultTableModel(); //Model oluşturuyoruz
                for(int i = 1; i <= colcount; i++)
                    tm.addColumn(rs.getMetaData().getColumnName(i)); //Tabloya sütun ekliyoruz veritabanımızdaki sütun ismiyle aynı olacak şekilde
                while(rs.next())
                    {
                        Object[] row = new Object[colcount];
                        for(int i=1;i<=colcount;i++)
                            row[i-1] = rs.getObject(i);
                        tm.addRow(row);
                    }
                tbl_online.setModel(tm);
        }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Hata !!");
        }
    }//GEN-LAST:event_btn_onlineUsersActionPerformed

    private void btn_startServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startServerActionPerformed

        try {
//            int port_no = 0;
//            int username_selected = tbl_online.getSelectedRow();
//            String username = Integer.toString(username_selected);
//
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbChatApp", "root", "");
//                Statement st = conn.createStatement();
//                ResultSet result = st.executeQuery("select * from users");
//                
//                while(result.next()){
//                    if(username.equals(result.getString("kullanici_adi"))){
//                        String port = result.getString("portNo");
//                        port_no = Integer.parseInt(port);
//                    }
//                }
            server = new ServerSocket(1234);
            //new Accept().setVisible(true);
            client = server.accept();
            JOptionPane.showMessageDialog(null, "İstemci isteği kabul etti");
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
            ReceiveMessage serverThread = new ReceiveMessage(dis, area_message);
            serverThread.setDaemon(true);
            serverThread.setName("İstemci");
            serverThread.start();
            
        } catch (IOException ex) {
            //Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "İstemci bulunamadı");
        } //catch (SQLException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btn_startServerActionPerformed

    private void txt_sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sendMessageActionPerformed
        String message = txt_sendMessage.getText();
        try {
            dos.writeUTF(message);
            txt_sendMessage.setText("");
        } catch (IOException ex) {
            //Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Mesaj gönderilemedi");
        }
    }//GEN-LAST:event_txt_sendMessageActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_message;
    private javax.swing.JButton btn_onlineUsers;
    private javax.swing.JButton btn_send;
    private javax.swing.JButton btn_startServer;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_online;
    private javax.swing.JTextField txt_sendMessage;
    // End of variables declaration//GEN-END:variables
}
