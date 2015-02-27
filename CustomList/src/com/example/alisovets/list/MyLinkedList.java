package com.example.alisovets.list;

public class MyLinkedList implements MyList {
	private ListNode firstNode;
	private ListNode lastNode;
	private int size;

	@Override
	public void add(Object object) {
		ListNode newNode = new ListNode(object);

		if (size == 0) {
			// it is the first element
			firstNode = newNode;
			lastNode = newNode;
		} else {
			// it is the last element
			lastNode.next = newNode;
			lastNode = newNode;
		}
		size++;
	}

	@Override
	public void add(int index, Object object) {
		checkAddIndex(index);
		ListNode newNode = new ListNode(object);

		// it is the first element
		if (size == 0) {
			firstNode = newNode;
			lastNode = newNode;
		}

		// this is the last element
		else if (index == size) {
			lastNode.next = newNode;
			lastNode = newNode;
		}

		// not only one, not fist, not last
		// there is at least two elements.
		else {
			ListNode previousNode = getNodeByIndex(index - 1);
			newNode.next = previousNode.next;
			previousNode.next = newNode;
		}
		size++;
	}

	@Override
	public void addAll(Object[] c) {
		for (int i = 0; i < c.length; i++) {
			add(c[i]);
		}
	}

	@Override
	public void addAll(int index, Object[] c) {
		checkAddIndex(index);
		if(c.length == 0){
			return;
		}
		ListNode localFirstNode = new ListNode(c[0]);
		ListNode localLastNode = localFirstNode;
		for (int i = 1; i < c.length; i++) {
			localLastNode.next = new ListNode(c[i]);
			localLastNode = localLastNode.next;
		}

		// it is the first element
		if (size == 0) {
			firstNode = localFirstNode;
			lastNode = localLastNode;
		}

		// there is at least one element.
		// and this is first
		else if (index == 0) {
			localLastNode.next = firstNode;
			firstNode = localFirstNode;
		}

		// this is the last element
		else if (index == size) {
			lastNode.next = localFirstNode;
			lastNode = localLastNode;
		}

		// not only one, not fist, not last
		// there is at least two elements.
		else {
			ListNode previousNode = getNodeByIndex(index - 1);
			localLastNode.next = previousNode.next;
			previousNode.next = localFirstNode;
		}
		size += c.length;
	}

	@Override
	public Object get(int index) {
		checkIndex(index);
		return getNodeByIndex(index).object;
	}

	@Override
	public Object remove(int index) {
		checkIndex(index);

		ListNode deletedNode;
		if (index == 0) {
			deletedNode = firstNode;
			firstNode = firstNode.next;
		} else {
			ListNode previousNode = getNodeByIndex(index - 1);
			deletedNode = previousNode.next;
			if (deletedNode.next == null) {
				// it is the last node
				lastNode = previousNode;
			}
			previousNode.next = deletedNode.next;
		}
		size--;
		return deletedNode.object;
	}

	@Override
	public void set(int index, Object object) {
		checkIndex(index);
		getNodeByIndex(index).object = object;
	}

	@Override
	public int indexOf(Object o) {
		ListNode currentNode = firstNode;
		for (int i = 0; i < size; i++) {
			if (o == null) {
				if ((currentNode.object == null))
					return i;
			} else if (o.equals(currentNode.object))
				return i;
			currentNode = currentNode.next;
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		ListNode currentNode = firstNode;
		for (int i = 0; i < size; i++) {

			array[i] = currentNode.object;
			currentNode = currentNode.next;
		}
		return array;
	}

	private ListNode getNodeByIndex(int index) {
		ListNode currentNode = firstNode;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	private void checkIndex(int index) {
		if ((index >= size) || (index < 0))
			throw new IndexOutOfBoundsException();
	}
	
	private void checkAddIndex(int index) {
		if ((index > size) || (index < 0))
			throw new IndexOutOfBoundsException();
	}	

	private static class ListNode {
		private ListNode next;
		private Object object;

		ListNode(Object obj) {
			object = obj;
		}
	}
}
