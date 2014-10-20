package pl.lodz.wspolbiezne.tutorial;

public class MyThread extends Thread {

	public int total;
//	private MySignal sharedSignal;
	private MyWaitNotify sharedSignal;

//	public MyThread(MySignal signal) {
//		this.sharedSignal = signal;
//	}
	
	public MyThread() {
		super();
	}

	public MyThread(MyWaitNotify signal) {
		super();
		this.sharedSignal = signal;
	}

	@Override
	public void run() {
		System.out.println("do nothing... busy waiting");
		System.out.println("Finished waiting.");
	}
}
