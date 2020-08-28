/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Anurag
 */
public class OnlineChat extends javax.swing.JFrame {

    /**
     * Creates new form OnlineChat
     */
    private Socket soc = null;
    public File selectedfile;
    public OnlineChat(Socket soc) {
        initComponents();
        this.soc=soc;
    }
    
       public Socket getSocket() {
        return soc;
    }
    void Connect()
    {
                MessageReceiver m1 = new MessageReceiver(this);
                Thread t1 = new Thread(m1);
                t1.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    int SendMessage()
{
    try {
            String message  = messagefield.getText()+":";
            PrintWriter out =new PrintWriter(soc.getOutputStream(),true);
            out.println(message);
            System.out.println("Message sent to server");
        } catch (IOException ex) 
        {
            ex.printStackTrace();
        }  
    return 0;
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NameOfuser = new javax.swing.JLabel();
        fileNameDisplay = new javax.swing.JTextField();
        sendFile = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        attachFile1 = new javax.swing.JButton();
        messagefield = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayBox = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Chatting Interface");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, 72));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Peer");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 12, -1, -1));

        NameOfuser.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        NameOfuser.setForeground(new java.awt.Color(0, 0, 0));
        NameOfuser.setText("USER");
        jPanel1.add(NameOfuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, -1, 24));
        jPanel1.add(fileNameDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 300, 40));

        sendFile.setBackground(new java.awt.Color(51, 102, 255));
        sendFile.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        sendFile.setForeground(new java.awt.Color(0, 0, 0));
        sendFile.setText("Send File");
        sendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileActionPerformed(evt);
            }
        });
        jPanel1.add(sendFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 140, 40));

        jButton2.setBackground(new java.awt.Color(51, 102, 255));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("SEND");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, 40));

        attachFile1.setBackground(new java.awt.Color(51, 102, 255));
        attachFile1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        attachFile1.setForeground(new java.awt.Color(0, 0, 0));
        attachFile1.setText("Attach File");
        attachFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachFile1ActionPerformed(evt);
            }
        });
        jPanel1.add(attachFile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 150, 40));
        jPanel1.add(messagefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 300, 40));

        displayBox.setColumns(20);
        displayBox.setRows(5);
        jScrollPane1.setViewportView(displayBox);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 440, 290));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 680, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void attachFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachFile1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fc=new JFileChooser();
          fc.showOpenDialog(this);
        selectedfile = fc.getSelectedFile();
        String filename = selectedfile.getName();
        fileNameDisplay.setText(filename);
    }//GEN-LAST:event_attachFile1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SendMessage();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void sendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileActionPerformed
        // TODO add your handling code here:
           FileInputStream fin=null;
        try {
            String filepath=selectedfile.getAbsolutePath();
            fin = new FileInputStream(filepath);  
            byte b[]=new byte[9999999];
            fin.read(b,0,b.length);
            OutputStream os=soc.getOutputStream();
            os.write("!!FILEINCOMING!!".getBytes());
            //os.flush();
            os = soc.getOutputStream();
            os.write(b, 0, b.length);
            System.out.println("File Send");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OnlineChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OnlineChat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(OnlineChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_sendFileActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel NameOfuser;
    private javax.swing.JButton attachFile1;
    public javax.swing.JTextArea displayBox;
    private javax.swing.JTextField fileNameDisplay;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField messagefield;
    private javax.swing.JButton sendFile;
    // End of variables declaration//GEN-END:variables
}
