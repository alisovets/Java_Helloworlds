package com.example.alisovets.spiralmatrix;

/**
 * Creates square matrix and fills it values spirally from the edge to the center.
 */
public class MatrixFiller {
	
	private int[][] matrix;

	public MatrixFiller(int size) {
		matrix = new int[size][size];
	}

	public void fillMatrix(int startValue) {
		for (int i = 0; i < (matrix.length + 1) / 2; i++) {
			startValue = fillMatrixBorder(i, startValue);
		}
	}

	private int fillMatrixBorder(int indent, int startValue) {
		int value = startValue;
		for (int j = indent; j < matrix.length - indent; j++) {
			matrix[indent][j] = value++;
		}
		for (int i = indent + 1; i < matrix.length - indent; i++) {
			matrix[i][matrix.length - indent - 1] = value++;
		}

		for (int j = matrix.length - indent - 2; j >= indent; j--) {
			matrix[matrix.length - indent - 1][j] = value++;
		}

		for (int i = matrix.length - indent - 2; i > indent; i--) {
			matrix[i][indent] = value++;
		}
		return value;

	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j == 0) {
					stringBuilder.append(String.format("%3d", matrix[i][j]));
				} else {
					stringBuilder.append(String.format(",%3d", matrix[i][j]));
				}
			}
			if (i < matrix.length - 1) {
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] arg) {
		MatrixFiller mf;
		
		for (int i = 1; i < 10; i++) {
			mf = new MatrixFiller(i);
			mf.fillMatrix(1);
			System.out.println("matrix(" + i + "):");
			System.out.println(mf);
			System.out.println();
		}
	}
}
