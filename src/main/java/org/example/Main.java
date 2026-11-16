package org.example;

public class Main {
    static void main() throws InterruptedException {
        Cinta cinta = new Cinta();

        //Iniciamos las 3 estaciones
        Estacio[] estacions = new Estacio[3];
        for (int i = 0; i < estacions.length; i++) {
            estacions[i] = new Estacio(i);
        }

        //Creamos el supervisor central
        SupervisorCentral sc = new SupervisorCentral(estacions);

        //Iniciamos los hilos de robots
        Robot[] robots = new Robot[10];
        Thread[] tRobots = new Thread[10];
        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Robot(i, cinta, sc);
            tRobots[i] = new Thread(robots[i]);
            tRobots[i].start();
        }

        //Iniciamos los hilos de plataformas
        Platform[] plataformas = new Platform[10];
        Thread[] tPlataformas = new Thread[10];
        for (int i = 0; i < plataformas.length; i++) {
            plataformas[i] = new Platform(i, cinta, sc);
            tPlataformas[i] = new Thread(plataformas[i]);
            tPlataformas[i].start();
        }

        //Iniciamos el hilo de Dashboard
        Dashboard dashboard = new Dashboard(cinta, sc, robots, plataformas);
        Thread tDashboard = new Thread(dashboard);
        tDashboard.setDaemon(true);
        tDashboard.start();

        //Creamos una alerta para probar que Supervisor funciona
        sc.activaAlerta();
        System.out.println("");
        System.out.println("Alerta activada");

        Thread.sleep(6000);
        sc.resolAlerta();
        System.out.println("");
        System.out.println("Alerta resolta");

        //Ejecutar el programa durante 2 minutos
        try{
            Thread.sleep(120000);
        } finally {
            //Hacemos interrupt de robots y plataformas
            for(int i = 0; i < 10; i++){
                tRobots[i].interrupt();
                tPlataformas[i].interrupt();
            }

            tDashboard.interrupt();

            //Hacemos joins de robots y plataformas
            for(int i = 0; i < 10; i++){
                tRobots[i].join();
                tPlataformas[i].join();
            }

            tDashboard.join();
        }
    }
}
