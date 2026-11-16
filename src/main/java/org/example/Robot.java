package org.example;

public class Robot implements Runnable {
    private int id;
    private Cinta cinta;
    private volatile String estat;
    private SupervisorCentral sc;

    public Robot(int id, Cinta cinta, SupervisorCentral sc){
        this.id = id;
        this.cinta = cinta;
        this.estat = "Iniciat";
        this.sc = sc;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                Thread.sleep(20000);
                estat =  "Esperant a agafar bateria";
                int bateria = cinta.extreuBat();

                estat =  "Bateria agafada, esperant estacio";
                Estacio estacio = sc.reservaEstacio();

                try{
                    estat = ("Soldant a l'estacio " + estacio.getId());
                    Thread.sleep(4000);
                }finally{
                    sc.alliberarEstacio(estacio);
                    estat = "Esperant a agafar bateria";
                }

                //Solicitar el certificado
                sc.signaCertificat(id);
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
