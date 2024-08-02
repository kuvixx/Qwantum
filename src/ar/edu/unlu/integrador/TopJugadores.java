package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.modelo.Jugador;

import java.io.Serializable;

public class TopJugadores implements Serializable {

    private static Jugador[] jugadors = new Jugador[5];


    public static int size ;
    private static TopJugadores instancia;
    public static TopJugadores getInstance(){
        if (instancia == null){
            instancia = new TopJugadores();
        }
        return instancia;
    }

    public void agregarJugadores(Jugador jugador){

        if (size == 0){
            jugadors[0] = jugador;
            size ++;
        }else {
            for (int i = 0; i < jugadors.length; i++) {
                if (jugadors[i] == null) {

                    jugadors[i] = jugador;
                    size ++;
                    break;
                } else {
                    if (jugadors[i].getPuntaje().getTotalPuntos() < jugador.getPuntaje().getTotalPuntos()) {

                            Jugador jugadoraux = jugadors[i];
                            jugadors[i] = jugador;
                            agregarJugadores(jugadoraux);
                            break;
                    }
                }
            }
        }
    }


    public Jugador get(int i) {
        return jugadors[i];
    }

    public int getSize() {
        return size;
    }


    public  String getJugadores(){
        String aux = "";
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                Jugador jugadorActual = jugadors[i];
                aux =  aux +" TOP " + (i+1) +" : "+ jugadorActual.getNombre() +" : " +jugadorActual.getPuntaje().getTotalPuntos()+"p"+ "\n";

            }
        }else{
            aux = "No hay jugadores guardados";
        }
        return (aux);
    }


}

