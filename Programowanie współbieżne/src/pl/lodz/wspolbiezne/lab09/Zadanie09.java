package pl.lodz.wspolbiezne.lab09;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Zadanie09 {
	private static final String SCIEZKA_DO_PLIKU = "resources/macierze/doubles.dat";
	private static final boolean USE_FILE = false;
	private static final int ROZMIAR_MACIERZY = 1024;
	private static final int LICZBA_W¥TKÓW = 4;
	
	public static void main(String[] args) {
		new Zadanie09();
	}
	
	public Zadanie09() {
		double[][] macierz = initMacierz();
		performGaussJordan(macierz);
	}
	
	private double[][] initMacierz() {
		Path path = Paths.get(SCIEZKA_DO_PLIKU);
		double[][] doubles = null;
		if (USE_FILE) {
			try {
				if (!Files.exists(path)) {
					doubles = getRandomValues();
					ObjectOutputStream oos = new ObjectOutputStream(
							new BufferedOutputStream(new FileOutputStream(SCIEZKA_DO_PLIKU)));
					oos.writeObject(doubles);
					oos.close();
				} else {
					ObjectInputStream ois = new ObjectInputStream(
							new BufferedInputStream(new FileInputStream(SCIEZKA_DO_PLIKU)));
					doubles = (double[][])ois.readObject();
					ois.close();
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			doubles = getRandomValues();
		}
		return doubles;
	}
	
	private double[][] getRandomValues() {
		int n = ROZMIAR_MACIERZY;
		int m = n+1;
		double[][] d = new double[n+1][m+1];
		Random rnd = new Random();
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=m; j++) {
				d[i][j] = rnd.nextDouble()*10;
			}
		}
		return d;
	}
	
	private void performGaussJordan(double[][] macierz) {
		Runnable barrier1Action = new Runnable() {
		    public void run() {
		    	try {
					System.out.println("PartialPivoting/Swap trwa...");
					Thread.sleep(5000);
			        System.out.println("PartialPivoting/Swap zrobione ");
			        Thread.sleep(1000);
			        System.out.println("FORK: Rozdzielenie zadañ na wiele w¹tków");	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		};
		
		Runnable barrier2Action = new Runnable() {

			public void run() {
		    	try {
					Thread.sleep(5000);
			    	System.out.println("JOIN: Z³¹czenie wyników obliczeñ.");
			        Thread.sleep(1000);
			    	System.out.println("Eliminate/divide dla wszystkich w¹tków zrobione ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		};
		
		initializeCyclicBarriers(barrier1Action, barrier2Action, macierz);
	}

	private void initializeCyclicBarriers(Runnable barrier1Action,
			Runnable barrier2Action, double[][] macierz) {
		CyclicBarrier barrier1 = new CyclicBarrier(LICZBA_W¥TKÓW, barrier1Action);
		CyclicBarrier barrier2 = new CyclicBarrier(LICZBA_W¥TKÓW, barrier2Action);
		
		Thread[] threads = new Thread[LICZBA_W¥TKÓW];
	
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new CyclicBarrierRunnable(barrier1, barrier2,
					macierz));
		}
		for (Thread thread : threads) {
			thread.start();
		}
	}
	
}
