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
}
