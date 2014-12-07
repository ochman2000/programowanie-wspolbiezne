package pl.lodz.wspolbiezne.lab05;

import java.util.logging.Logger;

class Serwer extends Thread {
	private Zasób zasób;

	Serwer(Zasób b) {
		zasób = b;
	}

	public void run() {
		while (!zasób.tasksFinished()) {
			zasób.take();
		}
		Logger.getGlobal().info("Zakoñczono procesowanie wszystkich zleconych zadañ.");
	}
}