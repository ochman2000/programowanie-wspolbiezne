package pl.lodz.wspolbiezne.lab09;

import java.util.logging.Logger;

public class GaussJordan {

	private static final boolean BOOLEAN = false;

	public GaussJordan(double[][] A) {
		int n, m, i, j, k;
		i = 1; j = 1; n = A.length-1; m = A[0].length-1;
		long starttime = System.currentTimeMillis();
		System.out.println("n="+n+" m="+m);
		
		while (i <= n && j <= m) {

			k = i;
			while (k <= n && A[k][j] == 0)
				k++;

			if (k <= n) {
				if (k != i) {
					swap(A, i, k, j);
				}
				if (A[i][j] != 1) {
					divide(A, i, j);
				}
				eliminate(A, i, j);
				i++;
			}
			Logger.getGlobal().info("Iteracja nr \t"+j);
			j++;
		}
		long endtime = System.currentTimeMillis();
		Logger.getAnonymousLogger().info("Czas wykonania: "+(double)((endtime-starttime)/1000.0)+" sekundy.");
		if (BOOLEAN) { printMatrix(A); }
	}
	
	private static void swap(double[][] A, int i, int k, int j) {
		int m = A[0].length - 1;
		double temp;
		for (int q = j; q <= m; q++) {
			temp = A[i][q];
			A[i][q] = A[k][q];
			A[k][q] = temp;
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

	private static String printMatrix(double[][] A) {
		StringBuilder sb = new StringBuilder();
		int n = A.length - 1;
		int m = A[0].length - 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++)
				sb.append(A[i][j] + "  ");
			sb.append("\n");
		}
		sb.append("\n\n");
		System.out.println(sb);
		return sb.toString();
	}
}
