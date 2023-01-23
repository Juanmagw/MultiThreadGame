package ProyectoMultihilo.utils.chronometer;

public class SynchronizedMethods {
	private boolean wait;
	
	public boolean getWait() {
		return wait;
	}
	
	
	public synchronized void setWait(boolean b) {
		this.wait =b;
		notifyAll();
	}
	
	public synchronized void waiting() throws InterruptedException {
		while(this.wait) {
			wait();
		}
	}
	
}
