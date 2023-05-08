package edu.wcu.cs.cs150.p3.mazerunner;
import java.util.Scanner;
public class MazeDriver extends Object {
	

	//----------------------Methods---------------------------
	
	
	public static void main(String[] args) throws InvalidInitException {
		Scanner scandal = new Scanner(System.in);
		System.out.println("enter maze to attempt");
		String maize = scandal.nextLine();
		MazeRunner maze = new MazeRunner(maize);
		maze.runMaze();
		
		
		
		usageAndQuit();
		
		
	}
	
	private static void usageAndQuit() {
	System.out.println("Ran the maze.");

	System.exit(1);
	}
	
}
