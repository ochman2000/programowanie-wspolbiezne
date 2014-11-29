package pl.lodz.wspolbiezne.lab05;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ServerTask implements Runnable {
	
	private BlockingQueue<File> queue;
	private String keyword;

	public ServerTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}
	
	public void run() {
		try {
			boolean done = false;
			while (!done) {
				File file = queue.take();
				System.out.println("Zleceono obs³ugê:\t"+file.getAbsoluteFile());
				if (file==KlientTask.DUMMY) {
					queue.put(file);
					done = true;
					System.out.println("Zakoñczono przetwarzanie.");
				}
				else {
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
				System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
			}
		}
		in.close();
	}
}
