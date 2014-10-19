package pl.lodz.wspolbiezne.tutorial;

public class MySignal {

	protected boolean hasDataToProcess = false;

	public synchronized boolean hasDataToProcess() {
		return this.hasDataToProcess;
	}

	public synchronized void setHasDataToProcess(boolean hasData) {
		this.hasDataToProcess = hasData;
	}

}