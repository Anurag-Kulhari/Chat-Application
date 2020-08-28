/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Anurag
 */
public class server {
    public static Socket [] clientSockets =new Socket [100];
    public static String [] userClients=new String[100];
    public static int activeUser;
    
    public static void main(String [] args) throws IOException, InterruptedException{
        
        int port=8818;
        ServerSocket ss=new ServerSocket(port);
        activeUser=0;
        while(true)
            
        {
            System.out.println("Connecting to server");
            clientSockets[activeUser]=new Socket("localhost",port);
            clientSockets[activeUser]=ss.accept(); 
            
            ServerHandler sh=new ServerHandler(clientSockets[activeUser]);
            Thread th=new Thread(sh);
            System.out.println("Acctive user is " + ++activeUser);
            th.start();
            Thread.sleep(2000);
        
            userClients[activeUser-1]=sh.uName;
           
        }   
    }
    void destroyme(ServerHandler sh)
    {
        sh.destroyme(sh);
    }
            
}
