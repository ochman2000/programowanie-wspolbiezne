package pl.lodz.wspolbiezne.lab07;

import java.io.Serializable;
import java.util.List;

public class MacierzeDto implements Serializable {
	
	private static final long serialVersionUID = 1363948434542689151L;
	private List<Zbiór> rows;
	private List<Zbiór> columns;
	
//	public String toString() {
//		if (rows == null)
//			return "null";
//
//		int iMax = rows.size() - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0;; i++) {
//			b.append(rows.get(i).getWartosc());
//			if (i == iMax) {
//				b.append("]\t");
//				break;
//			}
//			b.append(",");
//		}
//		b.append('[');
//		for (int i = 0;; i++) {
//			b.append(columns.get(i).getWartosc());
//			if (i == iMax) {
//				return b.append(']').toString();
//			}
//			b.append(",");
//		}
//	}
	
	public List<Zbiór> getRows() {
		return rows;
	}

	public void setRows(List<Zbiór> values) {
		this.rows = values;
	}
	
	public List<Zbiór> getColumns() {
		return columns;
	}

	public void setColumns(List<Zbiór> columns) {
		this.columns = columns;
	}
}


class Element implements Serializable {
	private static final long serialVersionUID = 1363948434542689151L;
	private int wartosc;
	private int wiersz;
	private int kolumna;

	public Element() {
		
	}
	
	public Element(int wiersz, int kolumna, int wartosc) {
		this.wiersz = wiersz;
		this.kolumna = kolumna;
		this.wartosc = wartosc;
	}
	
	public String toString() {
		return "["+getKolumna()+"]"+"["+getWiersz()+"]="+getWartosc();
	}
	
	public int getWiersz() {
		return wiersz;
	}

	public int getKolumna() {
		return kolumna;
	}

	public int getWartosc() {
		return wartosc;
	}

	public void setWartosc(int wartosc) {
		this.wartosc = wartosc;
	}

	public void setWiersz(int wiersz) {
		this.wiersz = wiersz;
	}

	public void setKolumna(int kolumna) {
		this.kolumna = kolumna;
	}
}

class Zbiór implements Serializable {
	private List<Element> values;
	private int index;
	public List<Element> getValues() {
		return values;
	}
	public void setValues(List<Element> values) {
		this.values = values;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}