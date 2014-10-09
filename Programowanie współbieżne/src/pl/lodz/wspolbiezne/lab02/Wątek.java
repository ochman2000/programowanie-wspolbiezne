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
		System.out.println("Threads running: "+Zadanie02.getThreadCounter());
		if (Zadanie02.decrementThreadCounter()==0) {
			printSummary();
		}
	}
	
	private void printSummary() {
		System.out.println(Arrays.toString(histogram));
		System.out.println("Liczba bajtów: " + IntStream.of(histogram).sum());
		long endTime = System.currentTimeMillis();
		System.out.println("Czas wykonania: " + (endTime - startTime) + "ms");
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
