package pl.lodz.wspolbiezne.lab06;

public class Matrices {
	int cols, rows;
	private int[][] A;
	private int[][] B;

	public Matrices(int[][] a, int [][] b) {
			this.A = a;
			this.B = b;
			this.cols = a.length;
			this.rows = a[0].length;
			if (!(a.length==a[0].length 
					&& a[0].length==b.length
					&& b.length==b[0].length)) {
				throw new RuntimeException("Rozmiar macierzy ma byæ taki sam.");
			}
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

	public int[][] getA() {
		return A;
	}
}
