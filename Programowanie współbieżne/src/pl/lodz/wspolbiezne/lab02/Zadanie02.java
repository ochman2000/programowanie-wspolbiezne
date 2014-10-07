package pl.lodz.wspolbiezne.lab02;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Zadanie02 {
	
	private static int ROZMIAR_TABLICY = 4_000_000; 
	private W¹tek[] threads;
	private byte[] tablicaBajtow;
	private int[] histogram;

	public static void main(String[] args) {
		new Zadanie02(1);
//		new Zadanie02(2);
//		new Zadanie02(4);
//		new Zadanie02(6);
//		new Zadanie02(8);
//		new Zadanie02(10);
//		new Zadanie02(12);
//		new Zadanie02(14);
//		new Zadanie02(16);
	}
	
	public Zadanie02(int threadsNumber) {
		histogram = new int[256];
		tablicaBajtow = initTablicaBajtow();
		threads = initThreads(threadsNumber);
		startAll();
		System.out.println(Arrays.toString(histogram));
		System.out.println(IntStream.of(histogram).sum());
	}
	

	private W¹tek[] initThreads(int threadsNumber) {
		if (threadsNumber<0) {
			throw new IllegalArgumentException("Liczb¹ w¹tków musi byæ wiêksza od 0");
		}
		W¹tek[] thread = new W¹tek[threadsNumber];
		for (int interval=0; interval<threadsNumber; interval++) {
			W¹tek w = new W¹tek();
			w.setStartIndex(getBeginningOfInterval(interval, threadsNumber));
			w.setEndIndex(getEndOfInterval(interval, threadsNumber));
			w.setTablicaBajtow(tablicaBajtow);
			w.setHistogram(histogram);
			thread[interval] = w;
		}
		return thread;
	}
	
	private byte[] initTablicaBajtow() {
		byte[] tablicaBajtow = new byte[ROZMIAR_TABLICY];
		new Random().nextBytes(tablicaBajtow);
		return tablicaBajtow;
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
		int i = 0;
		for (W¹tek w : threads) {
			Thread t = new Thread(w);
			t.start();
			System.out.println("W¹tek "+ ++i+" uruchmiony");
		}
	}
}
