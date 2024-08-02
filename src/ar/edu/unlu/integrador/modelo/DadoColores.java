package ar.edu.unlu.integrador.modelo;

import ar.edu.unlu.integrador.modelo.Dado;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class DadoColores extends Dado implements Serializable {

    private int numeroActual;
    private Color colorActual;
    private Color[] valoresDado = new Color[6];

    public void cargarDado(int rojo, int azul, int violeta, int amarillo){


        this.valoresDado[rojo-1] = Color.red;
        this.valoresDado[azul-1] = Color.blue;
        this.valoresDado[violeta-1] = Color.magenta;
        this.valoresDado[amarillo-1] = Color.YELLOW;


        this.valoresDado[5] = this.valoresDado[0];
        this.valoresDado[4] = this.valoresDado[1];

        //nivelamos los valores del dado para que todos los colores tengan 9 caras en los 6 dados

    }
    public void tirarDado(){
        Random r = new Random();
        int valorDado = r.nextInt(5);
        this.numeroActual = valorDado+1;
        this.colorActual = this.valoresDado[valorDado];
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public Color getColorActual() {
        return colorActual;
    }
    public String getTextColor(){
        String aux = "";
        if (colorActual.equals(Color.blue)) {
            aux = "azul";
        } else if (colorActual.equals(Color.YELLOW)) {
            aux = "amarillo";
        } else if (colorActual.equals(Color.magenta)) {
            aux = "violeta";
        } else if (colorActual.equals(Color.red)) {
            aux = "rojo";
        }
        return aux;
    }
}
