package client;

import java.util.ArrayList;

import java.util.List;

import java.util.Collections;
import java.util.Collection;



/**
 * Holds all the methods to perform a game of BattleShip.
 * @author Victoria Pruett & Melchor Dominguez
 * @version 11/13/17
 *
 */

public class Game {

	/**
	 * Arraylist that holds all of the players.
	 */
	List<Player> playerList;
	/**
	 * Boolean to tell if a game is already in progress.
	 */
	boolean inProgress;

	/**
	 * The play method checks to see if there is a already a game in progress
	 * and if there are enough players and then starts a game accordingly.	
	 */

	public Game(){
		
		playerList = new ArrayList<Player>();
		inProgress = false;
		
	}


	public void play() {
		if(inProgress = false) {
			if(playerList.size() >= 2) {
				System.out.println("The game begins!");
				inProgress = true;

			}else {
				System.out.println("Not enough players to play the game");
			}
		}else {
			System.out.println("Game already in progess.");


		}
	}
	/**
	 * Adds a player to the game if the username has not already been used and if 
	 * there is not already a game in progress.
	 * @param username The username of the player that wants to join the game.
	 */

	public void join(Player player) {
		
		if(!playerList.contains(player)){
			playerList.add(player);
			System.out.println("!!! " + player.getUsername() + " " + " has joined the game");
		}else{
			System.out.println("That user has already joined the game.");

		}
	}
/*public void Print(){
	for(int i = 0; i < playerList.size(); i++){
		System.out.println(playerList.get(i).username + " ");
	}
	
	
}
*/
	
	/**
	 * Decides whose turn it is next.
	 */
	public void turn(){
		
		//for(int i = 0; i < playerList.size(); i++){
			System.out.println("It is " + playerList.get(0).username + "'s" + " turn!");

		//}

		Collections.rotate(playerList, 1);


	}
	
	public List<Player> getList(){
		return this.playerList;
	}

	public Player getPlayer(String username){
		Player retPlayer = new Player(null);
		for(int i = 0; i < playerList.size(); i++){
			if(playerList.get(i).username.equals(username)){
				retPlayer = playerList.get(i);
			}
		}
		return retPlayer;
	}



	/**
	 * Holds all the information to create a new player. Also contains 
	 * methods to get and receive appropriate fields.
	 * @author Victoria Pruett & Melchor Dominguez
	 * @version 11/13/17
	 * 
	 */
	public  class Player{
		

		/**
		 * The username of the player.
		 */
		private String username;

		/**
		 * The players grid or game board.
		 */
		private Grid playerGrid;

		/**
		 * Constructor to give a player a username.
		 * @param username Username of the player.
		 */
		public Player(String username) {
			
					this.username = username;
				    playerGrid = new Grid(10);
				    
			}

		

		/**
		 * Setter method to set or change the players username.
		 * @param username Username of the player.
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * Getter method to get the players username.
		 * @return The players username.
		 */
		public String getUsername() {
			return this.username;
		}
		
		public Grid getGrid(){
			return this.playerGrid;
		}
		
		
	}
	public static void main(String args[]) {
		
	Player play = new Game().new Player("player1");
	Player newPlayer = new Game().new Player("player2");
	Player thirdPlayer = new Game().new Player("player3");
	
	Game game = new Game();
	game.join(play);
	game.join(newPlayer);
	game.join(thirdPlayer);
	game.join(thirdPlayer);
	//game.Print();
	game.turn();
	game.turn();
	game.turn();
	game.turn();
   game.play();
 play.playerGrid.setBattle();
 newPlayer.playerGrid.setBattle();
 
play.playerGrid.printGrid();
//play.playerGrid.hit(0, 5);
//play.playerGrid.hit(0, 6);
//play.playerGrid.hit(3, 4);
play.playerGrid.printGrid();
   
   
   
   
		
	
	}

}



