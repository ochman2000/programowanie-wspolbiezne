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

	private final int LICZBA_PROCESORÓW = 4;
	private final int N;
	int[][] A={ { 1, 2, 3, 4 },
				{ 5, 6, 7, 8 },
				{ 9,10,11,12 },
				{13,14,15,16 } };

	int[][] B={ { 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9,10,11,12 },
				{13,14,15,16 } };

	public Obliczenia() {
		if (!(A.length==A[0].length 
				&& A[0].length==B.length
				&& B.length==B[0].length)) {
			throw new RuntimeException("Rozmiar macierzy ma byæ taki sam.");
		}
		N = A.length;
		dispatch();
	}

	private void dispatch() {
		for (int proces=0; proces<LICZBA_PROCESORÓW; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESORÓW);;
			int end = getEndOfInterval(proces, LICZBA_PROCESORÓW);
			int[][] C = getBlock(start, end);
			System.out.println(toString(C)+"\n");
		}
		//merge results here
	}

	private int[][] getBlock(int start, int end) {
		int[][] C = new int[N][end-start];
//		for (int i = 0; i < N; i++) {
//			for (int j = start; j < end; j++) {
//				C[i][j-start] = multiply(i, j);
//			}
//		}
		return C;
	}

//	public int multiply(int row, int col) {
//		int sum = 0;
//		for (int i = 0; i < N; i++) {
//			sum += A[row][i] * B[i][col];
//		}
//		return sum;
//	}

	public int multiply(int[] row, int[] col) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += row[i] * col[i];
		}
		return sum;
	}
	
	
	public int[] multiply(int[] row, int[][] columns) {
		int[] C = new int[row.length];
		for (int i=0; i<row.length; i++) {
			C[i] = multiply(row, columns[i]);
		}
		return C;
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
	
	public int getBeginningOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia³ nie mo¿e byæ wiêkszy ni¿: " + totalIntervals
							+ " a podano: " + interval);
		}
		double fraction = (double) interval / (double) totalIntervals;
		return (int) (fraction * N);
	}

	public int getEndOfInterval(int interval, int totalIntervals) {
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
	    	  sb.append(new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime()));
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
		for (int i=0; i<rows.length; i++) {
			for (int j=0; j<columns.length; j++) {
				C[i][j] = multiply(rows[i], columns[i]);
			}
		}
		return C;
	}
}
