package pl.lodz.wspolbiezne.tutorial;

public class Test02 {

	public static void main(String[] args) {

		MyWaitNotify mySignal = new MyWaitNotify();
		
		Thread a = new Thread(){
			@Override
			public void run() {
				for (int i=0; i<100000; i++) {
					System.out.println("Thread A: "+i);
				}
				mySignal.doNotify();
			}
		};
		
		Thread b = new Thread(){
			@Override
			public void run() {
				mySignal.doWait();
				for (int i=0; i<100000; i++) {
					System.out.println("Thread B: "+ i+"\t\tThread A finished...");
				}
			}
		};

		b.start();
		a.start();
	}
}
