package pl.lodz.wspolbiezne.lab05;

import java.util.logging.Logger;

public class Klient extends Thread {
	private Zasób zasób;
	private long pauza;
	private int znaków;

	Klient(Zasób b, long pauza, int znaków) {
		this.zasób = b;
		this.pauza = pauza;
		this.znaków = znaków;
		b.addTaskCount(znaków);
	}

	public void run() {
		for (int i = 0; i < znaków; i++) {
			zasób.put();
			try {
				sleep(pauza);
			} catch (InterruptedException e) {
				Logger.getGlobal().info("Ktoœ przerwa³ mój sen!");
			}
		}
	}
}