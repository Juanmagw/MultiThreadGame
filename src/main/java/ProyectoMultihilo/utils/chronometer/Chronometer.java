package ProyectoMultihilo.utils.chronometer;


import ProyectoMultihilo.productor_consumidor.Consumidor;
import ProyectoMultihilo.utils.log.Log;

public class Chronometer extends Thread {

    /**
     * Attributes of this
     */
    private String sessionTime;
    private SynchronizedMethods wait = new SynchronizedMethods();

    /**
     * Constructor
     */
    public Chronometer() {
    }

    public Chronometer(String sessionTime, SynchronizedMethods wait) {
        this.sessionTime = sessionTime;
        this.wait.setWait(false);
    }

    /**
     * Getters and Setters
     */
    public String getSessionTime() {
        return this.sessionTime;
    }
    public SynchronizedMethods getWait() {
        return wait;
    }
    public void setWait(SynchronizedMethods wait) {
        this.wait = wait;
    }

    /**
     * Method that permit to start a Thread when it calls start()
     */
    public void run() {
        int seconds = 0;
        try {
            while (!this.isInterrupted() && !this.wait.getWait()) {
                Thread.sleep(1000);
                calculateSessionTime(seconds++);
            }
        } catch (InterruptedException e) {
            Log.warningLogging(e + "");
        }
    }

    /**
     * Method that calculates the time in seconds whe the app is running
     * @param seconds Time in seconds that is counted
     */
    public void calculateSessionTime(int seconds) {
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        this.sessionTime = String.valueOf(hours + ":" + minutes + ":" + seconds);
    }
}