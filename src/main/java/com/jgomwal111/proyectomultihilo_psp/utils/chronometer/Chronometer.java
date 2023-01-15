package com.jgomwal111.proyectomultihilo_psp.utils.chronometer;

import com.jgomwal111.proyectomultihilo_psp.log.Log;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.util.List;

public class Chronometer implements Runnable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Atributos de la clase
     */
    private int id;
    private String time;
    private List<String> times;
    private TextField tfChronometer;
    private SynchronizedMethods wait = new SynchronizedMethods();

    /**
     * Constructores de la clase
     */
    public Chronometer() {
        this(-1,"",null,null);
    }
    public Chronometer(TextField tfChronometer) {
        this.tfChronometer = tfChronometer;
    }
    public Chronometer(int id, String time){
        this.id = id;
        this.time = time;
    }
    public Chronometer(int id, String time, TextField tfChronometer, SynchronizedMethods wait){
        this.id = id;
        this.time = time;
        this.tfChronometer = tfChronometer;
        this.wait.setWait(false);
    }

    /**
     * Tiempo guardado de cada cronómetro
     * @return el tiempo que ha hecho el cronómetro
     */
    public String getTime() {
        return time;
    }
    /**
     * Método que permite escribir un texto
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }
    /**
     * ID del cronómetro
     * @return la id que posee cada tiempo
     */
    public int getIdChrono() {
        return id;
    }
    /**
     * Método que permite escribir un texto
     * @param idChrono
     */
    public void setIdChrono(int idChrono) {
        this.id = idChrono;
    }
    /**
     * Lista de los tiempos que ha guardado el cronómetro
     * @return
     */
    public List<String> getTimes() {
        return times;
    }
    /**
     * Método para añadir tiempos a la lista
     * @param time tiempo a añadir
     */
    public void addTime(String time){
        if(time.equals(this.time) && !this.times.contains(time) && time!=null){
            this.times.add(time);
        }
    }

    public TextField getTfChronometer() {
        return tfChronometer;
    }

    public void setTfChronometer(TextField tfChronometer) {
        this.tfChronometer = tfChronometer;
    }

    public SynchronizedMethods getWait() {
        return wait;
    }

    public void setWait(SynchronizedMethods wait) {
        this.wait = wait;
    }

    /**
     * Método para crear el cronómetro
     * @param seconds tiempo que cuadra el cronómetro
     */
    public void timer(int seconds){
        int hours = seconds/3600;
        seconds %= 3600;
        int minutes = seconds/60;
        seconds %= 60;
        if(seconds<10){
            this.time = hours+":"+minutes+":"+seconds;
        }
        if(minutes<10){
            this.time = hours+":"+minutes+":"+seconds;
        }
        if(hours<10){
            this.time = ""+hours+":"+minutes+":"+seconds;
        }
    }

    @Override
    public void run() {
        Thread t = new Thread(this);
        int seconds = 0;
        try{
            do{
                while(!this.wait.getWait()){
                    this.timer(++seconds);
                    tfChronometer.setText(this.getTime());
                    Thread.sleep(1000);
                    this.wait.waiting();
                }
            }while(!t.isInterrupted());
        }catch(InterruptedException e){
            Log.warningLogging(e+"");
        }
    }

    @Override
    public String toString() {
        return "Chronometer{" +
                "idChrono=" + id +
                ", time='" + time + '\'' +
                '}';
    }
}