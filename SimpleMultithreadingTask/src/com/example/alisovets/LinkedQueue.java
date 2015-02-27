package com.example.alisovets;

public class LinkedQueue<E> {
	protected int capacity;
	protected Node mainNode;
	protected Node endNode;

	public LinkedQueue(int capacity) {
		if (capacity <= 2) {
			throw new IllegalArgumentException("Queue capacity must be more then 2");
		}

		Node currentNode = new Node();
		Node startNode = currentNode;
		for (int i = 1; i < capacity; i++) {
			currentNode.next = new Node();
			currentNode = currentNode.next;
		}

		currentNode.next = startNode;
		mainNode = startNode;
		endNode = startNode;
		this.capacity = capacity;
	}

	public void write(E element) {
		if (element == null) {
			throw new NullPointerException();
		}

		synchronized (this) {
			endNode = endNode.next;
			endNode.element = element;
		}
	}

	public synchronized E read() {
		mainNode = mainNode.next;
		return mainNode.element;
	}

	protected class Node {
		Node next;
		E element;
	}

}
