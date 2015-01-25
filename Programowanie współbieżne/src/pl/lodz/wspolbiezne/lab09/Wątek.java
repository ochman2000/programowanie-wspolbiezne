package pl.lodz.wspolbiezne.lab09;

public class W¹tek implements Runnable {
	
	private double[][] A;
	private int pocz¹tek;
	private int koniec;
	private int i;
	
	public W¹tek(double[][] A, int pocz¹tek, int koniec, int i) {
		this.A = A;
		this.pocz¹tek = pocz¹tek;
		this.koniec = koniec;
		this.i = i;
	}

	@Override
	public void run() {
		for (int j=pocz¹tek; j<koniec; j++) {
			if (A[i][j] != 1) {
				divide(A, i, j);
			}
			eliminate(A, i, j);
		}	
	}

	private static void divide(double[][] A, int i, int j) {
		int m = A[0].length - 1;
		for (int q = j + 1; q <= m; q++)
			A[i][q] /= A[i][j];
		A[i][j] = 1;
	}

	private static void eliminate(double[][] A, int i, int j) {
		int n = A.length - 1;
		int m = A[0].length - 1;
		for (int p = 1; p <= n; p++) {
			if (p != i && A[p][j] != 0) {
				for (int q = j + 1; q <= m; q++) {
					A[p][q] -= A[p][j] * A[i][q];
				}
				A[p][j] = 0;
			}
		}
	}
}
