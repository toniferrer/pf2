package org.example;

import java.util.concurrent.Semaphore;

public class SupervisorCentral {
    private Semaphore mutex;
    private Semaphore semaforo;

    public SupervisorCentral(){
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
}