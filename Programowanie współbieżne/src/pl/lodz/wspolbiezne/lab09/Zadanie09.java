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

public class Zadanie09 {
	private static final String SCIEZKA_DO_PLIKU = "resources/macierze/doubles.dat";
	private static boolean USE_FILE = false;
	private static int ROZMIAR_MACIERZY = 1024;
	
	public static void main(String[] args) {
		new Zadanie09();
	}
	
	public Zadanie09() {
		double[][] macierz = initMacierz();
		new GaussJordan(macierz);
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
		// declare d to be of size (n+1)x(m+1) and do not use index 0
		double[][] d = new double[n+1][m+1];
		Random rnd = new Random();
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=m; j++) {
				d[i][j] = rnd.nextDouble()*10;
			}
		}
		return d;
	}
}
