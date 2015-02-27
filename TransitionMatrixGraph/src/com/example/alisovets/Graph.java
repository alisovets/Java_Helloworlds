package com.example.alisovets;

import java.util.Arrays;

public class Graph{
	private static final int NOT_EXIST_VALUE = -1;

	private int[][] passMatrix;

	/**
	 * creates the empty graph with the specified size;
	 * 
	 * @param numberOfVertexes
	 *            - number of vertexes.
	 */
	public Graph(int numberOfVertexes) {
		
		this.passMatrix = new int[numberOfVertexes][numberOfVertexes];
	}

	/**
	 * Sets the passage between two vertices
	 * 
	 * @param vertexFrom
	 *            - a vertex index from which pass is directed. (the vertex
	 *            index begins with 0)
	 * @param vertexTo
	 *            - a vertex index to which pass is directed
	 * @param passLength
	 *            - the length of the pass
	 */
	public void setPass(int vertexFrom, int vertexTo, int passLength) {
		if ((vertexFrom > passMatrix.length) || (vertexFrom <= 0)
				|| (vertexTo > passMatrix.length) || (vertexTo <= 0)) {
			throw new IllegalArgumentException(
					"Vertex values should be between 1 and matrix size, inclusive!");
		}
		if (vertexFrom == vertexTo) {
			throw new IllegalArgumentException(
					"Vertexes ​​can not be the same!");
		}
		passMatrix[vertexTo - 1][vertexFrom - 1] = passLength;
	}

	/**
	 * Finds the shortest or longest path between the specified pair of vertexes
	 * 
	 * @param vertexFrom
	 *            - The index of vertex with which begins a path
	 * @param vertexTo
	 *            - The index of the end path vertex
	 * @param VertexPath
	 *            - An array in which all vertexes of the path will be returned.
	 *            It must be of sufficient size (should be size = numberOfVertexes). 
	 * @param    shouldBeShortest = true if need shortest path, false if need longest path        
	 * @return - length of shortest path.
	 */
	public int findExtremePath(int vertexFrom, int vertexTo, int[] VertexPath, boolean shouldBeShortest) {

		if ((vertexFrom > passMatrix.length) || (vertexFrom <= 0)
				|| (vertexTo > passMatrix.length) || (vertexTo <= 0)) {
			throw new IllegalArgumentException(
					"Vertex values should be between 1 and matrix size, inclusive!");
		}

		
		//Find the index of the first element with a value of zero
		int currentIndex = 0; 
		for (int i = 0; i < VertexPath.length; i++) {
			if (VertexPath[i] == 0) {
				currentIndex = i;
				break;
			}
		}
		
		//add the start vertex in the path
		VertexPath[currentIndex] = vertexFrom;
		
		if(vertexTo == vertexFrom){
			//path is found
			return 0;
		}
		
		int extremePassLength = NOT_EXIST_VALUE; 	//extreme pass length from the current vertex
		int[] extremeVertexPath = null;   		 	//Vertexes of the current extreme path from the current start vertex

		//Iterate through all the way from the start vertex (vertexFrom)
		for (int i = 0; i < passMatrix.length; i++) {

			if (passMatrix[i][vertexFrom - 1] <= 0) {
				//skip if no pass
				continue;
			}

			if (isValueInArray(i + 1, VertexPath)) {
				//skip repeat the vertex
				continue;
			}

			//find the most suitable path from a next vertex  
			int[] currentVertexes = Arrays.copyOf(VertexPath, VertexPath.length);
			int length = findExtremePath(i + 1, vertexTo, currentVertexes, shouldBeShortest);

			if (length == NOT_EXIST_VALUE) {
				continue;
			}

			length += passMatrix[i][vertexFrom - 1];
			if ((extremePassLength == NOT_EXIST_VALUE) || isFirstMore(length, extremePassLength, shouldBeShortest)) {
				//this pass from 'vertexFrom' the most suitable at the moment
				//change the extreme path;
				extremePassLength = length;
				extremeVertexPath = currentVertexes;
			}

		}

		if (extremePassLength == NOT_EXIST_VALUE) {
			//no suitable path from 'vertexFrom'
			return NOT_EXIST_VALUE;
		}
		
		//Add the path found in the input array for to return at the upper level 
		fillArrayFromArray(VertexPath, extremeVertexPath, 0, VertexPath.length);
		return extremePassLength;

	}

