package pl.lodz.wspolbiezne.lab02;

import java.util.Random;

public class Zadanie02 {
	
	private static int ROZMIAR_TABLICY = 4_000_000; 
	private W¹tek[] threads;
	private byte[] tablicaBajtow;

	public static void main(String[] args) {

	}
	
	public Zadanie02(int threadsNumber) {
		tablicaBajtow = initTablicaBajtow();
		threads = initThreads(threadsNumber);
		startAll();
		
	}
	

	private W¹tek[] initThreads(int threadsNumber) {
		W¹tek[] thread = new W¹tek[threadsNumber];
		for (int interval=0; interval<threadsNumber; interval++) {
			W¹tek w = new W¹tek();
			w.setStartIndex(getBeginningOfInterval(interval, threadsNumber));
			w.setEndIndex(getEndOfInterval(interval, threadsNumber));
			thread[interval] = w;
		}
		return thread;
	}
	
	private byte[] initTablicaBajtow() {
		byte[] tablicaBajtow = new byte[ROZMIAR_TABLICY];
		new Random().nextBytes(tablicaBajtow);
		return tablicaBajtow;
	}
	
	private Histogram getHistogram() {
		for (W¹tek w : threads) {
			w.getHistogram();
		}
	}

	public static int getBeginningOfInterval(int interval, int totalIntervals) {
		if (totalIntervals<=interval) {
			throw new IllegalArgumentException("Przedzia³ nie mo¿e byæ wiêkszy ni¿: "
					+totalIntervals+" a podano: "+interval);
		}
		double fraction = (double)interval/(double)totalIntervals;
		return (int) (fraction * ROZMIAR_TABLICY);
	}
	
	public static int getEndOfInterval(int interval, int totalIntervals) {
		double rozmiarPrzedzialu = (double)ROZMIAR_TABLICY/(double)totalIntervals;
		double fraction = (double)interval/(double)totalIntervals; 
		return (int) ((fraction * ROZMIAR_TABLICY) + rozmiarPrzedzialu);
	}
	
	public void startAll() {
		for (W¹tek w : threads) {
			Thread t = new Thread(w);
			t.start(); 
		}
	}
}
