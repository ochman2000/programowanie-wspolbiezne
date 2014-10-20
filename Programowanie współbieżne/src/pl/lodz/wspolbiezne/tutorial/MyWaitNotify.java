package pl.lodz.wspolbiezne.tutorial;

public class MyWaitNotify {

	private MonitorObject myMonitorObject = new MonitorObject();
	boolean wasSignalled = false;

	public void doWait() {
		synchronized (myMonitorObject) {
			if (!wasSignalled) {
				try {
					myMonitorObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// clear signal and continue running.
			wasSignalled = false;
		}
	}

	public void doNotify() {
		synchronized (myMonitorObject) {
			wasSignalled = true;
			myMonitorObject.notify();
		}
	}

}