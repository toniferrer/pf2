package org.example;

public class Dashboard implements Runnable{
    private Cinta cinta;
    private SupervisorCentral sc;
    private final Robot[] robots;
    private final Platform[] plataformes;

    public Dashboard(Cinta cinta, SupervisorCentral sc, Robot[] robots, Platform[] plataformes) {
        this.cinta = cinta;
        this.sc = sc;
        this.robots = robots;
        this.plataformes = plataformes;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("");
                System.out.println("Soldadura: " + sc.getEstatEstacions());
                System.out.println("Bateries en la cinta " + cinta.getQuantitat() + "/2");

                for (Robot robot : robots) {
                    System.out.println("Robot " + robot.getId() + ": " + robot.getEstat());

                }

                for (Platform plataforma : plataformes) {
                    System.out.println("Plataforma " + plataforma.getId() + ": " + plataforma.getEstat());
                }

                Thread.sleep(2000);
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
