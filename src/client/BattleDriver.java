package client;
import client.BattleClient;

import java.io.IOException;
import java.net.InetAddress;

public class BattleDriver {
   
   public static void main(String[] args) throws IOException{
      String defaultHost = "localhost";
       int defaultPort  = 2323;
       try{
           if(args.length == 2){
       BattleClient BC = new BattleClient(args[0],Integer.parseInt(args[1]),args[2]);
       BC.connect();
       BC.send(args[2]);
       
           } else if(args.length == 1){
               
               
               
               
               
       }else if(args.length > 2){
               System.out.println("Usage: <Hostname> <Port Number> <User Nickname>");
           }
   }catch(IOException io){
       
       System.err.println(io.getMessage());
   }
       
       
   }
}
