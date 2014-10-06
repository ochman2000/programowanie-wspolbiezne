package pl.lodz.wspolbiezne.lab02;

public class Thread implements Runnable {

	private int startIndex;
	private int endIndex;
	private byte[] tablicaBajtow;
	private byte[] histogram;
	
	@Override
	public void run() {
		for (int i = startIndex; i < endIndex; i++) {
			histogram[tablicaBajtow[i]]++;
		}
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public byte[] getTablicaBajtow() {
		return tablicaBajtow;
	}

	public void setTablicaBajtow(byte[] tablicaBajtow) {
		this.tablicaBajtow = tablicaBajtow;
	}

	public byte[] getHistogram() {
		return histogram;
	}

	public void setHistogram(byte[] histogram) {
		this.histogram = histogram;
	}
}
