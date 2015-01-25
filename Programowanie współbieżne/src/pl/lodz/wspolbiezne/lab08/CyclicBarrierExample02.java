package pl.lodz.wspolbiezne.lab08;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample02 {
	
	public static void main(String[] args) {

	Runnable barrier1Action = new Runnable() {
	    public void run() {
	        System.out.println("FORK: PartialPivoting/Swap 1 zrobione ");
	    }
	};
	Runnable barrier2Action = new Runnable() {
	    public void run() {
	    	System.out.println("JOIN: Eliminate/divide dla wszystkich w¹tków zrobione ");
	    }
	};

	CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Action);
	CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Action);

	CyclicBarrierRunnable barrierRunnable1 =
	        new CyclicBarrierRunnable(barrier1, barrier2);

	CyclicBarrierRunnable barrierRunnable2 =
	        new CyclicBarrierRunnable(barrier1, barrier2);

	new Thread(barrierRunnable1).start();
	new Thread(barrierRunnable2).start();
	
	}
}
