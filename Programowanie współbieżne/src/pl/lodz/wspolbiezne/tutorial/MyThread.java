package pl.lodz.wspolbiezne.tutorial;

public class MyThread extends Thread {

	private MySignal sharedSignal;

	public MyThread(MySignal signal) {
		super();
		this.sharedSignal = signal;
	}

	@Override
	public void run() {
		while (!sharedSignal.hasDataToProcess) {
			System.out.println("do nothing... busy waiting");
		}
		System.out.println("Finished waiting.");
	}
}
