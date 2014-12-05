package pl.lodz.wspolbiezne.lab05;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
	
	private BlockingQueue<File> queue;
	private String keyword;
	private static int serverThreadCount;

	public Server(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}
	
	public void run() {
		try {
			incrementServerThreadCount();
			boolean done = false;
			while (!done) {
				File file = queue.take();
				if (file==KlientTask.DUMMY) {
					queue.put(file);
					done = true;
					decrementServerThreadCount();
				}
				else {
					System.out.println("Przetwarzanie pliku:\t"+file.getAbsolutePath());
					search(file);
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void search(File file) throws IOException {
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		while (in.hasNextLine()) {
			lineNumber++;
			String line = in.nextLine();
			if (line.contains(keyword)) {
				System.out.printf("%s%s%s%d%n", "Znaleziono s³owo kluczowe w pliku: ", file.getName(), " w lini: ", lineNumber);
			}
		}
		in.close();
	}

	public static int decrementServerThreadCount() {
		System.out.println("Zamykanie w¹tku. (Running threads: "+ --serverThreadCount +")");
		return serverThreadCount;
	}

	public static int incrementServerThreadCount() {
		System.out.println("Otwieranie w¹tku. (Running threads: "+ ++serverThreadCount +")");
		return serverThreadCount;
	}
}
