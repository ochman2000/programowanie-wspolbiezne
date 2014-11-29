package pl.lodz.wspolbiezne.lab05;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class KlientTask implements Runnable {
	
	public static File DUMMY = new File("");
	private File startingDirectory;
	private BlockingQueue<File> queue;
	
	public KlientTask(BlockingQueue<File> queue, File startingDirectory) {
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}

	@Override
	public void run() {
		try {
			skanujFoldery(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void skanujFoldery(File directory) throws InterruptedException {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				queue.put(file);
				System.out.println("Pzetwarzanie pliku:\t"+file.getAbsoluteFile());
			}
			else {
				skanujFoldery(file);
			}
		}
	}
}
