package ar.edu.unlu.integrador.modelo;

import java.awt.*;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Puntaje implements Serializable {
    private int[] puntosAmarillo = new int[6];
    private int ultimoAmarillo = 0;
    private int[] puntosAzul = new int[6];
    private int ultimoAzul = 0;
    private int[] puntosRojo = new int[6];
    private int ultimoRojo = 0;
    private int[] puntosVioleta = new int[6];
    private int ultimoVioleta = 0;
    private int contadorErrores = 0;
    private int totalPuntos = 0;


    public boolean agregarRojo(int numero){
        if (ultimoRojo < 6) {
            this.puntosRojo[ultimoRojo] = numero;
            this.totalPuntos = this.totalPuntos + numero;
            ultimoRojo ++;
            return true;
        }else {
            return false;
        }
    }

    public boolean agregarAmarillo(int numero){
        if (ultimoAmarillo < 6) {
            this.puntosAmarillo[ultimoAmarillo] = numero;
            this.totalPuntos = this.totalPuntos + numero;
            ultimoAmarillo ++;
            return true;
        }else {
            return false;
        }
    }

    public boolean agregarAzul(int numero){
        if (ultimoAzul < 6) {
            this.puntosAzul[ultimoAzul] = numero;
            this.totalPuntos = this.totalPuntos + numero;
            ultimoAzul ++;
            return true;
        }else {
            return false;
        }
    }

    public boolean agregarVioleta(int numero){
        if (ultimoVioleta < 6) {
            this.puntosVioleta[ultimoVioleta] = numero;
            this.totalPuntos = this.totalPuntos + numero;
            ultimoVioleta ++;
            return true;
        }else {
            return false;
        }
    }
    public void sumarError(){
        this.contadorErrores ++;
    }

    public int[] getPuntosAmarillo() {
        return puntosAmarillo;
    }

    public int[] getPuntosAzul() {
        return puntosAzul;
    }

    public int[] getPuntosRojo() {
        return puntosRojo;
    }

    public int[] getPuntos(Color color){
        if (color.equals(Color.red)){
            return puntosRojo;
        } else if (color.equals(Color.blue)) {
            return puntosAzul;
        } else if (color.equals(Color.YELLOW)) {
            return puntosAmarillo;
        } else if (color.equals(Color.magenta)) {
            return puntosVioleta;
        }
        return puntosRojo;
    }

    public int getUltimo(Color color){
        if (color.equals(Color.red)){
            return ultimoRojo;
        } else if (color.equals(Color.blue)) {
            return ultimoAzul;
        } else if (color.equals(Color.YELLOW)) {
            return ultimoAmarillo;
        } else if (color.equals(Color.magenta)) {
            return ultimoVioleta;
        }
        return ultimoRojo;

    }

    public int[] getPuntosVioleta() {
        return puntosVioleta;
    }

    public void setPuntos(Color color, int numero){
        if (color.equals(Color.red)){
           agregarRojo(numero);
        } else if (color.equals(Color.blue)) {
            agregarAzul(numero);
        } else if (color.equals(Color.YELLOW)) {
            agregarAmarillo(numero);
        } else if (color.equals(Color.magenta)) {
            agregarVioleta(numero);
        }
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

    public int calcularPuntajeFinal(){
        return (getTotalPuntos() - this.contadorErrores*5);
    }

    public boolean comprobarFinPartida(){
        if ((puntosAmarillo[5] != 0 && puntosAzul[5] != 0 && puntosVioleta[5] != 0 && puntosRojo[5] != 0 ) || contadorErrores == 5){
            return true;
        }

        return false;
    }
    public void addContadorErrores(){
        contadorErrores ++;
    }
}
