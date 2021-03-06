package server;

import java.util.ArrayList;

import java.util.List;

import java.util.Collections;



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
    ArrayList<Player> playerList;
    /**
     * Boolean to tell if a game is already in progress.
     */
    boolean inProgress;

    /** Boolean value to tell whether or not someone has entered a valid command before rotating to the next person.  */
    boolean turnCheck = true;

    /**
     * The play method checks to see if there is a already a game in progress
     * and if there are enough players and then starts a game accordingly.	
     */

    public Game(){
        playerList = new ArrayList<Player>();
        inProgress = false;
    }//end Game()


    public String play() {
        String returnLine;
        if(inProgress == false) {
            if(playerList.size() >= 2) {
                System.out.println("The game begins!");
                returnLine = "The game begins!";
                inProgress = true;
                this.turn();
            }else {
                System.out.println("Not enough players to play the game");
                returnLine = "Not enough players to play the game";
            }
        }else {
            System.out.println("Game already in progress.");
            returnLine = "Game already in progress.";
        }
        return returnLine;
    }
    /**
     * Adds a player to the game if the username has not already been used and if 
     * there is not already a game in progress.
     * @param username The username of the player that wants to join the game.
     */

    public String join(String player) {
        String returnLine;
        if(!this.inList(player)) {
            Player newPlayer = new Player(player);
            playerList.add(newPlayer);
            System.out.println("!!! " + player + " has joined");
            returnLine = "!!! " + player + " has joined";
        }else {
            System.out.print("There is already a player with that name ");
            returnLine = "There is already a player with that name";
        }
        return returnLine;
    }//end join()


    /**
     * Decides whose turn it is next.
     */
    public Player turn(){
        if(turnCheck == true){
            //System.out.println("It is " + playerList.get(0).username + "'s" + " turn!");
            Collections.rotate(playerList, 1);
            return playerList.get(0);	
        }else{
            //System.out.println(playerList.get(playerList.size()-1).username + " please try again.");
            System.out.println("It is still " + playerList.get(playerList.size()-1).username + "'s" + " turn.");
            return playerList.get(playerList.size()-1);	
        }//end if-else
    }//end turn()

    /**
     * Hits the player at the specified 
     * @param player - player to hit
     * @param row - row to hit
     * @param col - col to hit
     */
    public boolean attack(String player, int row, int col){
        boolean answer = false;
        for(Player cur: playerList){
            if(cur.getUsername().equals(player)){
                cur.getGrid().hit(row, col);

                if(cur.playerGrid.checkGrid()){
                    Winner(cur);
                }
                this.turn();
                answer =  true;
            }//end if

        }//end for each 
        return answer;
    }//end attack


    /**
     * return the turn check
     * @return - turn check
     */
    public boolean getTurnCheck(){
        return this.turnCheck;
    }//end getTurnCheck()

    /**
     * Change the turnCheck boolean
     * @param it - new boolean 
     */
    public void setTurnCheck(boolean it){
        this.turnCheck  = it;
    }//end setTurnCheck()

    /**
     * Return the list of the players
     * @return - playerList
     */
    public List<Player> getList(){
        return this.playerList;
    }//end getList()

    /**
     * Return player whose name matches
     *@return - a player whose name matches username
     */
    public Player getPlayer(String username){
        Player retPlayer = null;
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).username.equals(username)){
                retPlayer = playerList.get(i);
            }//end if
        }//end for
        return retPlayer;
    }//end getPlayer()

    /**
     * returns a boolean if there is a player whose name matches the list
     * @param username - username to search for
     * @return - true, if there is a username who matches the input 
     */
    public boolean inList(String username){
        boolean check  =  false;
        for(Player curr: playerList) {
            if(curr.getUsername().equals(username))
                check = true;
        }
        return check;
    }//end inList()


    /**
     * Checks to see if there's a winnera
     * @param player - player to remove 
     */
    public void Winner(Player player){
        
        //checks to see if the player lost
        if(player.playerGrid.lost){//if the player lost, remove 
            System.out.println(player.getUsername() + " has lost!");
            playerList.remove(player); //removes player from the list 
            player.lost = true;
            if(playerList.size() == 1){ // if the size of the list is 1, there's a winner
                System.out.println(playerList.get(0).getUsername() + " has won!");

            }//end if 
        }//end if
    }//end Winer()


    /**
     * show the grid of a player
     * @param username - username to search for
     */
    public String show(String username) {
        String returnLine;
        if(inList(username)) {
            Player toShow = this.getPlayer(username);
            if(!playerList.get(0).getUsername().equals(username))
                returnLine = toShow.playerGrid.printGrid();
            else
                returnLine = toShow.playerGrid.printOppGrid();
        }else {
            //System.out.print("There is no player with that username");
            returnLine = "There is no player with that username";
        }//end if
        return returnLine;
    }//end show()

    /**
     * quits whoevers turn it is
     * @return
     */
    public String quit() {
        return "!!! " + this.turn().username + " surrendered";
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
         * Boolean value to determine if the player lost.
         */
        private boolean lost = false;


        /**
         * Constructor to give a player a username.
         * @param username Username of the player.
         */
        public Player(String username) {
            this.username = username;
            playerGrid = new Grid(10);
            playerGrid.setBattle();
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

}



