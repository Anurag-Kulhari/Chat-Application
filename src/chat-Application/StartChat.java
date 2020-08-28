/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Anurag
 */
public class StartChat extends javax.swing.JFrame {

    /**
     * Creates new form StartChat
     */
    String []users =  null;
    private Socket soc =new Socket();
    public StartChat(Socket soc) {
 
        initComponents();
        this.soc=soc;
        PrintWriter out =null;
        try {
            initComponents();
            this.soc = soc;
            out = new PrintWriter(soc.getOutputStream(),true);
            out.println("!!GETUSERSLIST!!");
            BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String line;
            System.out.println("Messages recieving");
            
            line = reader.readLine();
            users=line.split("-");
            for(int i=0;i<users.length;i++)
            {
               // jList1.setText(users[i]);
                System.out.print(users[i]+"  ");
            }
            System.out.println();
        } 
        catch (IOException ex) {
            System.out.println("Problem Detected in Catch Block");
            Logger.getLogger(StartChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
        private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        DefaultComboBoxModel model;
        model=(DefaultComboBoxModel) jComboBox1.getModel();
        model.removeAllElements();
        for(int i=0;i<users.length;i++)
        {
            model.addElement(users[i]);
        }
        DefaultListModel model1=new DefaultListModel();
        model1=(DefaultListModel)jList1.getModel();
        for(int i=0;i<users.length;i++)
        {
            model1.addElement(users[i]);
        }
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        NameOfUser = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 160, 130));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 130, -1));

        NameOfUser.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        NameOfUser.setText("User Name");
        NameOfUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameOfUserActionPerformed(evt);
            }
        });
        jPanel1.add(NameOfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, 30));

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Start Chat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 540, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void NameOfUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameOfUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameOfUserActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        OnlineChat c1 = new OnlineChat(soc);
        c1.NameOfuser.setText(NameOfUser.getText());
        c1.setVisible(true);
        c1.Connect();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField NameOfUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
