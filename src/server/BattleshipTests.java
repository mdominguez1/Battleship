package client;
import java.util.Scanner;

import client.Game;
import client.Game.Player;

public class BattleshipTests {
	
	boolean check = false;
	
	//static Game game;
	public static void main(String[] args){
		 String turnName;
		 String oppPlayer;
		 int row;
		 int column;
		System.out.println("Please enter a username to join the game.");
		Scanner scan = new Scanner(System.in);
		
		Player firstPlayer = new Game().new Player(scan.nextLine());
		Game game = new Game();
		game.join(firstPlayer.getUsername());
		
		System.out.println("Please enter a username to join the game.");
		Player secondPlayer = new Game().new Player(scan.nextLine());
		game.join(secondPlayer.getUsername());
		
		
		
		firstPlayer.getGrid().setBattle();
		secondPlayer.getGrid().setBattle();
		
		firstPlayer.getGrid().printGrid();
		secondPlayer.getGrid().printGrid();
		
		
		game.inProgress = true;
		
		while(game.inProgress == true){
		
		
		turnName = game.turn().getUsername();
		System.out.println("It is " + turnName  + "'s turn!!");
		
		
	
		
	 System.out.println("Who would you like to attack?");
	 
	 oppPlayer = scan.next();
	

	 
	 System.out.println("What row would you like to attack?");
	
	
	   row = scan.nextInt();
	 
	 System.out.println("What column would you like to attack?");
	   column = scan.nextInt();
	  
	   
	 if(!oppPlayer.equals(turnName)){
		
		 
	 game.attack(oppPlayer,row, column);
	 
	 System.out.println(turnName + " just attacked " + oppPlayer + "!!!");
	 
	 game.getPlayer(oppPlayer).getGrid().printGrid();
	 
		 game.setTurnCheck(true);
		 
		
		 
	 
	 }else {
		 
		 System.out.println("The player specified to attack is not in the game or you are trying to attack yourself.");
		 game.setTurnCheck(false);
		 System.out.println("It is still " + turnName + "'s turn.");
			
		 System.out.println("Who would you like to attack?");
		 
		 oppPlayer = scan.next();
		

		 
		 System.out.println("What row would you like to attack?");
		   row = scan.nextInt();
		 
		 System.out.println("What column would you like to attack?");
		   column = scan.nextInt();
		 
		
		}
	
	 
		}
	 
		
	 
	 
	 
	 
		
	scan.close();
	
	
		
		
		
		
		
	}

	
	
	
}