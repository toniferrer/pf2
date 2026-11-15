package org.example;

public class Robot implements Runnable {
    private int id;
    private Cinta cinta;

    public Robot(int id, Cinta cinta){
        this.id = id;
        this.cinta = cinta;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                Thread.sleep(20000);
                int bateria = cinta.extreuBat();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            Thread.currentThread().interrupt();
        }

    }
}
