package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.modelo.Jugador;

public class TopJugadores  {

    private static Jugador[] jugadors = new Jugador[5];

    public static boolean vacio = true;

    public static int size ;
    private static TopJugadores instancia;
    public static TopJugadores getInstance(){
        if (instancia == null){
            instancia = new TopJugadores();
        }
        return instancia;
    }

    public void agregarJugadores(Object[] jugador){
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

    public static Jugador get(int i) {
        return jugadors[i];
    }

    public static int getSize() {
        return size;
    }

    public static void setSize() {
        TopJugadores.size = jugadors.length;
    }

    public static String getJugadores(){
        return ("Hola");
    }


}

