/**
 * @author Rachel Lavallee
 * @version Project 4 : Maze Runner : Maze
 */



package edu.wcu.cs.cs150.p3.mazerunner;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import edu.wcu.cs.cs150.p3.adt.LinkedDeque;

public class Maze {

	
	//--------------------------Fields--------------------------------
	
	// The maze, made up of an enum, is in form of y (rows) , x (columns)
	private static MazeCell[][] grid;
	
	// Column number of the starting location
	private int startCol;
	
	// Row number of the starting location
	private int startRow;
	
	
	
	
	//-----------------------Constructors-----------------------------
	
	
	
	/**
	 * Create a new maze based upon the file defaultMaze.txt.
	 * @throws FileNotFoundException If the file defaultMaze.txt cannot be 
	 * loaded.
	 * @throws InvalidInitException If the first and second item in the file 
	 * is not an integer.
	 */
	public Maze() throws FileNotFoundException, InvalidInitException{
		Maze maze= new Maze("default.txt");
	}
	
	
	
	/**
	 * Create a new maze based on a file by getting each character of each line,
	 *  and storing it in a 2D array.
	 * @param fileName The filename containing information about the maze to 
	 * create.
	 * @throws FileNotFoundException If the first and second item in the file 
	 * is not an integer.
	 * @throws InvalidInitException The scanner will throw this error if the 
	 * specified file does not exist.
	 */
	public Maze(String fileName) throws FileNotFoundException, InvalidInitException{
		
		
		// Create new scanney
		Scanner scanny = null;
		
		
		File file = new File(fileName);
		
		try {
			scanny = new Scanner(file);
			int row = scanny.nextInt();
			int col = scanny.nextInt();
			String line = scanny.nextLine();
			grid = new MazeCell[row][col];
			for(int i = 0; i< row; i++) {
				line = scanny.nextLine();
				for (int column = 0; column < line.length(); column++) {
					grid[i][column] = getSymbol(line.charAt(column));
				}
			
			}
		}	

		catch (FileNotFoundException fnfe){
			
			System.out.println("No file found.");
			
		}
		
		
	}
	
	
	
	/**
	 * Create a maze from an already existing collection of MazeCells.
	 * @param maze
	 * @throws InvalidInitException 
	 * @throws FileNotFoundException 
	 */
	public Maze(MazeCell[][] maze) throws FileNotFoundException, InvalidInitException {
		
		
		this();
		
		int row = maze.length;
		int col = maze[0].length;
		
		grid = new MazeCell[row][col];

		Point start = getStart();
		
		this.startRow = (int) start.getX();
		this.startCol = (int) start.getY();
		
	}
	
	
	
	//-------------------------------Methods----------------------------------
	
	
	
	
	/**
	 * Returns the starting location of the maze.
	 * @return point The starting location in the maze.
	 */
	public Point getStart() {
		
		
		
		
		for(int i = 0; i < getNumRows(); i++) {
			
			for(int j = 0; j < getNumCols(); j++) {
				
				if(grid[i][j].equals(MazeCell.ENTER)){
					
					startRow = i;
					startCol = j;
					
				}
				
			}
			
		}
		
		// Creates a point with the start column and start row
		Point point = new Point(startRow, startCol);

		// Returns the start point.
		return point;
		
	}
	
	
	
	/**
	 * Simple method that maps a character to a MazeCell enumerated value.
	 * A W is a wall WALL, a space is an open area OPEN, an X is an exit EXIT, 
	 * and an E is an entry ENTER.
	 * @param ch The character to translate into a symbol.
	 * @return An enumeration representing a valid symbol.
	 */
	private MazeCell getSymbol(char ch) {
		
		// Returns the value of the MazeCell that has the given character.
		return MazeCell.valueGettyImages(Character.toString(ch));
		
	}
	
	
	
	
	/**
	 * Finds the symbol at the specified location in the maze.
	 * @param row Row in the maze we wish to check
	 * @param column Column in the maze we wish to check
	 * @return The symbol in that location, or an invalid symbol if row or 
	 * column was an invalid index into the maze.
	 */
	public MazeCell getCellValue(int row, int column) {
		
		// Returns the symbol in the given location of the maze.
		return grid[row][column];
		
	}
	
	
	
	/**
	 * Sets a location in the maze to a specified value.
	 * @param row Row in the maze we wish to set.
	 * @param column Column in the maze we wish to set.
	 * @param value Symbol we wish to place at the cell.
	 */
	public void setCellValue(int row, int column, MazeCell value) {
		
		// Sets the given location in the maze to the value that is given.
		grid[row][column] = value;
	
	}
	
	
	
	/**
	 * Gets the total number of rows in the maze.
	 * @return The number of rows in the maze.
	 */
	public int getNumRows() {
		
		// Returns the number of rows
		return grid.length;
		
	}
	
	
	
	
	/**
	 * Gets the total number of columns in the maze.
	 * @return The number of columns in the maze.
	 */
	public int getNumCols() {

		// Returns the number of columns
		return grid[0].length;
	}
	
	
	
	
	
	/**
	 * Set mark a discovered path within the maze.
	 * @param path A set of Points that indicated locations for a path from 
	 * entry to exit.
	 */
	public void setPath(Stack<Point> path) {

		for(Point pointy : path) {
			
			int x = (int) pointy.getX();
			int y = (int) pointy.getY();
			
			MazeCell value = getCellValue(x,y);
			if(value != MazeCell.ENTER
					&& value != MazeCell.EXIT) {
			
			setCellValue(x, y, MazeCell.PERSON);
			}
			
		}
		
		
	}
	
	
	
	/**
	 * Determines if the Point value provided maps to an exit location.
	 * @param location The location to check to see if it is an exit.
	 * @return result True if the Point maps to an exit location, false 
	 * otherwise.
	 */
	public boolean isExit(Point location) {
		
		// Initializes boolean result as false.
		boolean result = false;
		
		// Gets the row integer
		int row = (int) location.getX();
		
		// Gets the column integer
		int col = (int) location.getY();
		
		// Creates a temporary MazeCell with the location's row and column
		MazeCell temp = getCellValue(col, row);
		
		
		// Checks if that MazeCell contains X
		if(temp == MazeCell.EXIT) {
			
			// Result is changed to true.
			result = true;
			
		}
		
		
		return result;

	}
	
	
	
	
	/**
	 * Creates a String representing the current state of the maze.
	 * @return The current state of the maze.
	 */
	public String toString() {
		
		String result = "";
		int inty = getNumCols();
		for(int i = 0; i < getNumRows(); i++ ) {
			
			for(int j = 0; j < getNumCols(); j++) {
				
				switch(getCellValue(i,j).toString()) {
				case "ENTER":
					result += "E";
					break;
				case "EXIT":
					result += "X";
					break;
				case "OPEN":
					result += " ";
					break;
				case "PERSON":
					result += "o";
					break;
				case "WALL":
					result += "W";
					// /u2588
					break;
				}
				
				if(j == getNumCols()-1) {
					
					result += '\n';
					
				}
				
			}
			
		}
				
		
		
		return result;
		
		
		
		// String result = this.toString();
		
	}
	
	
	
	
	
	
}
