package com.example.alisovets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
	private static final int QUEUE_CAPACITY = 6;
	private LinkedBlockingQueue<String> queue;

	@Before
	public void init() {
		queue = new LinkedBlockingQueue<String>(QUEUE_CAPACITY);
	}

	@Test
	public void initTest() {
		assertNotNull(queue);
	}

	@Test
	public void write_Empty_SizeBecomes1() {
		final int expected = 1;
		queue.write("reccord1");
		int size = queue.size();
		assertEquals(expected, size);
	}

	@Test(expected = NullPointerException.class)
	public void write_Null_NullPointerExceptionThrown() {
		queue.write(null);
	}

	@Test
	public void write_SizeIs1_SizeBecomes2() {
		final int expected = 2;
		queue.write("record1");
		queue.write("record2");
		int size = queue.size();
		assertEquals(expected, size);
	}

	@Test
	public void write_SizeIsLessThenCapacityOf1_SizeBecomesCapacity() {
		final int expected = QUEUE_CAPACITY;
		for (int i = 0; i < QUEUE_CAPACITY; i++) {
			queue.write("record" + i);

		}
		int size = queue.size();
		assertEquals(expected, size);
	}

	@Test
	public void write_WriteCapacityTimesRead1Time_SizeBecomesCapacity() {
		final int expected = QUEUE_CAPACITY;
		for (int i = 0; i < QUEUE_CAPACITY; i++) {
			queue.write("record" + i);
		}
		queue.read();
		queue.write("record");
		int size = queue.size();
		assertEquals(expected, size);
	}

	@Test
	public void write_SizeIsMax_WaitForReadAndAfterWrite() throws Exception {

		for (int i = 0; i < QUEUE_CAPACITY; i++) {
			queue.write("record" + i);
		}

		System.out.println("X");
		WriterThread thread = new WriterThread("record");
		thread.start();

		Thread.sleep(200);

		assertTrue(thread.isAlive());
		queue.read();

		Thread.sleep(100);

		assertFalse(thread.isAlive());
	}

	@Test
	public void read_SizeMoreThen0_ElementGotSizeDecremented() {
		final int expectedSize = 0;
		final String expectedElement = "record1";
		queue.write(expectedElement);
		String gotElement = queue.read();
		assertEquals(expectedElement, gotElement);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void read_SizeIs0_WaitForWrite() throws Exception {
		final String expected = "record";
		ReaderThread thread = new ReaderThread();
		thread.start();

		Thread.sleep(200);

		assertTrue(thread.isAlive());
		queue.write(expected);

		Thread.sleep(100);

		assertFalse(thread.isAlive());
		assertEquals(expected, thread.readElement);
	}

	@Test
	public void size_Empty_0() {
		final int expected = 0;
		int size = queue.size();
		assertEquals(expected, size);
	}

	private class ReaderThread extends Thread {
		String readElement;

		ReaderThread() {
			setDaemon(true);
		}

		public void run() {
			readElement = queue.read();
		}
	}

	private class WriterThread extends Thread {
		String writtenElement;

		WriterThread(String element) {
			writtenElement = element;
			setDaemon(true);
		}

		public void run() {
			queue.write(writtenElement);
		}
	}

}
