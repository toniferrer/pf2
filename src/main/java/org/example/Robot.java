package org.example;

public class Robot implements Runnable {
    private int id;
    private Cinta cinta;
    private volatile String estat;

    public Robot(int id, Cinta cinta){
        this.id = id;
        this.cinta = cinta;
        this.estat = "Iniciat";
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                Thread.sleep(20000);
                estat =  "Esperant a agafar bateria";
                int bateria = cinta.extreuBat();
                estat =  "Bateria agafada";
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            Thread.currentThread().interrupt();
        }
    }

    public String getEstat() {
        return estat;
    }

    public int getId(){
        return id;
    }
}
