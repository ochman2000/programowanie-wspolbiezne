package pl.lodz.wspolbiezne.lab06;

public class Macierz {
	
	private int[][] A;
	private int[][] B;
	private int cols;
	private int rows;
	private int liczbaProcesów = 4;

	public Macierz(int[][] a, int[][] b) {
		this.A = a;
		this.B = b;
		cols = A[0].length;
		rows = A.length;
	}
	
	public int[][] podzielNaProcesy() {
		int p = (int) Math.sqrt(liczbaProcesów);
		int[][] procesy = new int[p][p];
		
		return procesy;
	}
	
	public void Shift() {
		int[][] blockA = new int[2][2];
		int[][] blockB = new int[2][2];
		int[][] blockC = new int[2][2];
		int[][] blockD = new int[2][2];
	
		blockA[0][0] = A[0][0];
		blockA[0][1] = A[0][1];
		blockA[1][0] = A[1][0];
		blockA[1][1] = A[1][1];
		
		blockB[0][0] = A[0][2];
		blockB[0][1] = A[0][3];
		blockB[1][0] = A[1][2];
		blockB[1][1] = A[1][3];
		
		blockC[0][0] = A[2][0];
		blockC[0][1] = A[2][1];
		blockC[1][0] = A[3][0];
		blockC[1][1] = A[3][1];
		
		blockD[0][0] = A[2][2];
		blockD[0][1] = A[2][3];
		blockD[1][0] = A[3][2];
		blockD[1][1] = A[3][3];
		
		
		
	}
}
