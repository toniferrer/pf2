package org.example;

public class Main {
    static void main() throws InterruptedException {
        SupervisorCentral sc = new SupervisorCentral();
        Cinta cinta = new Cinta();
        Estacio[] estacions = new Estacio[3];

        for (int i = 1; i < estacions.length; i++) {
            estacions[i] = new Estacio(i);
        }

        Robot[] robots = new Robot[10];
        Thread[] tRobots = new Thread[10];
        Platform[] plataformas = new Platform[10];
        Thread[] tPlataformas = new Thread[10];

        for (int i = 1; i < robots.length; i++) {
            robots[i] = new Robot(i, cinta);
            tRobots[i] = new Thread(robots[i]);
            tRobots[i].start();
        }

        for (int i = 1; i < plataformas.length; i++) {
            plataformas[i] = new Platform(i, cinta);
            tPlataformas[i] = new Thread(plataformas[i]);
            tPlataformas[i].start();
        }

        for(int i = 1; i < 10; i++){
            tRobots[i].join();
            tPlataformas[i].join();
        }
    }
}
