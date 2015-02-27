package com.example.alisovets.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.list.MyLinkedList;

public class MyLinkedListTest {
	MyLinkedList list;

	@Before
	public void initList() {
		list = new MyLinkedList();
	}

	@Test
	public void testCreate() {
		assertNotNull(list);
	}

	@Test
	public void testAddNull() {
		list.add(null);
		assertEquals(null, list.get(0));
	}

	@Test
	public void testAdd() {
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
		assertEquals("3", list.get(3));
	}

	@Test
	public void testAddByIndexConsistently() {
		list.add(0, "0");
		list.add(1, "1");
		list.add(2, "2");
		list.add(3, "3");

		assertEquals(4, list.size());
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
		assertEquals("3", list.get(3));
	}

	@Test
	public void testAddByIndexNotConsistently() {
		list.add(0, "0");
		list.add(1, "1");
		list.add(2, "2");
		// 012
		list.add(1, "3");
		// 0312
		list.add(0, "4");
		// 40312
		list.add(4, "5");
		// 403152

		assertEquals(6, list.size());
		assertEquals("4", list.get(0));
		assertEquals("0", list.get(1));
		assertEquals("3", list.get(2));
		assertEquals("1", list.get(3));
		assertEquals("5", list.get(4));
		assertEquals("2", list.get(5));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddByIndexNegative() {
		list.add(-1, "0");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddByIndexIndexMoreThenSize() {
		list.add(1, "0");
	}

	@Test
	public void testAddAllWhenListEmpty() {
		String[] array = { "0", "1", "2", "3" };
		list.addAll(array);
		assertEquals(4, list.size());
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
		assertEquals("3", list.get(3));
	}

	@Test
	public void testAddAll() {
		list.add("a");
		String[] array = { "0", "1", "2", "3" };
		list.addAll(array);

		assertEquals(5, list.size());
		assertEquals("a", list.get(0));
		assertEquals("0", list.get(1));
		assertEquals("1", list.get(2));
		assertEquals("2", list.get(3));
		assertEquals("3", list.get(4));
	}

	@Test
	public void testAddAllByIndex0() {
		list.add("a");
		list.add("b");
		String[] array = { "0", "1", "2", "3" };
		list.addAll(0, array);

		assertEquals(6, list.size());
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
		assertEquals("3", list.get(3));
		assertEquals("a", list.get(4));
		assertEquals("b", list.get(5));
	}

	@Test
	public void testAddAllByIndexMiddle() {
		list.add("a");
		list.add("b");
		String[] array = { "0", "1", "2", "3" };
		list.addAll(1, array);

		assertEquals(6, list.size());
		assertEquals("a", list.get(0));
		assertEquals("0", list.get(1));
		assertEquals("1", list.get(2));
		assertEquals("2", list.get(3));
		assertEquals("3", list.get(4));
		assertEquals("b", list.get(5));
	}

	@Test
	public void testAddAllByIndexEnd() {
		list.add("a");
		list.add("b");
		String[] array = { "0", "1", "2", "3" };
		list.addAll(2, array);

		assertEquals(6, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("0", list.get(2));
		assertEquals("1", list.get(3));
		assertEquals("2", list.get(4));
		assertEquals("3", list.get(5));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddAllByIndexToBigIndex() {
		list.add("a");
		String[] array = { "0", "1", "2", "3" };
		list.addAll(2, array);
	}

	@Test
	public void testRemoveFirstLast() {
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.remove(0);
		list.remove(2);

		assertEquals(2, list.size());
		assertEquals("1", list.get(0));
		assertEquals("2", list.get(1));

	}

	@Test
	public void testRemoveMiddle() {
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.remove(1);
		list.remove(1);

		assertEquals(2, list.size());
		assertEquals("0", list.get(0));
		assertEquals("3", list.get(1));

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveFromEmpty() {
		list.remove(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveBigIndex() {
		list.add("0");
		list.remove(1);
	}

	@Test
	public void testSet() {
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.set(1, "a");
		list.set(2, "b");

		assertEquals(4, list.size());
		assertEquals("0", list.get(0));
		assertEquals("a", list.get(1));
		assertEquals("b", list.get(2));
		assertEquals("3", list.get(3));
	}

	@Test
	public void testIndexOff() {
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		int index0 = list.indexOf("0");
		int index1 = list.indexOf("1");
		int index3 = list.indexOf("3");
		int indexA = list.indexOf("A");

		assertEquals(0, index0);
		assertEquals(1, index1);
		assertEquals(3, index3);
		assertEquals(-1, indexA);
	}
}
