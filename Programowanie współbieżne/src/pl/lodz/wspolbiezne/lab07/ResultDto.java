package pl.lodz.wspolbiezne.lab07;

import java.io.Serializable;
import java.util.List;

public class ResultDto implements Serializable {
	
	private static final long serialVersionUID = 1363948434542689151L;
	
	private List<Element> elements;
	
	public List<Element> getElements() {
		return elements;
	}
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Element element : elements) {
			sb.append(element.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public Element getElement(int i) {
		return getElements().get(i);
	}
}

class Element implements Serializable {
	private static final long serialVersionUID = 1363948434542689151L;
	private int wiersz;
	public Element(int j, int i, int k) {
		this.kolumna = j;
		this.wiersz = i;
		this.wartoœæ = k;
	}
	
	public Element() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "["+getKolumna()+"]["+getWiersz()+"]="+getWartoœæ();
	}

	public int getWiersz() {
		return wiersz;
	}
	public void setWiersz(int wiersz) {
		this.wiersz = wiersz;
	}
	public int getKolumna() {
		return kolumna;
	}
	public void setKolumna(int kolumna) {
		this.kolumna = kolumna;
	}
	public int getWartoœæ() {
		return wartoœæ;
	}
	public void setWartoœæ(int wartoœæ) {
		this.wartoœæ = wartoœæ;
	}
	private int kolumna;
	private int wartoœæ;
}
