package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import common.MessageSource;

public class ConnectionAgent extends MessageSource implements Runnable{
    
Socket socket;
Scanner in;
PrintStream out;
Thread thread;
String message;
private static DataInputStream is = null;
BufferedReader inputLine;
boolean scannerClose =  false;

public ConnectionAgent(Socket socket){
    try{
    this.socket = socket;
    thread = new Thread();
    //thread.start();
    in = new Scanner(System.in);
   
    
   inputLine = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintStream(socket.getOutputStream());
    }catch(IOException e){
        System.err.println(e.getMessage());
    }
    
    
}


public void sendMessage(String message){
  
    if(Check(message)){
        out.println(message);
        out.flush();
        
    
    }
}
    
 
public boolean isConnected(){
    boolean connect = false;
    if(socket.isConnected()){
        connect = true;
    }
    
    return connect;
}

public void close(){
 try{
 if(scannerClose == false){
     in.close();
 }
     
socket.close();

 }catch(IOException e){
     System.err.println(e.getMessage());
 }
}


public boolean Check(String message1){
    boolean check = true;
    
    String [] arrayMessage = message1.split(" ");
    if(!arrayMessage[1].equals("/join") || !arrayMessage[1].equals("/play") || !arrayMessage[1].equals("/attack") || 
            !arrayMessage[1].equals("/quit") || !arrayMessage[1].equals("/show")){
        System.out.println("This is an invalid command");
        check = false;
    } else if(message1.contains("/quit")){
        close();
        check = true;
    }
    
    return check;
}




public void run(){

    while(socket.isConnected()){
        if(in.hasNext()){
            notifyReceipt(in.nextLine());   
        }


    }
    //in.close();
    //scannerClose  = true;
}
}


    




