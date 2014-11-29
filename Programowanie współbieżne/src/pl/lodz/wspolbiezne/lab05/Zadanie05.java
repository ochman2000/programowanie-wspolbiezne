package pl.lodz.wspolbiezne.lab05;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Zadanie05 {
	
	private static final String SCIEZKA_DO_PLIKU = "resources";

	public static void main(String[] args) {
		String directory = SCIEZKA_DO_PLIKU;
		String keyword = "Marcel";
		System.out.println("Trwa poszukiwanie s³owa: \"Marcel\"");
		
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 100;
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
		KlientTask klient = new KlientTask(queue, new File(directory));
		new Thread(klient).start();
		for (int i=0; i<=SEARCH_THREADS; i++) {
			new Thread(new ServerTask(queue, keyword)).start();
		}
	}
}
