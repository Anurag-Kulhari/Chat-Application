/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anurag
 */
    public class MessageReceiver implements Runnable{
     private OnlineChat currentW;
     
    public MessageReceiver(OnlineChat w) {
        this.currentW = w;
       
   }

    
    @Override 
    public void run(){
        try {
            //r.run();
            handlereceiving();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handlereceiving() throws IOException, InterruptedException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(currentW.getSocket().getInputStream()));
        String line;
        System.out.println("Messages recieving");
        while((line = reader.readLine())!=null)
        {
            System.out.println("Message Recieved");
            if("quit".equalsIgnoreCase(line)){
                break;
            }
            System.out.println(line);
            currentW.displayBox.append(line+"\n");
        }
    }
}


