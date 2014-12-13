package pl.lodz.wspolbiezne.lab07;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Obliczenia {

	private int N;
	private int[][] A,B;

	public Obliczenia(int[][] A, int[][] B) {
		if (!(A.length == A[0].length 
				&& A[0].length == B.length 
				&& B.length == B[0].length)) {
			throw new RuntimeException("Rozmiar macierzy ma byæ taki sam.");
		}
		N = A.length;
		this.A = A;
		this.B = B;
//		dispatch();
	}



//	public int[][][] getBlock(int start, int end) {
//		int l = end - start;
//		int[][][] C = new int[2][l][N];
//		for (int j=0; j<l; j++) {
//			for (int i=0; i<N; i++) {
//				C[0][j][i] = B[start+j][i];
//				C[1][j][i] = A[i][start+j];
//			}
//		}
//		return C;
//	}
	
	public MacierzeDto getBlock(int start, int end) {
		MacierzeDto D = new MacierzeDto();
		Macierz columns = new Macierz();
		Macierz rows = new Macierz();

		int l = end - start;
		int[][][] C = new int[2][l][N];
		Matrix[] kolumny = new Matrix[l];
		Matrix[] wiersze = new Matrix[l];
		for (int j=0; j<l; j++) {
			for (int i=0; i<N; i++) {
				C[0][j][i] = B[start+j][i];
				C[1][j][i] = A[i][start+j];
			}
			Matrix kol = new Matrix();
			Matrix wrs = new Matrix();
			kol.setIndex(j);
			kol.setWartosc(C[0][j]);
			wrs.setIndex(j);
			wrs.setWartosc(C[1][j]);
			kolumny[j] = kol;
			wiersze[j] = wrs;
		}
		
		columns.setValues(kolumny);
		rows.setValues(wiersze);
		D.setColumns(columns);
		D.setRows(rows);
		return D;
	}

	public int multiply(int[] row, int[] col) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += row[i] * col[i];
		}
		return sum;
	}

	public static String toString(int[][] c) {
		if (c == null)
			return "null";

		int iMax = c.length - 1;
		if (iMax == -1)
			return "[]";

		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0;; i++) {
			b.append(Arrays.toString(c[i]));
			if (i == iMax)
				return b.append(']').toString();
			b.append(",\n");
		}
	}

	public Logger getCustomLogger() {
		Logger.getGlobal().setUseParentHandlers(false);
		Handler conHdlr = new ConsoleHandler();
		conHdlr.setFormatter(new Formatter() {
			public String format(LogRecord record) {
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				sb.append(record.getLevel());
				sb.append("] ");
				final Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(record.getMillis());
				sb.append(new SimpleDateFormat("HH:mm:ss:SSS").format(cal
						.getTime()));
				sb.append("\tthread: ");
				sb.append(record.getThreadID());
				sb.append("\tmethod: ");
				sb.append(record.getSourceMethodName());
				sb.append("()\t");
				sb.append(record.getMessage());
				sb.append("\n");
				return sb.toString();
			}
		});
		Logger.getGlobal().addHandler(conHdlr);
		Logger.getGlobal().setLevel(Level.FINE);
		return Logger.getGlobal();
	}

	public int[][] processInput(int[][] rows, int[][] columns) {
		int[][] C = new int[rows.length][columns.length];
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < columns.length; j++) {
				C[i][j] = multiply(rows[i], columns[i]);
			}
		}
		return C;
	}
}
