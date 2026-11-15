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
                String bateria = cinta.extreuBat(id);
                //System.out.println("Soy el robot " + id + " y me vas a comer un cojón");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            Thread.currentThread().interrupt();
        }

    }
}
