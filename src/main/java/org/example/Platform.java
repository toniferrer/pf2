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
                System.out.println("Soy la plataforma " + id + " y me vas a comer un teta");
            }
        }finally{
            Thread.currentThread().interrupt();
        }
    }
}
