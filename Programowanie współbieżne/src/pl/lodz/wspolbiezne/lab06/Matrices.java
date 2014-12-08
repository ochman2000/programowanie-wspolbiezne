package pl.lodz.wspolbiezne.lab06;

public class Matrices {
	int cols, rows;
	private int[][] A;
	private int[][] B;

	public Matrices(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		this.A = new int[cols][rows];
		this.B = new int[cols][rows];
	}

	public int multiply(int rowA, int colB) {
		int c = 0;
		for (int i = 0, j = 0; i < cols; i++, j++) {
			c += A[rowA][i] * B[j][colB];
		}
		return c;
	}

	public int[][] getB() {
		return B;
	}

	public void setB(int[][] b) {
		B = b;
	}

	public int[][] getA() {
		return A;
	}

	public void setA(int[][] a) {
		A = a;
	}

}
