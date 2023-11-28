package ar.edu.unlu.integrador.modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private Puntaje puntaje;

    public Jugador(String nombre, Puntaje puntaje){
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public Puntaje getPuntaje() {
        return puntaje;
    }
}
