package ProyectoMultihilo;

import ProyectoMultihilo.productor_consumidor.Hucha;
import ProyectoMultihilo.productor_consumidor.Consumidor;
import ProyectoMultihilo.productor_consumidor.Productor;
import ProyectoMultihilo.utils.chronometer.Chronometer;
import ProyectoMultihilo.utils.log.Log;

import java.util.Scanner;

public class Ejecutable {
    public static void main(String[] args) {
        Hucha h = new Hucha();
        Scanner s = new Scanner(System.in);
        int tamaño;
        System.out.println("¿Cuántas monedas desea introducir?");
        tamaño = s.nextInt();
        Consumidor c = new Consumidor(h, tamaño);
        Productor p = new Productor(h, tamaño);
        Chronometer ch = new Chronometer();

        boolean inicio = false;
        while (!inicio) {
            System.out.println("¿Desea iniciar el programa?" +
                    "Escriba Si para comenzar");
            String respuesta = s.next();
            if (respuesta.equals("Si")) {
                inicio = true;
                ch.start();
                p.start();
                c.start();
                if(!p.isInterrupted() && !c.isInterrupted() && !ch.isInterrupted()){
                    ch.getWait().setWait(false);
                }else{
                    ch.getWait().setWait(true);
                }
                try {
                    System.out.println("Inicio del programa");
                    p.join();
                    c.join();

                    System.out.println("El programa ha durado: " + ch.getSessionTime());
                    System.out.println("Fin del programa");
                } catch (InterruptedException e) {
                    Log.warningLogging(e + "");
                }
            } else {
                System.out.println("Finalizando programa");
            }

        }


    }
}
