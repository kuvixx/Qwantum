package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.modelo.Jugador;

public class TopJugadores {

    private static String[] jugadores = {"VACIO","VACIO","VACIO","VACIO","VACIO"} ;
    private static Jugador[] jugadors = new Jugador[5];
    private static boolean vacio = true;

    public void setJugadores(String[] jugadores) {
        this.jugadores = jugadores;
    }

    public static String[] getJugadores() {
        return jugadores;
    }

    public void agregarJugadores(Jugador jugador){
        for (int i=0 ; i <= jugadors.length; i++) {
            if (jugadors[i] == null) {
                jugadors[i] = jugador;
            } else {
                if (jugadors[i].getPuntaje().getTotalPuntos() < jugador.getPuntaje().getTotalPuntos()) {
                    for (int j = i; j <= jugadors.length; j++) {
                        Jugador jugadoraux = jugadors[i];
                        jugadors[i] = jugador;
                        jugador = jugadoraux;

                    }
                    break;
                }
            }
        }
    }

    public static Jugador[] getJugadors() {
        return jugadors;
    }
}

