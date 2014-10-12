package pl.lodz.wspolbiezne.lab04;

public class W¹tek implements Runnable {

	private int startIndex;
	private int endIndex;
	private MainThread parent;

	@Override
	public void run() {
		for (int i = startIndex; i < endIndex; i++) {
			parent.incrementHistogramAtByteIndex(i);
		}
		parent.decrementThreadCounter();
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	public MainThread getParent() {
		return parent;
	}

	public void setParent(MainThread parent) {
		this.parent = parent;
	}
}
