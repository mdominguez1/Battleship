package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import common.MessageListener;
import common.MessageSource;

/**
 * 
 * @author Melchor Dominguez & Tori Pruett
 * @version 11/27/2017
 */
public class BattleServer implements MessageListener{
    
    /** Listen for clients */
    ServerSocket serverSocket;
    
    /**  */
    int current;
    
    /** Game for the clients */
    Game game;
    
    /**
     * Create a Server with a listening socket to 
     * accept client connections 
     * @throws IOException 
     */
    public BattleServer(String hostname, int port, String username) throws IOException {
        
        // socket listens for incoming connections
        serverSocket = new ServerSocket(port);
    }
    
    /**
     * Accept connections from clients, Get data they send and 
     * send it back modified.
     * @throws IOException 
     */
    public void listen() throws IOException {
        
        for(; ;) {
            //Accept a connection, and create a new 'direct' socket
            // This socket has the same port as the welcome socket. 
            Socket connectionSocket = serverSocket.accept();
            
            // create a Scanner (stream) connected to the client's socket
            Scanner clientIn = new Scanner(connectionSocket.getInputStream());
            DataOutputStream clientOut = new DataOutputStream(connectionSocket.getOutputStream());
            
            // read from the socket
            String clientLine = clientIn.nextLine();
            
            // modify the data and send it back though the socket.
            // don't forget the newline, the client expects one!
            String modLine = clientLine.toUpperCase();
            clientOut.writeBytes(modLine + "\n");
        }
    }
    
    public void broadcast(String message) {
        
    }
    
    public void messageReceived(String message, MessageSource source) {
        
    }
    
    public void sourceClosed(MessageSource source) {
        
    }
}
