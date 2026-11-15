package org.example;

public class Estacio {
    private int id;
    private boolean ocupada;

    public Estacio(int id){
        this.id = id;
        this.ocupada = false;
    }

    public int getId() {
        return id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
