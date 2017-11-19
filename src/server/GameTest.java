package server;

import java.util.Scanner;

public class GameTest {

    static String line = "";
    static Game game = new Game();
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
     
        System.out.println("Begin test");
        while(!line.equals("Q")) {
            String parseline = input.nextLine();
            parse(parseline);
            System.out.println(line);
        }
        input.close();
    }
    
    public static void parse(String parseLine) {
        String[] output = parseLine.split("\\s+");
        
        if(output[0].startsWith("/")){
            output[0] = output[0].replace("/", "");
            if(output[0].equals("join")) {
                game.join(output[1]);
            }else if(output[0].equals("play")) {
                game.play();
            }else if(output[0].equals("show")) {
                game.show(output[1]);
            }else if(output[0].equals("hit")) {
                String[] numbers = output[2].split(",");
                game.attack(output[1], Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
            }
        }else if(output[0].equals("Q")){
            line = "Q";
        }else {
            line = "not a valid command";
        }//end if-else
    }//end parse()
}
