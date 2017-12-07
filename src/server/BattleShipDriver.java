/**
 * 
 */
package server;

import java.io.IOException;

/**
 * Driver to run the  Server for BattleShip
 * @author Melchor Dominguez & Tori Pruett
 * @version 12/9/2017
 */
public class BattleShipDriver {

    /** Default port for the connection */
    private static final int DEFAULT_PORT = 2323;

    /**
     * run BattleServer with given command line arguments
     * @param args - command line arguments to run the server 
     *               where:
     *               args[0] - port number where the server will connect
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        BattleServer server;
        
        if(args.length == 0){
            server = new BattleServer(DEFAULT_PORT);
        }else if(args.length == 1){
            server = new BattleServer(Integer.parseInt(args[0]);
        }else{
            System.out.println(" Error, must use correct format. Use layout > " +
            "BattleShipDriver [<port>] " );
            System.exit(0);
        }//end if-else
        
        // listen for connection 
        server.listen();

    }//end main

}//end BattleShipDriver
