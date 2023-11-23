package ar.edu.unlu.integrador.modelo;

import java.util.Random;

public class Dado {

    private int numeroActual;

    public void tirarDado(){
        Random r = new Random();
        this.numeroActual = r.nextInt(5)+1;

    }

    public int getNumeroActual() {
        return numeroActual;
    }
}
