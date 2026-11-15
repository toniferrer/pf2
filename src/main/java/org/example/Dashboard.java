package org.example;

public class Dashboard implements Runnable{
    private Cinta cinta;
    private SupervisorCentral sc;


    public Dashboard(Cinta cinta, SupervisorCentral sc) {
        this.cinta = cinta;
        this.sc = sc;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("");
                System.out.println("Soldadura: " + "idEstaciones" +"estado");
                System.out.println("Bateries en la cinta " + cinta.getQuantitat() + "/2");
                System.out.println("Robot: " + "id" +"estado");

                Thread.sleep(2000);
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
