package pl.lodz.wspolbiezne.lab09;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample03 {
	
	private static boolean done = false;
	private static final int LICZBA_W¥TKÓW = 4;

	public static void main(String[] args) {
		
		new CyclicBarrierExample03();
		
	}
	
	public CyclicBarrierExample03() {
		
			performGaussJordan();
			
		
	}

	private void performGaussJordan() {
		Runnable barrier1Action = new Runnable() {
		    public void run() {
		    	try {
					System.out.println("PartialPivoting/Swap trwa...");
					Thread.sleep(5000);
			        System.out.println("PartialPivoting/Swap zrobione ");
			        Thread.sleep(1000);
			        System.out.println("FORK: Rozdzielenie zadañ na wiele w¹tków");	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		};
		
		Runnable barrier2Action = new Runnable() {

			public void run() {
		    	try {
					Thread.sleep(5000);
			    	System.out.println("JOIN: Z³¹czenie wyników obliczeñ.");
			        Thread.sleep(1000);
			    	System.out.println("Eliminate/divide dla wszystkich w¹tków zrobione ");
			    	if (!done) {
			    		performGaussJordan();
			    	}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		};
		
		initializeCyclicBarriers(barrier1Action, barrier2Action);
	}

	private void initializeCyclicBarriers(Runnable barrier1Action,
			Runnable barrier2Action) {
		CyclicBarrier barrier1 = new CyclicBarrier(LICZBA_W¥TKÓW, barrier1Action);
		CyclicBarrier barrier2 = new CyclicBarrier(LICZBA_W¥TKÓW, barrier2Action);
		
		Thread[] threads = new Thread[LICZBA_W¥TKÓW];
	
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new CyclicBarrierRunnable(barrier1, barrier2));
		}
		for (Thread thread : threads) {
			thread.start();
		}
	}
}
