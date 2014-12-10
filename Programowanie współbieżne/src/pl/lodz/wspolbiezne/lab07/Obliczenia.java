package pl.lodz.wspolbiezne.lab07;

import java.util.Arrays;

public class Obliczenia {

	private final int LICZBA_PROCESORÓW = 4;
	private final int N = 4;
	int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
			{ 13, 14, 15, 16 } };

	int[][] B = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
			{ 13, 14, 15, 16 } };

	public Obliczenia() {
		int[][] C = new int[N][N];
		int start;
		int end;
		// proces 0
		start = 0;
		end = 1;
		for (int i = 0; i < N; i++) {
			for (int j = start; j < end; j++) {
				C[i][j] = get(i, j);
			}
		}

		// proces 1
		start = 1;
		end = 2;
		for (int i = 0; i < N; i++) {
			for (int j = start; j < end; j++) {
				C[i][j] = get(i, j);
			}
		}

		// proces 2
		start = 2;
		end = 3;
		for (int i = 0; i < N; i++) {
			for (int j = start; j < end; j++) {
				C[i][j] = get(i, j);
			}
		}

		// proces 3
		start = 3;
		end = 4;
		for (int i = 0; i < N; i++) {
			for (int j = start; j < end; j++) {
				C[i][j] = get(i, j);
			}
		}

		System.out.println(Arrays.toString(C));

	}

	public int get(int row, int col) {
		// row(0), col(1) = 2 = A[0][1]
		int sum = 0;
		for (int i = 0; i < N; i++) {
			System.out.println("+");
			System.out.println(A[row][i] * B[i][col]);
			sum += A[row][i] * B[i][col];
		}
		return sum;
	}

	public int multiply(int[] row, int[] col) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += row[i] * col[i];
		}
		return sum;
	}

	public static void main(String[] args) {
//		System.out.println((new Obliczenia().get(0, 1)));
		new Obliczenia();

	}
}
