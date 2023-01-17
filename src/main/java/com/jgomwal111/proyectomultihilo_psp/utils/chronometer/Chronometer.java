package com.jgomwal111.proyectomultihilo_psp.utils.chronometer;

import com.jgomwal111.proyectomultihilo_psp.log.Log;

public class Chronometer extends Thread {

    /**
     * Attributes of this
     */
    private String sessionTime;

    /**
     * Constructor
     */
    public Chronometer() {
    }

    /**
     * It save the time that is calculated and converted to String
     * @return String that contains the time of calculateSessionTime(seconds)
     */
    public String getSessionTime() {
        return this.sessionTime;
    }

    /**
     * Method that permit to start a Thread when it calls start()
     */
    public void run() {
        int seconds = 0;
        try {
            while (!this.isInterrupted()) {
                Thread.sleep(1000);
                calculateSessionTime(++seconds);
            }
        } catch (InterruptedException e) {
            Log.warningLogging(e + "");
        }
    }

    /**
     * Method that calculates the time in seconds whe the app is running
     * @param seconds Time in seconds that is counted
     */
    private void calculateSessionTime(int seconds) {
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        this.sessionTime = String.valueOf(hours + ":" + minutes + ":" + seconds);
    }
}