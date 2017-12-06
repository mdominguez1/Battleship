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

    private Socket socket;
    private Scanner in;
    private PrintStream out;
    private Thread thread;
    
    private static DataInputStream is = null;
    BufferedReader inputLine;
    boolean scannerClose =  false;

    public ConnectionAgent(Socket socket){
        try{
            this.socket = socket;
            in = new Scanner(System.in);

            inputLine = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintStream(socket.getOutputStream());
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        thread = new Thread(this);
        thread.start();

    }



    public void sendMessage(String message){
        while(!socket.isClosed()){
            if(Check(message)){
                out.println(message);
                out.flush();
            }
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
          
            check = false;
        }

        return check;
    }

    public boolean quitCheck(String message){
        boolean quitBoolean = false;
        if(message.contains("/quit")){
            close();
            quitBoolean = true;
        }
        return quitBoolean;
    }


    public void run(){

        while(socket.isConnected()){
            if(in.hasNext()){
                if(Check(in.nextLine())){

                    if(quitCheck(in.nextLine())){
                        close();
                    }else{
                        notifyReceipt(in.nextLine());   
                    }
                } else{
                    System.out.println("This is an invalid command");
                }

            }
            in.close();
            //scannerClose  = true;
        }
    }
}







