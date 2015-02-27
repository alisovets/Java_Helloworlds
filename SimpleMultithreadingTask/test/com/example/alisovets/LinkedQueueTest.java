package com.example.alisovets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.LinkedQueue;

public class LinkedQueueTest {
	private static final int QUEUE_CAPACITY = 6;
	private LinkedQueue<String> queue;

	@Before
	public void init() {
		queue = new LinkedQueue<String>(QUEUE_CAPACITY);
	}

	@Test
	public void initTest() {
		assertNotNull(queue);
	}

	@Test
	public void write_Empty_ElementWrites() {
		final String expected = "reccord1";
		queue.write(expected);
		assertEquals(expected, queue.read());
	}

	@Test(expected = NullPointerException.class)
	public void write_Null_NullPointerExceptionThrown() {
		queue.write(null);
	}

	@Test
	public void write_1ElementIs_ElementWrites() {
		final String expected = "reccord2";
		queue.write("record1");
		queue.write(expected);
		assertEquals("record1", queue.read());
		assertEquals(expected, queue.read());
	}
	
	@Test
	public void write_BuferIsFull_ElementWrites() {
		final String expected = "reccord";
		for (int i = 0; i < QUEUE_CAPACITY; i++) {
			queue.write("record" + i);
		}
		queue.write(expected);
		assertEquals(expected, queue.read());
	}
	
	@Test
	public void read_1ElementIs_ElementIsGot() {
		final String expectedElement = "record1";
		queue.write(expectedElement);
		String gotElement = queue.read();
		assertEquals(expectedElement, gotElement);
	}

	@Test
	public void read_NoElements_NullIsGot(){
		assertNull(queue.read());
	}
	
	@Test
	public void read_BuferIsFullReadMoreThenWriten_ElementIsGot() {
		final String expected = "record";
		queue.write(expected);
		queue.read();
		for (int i = 1; i < QUEUE_CAPACITY; i++) {
			queue.write("record" + i);
			queue.read();
		}
		assertEquals(expected, queue.read());
	}
}
