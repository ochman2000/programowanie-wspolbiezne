package pl.lodz.wspolbiezne.lab07;

import java.io.Serializable;
import java.util.List;

public class MacierzeDto implements Serializable {
	
	private static final long serialVersionUID = 1363948434542689151L;
	private List<Zbiór> rows;
	private List<Zbiór> columns;

	public List<Zbiór> getRows() {
		return rows;
	}

	public void setRows(List<Zbiór> values) {
		this.rows = values;
	}
	
	public Zbiór getRow(int index) {
		return getRows().get(index);
	}
	
	public List<Zbiór> getColumns() {
		return columns;
	}
	
	public Zbiór getColumn(int index) {
		return getColumns().get(index);
	}

	public void setColumns(List<Zbiór> columns) {
		this.columns = columns;
	}
}

class Zbiór implements Serializable {
	private static final long serialVersionUID = 1363948434542689151L;
	private List<Double> values;
	private int index;
	public List<Double> getValues() {
		return values;
	}
	
	public Double getValue(int index) {
		return getValues().get(index);
	}
	
	public void setValues(List<Double> values) {
		this.values = values;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}