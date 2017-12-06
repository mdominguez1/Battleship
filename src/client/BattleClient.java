package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import client.ConnectionAgent;

import common.MessageListener;
import common.MessageSource;

public class BattleClient extends MessageSource implements MessageListener{
    private InetAddress host;
    private int port;
    private String username;
    
    ConnectionAgent ca;    
    Scanner scanning;
    PrintStreamMessageListener pl = new PrintStreamMessageListener();
    
    private static PrintStream os = null;
    // The input stream
    private static DataInputStream is = null;

    private static BufferedReader inputLine = null;
     boolean sourceC = false;

    public BattleClient(String hostname, int port, String username) throws IOException{
        this.host = InetAddress.getByName(hostname);
        this.port = port;
        this.username = username;
        
        ca.addMessageListener(this);
        this.addMessageListener(pl);
       
        
        inputLine = new BufferedReader(new InputStreamReader(System.in));
        scanning = new Scanner(System.in);

    }

    public void connect(){
        try{
            
      Socket clientSocket  = new Socket(this.host,this.port);
      ca  = new ConnectionAgent(clientSocket);
      ca.run();
      
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
      
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
      
    }
    
    
    public boolean Check(String message1){
        boolean check = true;

        String [] arrayMessage = message1.split(" ");
        if(!arrayMessage[1].equals("/join") || !arrayMessage[1].equals("/play") || !arrayMessage[1].equals("/attack") || 
                !arrayMessage[1].equals("/quit") || !arrayMessage[1].equals("/show")){
          
            check = false;
        }

        return check;
    }

    public boolean quitCheck(String message){
        boolean quitBoolean = false;
        if(message.contains("/quit")){
       
            quitBoolean = true;
        }
        return quitBoolean;
    }



    public void sourceClosed(MessageSource source){
       source.removeMessageListener(this);
        
    }
    
    public void send(String message){
        ca.sendMessage(message); 
        
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        if(Check(message)){
            if(quitCheck(message) == false){       
                System.out.println(message);
            }else{
                sourceClosed(source);
            }
        }
    }
}



    
    
    






