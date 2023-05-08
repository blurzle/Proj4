/**
 * @author Rachel Lavallee
 */


package edu.wcu.cs.cs150.p3.mazerunner;



public class InvalidInitException extends Exception {

	
	// A requirement of serialization
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * Create a new exception with a default message.
	 */
	public InvalidInitException() {
		
		super("Invalid Exception.");
	}
	
	
	/**
	 * Create a new exception.
	 * @param message Error method for the exception.
	 */
	public InvalidInitException(String message) {
		
		super(message);
		
	}
	
}
