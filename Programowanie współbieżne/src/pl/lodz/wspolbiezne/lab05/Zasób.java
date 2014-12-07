package pl.lodz.wspolbiezne.lab05;

import java.util.logging.Logger;

/**
 * To jest de facto Monitor zawieraj¹cy zasób (tablicê znaków).
 * Ten obiekt jest przekazywany do konstruktora w¹tku i przekazywany
 * z "r¹k do r¹k".
 * 
 * @author £ukasz Ochmañski, Marcel Wieczorek
 *
 */
public class Zasób {
	private char[] zasób;
	private int count, in, out, literka;
	private static int tasks;

	public Zasób(int size) {
		zasób = new char[size];
	}

	public synchronized void put() {
		while (count == zasób.length) {
			Logger.getGlobal().info("Zasób jest pe³ny, wiêc usypiam w¹tek.");
			try {
				wait();
			} catch (InterruptedException e) {
				Logger.getGlobal().info("Nast¹pi³o przerwanie w metodzie put");
			}
		}
		char c =(char) ('A' + literka % 26);
		Logger.getGlobal().info("-->> " + c + " -->>");
		zasób[in] = c;
		in = (in + 1) % zasób.length;
		literka++;
		count++;
		notifyAll();
	}

	public synchronized char take() {
		while (count == 0) {
			Logger.getGlobal().info("Zasób jest pusty, wiêc usypiam w¹tek.");
			try {
				wait();
			} catch (InterruptedException e) {
				Logger.getGlobal().info("Nast¹pi³o przerwanie w metodzie take");
			} 
		}
		char c = zasób[out];
		out = (out + 1) % zasób.length;
		count--;
		tasks--;
		Logger.getGlobal().info("<<-- " + c + " <<--");
		notifyAll();
		return c;
	}
	
	public synchronized boolean tasksFinished() {
		return tasks==0;
	}

	public synchronized void addTaskCount(int znaków) {
		tasks +=znaków;
	}
}