package pl.lodz.wspolbiezne.lab08;

public class Cw9 {
	private int iloscWatkow = 2;
	private final int ROWS = 4, COLS = 4;
	private double[][] macierz = new double[ROWS][COLS + 1];

	public void uruchomWatki() throws InterruptedException {
		Thread[] threads = new Thread[iloscWatkow];
		Jordan w;
		for (int r = 0; r < ROWS; r++) {
			double d = macierz[r][r];
			for (int c = 0; c < COLS + 1; c++) {
				macierz[r][c] /= d;
			}
			for (int i = 0; i < iloscWatkow; i++) {
				int start = i * (ROWS / iloscWatkow);
				int end = (i + 1) * (ROWS / iloscWatkow);
				w = new Jordan(start, end, macierz, r, COLS);
				threads[i] = new Thread(w);
				threads[i].start();
				threads[i].join();
			}
		}
	}

	public void pokazMacierz(double[][] matrix) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS + 1; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public double[][] getMatrix() {
		return this.macierz;
	}

	public void setMatrix(double[][] matrix) {
		this.macierz = matrix;
	}

	public static void main(String[] args) throws InterruptedException {
		double[][] matrix =
			{ 	{ 3, 6, 4, 6, 2 },
				{ 4, 4, 3, 1, 5 },
				{ 3, 1, (5 / 4d), (5 / 3d), 1 },
				{ (5 / 3d), (1 / 2d), 9, 5, 3 } };
		Cw9 cw = new Cw9();
		cw.setMatrix(matrix);
		cw.uruchomWatki();
		cw.pokazMacierz(cw.getMatrix());
	}
}