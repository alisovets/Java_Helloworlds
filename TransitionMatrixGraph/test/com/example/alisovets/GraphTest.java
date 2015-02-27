package com.example.alisovets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class GraphTest {
	
	@Test
	public void testCreate() {
		Graph graph = new Graph(10);
		assertNotNull(graph);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFindExtremePathVertexFrom0() {
		final int SIZE = 10;
		Graph graph = new Graph(SIZE);
		int[] vertexes = new int[SIZE];
		graph.findExtremePath(0, 1, vertexes, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindExtremePathVertexTo0() {
		final int SIZE = 10;
		Graph graph = new Graph(SIZE);
		int[] vertexes = new int[SIZE];
		graph.findExtremePath(1, 0, vertexes, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindExtremePathVertexFromIsTooBig() {
		final int SIZE = 10;
		Graph graph = new Graph(SIZE);
		int[] vertexes = new int[SIZE];
		graph.findExtremePath(SIZE + 1, 1, vertexes, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindExtremePathVertexToIsTooBig() {
		final int SIZE = 10;
		Graph graph = new Graph(SIZE);
		int[] vertexes = new int[SIZE];
		graph.findExtremePath( 1, SIZE + 1, vertexes, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPassVertexFromIs0() {
		final int SIZE = 5;
		Graph graph = new Graph(SIZE);
		graph.setPass(0, 1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPassVertexToIs0() {
		final int SIZE = 5;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 0, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPassVertexFromIsTooBig() {
		final int SIZE = 5;
		Graph graph = new Graph(SIZE);
		graph.setPass(SIZE + 1, 1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPassVertexToIsToBig() {
		final int SIZE = 5;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, SIZE + 1, 1);
	}
	
	@Test
	public void testFindExtremePath2VertexesNoPass() {
		final int SIZE = 2;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(2, 1, vertexes, true);
		assertEquals(-1, passLength);
		
		vertexes = new int[SIZE];
		passLength = graph.findExtremePath(2, 1, vertexes, false);
		assertEquals(-1, passLength);
	}
	
	@Test
	public void testFindShortest2Vertexes() {
		final int SIZE = 2;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 2, vertexes, true);
		assertEquals(1, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(2, vertexes[1]);
	}

	@Test
	public void testFindLongest2Vertexes() {
		final int SIZE = 2;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 2, vertexes, false);
		assertEquals(1, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(2, vertexes[1]);
	}
	
	@Test
	public void testFindShortest3Vertexes() {
		final int SIZE = 3;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 2);
		graph.setPass(1, 3, 4);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 3, vertexes, true);
		assertEquals(3, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(2, vertexes[1]);
		assertEquals(3, vertexes[2]);
	}

	@Test
	public void testFindLongest3Vertexes() {
		final int SIZE = 3;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 2);
		graph.setPass(1, 3, 4);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 3, vertexes, false);
		assertEquals(4, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(3, vertexes[1]);
		assertEquals(0, vertexes[2]);
		
	}
	
	@Test
	public void testFindShortest4Vertexes() {
		final int SIZE = 4;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 2);
		graph.setPass(1, 3, 4);
		graph.setPass(2, 4, 6);
		graph.setPass(3, 4, 2);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 4, vertexes, true);
		assertEquals(5, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(2, vertexes[1]);
		assertEquals(3, vertexes[2]);
		assertEquals(4, vertexes[3]);
	}
	
	
	@Test
	public void testFindLongest4Vertexes() {
		final int SIZE = 4;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 2);
		graph.setPass(1, 3, 4);
		graph.setPass(2, 4, 6);
		graph.setPass(3, 4, 2);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 4, vertexes, false);
		assertEquals(7, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(2, vertexes[1]);
		assertEquals(4, vertexes[2]);
		assertEquals(0, vertexes[3]);
	}
	
	@Test
	public void testFindLongestVertexes6Passes() {
		final int SIZE = 4;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 2);
		graph.setPass(1, 3, 4);
		graph.setPass(2, 4, 6);
		graph.setPass(3, 4, 2);
		graph.setPass(1, 4, 8);
		
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 4, vertexes, false);
		assertEquals(8, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(4, vertexes[1]);
		assertEquals(0, vertexes[2]);
		assertEquals(0, vertexes[3]);
	}
	
	@Test
	public void testFindShortest4Vertexes6Pases() {
		final int SIZE = 4;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 2);
		graph.setPass(1, 3, 4);
		graph.setPass(2, 4, 6);
		graph.setPass(3, 4, 2);
		graph.setPass(1, 4, 8);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 4, vertexes, true);
		assertEquals(5, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(2, vertexes[1]);
		assertEquals(3, vertexes[2]);
		assertEquals(4, vertexes[3]);
	}
	
	@Test
	public void testFindShortest5Vertexes8Pases() {
		final int SIZE = 5;
		Graph graph = new Graph(SIZE);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 2);
		graph.setPass(1, 3, 4);
		graph.setPass(2, 4, 6);
		graph.setPass(3, 4, 2);
		graph.setPass(1, 4, 8);
		
		graph.setPass(1, 5, 1);
		graph.setPass(5, 4, 1);
		
		int[] vertexes = new int[SIZE];
		int passLength = graph.findExtremePath(1, 4, vertexes, true);
		assertEquals(2, passLength);
		
		assertEquals(1, vertexes[0]);
		assertEquals(5, vertexes[1]);
		assertEquals(4, vertexes[2]);
		assertEquals(0, vertexes[3]);
		assertEquals(0, vertexes[4]);
	}
}
