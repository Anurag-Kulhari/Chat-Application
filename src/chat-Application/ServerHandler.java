/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anurag
 */
public class ServerHandler extends server implements Runnable{
    
    private final Socket localsocket;
    public String uName=null; 
    public  ServerHandler(Socket cs)
    {
        this.localsocket=cs;
    }
    
    @Override
    public void run(){
        try{
            handleClientSocket(localsocket);
        }
       catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    
   int Sendmessages(String clientname,String message)
    {
        int onlineClientsCount= activeUser;
        System.out.println("ServerHandler.Sendmessages() "+onlineClientsCount);
        for(int j=0;j<onlineClientsCount;j++)
        {
             System.out.println("inside horny");
            System.out.println("inside "+clientSockets[j]+userClients[j]);
            if(clientname.equals(userClients[j]))
            {
                System.out.println("inside " + clientname );
                try {
//                    OutputStream outputStream=clientSocket[j].getOutputStream();
//                    String messageline = message;
//                    outputStream.write(messageline.getBytes());
//                    outputStream.
                    PrintWriter out =new PrintWriter(clientSockets[j].getOutputStream(),true);
                    uName=clientname;
                    out.println(uName+"-->"+message);
                    return 1;
                } catch (IOException ex) {
                    System.out.println("Error in generating outputstream");
                }
                
            }
        }
        return 0;
    }
    void RecieveFile()
    {
        try {
            FileInputStream fin = new FileInputStream("S:\\a1.txt");
            byte b[] = new byte[999999999];
            fin.read(b,0,b.length);
            System.out.println("File Recieved");
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 int loginVerification(String msg) throws ClassNotFoundException    
  {
    String data[];
    data=msg.split("-");
     String uname=data[1];
    String pass=data[2];
    try{
        Class.forName("java.sql.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chatapplication","root","mysql");
        Statement stmt=con.createStatement();
        String q1="select password from currentusers where username='"+(uname)+"';";
        ResultSet rs=stmt.executeQuery(q1);
        rs.next();
        String p=rs.getString("password");
        if(p.equals(pass))
        {
            return 1;
        }
    }
    catch(SQLException ex)
    {
        Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE,null,ex);
        
    }
     return 0; 
  }
  int createUser(String msg)
  {
      String data[];
      data=msg.split("-");
      try{
          System.out.println("Hii from sHandler");
          Class.forName("java.sql.Driver");
          Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/chatapplication","root","mysql");
          Statement stmt=(Statement)con.createStatement();
          String q1="insert into currentusers values ('"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"');";
          stmt.executeUpdate(q1);
          return 1;
      }
      catch(Exception e){
          return 0;
      }
      
  }
  void getuserslist()
  {
      System.out.println("wait!! Data is Fetching from Database");
      try{
      Class.forName("java.sql.Driver");
      Connection con=DriverManager.getConnection ("jdbc:mysql://localhost/chatapplication","root","mysql");
      Statement stmt=con.createStatement();
       System.out.println("connection done");
       String q1="select username from currentusers;";
       
         ResultSet rs=stmt.executeQuery(q1);
            System.out.println("query exceuted");
               String message="";
            while(rs.next())
            {
                message=message+rs.getString("username")+"-";
            }
            //System.out.println(message);
            message=message.substring(0,message.length()-1);
            System.out.println(message);
            PrintWriter out=null;
            out = new PrintWriter(localsocket.getOutputStream(),true);
            out.println(message);
            System.out.println("User list sent");
      
      }
      catch(Exception e)
      {
                System.out.println(e.getMessage());
      }
  }
    
    
    private void handleClientSocket(Socket localsocket) throws IOException, ClassNotFoundException {
        InputStream is= localsocket.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is));    
        
        String msg;
        while((msg=br.readLine())!=null)
        {
            
            
            System.out.println(msg);
            int len=msg.length();
            if(len>9 && msg.substring(0,9).equals("!!Login!!"))
            {
                if(loginVerification(msg)==0)
                {
                    PrintWriter pw=new PrintWriter(localsocket.getOutputStream(),true);
                    pw.println("!!0!!");
                   // destroyme(this);
                }
                if(loginVerification(msg)==1)
                {
                    PrintWriter pw=new PrintWriter(localsocket.getOutputStream(),true);
                    pw.println("!!1!!");
                    continue;
                }
          
            }
            if(len>17 && msg.substring(0,17).equals("!!CreateNewUser!!"))
            {
               if(createUser(msg)==0)
               {
                   //destroyme(this);
               }
               if(createUser(msg)==1)
               {
                   continue;
               }
               continue;
            }
            if(len>=16 && msg.substring(0,16).equals("!!GETUSERSLIST!!"))
            {
                getuserslist();
                continue;
                
            }
            //msg.charAt(activeUser)!=':'&&;
              for(int k=0,flag=0;k<len;k++)
            {
                
                if(msg.charAt(k)=='~')
                {
                    k++;
                    String clientname;
                    int j=k;
                    for(;msg.charAt(k)!='~'&&msg.charAt(k)!=':';)
                    {
                        k++;
                    }
                    clientname=msg.substring(j,k);
                    k=j-1;
                    System.out.println("Clisnt "+clientname);    
                    System.out.println(msg.substring(0,k)+" "+clientname);
                    int response = Sendmessages(clientname,msg.substring(0, k));
                    if(response==0)
                    {
                        System.out.println("Client not online");
                    }
                    if(response==1)
                    {
                        System.out.println("message sent");
                    }
                    //k--;
                    flag=1;
                }
                
            }
            if("quit".equalsIgnoreCase(msg)){
                break;
            }
         msg=null;   
        }
        localsocket.close();
    }
    
}
