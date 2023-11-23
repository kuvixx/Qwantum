package ar.edu.unlu.integrador.modelo;

import java.util.ArrayList;

public class Puntaje {
    private ArrayList<Integer> puntosAmarillo;
    private ArrayList<Integer> puntosRojo;
    private ArrayList<Integer> puntosAzul;
    private ArrayList<Integer> puntosVioleta;
    private int contadorErrores = 0;
    private int totalPuntos = 0;


    public void agregarRojo(int numero){
        this.puntosRojo.add(numero);
        this.totalPuntos = this.totalPuntos + numero;
    }

    public void agregarAmarillo(int numero){
        this.puntosAmarillo.add(numero);
        this.totalPuntos = this.totalPuntos + numero;
    }

    public void agregarAzul(int numero){
        this.puntosAzul.add(numero);
        this.totalPuntos = this.totalPuntos + numero;
    }

    public void agregarVioleta(int numero){
        this.puntosVioleta.add(numero);
        this.totalPuntos = this.totalPuntos + numero;
    }
    public void sumarError(){
        this.contadorErrores ++;
    }


    public ArrayList<Integer> getPuntosAmarillo() {
        return puntosAmarillo;
    }

    public ArrayList<Integer> getPuntosAzul() {
        return puntosAzul;
    }

    public ArrayList<Integer> getPuntosRojo() {
        return puntosRojo;
    }

    public ArrayList<Integer> getPuntosVioleta() {
        return puntosVioleta;
    }

    public void mostrarPuntajes(){
        System.out.println(puntosAzul.toString());
        System.out.println(puntosAmarillo.toString());
        System.out.println(puntosVioleta.toString());
        System.out.println(puntosAzul.toString());
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }
}
