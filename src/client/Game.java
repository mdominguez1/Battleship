package client;

import java.util.ArrayList;
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
	ArrayList<Player> playerList = new ArrayList<Player>();
	
	/**
	 * Boolean to tell if a game is already in progress.
	 */

	boolean inProgress = false;
	
	/**
	 * The play method checks to see if there is a already a game in progress
	 * and if there are enough players and then starts a game accordingly.	
	 */
	public void play() {
		if(inProgress = false) {
			if(playerList.size() >= 2) {
				System.out.println("The game begins!");

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
	public void join(String username) {
		Player newPlayer = new Player(username);
		if(!playerList.contains(newPlayer)){
		playerList.add(newPlayer);
		System.out.println("!!! " + username + " has joined the game");
	}else{
		System.out.println("Player has already joined the game.");
		
	    }
	}
	
	
	/**
	 * Decides whose turn it is next.
	 */
	public void turn(){
		// Still working on this method.
		for(int i = 0; i < playerList.size(); i++){
			System.out.println(playerList.get(i).getUsername() + " it is your turn");
			
		}
	}
	
	
	
/**
 * Holds all the information to create a new player. Also contains 
 * methods to get and receive appropriate fields.
 * @author Victoria Pruett & Melchor Dominguez
 * @version 11/13/17
 * 
 */
	public class Player{
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
			// Still working on this check.
			for(int i = 0; i < playerList.size(); i++)
				if(!playerList.get(i).username.equals(username)){
					this.username = username;
				} else{
					System.out.println("Please choose a different username. "
						+ "The one you have chosen has already been taken.");
			}	
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
	}
		
}



