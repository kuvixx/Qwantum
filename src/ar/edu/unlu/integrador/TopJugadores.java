package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.modelo.Jugador;

import java.io.Serializable;

public class TopJugadores implements Serializable {

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

    public void agregarJugadores(Jugador jugador){
        System.out.println("size" + size);
        System.out.println("lengt: "+ jugadors.length);


        if (size == 0){
            jugadors[0] = jugador;
            size ++;
        }else {
            for (int i = 0; i <= jugadors.length; i++) {
                if (jugadors[i] == null) {

                    System.out.println("?");

                    jugadors[i] = jugador;
                    size ++;
                    break;
                } else {
                    if (jugadors[i].getPuntaje().getTotalPuntos() < jugador.getPuntaje().getTotalPuntos()) {

                        System.out.println("??");
                            Jugador jugadoraux = jugadors[i];
                            jugadors[i] = jugador;
                            agregarJugadores(jugadoraux);
                    }
                }
            }
        }
    }

    public static Jugador[] getJugadors() {
        return jugadors;
    }

    public Jugador get(int i) {
        return jugadors[i];
    }

    public int getSize() {
        return size;
    }

    public static void setSize() {
        TopJugadores.size = jugadors.length;
    }

    public  String getJugadores(){
        String aux = "";
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                Jugador jugadorActual = jugadors[i];
                System.out.println(i+ "getjuga");
                aux =  aux + jugadorActual.getNombre() +" : " +jugadorActual.getPuntaje().getTotalPuntos()+". -"+ "\n";

            }
            System.out.println(aux);
        }else{
            aux = "No hay jugadores guardados";
        }
        return (aux);
    }


}