	/*
	 * checks whether there is value in the array
	 */
	private boolean isValueInArray(int value, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return true;
			}
		}
		return false;
	}
	

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(String.format("    |"));
		for (int i = 0; i < passMatrix.length; i++) {
			stringBuilder.append(String.format("%3d |", i + 1));
		}
		stringBuilder.append("\n");
		for (int i = 0; i <= passMatrix.length; i++) {
			stringBuilder.append(String.format("----|"));
		}

		for (int i = 0; i < passMatrix.length; i++) {
			stringBuilder.append("\n");
			stringBuilder.append(String.format("%3d |", i + 1));
			for (int j = 0; j < passMatrix[i].length; j++) {
				stringBuilder.append(String.format(" %2d |", passMatrix[i][j]));
			}
		}

		stringBuilder.append("\n");

		for (int i = 0; i <= passMatrix.length; i++) {
			stringBuilder.append(String.format("-----"));
		}
		return stringBuilder.toString();
	}

	// Assigns to each element of the specified range of the specified array the
	// values from the values array. The range to be filled extends from index
	// fromIndex, inclusive, to index toIndex, exclusive
	private void fillArrayFromArray(int[] array, int[] values, int fromIndex,
			int toIndex) {
		int i = 0;
		for (int j = fromIndex; j < toIndex; j++) {
			array[j] = values[i++];
		}
	}

	
	private boolean isFirstMore(int first, int second, boolean invertResult){
		if(invertResult){
			return first < second;	
		}
		else{
			return first > second;
		}
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(10);
		graph.setPass(1, 2, 1);
		graph.setPass(2, 3, 1);
		graph.setPass(3, 4, 1);
		graph.setPass(4, 6, 3);
		graph.setPass(6, 7, 1);
		graph.setPass(7, 9, 1);
		graph.setPass(9, 10, 2);

		graph.setPass(1, 6, 4);
		graph.setPass(6, 9, 2);

		graph.setPass(1, 8, 1);
		graph.setPass(1, 5, 1);
		graph.setPass(5, 8, 1);
		graph.setPass(3, 1, 1);
		graph.setPass(9, 10, 1);
		graph.setPass(9, 2, 8);
		graph.setPass(1, 9, 12);
		graph.setPass(8, 4, 11);

		graph.setPass(7, 2, 2);

		System.out.println(graph.toString());
		int[] passVertexes = new int[10];
		System.out.println("pathes 1 - 10:");
		int passLength = graph.findExtremePath(1, 10, passVertexes, true);
		System.out.println("Shortest path = " + Arrays.toString(passVertexes));
		System.out.println("path length = " + passLength);
		
		passVertexes = new int[10];
		passLength = graph.findExtremePath(1, 10, passVertexes, false);
		System.out.println("Longest path = " + Arrays.toString(passVertexes));
		System.out.println("path length = " + passLength);
		System.out.println();

		passVertexes = new int[10];
		System.out.println("pathes 2 - 10:");
		passLength = graph.findExtremePath(2, 10, passVertexes, true);
		System.out.println("path length = " + passLength);
		System.out.println("Shortest path = " + Arrays.toString(passVertexes));
		
		passVertexes = new int[10];
		passLength = graph.findExtremePath(2, 10, passVertexes, false);
		System.out.println("path length = " + passLength);
		System.out.println("Shortest path = " + Arrays.toString(passVertexes));

	}	
}
