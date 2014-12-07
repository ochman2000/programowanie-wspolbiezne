package pl.lodz.wspolbiezne.lab05;

import java.util.logging.Logger;

public class Zasób {
	private char[] zasób;
	private int count = 0, in = 0, out = 0;

	public Zasób(int size) {
		zasób = new char[size];
	}

	public synchronized void put(char c) {
		while (count == zasób.length) {
			Logger.getGlobal().info("Zasób jest pe³ny, wiêc usypiam w¹tek.");
			try {
				wait();
			} catch (InterruptedException e) {
				Logger.getGlobal().info("Nast¹pi³o przerwanie w metodzie put");
			}
		}
		Logger.getGlobal().info("-->> " + c + " -->>");
		zasób[in] = c;
		in = (in + 1) % zasób.length;
		count++;
		notifyAll();
	}

	public synchronized char take() {
		while (count == 0) {
			Logger.getGlobal().fine("Zasób jest pusty, wiêc usypiam w¹tek.");
			try {
				wait();
			} catch (InterruptedException e) {
				Logger.getGlobal().info("Nast¹pi³o przerwanie w metodzie take");
			} 
		}
		char c = zasób[out];
		out = (out + 1) % zasób.length;
		count--;
		Logger.getGlobal().info("<<-- " + c + " <<--");
		notifyAll();
		return c;
	}
}