package server;

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
    boolean lost = false;
	
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
	public String printGrid() {
		String numLine = " ";
		String returnLine;
		
		//print number line
		for(int i = 0; i < gridSize; i++) {
			numLine += "  " + Integer.toString(i) + " ";
		}
		//System.out.println(numLine);
		returnLine = numLine + "\n";
		
		String gridLines = " +";
		//print lines above grid
		for(int i = 0; i < gridSize; i++) {
			gridLines += "---+";
		}//end for
		//System.out.println(gridLines);
		returnLine += gridLines + "\n";
		
		//prints the current grid
		for(int i = 0; i < gridSize; i++) {
			//System.out.print(Integer.toString(i) + "|");
			returnLine += Integer.toString(i) + "|";
			for(int j = 0; j < gridSize; j++) {
				//System.out.println(" " + grid[i][j] + " |");
				returnLine += " " + grid[i][j] + " |";
			}//end for
			//System.out.println("");
			returnLine += "\n";
			//System.out.println(gridLines);
			returnLine += gridLines + "\n";
		}//end for
		return returnLine;
	}//end printGrid()
	
	/**
	 * Prints a grid for the opposing players
	 */
	public String printOppGrid() {
	    String numLine = " ";
	    String returnLine;
	    
	  //print number line
        for(int i = 0; i < gridSize; i++) {
            numLine += "  " + Integer.toString(i) + " ";
        }
        //System.out.println(numLine);
        returnLine = numLine + "\n";
        
        
        String gridLines = " +";
        //print lines above grid
        for(int i = 0; i < gridSize; i++) {
            gridLines += "---+";
        }//end for
        //System.out.println(gridLines);
        returnLine += gridLines +"\n";
        
        //prints the current grid, except for the ship markers
        
      //prints the current grid
        for(int i = 0; i < gridSize; i++) {
            //System.out.print(Integer.toString(i) + "|");
            returnLine += Integer.toString(i) + "|";
            for(int j = 0; j < gridSize; j++) {
                if(grid[i][j].equals(" ") || grid[i][j].equals("@") || grid[i][j].equals("X"))
                    //System.out.print(" " + grid[i][j] + " |");
                    returnLine += " " + grid[i][j] + " |";
                else 
                    //System.out.print(" " + " " + " |");
                    returnLine += " " + " " + " |";
            }//end for
            //System.out.println("");
            //System.out.println(gridLines);
            returnLine += "\n" + gridLines + "\n";
        }//end for
        return returnLine;
	}
	
	/**
	 * public class to hit the 
	 * r row and the c column
	 */
	public void hit(int r, int c) {
		
			if(grid[r][c].equals("R") || grid[r][c].equals("T") || grid[r][c].equals("S") || grid[r][c].equals("W")) {
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



    public boolean checkGrid(){
       boolean check = false;
        for(int i  = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(!grid[i][j].equals("R") || 
                    !grid[i][j].equals("T") || 
                    !grid[i][j].equals("S") || 
                    !grid[i][j].equals("W")){
                    lost = true;

                    check = true;
                }
            }
        }
        return check;
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
		if(vert) {//places in vertical direction
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
		}else { // places in horizontal direction
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
		
		//removes ship markers if unsuccessful 
		if(!b) {
		    for(int i = 0; i < BATTLE_SIZE-1; i++) {
		        for(int j = 0; j < BATTLE_SIZE-1; j++) {
		            if(grid[i][j].equals(c)) {
		                grid[i][j] = " ";
		            }//end if
		        }//end for
		    }//end for
		}//end if
		
		return b;
	}
}
