package org.example;

public class Platform implements Runnable {
    int id;
    Cinta cinta;
    private volatile String estat;
    private SupervisorCentral sc;

    public Platform(int id, Cinta cinta, SupervisorCentral sc){
        this.id = id;
        this.cinta = cinta;
        this.estat = "Iniciat";
        this.sc = sc;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                Thread.sleep(5000);
                estat = "Esperant a posar bateria";
                cinta.posaBat(id);
                estat = "Bateria posada a cinta";
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
