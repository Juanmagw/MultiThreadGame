package ProyectoMultihilo.productor_consumidor;

import ProyectoMultihilo.utils.log.Log;

public class Hucha {
    private int contenido;
    private boolean disponible = false;

    public synchronized int get() {
        while (disponible == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                Log.warningLogging(e+"");
            }
        }
        disponible = false;
        notifyAll();
        return contenido;
    }

    public synchronized void put(int valor) {
        while (disponible == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                Log.warningLogging(e+"");
            }
        }
        contenido = valor;
        disponible = true;
        notifyAll();
    }
}