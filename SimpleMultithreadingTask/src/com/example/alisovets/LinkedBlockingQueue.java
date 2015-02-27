package com.example.alisovets;

public class LinkedBlockingQueue<E> extends LinkedQueue<E>{
	protected int size;

	public LinkedBlockingQueue(int capacity) {
		super(capacity);
		size = 0;
	}

	
	@Override
	public void write(E element) {
		if (element == null) {
			throw new NullPointerException();
		}

		synchronized (this) {
			while (size == capacity) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			notify();
			size++;
			super.write(element);
		}
	}

	
	@Override
	public synchronized E read() {
		while (size == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		size--;
		notify();
		return super.read();
	}

	
	public synchronized int size() {
		return size;
	}
}
