package server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import common.MessageSource;

/**
 * Class Responsible for receiving from and sending messages to
 * remote hosts. 
 * @author Melchor Dominguez & Tori Pruett
 * @version 11/27/2017
 */
public class ConnectionAgent extends MessageSource implements Runnable{

    private Socket socket; 
    
    private Scanner in;
    
    private PrintStream out;
    
    private Thread thread;
    
    
    public ConnectionAgent(Socket socket) {
        
    }
    
    /**
     * 
     * @param message
     */
    public void sendMessage(String message) {
        out.println(message);
    }
    
    public boolean isConnected() {
        return false;
        
    }
    
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
    
    public void close() {
        
    }

}
