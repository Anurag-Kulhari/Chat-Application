/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Anurag
 */
public class ServerFileSender {
    	  public static void main(String args[]){    
           try{    
                byte b[]=new byte[20002];
                Socket sr=new Socket("localhost",6777);
                InputStream is=sr.getInputStream();
             FileOutputStream fout=new FileOutputStream("F:\\random.txt");    
             is.read(b,0,b.length); //converting string into byte array    
             fout.write(b, 0, b.length);
             System.out.println("success...");    
            }catch(Exception e){System.out.println(e);}    
      }

   
}
