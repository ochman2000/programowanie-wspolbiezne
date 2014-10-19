package pl.lodz.wspolbiezne.tutorial;

public class MyThread extends Thread {

	public int total;
	private MySignal sharedSignal;

	public MyThread(MySignal signal) {
		this.sharedSignal = signal;
	}
	
	@Override
	public void run() {
		while(!sharedSignal.hasDataToProcess()){
			  System.out.println("do nothing... busy waiting");
		}
		System.out.println("Finished waiting.");
	}
}
