package com.example.alisovets.list;

import java.util.Arrays;
import java.util.RandomAccess;

public class MyArrayList implements MyList, RandomAccess {
	private final static int NORMAL_INITIAL_CAPACITY = 10;
	private int size;
	private Object[] data;

	public MyArrayList() {
		this(NORMAL_INITIAL_CAPACITY);
	}

	public MyArrayList(int initialCapacity) {
		data = new Object[initialCapacity];
		size = 0;
	}

	@Override
	public void add(Object element) {
		add(size, element);
	}

	@Override
	public void add(int index, Object element) {
		checkAddIndex(index);

		if (size == data.length - 1) {
			expand();
		}

		for (int i = size; i >= index; i--) {
			data[i + 1] = data[i];
		}

		data[index] = element;
		size++;
	}

	@Override
	public void addAll(Object[] c) {
		int newSize = size + c.length;
		expand(newSize);
		for (int i = 0; size < newSize; i++, size++) {
			data[size] = c[i];
		}
	}

	@Override
	public void addAll(int index, Object[] c) {
		checkAddIndex(index);
		int newSize = size + c.length;
		expand(newSize);
		
		for(int i = 1; i <= size - index; i++){
			data[newSize - i] = data[size - i]; 
		}
		
		for (int i = 0; i < c.length; i++) {
			data[index + i] =  c[i];
		}
		size = newSize;
	}

	@Override
	public Object get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public Object remove(int index) {
		checkIndex(index);

		Object obj = get(index);
		size--;
		for (int i = index; i < size; i++) {
			data[i] = data[i + 1];
		}
		return obj;
	}

	@Override
	public void set(int index, Object element) {
		checkIndex(index);
		data[index] = element;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (o == null) {
				if ((data[i] == null))
					return i;
			} else if (o.equals(data[i]))
				return i;
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	/*
	 * Doubles the storage for storing objects
	 */
	private void expand() {
		expand(data.length * 2);
	}

	private void expand(int newLength) {
		if (newLength > data.length) {
			data = Arrays.copyOf(data, newLength);
		}
	}

	private void checkIndex(int index) {
		if ((index >= size) || (index < 0))
			throw new IndexOutOfBoundsException();
	}
	
	private void checkAddIndex(int index) {
		if ((index > size) || (index < 0))
			throw new IndexOutOfBoundsException();
	}

}
