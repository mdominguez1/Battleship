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

    /**
     * run BattleServer
     * @param args - command line arguments to run the server 
     *               where:
     *               arg1 = port number where the server will connect
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        BattleServer server;
        
        if(args.length > 1) {// if there is more than 1 command line protocol
            System.out.println("Too many command line arguments given");
            System.out.println("Usage > BattleShipDriver [<port>]");
            System.exit(0);
        }else if(args.length == 1) {
            server = new BattleServer(Integer.parseInt(args[0]));
        }else {
            server = new BattleServer();
        }

    }

}
