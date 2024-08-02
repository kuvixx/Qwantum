package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.TopJugadores;
import ar.edu.unlu.integrador.modelo.Jugador;
import ar.edu.unlu.integrador.modelo.Puntaje;
import ar.edu.unlu.integrador.Serializador;
public class Serializacion {
    private static TopJugadores topJugadores;
    private static Serializador serializador = new Serializador("datos.dat");

    public static void main(String[] args) {
        topJugadores = TopJugadores.getInstance();
        /*
        Puntaje puntaje = new Puntaje();
        Jugador jugador = new Jugador("jorge",(puntaje ));
        topJugadores.agregarJugadores(jugador);

        System.out.println("Guardar datos");
        if (topJugadores.getSize() >= 1) {
            serializador.writeOneObject(topJugadores.get(0));
            for(int x = 1; x < topJugadores.getSize(); x ++){
                serializador.addOneObject(topJugadores.get(x));
            }
        }

         */

        System.out.println(" - Fase 4 - Recuperar datos ");
        Object[] recuperado = serializador.readObjects();
        for(int x = 0; x < recuperado.length; x ++){
            topJugadores.agregarJugadores((Jugador) recuperado[x]);
        }

        recuperado = serializador.readObjects();

        for(int x = 0; x < recuperado.length; x ++) {
            topJugadores.agregarJugadores((Jugador) recuperado[x]);
        }
        System.out.println(topJugadores.get(0).getNombre());

    }

    public static void guardarTop(TopJugadores top){
        System.out.println("Guardar datos");
        if (topJugadores.getSize() >= 1) {
            serializador.writeOneObject(topJugadores.get(0));
            for(int x = 1; x < topJugadores.getSize(); x ++){
                serializador.addOneObject(topJugadores.get(x));
            }
        }
        System.out.println("guardado");
    }

    public static TopJugadores obtenerTop(){
        topJugadores = TopJugadores.getInstance();
        System.out.println(" - Fase 4 - Recuperar datos ");
        Object[] recuperado = serializador.readObjects();
        for(int x = 0; x < recuperado.length; x ++){

            topJugadores.agregarJugadores((Jugador) recuperado[x]);
        }
        System.out.println(topJugadores.get(0).getPuntaje().getTotalPuntos());
        return topJugadores;
    }
}
