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
import common.ConnectionAgent;
import common.MessageListener;
import common.MessageSource;

/**
 * The class represents a client that sends and receives messages through a Connection Agent.
 * @author Tori Pruett & Melchor Dominguez
 * @version 12/8/17
 */
public class BattleClient extends MessageSource implements MessageListener{
    /**
     * InetAddress/host that we connect the socket to.
     */
    private InetAddress host;
    /**
     * Port that we connect the socket to.
     */
    private int port;
    /**
     * The username that the user specifies.
     */
    private String username;
    /**
     * Connection agent created to send/receive messages.
     */
    ConnectionAgent ca;  
    /**
     * Scanner to read in strings.
     */  
    Scanner scanning;
    /**
     * PrintStreamMessageListener object to add the class as a listener of BattleClient.
     */

    PrintStreamMessageListener pl = new PrintStreamMessageListener();
    
   // private static PrintStream os = null;
    // The input stream
    //private static DataInputStream is = null;

   // private static BufferedReader inputLine = null;
     boolean sourceC = false;
/**
 * Constructor to create a BattleClient object.
 * @param hostname The host that want to connect with.
 * @param port The port that we are using to connect with.
 * @param username The username specified by the user.
 */
    public BattleClient(String hostname, int port, String username) throws IOException{
        this.host = InetAddress.getByName(hostname);
        this.port = port;
        this.username = username;
        
        this.addMessageListener(pl);
       
        
      //  inputLine = new BufferedReader(new InputStreamReader(System.in));
        scanning = new Scanner(System.in);

    }
    /**
     * The method creates the socket to pass to the Connection Agent and adds BattleClient 
     * as a listener of ConnectionAgent.
     */

    public void connect(){
        try{
            
      Socket clientSocket  = new Socket(this.host,this.port);
      ca  = new ConnectionAgent(clientSocket);
      ca.addMessageListener(this);

      
    //  os = new PrintStream(clientSocket.getOutputStream());
     // is = new DataInputStream(clientSocket.getInputStream());
      
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
      
    }
    
    /**
     * The method checks if the argument typed in by the user is a valid command.
     * @param message1 The command we are checking.
     * @return Returns a boolean of false if the commands is not valid.
     */
    public boolean Check(String message1){
        boolean check = true;
        
        message1.replace("\n\n", "\n");
        String [] arrayMessage = message1.split(" ");
        if(!arrayMessage[1].equals("/join") || !arrayMessage[1].equals("/play") || !arrayMessage[1].equals("/attack") || 
                !arrayMessage[1].equals("/quit") || !arrayMessage[1].equals("/show")){
          
            check = false;
        }

        return check;
    }
/**
 * The method checks if the command is the quit command.
 * @param message The command we are checking.
 * @return Returns a boolean value of true if the command is the quit command.
 */
    public boolean quitCheck(String message){
        boolean quitBoolean = false;
        if(message.contains("/quit")){
       
            quitBoolean = true;
        }
        return quitBoolean;
    }


/**
 * Removes the listener from the list of listeners to disconnect it.
 * @param source The message source we are removing this class as a listener from.
 */
    public void sourceClosed(MessageSource source){
       source.removeMessageListener(this);
        
    }

    /**
     * Sends a message through the Connection Agent.
     * @param message the message we are sending.
     */
    public void send(String message){
        ca.sendMessage(message); 
        
    }
/**
 * The method receives the message and if it is valid it prints it and if it is the quit command
 * it calls sourceClosed.
 * @param message The message that we are printing/receiving.
 * @param source The source we are closing if the command is /quit.
 */
    @Override
    public void messageReceived(String message, MessageSource source) {
        //System.out.println("message received");
        if(Check(message)){
            if(quitCheck(message) == false){       
                System.out.println(message);
            }else{
                sourceClosed(source);
            }
        }
    }
}



    
    
    






