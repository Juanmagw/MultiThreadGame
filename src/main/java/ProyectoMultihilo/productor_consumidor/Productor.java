package ProyectoMultihilo.productor_consumidor;

import ProyectoMultihilo.utils.log.Log;

public class Productor extends Thread {
    private Hucha h;
    private int tamaño;

    public Productor(Hucha h, int tamaño) {
        this.h = h;
        this.tamaño = tamaño;
    }

    public void run() {
        for (int i = 0; i < (this.tamaño+1); i++) {
            h.put(i);
            System.out.println("Se han hechado " + i + " monedas");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.warningLogging(e+"");
            }
        }
    }
}