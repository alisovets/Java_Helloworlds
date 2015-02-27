package com.example.alisovets.list;

public interface MyList {
	
	/**
	 * Appends the specified element to the end of this list
	 * @param e element to be appended to this list 
	 */
	void add(Object e); 
	
	/**
	 * Appends the specified element to the specified position of this list
	 * @param index index
	 * @param element to be appended to this list 
	 */
	void add(int index, Object element);
	
	/**
	 * Appends all of the elements in the specified array to the end of this list
	 * @param c array of objects
	 */
	void addAll(Object[] c);
	
	/**
	 *  Appends all of the elements in the specified array to the specified position of this list/
	 * @param index to insert
	 * @param c
	 */
	void addAll(int index, Object[] c);
	
	/** 
	 * Returns the element at the specified position in this list. 
	 * @param - index of the element to return  
	 * @return the element at the specified position in this list
	 */
	Object get(int index); 
	
	/**
	 * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). 
	 * @param index - the index of the element to be removed 
	 * @return the element that was removed from the list 
	 */
	Object remove(int index);
	
	/**
	 * Replaces the element at the specified position in this list with the specified element. 
	 * @param index - index of the element to replace
	 * @param element - element to be stored at the specified position 
	 */
	void set(int index, Object element);
	
	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
	 * @param o element to search for 
	 * @return -the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
	 */
	int indexOf(Object o);
	
	/**
	 * Returns the number of elements in this list. 
	 */
	int size();
	
	/**
	 * Returns an array containing all of the elements in this list
	 * @return an array containing all of the elements in this list
	 */
	Object[] toArray(); 

}
