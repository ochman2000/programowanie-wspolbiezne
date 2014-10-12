package pl.lodz.wspolbiezne.lab04;

public class Zadanie04 {

	public static void main(String[] args) {
		long pause = 500;
		try {
			for (int i=0; i<10; i++) {
				new MainThread(1);
				Thread.sleep(pause);
				new MainThread(2);
				Thread.sleep(pause);
				new MainThread(4);
				Thread.sleep(pause);
				new MainThread(6);
				Thread.sleep(pause);
				new MainThread(8);
				Thread.sleep(pause);
				new MainThread(10);
				Thread.sleep(pause);
				new MainThread(12);
				Thread.sleep(pause);
				new MainThread(14);
				Thread.sleep(pause);
				new MainThread(160);
				Thread.sleep(pause);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
