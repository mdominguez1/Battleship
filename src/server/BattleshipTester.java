package server;

import java.util.Scanner;

import server.Game;
import server.Game.Player;

public class BattleshipTester {
	
	boolean check = false;
	
	public static void main(String[] args){
		System.out.println("Please enter a username to join the game.");
		Scanner scan = new Scanner(System.in);
		
		Player firstPlayer = new Game().new Player(scan.nextLine());
		Game game = new Game();
		game.join(firstPlayer);
		
		System.out.println("Please enter a username to join the game.");
		Player secondPlayer = new Game().new Player(scan.nextLine());
		game.join(secondPlayer);
		
		
		
		firstPlayer.getGrid().setBattle();
		secondPlayer.getGrid().setBattle();
		
		firstPlayer.getGrid().printGrid();
		secondPlayer.getGrid().printGrid();

		game.turn();
		
	
		
	 System.out.println("Who would you like to attack?");
	String oppPlayer = scan.next();

	 
	 System.out.println("What row would you like to attack?");
	 int row = scan.nextInt();
	 
	 System.out.println("What column would you like to attack?");
	 int column = scan.nextInt();
	 
	 game.getPlayer(oppPlayer).getGrid().hit(row, column);
	 
	 
	 game.getPlayer(oppPlayer).getGrid().printGrid();
	 
		System.out.println(firstPlayer.getUsername() + " just attacked " + oppPlayer + "!!!");
	 
	 
	 
	 
	 
	 game.turn();
	 
	 
	 System.out.println("Who would you like to attack?");
	 String secU = scan.next();
	 
	
	 
	 
	 System.out.println("What row would you like to attack?");
	 int row1 = scan.nextInt();
	 
	 System.out.println("What column would you like to attack?");
	 int column1 = scan.nextInt();
	 
	 game.getPlayer(secU).getGrid().hit(row1, column1);
	 
	 game.getPlayer(secU).getGrid().printGrid();
	 
	 System.out.println(secondPlayer.getUsername() + " just attacked " + secU + "!!!");
		
		
		
	scan.close();
	
	
		
		
		
		
		
	}

}
