package pl.lodz.wspolbiezne.lab05;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PC {
	public static void main(String[] args) {
		Logger.getGlobal().setLevel(Level.FINE);
		Buffer b = new Buffer(4);
		Producer p = new Producer(b);
		Consumer c = new Consumer(b);

		p.start();
		c.start();
	}
}

class Buffer {
	private char[] buffer;
	private int count = 0, in = 0, out = 0;

	Buffer(int size) {
		buffer = new char[size];
	}

	public synchronized void put(char c) {
		while (count == buffer.length) {
			Logger.getGlobal().info("Metoda put release the monitor.");
			try { wait(); }
            catch (InterruptedException e) { } 
            finally { }
		}
		System.out.println("Producing " + c + " ...");
		buffer[in] = c;
		in = (in + 1) % buffer.length;
		count++;
		notifyAll();
	}

	public synchronized char take() {
		while (count == 0) {
			Logger.getGlobal().fine("Metoda take release the monitor.");
			try { wait(); }
            catch (InterruptedException e) { } 
            finally { }
			
		}
		char c = buffer[out];
		out = (out + 1) % buffer.length;
		count--;
		System.out.println("Consuming " + c + " ...");
		notifyAll();
		return c;
	}
}

class Producer extends Thread {
	private Buffer buffer;

	Producer(Buffer b) {
		buffer = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			buffer.put((char) ('A' + i % 26));
		}
	}
}

class Consumer extends Thread {
	private Buffer buffer;

	Consumer(Buffer b) {
		buffer = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			buffer.take();
		}
	}
}