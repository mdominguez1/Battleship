package client;

import java.util.ArrayList;

public class Game {
	ArrayList<Player> playerList = new ArrayList<Player>();
	boolean inProgress = false;
	
		
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
	
	public void join(String username) {
		Player newPlayer = new Player(username);
		playerList.add(newPlayer);
		System.out.println("!!! " + username + " has joined the game");
		
	}

	public class Player{
		private String username;
		
		private Grid playerGrid;
		
		public Player(String username) {
			this.username = username;
			
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUsername() {
			return this.username;
		}
	}
		
}



