package pl.lodz.wspolbiezne.lab07;

import java.util.Arrays;

public class Obliczenia {

	private final int LICZBA_PROCESORÓW = 4;
	private final int N = 4;
	int[][] A={ { 1, 2, 3, 4 },
				{ 5, 6, 7, 8 },
				{ 9,10,11,12 },
				{13,14,15,16 } };

	int[][] B={ { 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9,10,11,12 },
				{13,14,15,16 } };

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
		System.out.println(toString(C));
	}

	public int get(int row, int col) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
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
		new Obliczenia();

	}
	
	public static String toString(int[][] c) {
        if (c == null)
            return "null";

        int iMax = c.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
        	b.append(Arrays.toString(c[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(",\n");
        }
    }
}
