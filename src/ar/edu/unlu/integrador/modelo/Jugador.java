package ar.edu.unlu.integrador.modelo;

public class Jugador {
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
