package client;

import java.util.Random;

/**
 * Class that will make a grid by specified dimensions
 * @author Melchor Dominguez & Victoria Pruett
 * 
 *
 */
public class Grid {

	/** The n size of the n x n grid */
	private int gridSize;
	
	/** The actual grid for the grid class*/
	private String[][] grid;
	
	Random rand;
	
	private static int BATTLE_SIZE = 10;
	/**
	 * Constructor that will 
	 * @param size
	 */
	public Grid(int size) {
		gridSize = size;
		grid = new String[gridSize][gridSize];
		defineGrid();
		rand = new Random();
	}//end constructor
	
	/**
	 * Defines a blank grid 
	 */
	public void defineGrid() {
		//Puts a blank space in every element of the array
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				grid[i][j] = " ";
			}//end for 
		}//end for
	}//end defineGrid()
	
	/**
	 * prints the current grid to screen
	 */
	public void printGrid() {
		String numLine = " ";
		
		//print number line
		for(int i = 0; i < gridSize; i++) {
			numLine += "  " + Integer.toString(i) + " ";
		}
		System.out.println(numLine);
		
		String gridLines = " +";
		//print lines above grid
		for(int i = 0; i < gridSize; i++) {
			gridLines += "---+";
		}//end for
		System.out.println(gridLines);
		
		//prints the current grid
		for(int i = 0; i < gridSize; i++) {
			System.out.print(Integer.toString(i) + "|");
			for(int j = 0; j < gridSize; j++) {
				System.out.print(" " + grid[i][j] + " |");
			}//end for
			System.out.println("");
			System.out.println(gridLines);
		}//end for
	}//end printGrid()
	
	/**
	 * public class to hit the 
	 * r row and the c column
	 */
	public void hit(int r, int c) {
			if(grid[r][c].equals("R") || grid[r][c].equals("C") || grid[r][c].equals("D") || grid[r][c].equals("B")) {
				grid[r][c] = "@";
			} else if(grid[r][c].equals(" ")) {
				grid[r][c] = "X";
			}				
	}
	
	/**
	 * Sets the grid up to be a playable
	 * grid of battleship
	 * 
	 */
	public void setBattle() {
		if(gridSize == BATTLE_SIZE) {
			//function will continue and set up battle
			boolean good = false;
			while(good != true) {
			    good = addShip(5, "S");
			}//end while
			good = false;
			while(good != true) {
			    good = addShip(4, "R");
			}
			good = false;
			while(good != true) {
			    good = addShip(3, "T");
			}
			good = false;
			while( good != true) {
			    good = addShip(3, "L");
			}
			good = false;
			while(good != true) {
			    good = addShip(2, "W");
			}
		}
		//function ends
	}
	
	/**
	 * 
	 * @param size
	 * @param c
	 * @return
	 */
	public boolean addShip(int size, String c) {
	    //sets return value as true until error found
		boolean b = true;
		
		//generate random placement for ship
		int row = rand.nextInt(9);
		int col = rand.nextInt(9);
		
		//set boolean to go in the vertical direction
		boolean vert = rand.nextBoolean();
		
		//places the next ship 
		if(vert) {
		    if((col + size) > 9) {
		        col -= size;
		    }//end if
		    for(int i = 0; i < size; i++) {
		        if(grid[row][i].equals(" ")) {
		            grid[row][i] = c;
		        }else {
		            b = false;
		        }//end if
		    }//end for
		    
		}else {
		   if((row + size) > 9) {
		       row -= size;
		   }//end if
		   for(int i = 0; i < size; i++) {
               if(grid[i][col].equals(" ")) {
                   grid[i][col] = c;
               }else {
                   b = false;
               }//end if
           }//end for
		}//end addShip
		
		if(!b) {
		    for(int i = 0; i < BATTLE_SIZE-1; i++) {
		        for(int j = 0; j < BATTLE_SIZE-1; j++) {
		            if(grid[i][j].equals(c)) {
		                grid[i][j].equals(" ");
		            }//end if
		        }//end for
		    }//end for
		}//end if
		
		return b;
	}
	
	/**
	 * main function to test grid
	 * @param args
	 */
	public static void main(String args[]) {
		Grid newGrid = new Grid(10);
		newGrid.printGrid();
		newGrid.setBattle();
		newGrid.printGrid();
	}
}
