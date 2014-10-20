package pl.lodz.wspolbiezne.tutorial;

public class Test02 {

	public static void main(String[] args) {

		MyWaitNotify mySignal = new MyWaitNotify();
		
		MyThread a = new MyThread(){
			@Override
			public void run() {
				for (int i=0; i<100000; i++) {
					System.out.println("Thread A!");
				}
			}
		};
		
		MyThread b = new MyThread(){
			@Override
			public void run() {
				for (int i=0; i<100000; i++) {
					System.out.println("Thread B!");
				}
			}
		};
		
//		new Thread(() -> System.out.println("Hello world !")).start();

		a.start();
		b.start();
	}
}
