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
		if (totalIntervals<=interval) {
			throw new IllegalArgumentException("Przedzia³ nie mo¿e byæ wiêkszy ni¿: "
					+totalIntervals+" a podano: "+interval);
		}
		double fraction = (double)interval/(double)totalIntervals;
		return (int) (fraction * ROZMIAR_TABLICY);
	}
	
	public static int getEndOfInterval(int interval, int totalIntervals) {
		double rozmiarPrzedzialu = (double)ROZMIAR_TABLICY/(double)totalIntervals;
		double fraction = (double)interval/(double)totalIntervals; 
		return (int) ((fraction * ROZMIAR_TABLICY) + rozmiarPrzedzialu);
	}
	
	public void runAll() {
		for (Thread t : thread) { t.start(); }
	}
}
