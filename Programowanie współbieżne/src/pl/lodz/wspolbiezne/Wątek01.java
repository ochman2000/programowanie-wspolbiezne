package pl.lodz.wspolbiezne;

public class W�tek01 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			System.out.println("W�tek 1");
		}
	}
}
