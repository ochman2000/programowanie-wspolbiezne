package pl.lodz.wspolbiezne.lab05;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Zadanie05 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Podaj œcie¿kê, od ktorej chcesz rozpocz¹æ.");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile): ");
		String keyword = in.nextLine();
		
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
