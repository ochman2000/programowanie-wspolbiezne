package pl.lodz.wspolbiezne.lab07;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Obliczenia {

	private int N;
	private double[][] A,B;

	public Obliczenia(double[][] A, double[][] B) {
		if (!(A.length == A[0].length 
				&& A[0].length == B.length 
				&& B.length == B[0].length)) {
			throw new RuntimeException("Rozmiar macierzy ma byæ taki sam.");
		}
		N = A.length;
		this.A = A;
		this.B = B;
	}
	
	public Obliczenia() {
		
	}
	
	public MacierzeDto getBlock(int start, int end) {
		MacierzeDto D = new MacierzeDto();
		
		int l = end - start;
//		int[][][] C = new int[2][l][N];
		List<Zbiór> rows = new ArrayList<>(N);
		List<Zbiór> columns = new ArrayList<>(l);
		
		for (int j=0; j<l; j++) {
			Zbiór z1 = new Zbiór();
			z1.setIndex(start+j);
			List<Double> values1 = new ArrayList<>(N);
			for (int i=0; i<N; i++) {
//				C[0][j][i] = B[start+j][i];
				values1.add(B[start+j][i]);
			}
			z1.setValues(values1);
			rows.add(z1);
		}
		for (int j=0; j<N; j++) {
			Zbiór z2 = new Zbiór();
			z2.setIndex(j);
			List<Double> values2 = new ArrayList<>(N);
			for (int i=0; i<N; i++) {
//				C[1][j][i] = A[i][start+j];
				values2.add(A[i][j]);
			}
			z2.setValues(values2);
			columns.add(z2);
		}
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
	
	public static String toString(double[][] c) {
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
	
	public static String arrayToString(int[][] c) {
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

	public static Logger getCustomLogger() {
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
				sb.append("\t");
				sb.append(record.getSourceClassName());
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
	
	public ResultDto processInput(MacierzeDto macierze) {
		ResultDto result = new ResultDto();
		int liczbaKolumn = macierze.getColumns().size();
		int liczbaWierszy = macierze.getRows().size();
		List<Element> elements = new ArrayList<>(liczbaKolumn*liczbaWierszy);
		for (int i=0; i<liczbaKolumn; i++) {
			for (int j=0; j<liczbaWierszy; j++) {
				Element e = new Element();
				e.setKolumna(macierze.getColumn(i).getIndex());
				e.setWiersz(macierze.getRow(j).getIndex());
				double v = 0;
				int size = macierze.getColumn(i).getValues().length;
				for (int m=0; m<size; m++) {
					v += macierze.getColumn(i).getValue(m)
						*macierze.getRow(j).getValue(m);
				}
				e.setWartoœæ(v);
				elements.add(e);
			}
		}
		result.setElements(elements);
		return result;
	}

	public void mergeInverted(ResultDto result, double[][] ab) {
		for (Element e : result.getElements()) {
			ab[e.getKolumna()][e.getWiersz()] = e.getWartoœæ();
		}
	}
	
	public void merge(ResultDto result, double[][] ab) {
		for (Element e : result.getElements()) {
			ab[e.getWiersz()][e.getKolumna()] = e.getWartoœæ();
		}
	}
}
