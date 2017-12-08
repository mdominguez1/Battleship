package client;
import client.BattleClient;
import java.util.Scanner;
import java.io.IOException;
import java.net.InetAddress;

public class BattleDriver {
      public static void main(String[] args) throws IOException{
      String defaultHost = "localhost";
       Scanner scanner = new Scanner(System.in);
     
       int defaultPort  = 2323;
       String command = "";
       try{
           if(args.length == 3){
      BattleClient  BC = new BattleClient(args[0],Integer.parseInt(args[1]),args[2]);
       BC.connect();
       BC.send("/join " + args[2]);
       while(scanner.hasNext()){
           command = scanner.nextLine();
           BC.send(command);
      }
             
       }else if(args.length > 2){
               System.out.println("Usage: <Hostname> <Port Number> <User Nickname>");
           }

       

   }catch(IOException io){
       
       System.err.println(io.getMessage());
   }
       
       
   }
}
