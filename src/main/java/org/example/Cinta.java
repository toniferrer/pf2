package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cinta {
    private int[] capacitat;
    private int quantitat;

    //Declaramos el ReentrantLock y los conditions
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cintaNoPlena = lock.newCondition();
    private final Condition cintaNoBuida = lock.newCondition();

    public Cinta(){
        capacitat = new int[2];
        quantitat = 0;
    }

    public String extreuBat(int id) throws InterruptedException {
        lock.lock();
        try{
            //Espera si la cinta se encuentra vacia
            while(quantitat == 0){
                cintaNoBuida.await();
            }


            cintaNoPlena.signal();
        } finally {
            lock.unlock();
        }
        return "";
    }
}
