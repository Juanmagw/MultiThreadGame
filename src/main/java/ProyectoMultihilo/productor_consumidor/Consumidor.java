package ProyectoMultihilo.productor_consumidor;

public class Consumidor extends Thread {
    private Hucha h;
    private int tamaño;

    public Consumidor(Hucha h, int tamaño) {
        this.h = h;
        this.tamaño = tamaño;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < (this.tamaño+1); i++) {
            valor = h.get();
            System.out.println("Se han recibido " + valor + " monedas");
        }
    }
}
