package org.example;

public class Platform implements Runnable {
    int id;
    Cinta cinta;

    public Platform(int id, Cinta cinta){
        this.id = id;
        this.cinta = cinta;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                Thread.sleep(5000);
                cinta.posaBat(id);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            Thread.currentThread().interrupt();
        }
    }
}
