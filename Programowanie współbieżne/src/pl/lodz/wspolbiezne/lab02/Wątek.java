package pl.lodz.wspolbiezne.lab02;

public class W¹tek implements Runnable {

	private int startIndex;
	private int endIndex;
	private byte[] tablicaBajtow;
	private int[] histogram;
	
	@Override
	public void run() {
		for (int i = startIndex; i < endIndex; i++) {
			if (Byte.toUnsignedInt(tablicaBajtow[i]) <0 
					|| Byte.toUnsignedInt(tablicaBajtow[i]) >255) {
				throw new IllegalArgumentException("Wartoœæ indeksu tablicy"
						+ " poza zakresem 0-255: "+
						Byte.toUnsignedInt(tablicaBajtow[i]));
			}
			histogram[Byte.toUnsignedInt(tablicaBajtow[i])]++;
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
