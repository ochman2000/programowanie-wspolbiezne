package pl.lodz.wspolbiezne.lab05;

class Serwer extends Thread {
	private Zasób zasób;

	Serwer(Zasób b) {
		zasób = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			zasób.take();
		}
	}
}