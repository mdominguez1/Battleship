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
    InetAddress host;
    int port;
    String username;
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
       
        //ca.thread.start();
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



    public void sourceClosed(MessageSource source){
       source.removeMessageListener(this);
        
    }
    
    public void send(String message){
        ca.sendMessage(message); 
        
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        System.out.println(message);
      if(message.contains("/quit")){
          sourceClosed(source);
          sourceC = true;
      }

    }
 

    
    
    
    
}





