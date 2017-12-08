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

    /** Socket to be listening to*/
    private Socket socket; 
    
    /** Scanner to read input stream*/
    private Scanner in;
    
    /** PrintStream for the output stream */
    private PrintStream out;
    
    /** Thread to start process of ConnectionAgent */
    private Thread thread;
    
    /** Stream to listen to */
    private DataInputStream input;
    
    /** Stream to send things threads */
    private DataOutputStream output;
    
    /** Boolean to say whether or not sockets are connected */
    private boolean connected;
    
    /**
     * Constructor to initialize Connection agent and start a thread
     * @param socket - socket to start connection with 
     */
    public ConnectionAgent(Socket socket) {
       this.socket = socket;
       try {
        // Create a stream connected to read data
        input = new DataInputStream(socket.getInputStream());

        //create a stream to send data 
        output = new DataOutputStream(socket.getOutputStream());

        // print stream connected to the output screen
        out = new PrintStream(this.socket.getOutputStream());

        //turn connection to true
        connected = true;
       } catch (IOException e) {
        e.printStackTrace();
       }
       //initialize thread
       thread = new Thread(this);

       //Use scanner to read data 
       in = new Scanner(input);
       
       //start the thread
       thread.start();

    }//end constructor

    /**
     * method to send message to output stream
     * @param message - message to be sent
     */
    public void sendMessage(String message) {
            try {
                //write the message to the output streama
                //System.out.println("send message");
                output.writeBytes(message + "\n");
                //flush the Bytes afterward
                output.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }//end sendMessage
    
    /**
     * Returns if the connection is established
     * @return - true if connection has started 
     *           false if connection has ended
     */
    public boolean isConnected() {
        return connected;
    }//end isConnected()
    
    
    @Override
    public void run() {

        //while loop if connection is still established
        while(connected) {

            String sendLine = "";     
            if(in.hasNext()){
                //System.out.println("run and sent");
                sendLine = in.nextLine(); // data from input 
                System.out.println(sendLine); // print data received 
                this.notifyReceipt(sendLine); // notify MessageListener of input
            }//end if

        }//end while

    }//end run()
    
    /**
     * Closes all streams connected to client
     * @throws IOException - if streams are already closed
     */
    public void close() throws IOException {

        if(connected) {
            System.out.println("Closing...");
            input.close();
            output.close();
            out.close();
            connected = false;
        }//end if

    }//end close

}
