/**
* @author Rachel Lavallee
* @author Mary Wang
* @Version Project 4
*/
package edu.wcu.cs.cs150.p3.mazerunner;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import edu.wcu.cs.cs150.p3.adt.LinkedDeque;
public class MazeRunner extends Object implements Cloneable {
	
	//------------------------Fields----------------------------
	
	
	// The Maze to run through
	private Maze maze;
	
	
	//The found Path (solution) through the maze (if any)
	LinkedDeque<Point> path;
	
	
	
	// The locations in the maze already tried to walk through, where VALID is
	// NOT walked through and INVALID is walked through
	private int[][] steps;
	
	
	
	// Indicates a spot has NOT been walked through
	private static final int VALID = 1;
	
	
	
	// Indicates a spot has been walked through
	private static final int INVALID = -1;
	
	
	
	// String to determine if the maze has been attempted
	private static String attemptStatus;
	
	
	
	
	//------------------------Constructor-------------------------
	
	
	/**
	 * Initializes a new MazeRunner based on an input file.
	 * @param filename Name of the maze input file.
	 * @throws InvalidInitException If the maze cannot be constructed.
	 */
	public MazeRunner(String filename) throws InvalidInitException{
		
		
		try {
			
		this.maze = new Maze(filename);
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File Not Found");
			
		}
		
		catch(InvalidInitException iie){
			System.out.println("The maze cannot be constructed.");
			
		}
		
		
		
		this.attemptStatus = "Unattempted";
		
//		this.path = new LinkedDeque<Point>();
//		
//		this.steps = new int [maze.getNumRows()][maze.getNumCols()];
		
		
		this.initSteps();
		

		
	}
	
	
	//--------------------------Methods-----------------------------
	
	
	/**
	 * Sets all the open locations in the map as valid for detecting a path.
	 * Sets up the steps 2D array with all VALID values to show no part of the
	 * maze has been walked on.
	 */
	private final void initSteps() {
		
		int numRow = maze.getNumRows();
		int numCol = maze.getNumCols();
		this.steps = new int[numRow][numCol];
		
		
		
		for(int i = 0; i < maze.getNumRows(); i++) {
			
			for(int j = 0; j < maze.getNumCols(); j++) {
				if(maze.getCellValue(i,j) == MazeCell.OPEN
						|| maze.getCellValue(i, j) == MazeCell.EXIT) {
					
					steps[i][j] = VALID;
				}
				else {
					steps[i][j] = INVALID;
				}
				
			} // end inner for loop
			
		} // end outer for loop
		
	}
	
	
	/**
	 * Gets the attempt status of the maze.
	 * @return A string containing the attempt status.
	 */
	public String getStatus() {
		
		return attemptStatus;
		
	}
	
	
	/**
	 * Try and discover a path from the start location to the exit location.
	 */
	@SuppressWarnings("unchecked")
	public void runMaze() {
		
		// Get start of the maze
		Point start = maze.getStart();
		// Create empty stack
		Stack<Point> stk = new Stack<Point>();
		// add start to stack
		stk.push(start);
		// create a queue
		LinkedDeque<Stack> queue = new LinkedDeque<Stack>();

		// add stack to front of queue
		queue.addFirst(stk);
		List<Point> moves;
		System.out.println(maze.toString());
		// create list of points from valid moves from start
		//List<Point> pts = validMoves(start);
				
		//////////////////////////////////////
		
		while(!maze.isExit(start))	{
			
			stk = queue.removeFirst();
			start =  stk.peek();
			moves = validMoves(start);
			Stack<Point> stkcpyStack = null;
			
			System.out.println(moves);
			for(Point point : moves) {
				stkcpyStack = (Stack<Point>) stk.clone();
				stkcpyStack.push(point);
				queue.addFirst(stkcpyStack);

			}
			
			if(queue.isEmpty()) {
				attemptStatus = "Maze attempted: Unsuccessful.";
				
			}
			if(!queue.isEmpty()) {
				attemptStatus = "Maze attempted: Successful! Woohoo!";
				maze.setPath(stk);
				
			}
			System.out.println(attemptStatus + "\n" + maze.toString());
			


	}
		
					
}//method				

			
		
	public Stack<Point> clone(Stack<Point> stack) {
		Stack<Point> theCopy = new Stack<Point>();
		
			Point pt = null;
			for(Point point : stack) {
				int x = (int)point.getX();
				int y = (int) point.getY();
				pt = new Point(x,y);
				theCopy.push(pt);

			}
		
		return theCopy;
		
	}
		
		
		
	
	
	
	/**
	 * Returns all valid steps (forward and back) that have not been tried.
	 * @param point Current location in the maze
	 * @return Valid steps a runner can take
	 */
	private List<Point> validMoves(Point point){
		
		List<Point> moves = new ArrayList<Point>();
		
		// Gets the row integer
		int row = (int) point.getX();
				
		// Gets the column integer
		int col = (int) point.getY();
		
		if(row > 0 &&  checkSpot(row-1, col)) {
			moves.add(new Point(row-1, col));
		}
		if( col < maze.getNumCols()-1 && checkSpot(row, col+1)) {
			moves.add(new Point(row, col+1));
		}
		if (row < maze.getNumRows()-1 && checkSpot(row+1, col)) {
			moves.add(new Point(row+1, col));
		}
		if (col > 0  && checkSpot(row, col-1)) {
			moves.add(new Point(row, col-1));
		}
		

		
		return moves;
		
	}
	
	
	/**
	 * A helper method to determine if a location counts as valid. It checks
	 * if the row and column are within the bounds of the maze. It also checks if
	 * the location defines an open spot or an exit, and that the location
	 * has not been walked on previously.
	 * @param tRow A row index for a possible valid location.
	 * @param tCol A column index for a possible valid location.
	 * @return True if the location exists within the maze AND counts as valid
	 * as part of a potential path.
	 */
	private boolean checkSpot(int tRow, int tCol) {
		
		boolean result = false;
		
		if(tRow >= 0 && tCol >= 0

				&& maze.getNumRows() > tRow
				&& maze.getNumCols() >tCol
				&& maze.getCellValue(tRow, tCol) == MazeCell.OPEN
				
				|| (maze.getCellValue(tRow, tCol) == MazeCell.EXIT)
				&& this.steps[tRow][tCol] == VALID){
			
			
			this.steps[tRow][tCol] = INVALID;
		
			result = true;

		}
		
		
		return result;
		
	}
	
	
	private LinkedDeque<Point> copy(LinkedDeque<Point> point){
		
		Iterator<Point> iter = point.iterator();
		
		LinkedDeque<Point> copy = new LinkedDeque<Point>();
		
		while(iter.hasNext()) {
			
			
			 Point poin = iter.next();
			
			 Point newPoin = new Point(poin.x, poin.y);
			
			 copy.addLast(newPoin);
//			
//			 LinkedDeque<Point>current = point;
			
		}
		
		return copy;
		
	}
	
	
	
	/**
	 * String method that overrides the toString method in class Object.
	 * @return toString method in class Object.
	 */
	public String toString() {
		return super.toString();
	}
	
	
	
	
	
	
	
	
}
