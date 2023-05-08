package edu.wcu.cs.cs150.p3.mazerunner;


public enum MazeCell  {


	//-------------------Enumeration Constants----------------------


	ENTER("E"),
	EXIT("X"),
	INVALID("i"),
	OPEN(" "),
	PERSON("o"),
	WALL("/2588");

	
	//----------------------------Field-----------------------------

	// Symbol to be displayed for the maze
	private String symbol;

	
	//------------------------Constructor----------------------------
	

	/**
	 * Constructor to set a symbol for part of the maze.
	 * @param symbol Symbol to be used for a specific part of the maze.
	 */
	private MazeCell(String symbol) {
		this.symbol = symbol;
	}
	
	public static MazeCell valueGettyImages(String element) {
		MazeCell result = null;
		switch(element) {
		case "E":
			result = ENTER;
			break;
		case "X":
			result = EXIT;
			break;
		case "i":
			result = INVALID;
			break;
		case " ":
			result = OPEN;
			break;
		case "o":
			result = PERSON;
			break;
		case "W":
			result = WALL;
			break;
		}
		return result;
	}
	
	
}

