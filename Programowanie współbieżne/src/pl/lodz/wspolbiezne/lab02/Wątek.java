package pl.lodz.wspolbiezne.lab02;

public class W¹tek implements Runnable {

	private int startIndex;
	private int endIndex;
	private byte[] tablicaBajtow;
	private int[] histogram;
	
	public W¹tek() {
		histogram = new int[256];
	}
	
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

	public int[] getHistogram() {
		return histogram;
	}

	public void setHistogram(int[] histogram) {
		this.histogram = histogram;
	}
}
