package ar.edu.unlu.integrador.modelo;

import ar.edu.unlu.integrador.modelo.Dado;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DadoColores extends Dado {

    private int numeroActual;
    private Color colorActual;
    private Color[] valoresDado = new Color[6];

    public void cargarDado(int rojo, int azul, int violeta, int amarillo){


        this.valoresDado[rojo-1] = Color.red;
        this.valoresDado[azul-1] = Color.blue;
        this.valoresDado[violeta-1] = Color.magenta;
        this.valoresDado[amarillo-1] = Color.yellow;

        Color uno = this.valoresDado[0];
        Color dos = this.valoresDado[1];

        this.valoresDado[5-1] = uno;
        this.valoresDado[4-1] = dos;

        //nivelamos los valores del dado para que todos los colores tengan 9 caras en los 6 dados

    }
    public void tirarDado(){
        Random r = new Random();
        int valorDado = r.nextInt(5)+1;
        this.numeroActual = valorDado;
        this.colorActual = this.valoresDado[valorDado];
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public Color getColorActual() {
        return colorActual;
    }
}
