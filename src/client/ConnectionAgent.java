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


            socket.close();
            in.close();


        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }





    public void run(){

        while(socket.isConnected()){
            if(in.hasNext()){
            
                        notifyReceipt(in.nextLine());   
                    }
            }
            in.close();
            
        }
    }








