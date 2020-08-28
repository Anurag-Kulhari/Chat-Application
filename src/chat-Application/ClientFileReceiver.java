/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Anurag
 */
public class ClientFileReceiver {
     public static void main(String args[]){    
          try{    
              ServerSocket s=new ServerSocket(6777);
              Socket sr=s.accept();
            FileInputStream fin=new FileInputStream("F:\\random2.txt");    
            byte b[]=new byte[20002]; 
            System.out.println("Failure");  
            fin.read(b,0,b.length);
            OutputStream os=sr.getOutputStream();
            os.write(b, 0, b.length);
          }catch(Exception e){System.out.println(e);}    
         } 
    
    
}
