package pl.lodz.wspolbiezne.tutorial;

public class Test02 {

	public static void main(String[] args) {

		MyWaitNotify mySignal = new MyWaitNotify();
		
		Thread a = new Thread(){
			@Override
			public void run() {
				for (int i=0; i<100000; i++) {
					mySignal.doWait();
					System.out.println("\t\tThread A: "+i);
				}
			}
		};
		
		Thread b = new Thread(){
			@Override
			public void run() {
				for (int i=0; i<100000; i++) {
					mySignal.doNotify();
					System.out.println("Thread B: "+ i);
				}
			}
		};
		
//		new Thread(() -> System.out.println("Hello world !")).start();

		a.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b.start();
	}
}
