package org.example;

import java.util.concurrent.Semaphore;

public class SupervisorCentral {
    private final Estacio[] estacions;
    private Semaphore mutex;
    private Semaphore semaforo;

    public SupervisorCentral(Estacio[] estacions){
        this.estacions = estacions;
        semaforo = new Semaphore(3);
        mutex =  new Semaphore(1);
    }

    //Semaforo mutex para firmar el certificado de la soldadura
     public void signaCertificat(int idRobot) throws InterruptedException {
        mutex.acquire();
        try {
            System.out.println("Se ha signat el certificat de soldadura del Robot: " + idRobot);
        } finally {
            mutex.release();
        }
    }

    //Obtener la reserva de una estación
    public Estacio reservaEstacio() throws InterruptedException{
        semaforo.acquire();

        synchronized (this){
            for(Estacio estacio : estacions){
                if(!estacio.isOcupada()){
                    estacio.setOcupada(true);
                    return estacio;
                }
            }
        }

        semaforo.release();
        throw new IllegalStateException("Cap estació lliure");
    }

    //Liberar una reserva de una estación
    public void alliberarEstacio(Estacio estacio){
        synchronized (this){
            estacio.setOcupada(false);
        }
        semaforo.release();
    }

    //Contar estaciones ocupadas y calcular las libres
    public synchronized String getEstatEstacions(){
        int ocupades = 0;

        for(Estacio estacio : estacions){
            if(estacio.isOcupada()){
                ocupades++;
            }
        }

        int lliures = estacions.length - ocupades;

        return ocupades + " ocupades " + lliures + " lliures";
    }
}