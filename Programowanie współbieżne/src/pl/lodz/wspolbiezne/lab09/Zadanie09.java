package pl.lodz.wspolbiezne.lab09;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Zadanie09 {
	private static final String SCIEZKA_DO_PLIKU = "resources/bajty.dat";
	private static int ROZMIAR_MACIERZY = 1024;
	
	public static void main(String[] args) {
		
	}
	
	public Zadanie09() {
		
	}
	
	private double[][] initMacierz() {
		Path path = Paths.get(SCIEZKA_DO_PLIKU);
		double[][] doubles = null;
		try {
			if (!Files.exists(path)) {
				doubles = getRandomValues();
				@SuppressWarnings("resource")
				ObjectOutputStream oos = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream(SCIEZKA_DO_PLIKU)));
				oos.writeObject(doubles);
			} else {
				@SuppressWarnings("resource")
				ObjectInputStream ois = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(SCIEZKA_DO_PLIKU)));
				doubles = (double[][])ois.readObject();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return doubles;
	}
	
	private double[][] getRandomValues() {
		double[][] d = new double[ROZMIAR_MACIERZY][ROZMIAR_MACIERZY];
		Random rnd = new Random();
		for (int i=0; i<ROZMIAR_MACIERZY; i++) {
			for (int j=0; j<ROZMIAR_MACIERZY; j++) {
				d[i][j] = rnd.nextDouble()*10-5;
			}
		}
		return d;
	}
}
