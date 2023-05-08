/**
 * @author Rachel Lavallee
 * @version Project 4
 */

package edu.wcu.cs.cs150.p3.adt;




public interface Dequeue<E> {

	
	
	
	/**
	 * Appends the specified value on the "right end" of this dequeue. Newly 
	 * appended elements become the last element in this dequeue and all 
	 * previous elements in the dequeue remain unchanged.
	 * @param value The value to add to the "right end" of this dequeue.
	 */
	public void addLast(E value);
	
	
	
	/**
	 * Prepends the specified value on the "left end" of this dequeue. Newly 
	 * prepended elements become the first element in this dequeue and all 
	 * previous elements in the dequeue remain unchanged.
	 * @param value The value to add to the "left end" of the dequeue.
	 */
	public void addFirst(E value);
	
	
	
	/**
	 * Removes the first element from this dequeue.
	 * @return The item at the front of the dequeue.
	 * @throws IllegalStateException if this dequeue is empty.
	 */
	public E removeFirst();
	
	
	
	/**
	 * Removes the last element from this dequeue.
	 * @return E The last item in the dequeue.
	 * @throws IllefalStateException if this dequeue is empty.
	 */
	public E removeLast();
	
	
	
	/**
	 * Returns a copy of the first element in this dequeue.
	 * @return The first element in this dequeue.
	 * @throws IllegalStateException if the dequeue is empty.
	 */
	public E peekFirst();
	
	
	
	/**
	 * If the dequeue is empty this function returns true.
	 * @return True if empty, false if not.
	 */
	public boolean isEmpty();
	
	
	
	
	
}
