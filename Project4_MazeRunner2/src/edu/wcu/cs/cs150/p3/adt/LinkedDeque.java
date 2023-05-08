/**
 * @author Rachel Lavallee
 * @author Mary Wang
 * Project 4
 */

package edu.wcu.cs.cs150.p3.adt;

import java.util.Iterator;




public class LinkedDeque<E> extends Object implements Dequeue<E>, Iterable<E> {

	
	//-----------------------------Fields------------------------------------
	
	
	// A reference to the first node in the linked list
	private LinkedDeque<E>.Node<E> head;
	
	
	private LinkedDeque<E>.Node<E> tail;
	
	
	// The number of values in this deque.
	private int size;
	
	
	
	//---------------------------Constructors--------------------------------
	
	
	/**
	 * Initializes a new empty deque.
	 */
	public LinkedDeque() {

		this.head = null;
		this.tail = null;
		this.size = 0;
		
	}
	
	/**
	 * Initliazes a new deque by using the items from the other deque.
	 * @param other Another LinkedDeque from with the values should be copied.
	 */
	public LinkedDeque(LinkedDeque<E> other) {
		
		this();
		
		Iterator<E> iter = new DequeIterator();
		
		for(int i = 0; i < other.size; i++) {
			
			addFirst(iter.next());
			
		}
		
	}
	
	
	
	//------------------------------Methods---------------------------------
	
	
	
	/**
	 * Returns true if this dequeue is empty.
	 * @return result True if empty, false if not.
	 */
	public boolean isEmpty() {
		
		boolean result = false;
		
		// checks if dequeue is empty
		if(size == 0) {
			
			result = true;
		}
		
		return result;
		
	}
	
	
	/**
	 * Appends the specified value on the "right end" of this deque.
	 * @param value The value to add to the "right end" of this deque.
	 */
	public void addLast(E value) {
		
		Node<E> lastValue = new Node<E>(value);
		
		if(isEmpty()) {
			
			head = lastValue;
		}
		
		else {
			
			tail.next = lastValue;

		}
			
		tail = lastValue;
		
		size++;
		
	}
	
	
	/**
	 * Prepends the specified value on the "left end" of this dequeue. Newly 
	 * prepended elements become the first element in this dequeue and all 
	 * previous elements in the dequeue remain unchanged.
	 * @param value The value to add to the "left end" of the dequeue.
	 */
	public void addFirst(E value) {
		
		Node<E> temp = head;
		Node<E> newHead = new Node<E>(value);
		
		head = newHead;
		head.next = temp;
		
		size++;
		if (size == 1) {
			tail = newHead;
		}
	}
	
	
	/**
	 * Removes the first element from this dequeue.
	 * @return The item at the front of the dequeue.
	 * @throws IllegalStateException if this dequeue is empty.
	 */
	public E removeFirst() {
		
		if(isEmpty()) {
			
			throw new IllegalStateException("Deque is empty, cannot remove "
					+ "from an empty deque.");
			
		}
		
		
		E temp = head.value;
		
		head = head.next;
		
		size--;
		
		return temp;
		
	}
	
	
	/**
	 * Removes the last element from this dequeue.
	 * @return E The last item in the dequeue.
	 * @throws IllefalStateException if this dequeue is empty.
	 */
public E removeLast() {
		
		Node<E> temp = head;
		int i = 1;
		
//		if(head == tail) {
//			head = null;
//			tail = null;
//			size = 0;
//		}
		
		while(i < size) {
		
			temp = temp.next;
			i++;
		}
		
		tail = temp;
		temp.next = null;
		size--;
		
	
		return tail.value;
	}
	
	
	
	/**
	 * Returns a copy of the first element in this dequeue.
	 * @return The first element in this dequeue.
	 * @throws IllegalStateException if the dequeue is empty.
	 */
	public E peekFirst() {
		
		if(isEmpty()) {
			
			throw new IllegalStateException("Deque is empty, cannot remove "
					+ "from an empty deque.");
			
		}
		
		
		E temp = head.value;
		
		return temp;

		
	}
	
	
	/**
	 * Returns a copy of the last element in this dequeue.
	 * @return a copy of the last element in this dequeue.
	 * @throws IllegalStateException if this dequeue is empty.
	 */
	public E peekLast() {
		
		if(isEmpty()) {
			
			throw new IllegalStateException("Deque is empty.");
			
		}
		
		E tempLast = tail.value;
		
		
		
		return tempLast;
		
		
	}
	
	
	/**
	 * Returns the number of elements in this dequeue.
	 * @return the number of elements in this dequeue.
	 */
	public int size() {
		
		
		return size;
	}
	
	
	
	/**
	 * Returns a String representation of this dequeue within angle brackets 
	 * and separated by commas.
	 * @return str A String representation of this dequeue.
	 */
	public String toString() {
		
		StringBuilder str = new StringBuilder("");
		

		str.append("<");
		
		for(int i = 0; i <= size; i++) {
			str.append(head.next.value + " ");
		}
		
		str.append(">");
		
		return str.toString();
		
	}
	
	
	
	/**
	 * Determines if this dequeue is equal to the given object.
	 * @param other The object with which to compare this dequeue.
	 * @return result True of other object is a GenericLinkedDeque with the 
	 * same abstract state as this object, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		
		boolean result = false;
		
		if(this == other) {
			result = true;
		}
		
		
		// ?????????? object cannot be safely cast to linkeddeque whyyyyy????
		else if((LinkedDeque<E>) other instanceof LinkedDeque<E>) {
			


			LinkedDeque<E> temp = (LinkedDeque<E>) other;
			
			result = this.size == temp.size;
				
				for(Node<E> i = this.head, j = temp.head; result;
						i = i.next, j = j.next){
					
					result = i.value.equals(j.value);
				}
				
		}
	
		return result;	
	}
	
	

	/**
	 * Returns a new deque iterator.
	 * @return Returns a new deque iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		
		return new DequeIterator();
	}
	
	
	
	
	//------------------------------Nested Classes-----------------------------
	
	
	
	public class DequeIterator extends Object implements Iterator<E>{
		
		// The current node reached
		private Node<E> current;
		
		
		/**
		 * Constructs an iterator pointing to the first item.
		 */
		public DequeIterator() {
			
			// Initialize current node as null
			current = null;
			
		}
		
		
		/**
		 * Is there another item to look at.
		 * @return True if there is a next item, false otherwise.
		 */
		public boolean hasNext() {
			
			
			return current.next != null;
 		}
		
		
		/**
		 * Returns the current value and advances on to the next object.
		 * @return The current value.
		 */
		public E next() {
			
			return current.value;
		}
		
	}// end DequeIterator class
	
	
	
	private class Node<T> extends Object{
		
		
		// The value at this node's location
		private Node<T> next;
		
		// A reference to the next node in the linked list
		private T value;
		
		
		
		// not sure if needed???
		public Node(T value) {
			this.value = value;
		}
		
		
		
		/**
		 * Constructs a new Node given a value and pointer to the next node in 
		 * the linked list.
		 * @param value The value this node should store.
		 * @param next A reference to the next node in the list, or null if 
		 * this node is the last node.
		 */
		@SuppressWarnings("unused")
		public Node(T value, Node<T> next) {
			
			this.value = value;
			
			this.next = next;
			
		}
	
	}

}
