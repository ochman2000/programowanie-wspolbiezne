package pl.lodz.wspolbiezne.lab02;

public class Zadanie02 {
	
	private static int ROZMIAR_TABLICY = 4_000_000;
	private Thread[] thread;

	public static void main(String[] args) {
		

	}
	
	public Zadanie02(int threadsNumber) {
		thread = new Thread[threadsNumber];
		for (int interval=0; interval<threadsNumber; interval++) {
			W¹tek w = new W¹tek();
			w.setStartIndex(getBeginningOfInterval(interval, threadsNumber));
			w.setEndIndex(getEndOfInterval(interval, threadsNumber));
			thread[interval] = new Thread();
		}
	}
	
	public static int getBeginningOfInterval(int interval, int totalIntervals) {
		int rozmiarPrzedzialu = ROZMIAR_TABLICY/totalIntervals;
//		return interval/totalIntervals * ROZMIAR_TABLICY - rozmiarPrzedzialu;
		return getEndOfInterval(interval, totalIntervals) - rozmiarPrzedzialu;
	}
	
	public static int getEndOfInterval(int interval, int totalIntervals) {
		return interval/totalIntervals * ROZMIAR_TABLICY;
	}
	
	public void runAll() {
		for (Thread t : thread) { t.start(); }
	}
}
