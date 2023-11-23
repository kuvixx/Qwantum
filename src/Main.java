import ar.edu.unlu.integrador.Partida;
import ar.edu.unlu.integrador.controlador.ControladorPartida;
import ar.edu.unlu.integrador.vista.VistaGrafica;
import ar.edu.unlu.integrador.vista.VistaPartida;

public class Main {


    public static void main(String[] args) {

        System.out.println("Hello world!");
        VistaPartida vistaPartida = new VistaPartida();
        ControladorPartida controladorPartida = new ControladorPartida(vistaPartida);
        vistaPartida.main();


/*
        VistaGrafica.main();

        Partida partida = new Partida();
        partida.nuevaPartida();*/

    }
}