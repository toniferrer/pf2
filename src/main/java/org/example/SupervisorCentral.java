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