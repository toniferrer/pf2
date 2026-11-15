package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cinta {
    private int[] capacitat;
    private int quantitat;
    private int entrada;
    private int sortida;

    //Declaramos el ReentrantLock y los conditions
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cintaNoPlena = lock.newCondition();
    private final Condition cintaNoBuida = lock.newCondition();

    public Cinta(){
        capacitat = new int[2];
        quantitat = 0;
        entrada = 0;
        sortida = 0;
    }

    //Añadir una bateria a la cinta
    public void posaBat(int bateria) throws InterruptedException{
        lock.lock();
        try{
            //Espera si la cinta se encuentra llena
            while(quantitat == capacitat.length){
                cintaNoPlena.await();
            }
            capacitat[entrada] = bateria;
            entrada = (entrada+1)% capacitat.length;
            quantitat++;
            cintaNoBuida.signal();
        } finally {
            lock.unlock();
        }
    }

    //Sacar una batería de la cinta
    public int extreuBat() throws InterruptedException {
        lock.lock();
        try{
            //Espera si la cinta se encuentra vacia
            while(quantitat == 0){
                cintaNoBuida.await();
            }
            int bateria = capacitat[sortida];
            sortida = (sortida+1) % capacitat.length;
            quantitat--;

            cintaNoPlena.signal();
            return bateria;
        } finally {
            lock.unlock();
        }
    }

    public int getQuantitat(){
        lock.lock();
        try {
            return quantitat;
        } finally {
            lock.unlock();
        }
    }
}
