package client;

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
	
	private static int BATTLE_SIZE = 10;
	/**
	 * Constructor that will 
	 * @param size
	 */
	public Grid(int size) {
		gridSize = size;
		grid = new String[gridSize][gridSize];
		defineGrid();
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
			
		}
		//function ends
	}
	
	/**
	 * main function to test grid
	 * @param args
	 */
	public static void main(String args[]) {
		Grid newGrid = new Grid(10);
		newGrid.printGrid();
	}
}
