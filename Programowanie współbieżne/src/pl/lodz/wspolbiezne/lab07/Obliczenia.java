package pl.lodz.wspolbiezne.lab07;

import java.util.Arrays;


public class Obliczenia {

	private final int LICZBA_PROCESORÓW = 4;
	private final static int N = 4;
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
		
		dispatch(C);
	}

	private void dispatch(int[][] C) {
		for (int proces=0; proces<N; proces++) {
			int start;
			int end;
			start = getBeginningOfInterval(proces, LICZBA_PROCESORÓW);
			end = getEndOfInterval(proces, LICZBA_PROCESORÓW);
			getBlock(C, start, end);
			System.out.println(toString(C)+"\n");
		}
	}

	private void getBlock(int[][] C, int start, int end) {
		for (int i = 0; i < N; i++) {
			for (int j = start; j < end; j++) {
				C[i][j] = get(i, j);
			}
		}
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
	
	public static int getBeginningOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia³ nie mo¿e byæ wiêkszy ni¿: " + totalIntervals
							+ " a podano: " + interval);
		}
		double fraction = (double) interval / (double) totalIntervals;
		return (int) (fraction * N);
	}

	public static int getEndOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia³ nie mo¿e byæ wiêkszy ni¿: " + totalIntervals
							+ " a podano: " + interval);
		}
		double rozmiarPrzedzialu = (double) N
				/ (double) totalIntervals;
		double fraction = (double) interval / (double) totalIntervals;
		return (int) ((fraction * N) + rozmiarPrzedzialu);
	}
}
