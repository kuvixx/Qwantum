package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.TopJugadores;
import ar.edu.unlu.integrador.modelo.Jugador;
import ar.edu.unlu.integrador.modelo.Puntaje;
public class Serializacion {
    private static TopJugadores topJugadores;
    private static Serializador serializador = new Serializador("datos.dat");

    public static void main(String[] args) {
        topJugadores = TopJugadores.getInstance();

        System.out.println("Guardar datos");
        if (topJugadores.vacio) {
            serializador.writeOneObject(topJugadores.get(0));
            for(int x = 1; x < topJugadores.getSize(); x ++){
                serializador.addOneObject(topJugadores.get(x));
            }
        }

        Object[] recuperado = serializador.readObjects();
        for(int x = 0; x < recuperado.length; x ++){
            topJugadores.agregarJugadores(recuperado);
        }

    }
}
