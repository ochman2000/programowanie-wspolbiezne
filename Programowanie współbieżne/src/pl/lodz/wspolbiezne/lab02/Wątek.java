package pl.lodz.wspolbiezne.lab02;

import java.util.Arrays;
import java.util.stream.IntStream;

public class W¹tek implements Runnable {

	private long startTime;
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
//		System.out.println("Threads running: "+Zadanie02.getThreadCounter());
		if (Zadanie02.decrementThreadCounter()==0) {
//			printSummary();
			getSummary();
		}
	}
	
//	private void printSummary() {
//		System.out.print(getSummary());
//	}
	
	private StringBuilder getSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append(Arrays.toString(histogram));
		sb.append("\n");
		sb.append("Liczba bajtów: ");
		sb.append(IntStream.of(histogram).sum());
		sb.append("\n");
		long endTime = System.currentTimeMillis();
		sb.append("Czas wykonania: ");
		long czasWykonania = endTime - startTime;
		Zadanie02.add(czasWykonania);
		sb.append(czasWykonania);
		sb.append("ms");
		sb.append("\n");
		return sb;
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

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
