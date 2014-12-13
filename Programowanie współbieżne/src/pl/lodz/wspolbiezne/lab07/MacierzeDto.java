package pl.lodz.wspolbiezne.lab07;

import java.util.Arrays;

public class MacierzeDto {
	
	private Macierz rows;
	private Macierz columns;
	
	public Macierz getRows() {
		return rows;
	}
	public void setRows(Macierz rows) {
		this.rows = rows;
	}
	public Macierz getColumns() {
		return columns;
	}
	public void setColumns(Macierz columns) {
		this.columns = columns;
	}
}

class Matrix {
	private int[] wartosc;
	private int index;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int[] getWartosc() {
		return wartosc;
	}

	public void setWartosc(int[] wartosc) {
		this.wartosc = wartosc;
	}
	
	public String toString() {
		return Arrays.toString(wartosc);
	}
}

class Macierz {
	private Matrix[] values;
	
	public Matrix[] getValues() {
		return values;
	}

	public void setValues(Matrix[] values) {
		this.values = values;
	}
	
	public String toString() {
		if (values == null)
			return "null";

		int iMax = values.length - 1;
		if (iMax == -1)
			return "[]";

		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0;; i++) {
			b.append(values[i].toString());
			if (i == iMax)
				return b.append(']').toString();
			b.append(",\n");
		}
	}
}
