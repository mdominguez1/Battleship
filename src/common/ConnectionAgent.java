package common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

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
    
    private DataInputStream input;
    
    private DataOutputStream output;
    
    private boolean connected;
    
    /**
     * Constructor to initialize Connection agent and start a thread
     * @param socket - 
     */
    public ConnectionAgent(Socket socket) {
       this.socket = socket;
       try {
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        out = new PrintStream(this.socket.getOutputStream());
        connected = true;
       } catch (IOException e) {
        e.printStackTrace();
       }
       thread = new Thread(this);
       thread.start();
       in = new Scanner(input);
    }//end constructor

    /**
     * method to send message to output stream
     * @param message - message to be sent
     */
    public void sendMessage(String message) {
        while(connected) {
            try {
                output.writeBytes(message);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }//end while
    }//end sendMessage
    
    public boolean isConnected() {
        return connected;
    }//end isConnected()
    
    
    @Override
    public void run() {
        while(connected) {
            String line = "";
            if(in.hasNext())
                line = in.nextLine();
            System.out.println(line);
            this.notifyReceipt(line);
        }//end while
    }//end run()
    
    /**
     * Closes all streams connected to client
     * @throws IOException - if streams are already closed
     */
    public void close() throws IOException {
        if(connected) {
            input.close();
            output.close();
            out.close();
            connected = false;
        }//end if
    }//end close

}
