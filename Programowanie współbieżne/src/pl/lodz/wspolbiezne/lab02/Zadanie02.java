package pl.lodz.wspolbiezne.lab02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Zadanie02 {

	private static int ROZMIAR_TABLICY = 4_000_000;
	private Thread[] threads;
	private byte[] tablicaBajtow;
	private int[] histogram;

	public static void main(String[] args) {
//		new Zadanie02(1);
//		new Zadanie02(2);
		new Zadanie02(4);
		// new Zadanie02(6);
		// new Zadanie02(8);
		// new Zadanie02(10);
		// new Zadanie02(12);
		// new Zadanie02(14);
		// new Zadanie02(160);
	}

	public Zadanie02(int threadsNumber) {
		long startTime = System.currentTimeMillis();
		histogram = new int[256];
		tablicaBajtow = initTablicaBajtow();
		threads = initThreads(threadsNumber);
		startAll();
		printSummary(startTime);
	}

	private void printSummary(long startTime) {
		for (Thread t : threads) {
			try {
				t.join(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (areNotAlive()) {
			System.out.println(Arrays.toString(histogram));
			System.out.println("Liczba bajtów: " + IntStream.of(histogram).sum());
			long endTime = System.currentTimeMillis();
			System.out.println("Czas wykonania: " + (endTime - startTime) + "ms");
		}
	}
	
	private boolean areNotAlive() {
		for (Thread w : threads) {
			if (w.isAlive()) {
				System.out.println("W¹tek jest alive");
				return false;
			}
		}
		System.out.println("Wszystkie w¹tki s¹ dead.");
		return true;
	}

	private Thread[] initThreads(int threadsNumber) {
		if (threadsNumber < 1) {
			throw new IllegalArgumentException(
					"Liczb¹ w¹tków musi byæ wiêksza od 0");
		}
		Thread[] thread = new Thread[threadsNumber];
		for (int interval = 0; interval < threadsNumber; interval++) {
			W¹tek w = new W¹tek();
			w.setStartIndex(getBeginningOfInterval(interval, threadsNumber));
			w.setEndIndex(getEndOfInterval(interval, threadsNumber));
			w.setTablicaBajtow(tablicaBajtow);
			w.setHistogram(histogram);
			thread[interval] = new Thread(w);
		}
		return thread;
	}

	private byte[] initTablicaBajtow() {
		Path path = Paths.get("resources/bajty.dat");
		byte[] bajty = null;
		try {
			if (!Files.exists(path)) {
				bajty = new byte[ROZMIAR_TABLICY];
				new Random().nextBytes(bajty);
				Files.write(path, bajty);
			} else {
				bajty = Files.readAllBytes(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bajty;
	}

	public static int getBeginningOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia³ nie mo¿e byæ wiêkszy ni¿: " + totalIntervals
							+ " a podano: " + interval);
		}
		double fraction = (double) interval / (double) totalIntervals;
		return (int) (fraction * ROZMIAR_TABLICY);
	}

	public static int getEndOfInterval(int interval, int totalIntervals) {
		double rozmiarPrzedzialu = (double) ROZMIAR_TABLICY
				/ (double) totalIntervals;
		double fraction = (double) interval / (double) totalIntervals;
		return (int) ((fraction * ROZMIAR_TABLICY) + rozmiarPrzedzialu);
	}

	public void startAll() {
		int i = 0;
		for (Thread t : threads) {
			t.start();
			System.out.println("W¹tek " + ++i + " uruchomiony");
		}
	}
}
