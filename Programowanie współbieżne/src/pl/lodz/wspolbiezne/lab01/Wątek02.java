package pl.lodz.wspolbiezne.lab01;

public class W¹tek02 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			System.out.println("W¹tek 2");
		}
	}
}
