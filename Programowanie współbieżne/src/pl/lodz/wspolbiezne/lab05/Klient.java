package pl.lodz.wspolbiezne.lab05;

public class Klient extends Thread {
	private Zasób zasób;

	Klient(Zasób b) {
		zasób = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			zasób.put((char) ('A' + i % 26));
		}
	}
}