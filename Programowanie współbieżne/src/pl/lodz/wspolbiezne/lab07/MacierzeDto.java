package pl.lodz.wspolbiezne.lab07;

import java.io.Serializable;
import java.util.List;

public class MacierzeDto implements Serializable {

	private static final long serialVersionUID = 1363948434542689151L;
	private Zbiór[] rows;
	private Zbiór[] columns;

	public Zbiór[] getRows() {
		return rows;
	}

	public void setRows(Zbiór[] values) {
		this.rows = values;
	}

	public void setRows(List<Zbiór> rows) {
		Zbiór[] target = new Zbiór[rows.size()];
		for (int i = 0; i < target.length; i++) {
			target[i] = rows.get(i);
		}
		this.rows = target;
	}

	public Zbiór getRow(int index) {
		return rows[index];
	}

	public Zbiór[] getColumns() {
		return columns;
	}

	public Zbiór getColumn(int index) {
		return columns[index];
	}

	public void setColumns(List<Zbiór> columns) {
		Zbiór[] target = new Zbiór[columns.size()];
		for (int i = 0; i < target.length; i++) {
			target[i] = columns.get(i);
		}
		this.columns = target;
	}

}

class Zbiór implements Serializable {
	private static final long serialVersionUID = 1363948434542689151L;
	private double[] values;
	private int index;

	public double[] getValues() {
		return values;
	}

	public Double getValue(int index) {
		return values[index];
	}

	public void setValues(List<Double> values) {
		this.values = values.stream().mapToDouble(Double::doubleValue)
				.toArray();
	}

	public void setValues(double[] values) {
		this.values = values;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}