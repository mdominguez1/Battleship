package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
    
    /**  the current client that is being worked with*/
    int current = 0;
    
    /** List of Connection Agents */
    private ArrayList<ConnectionAgent> agents = new ArrayList<ConnectionAgent>();

    /** Game for the clients */
    Game game;
    
    /** Default port for the server*/
    private static final  int DEFAULT_PORT = 2323;
    
    /**
     * Create a Server with a listening socket to 
     * accept client connections 
     * @throws IOException - if unable to connect to port
     */
    public BattleServer(int port) throws IOException {
        
        // socket listens for incoming connections
        serverSocket = new ServerSocket(port);
    }
    
    /**
     * Create a Server with a listening socket
     * to accept client connections from the 
     * Default port
     * @throws IOException - if unable to connect to port
     */
    public BattleServer() throws IOException{
        
        //Socket that listens for incoming connections
        serverSocket = new ServerSocket(DEFAULT_PORT);
    }//end BattleServer()
    
    /**
     * Accept connections from clients, Get data they send and 
     * send it back modified.
     * @throws IOException 
     */
    public void listen() throws IOException {
        
        while(true){ //will continuously listen for sockets until
            try{
                Socket connectionSocket = serverSocket.accept();

                // Creates a new Connection agent for the client
                ConnectionAgent newAgent = new ConnectionAgent(connectionSocket);

                newAgent.addMessageListener(this);
                // adds the agent to the list of agents 
                agents.add(newAgent);

                //updates the current number or clients
                current = agents.size();
                

            }catch (IOException e){
                System.out.println(e);
                break;
            }//end try-catch 
        }//end while
    }//end listen()
    
    /**
     * Sends a message to every client connected to the server
     * @param message - message to be sent
     */
    public void broadcast(String message) {
        
        for(ConnectionAgent agent: agents) {
            agent.sendMessage(message);
        }//end for-each
        
    }//end broadcast()
    
    /**
     * Used to notify observers that the subject has received a message.
     *
     * @param message The message received by the subject
     * @param source The source from which this message originated (if needed).
     */
    public void messageReceived(String message, MessageSource source) {
        System.out.println("Message Received: " + message);
    }
    
    /**
     * Used to notify observers that the subject will not receive new messages; observers can
     * deregister themselves.
     *
     * @param source The <code>MessageSource</code> that does not expect more messages.
     */
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
        agents.remove(source);
    }
}
