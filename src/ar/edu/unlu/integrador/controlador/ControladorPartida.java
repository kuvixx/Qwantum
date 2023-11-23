package ar.edu.unlu.integrador.controlador;

import ar.edu.unlu.integrador.Partida;
import ar.edu.unlu.integrador.exceptions.JugadoresLleno;
import ar.edu.unlu.integrador.modelo.Dado;
import ar.edu.unlu.integrador.modelo.DadoColores;
import ar.edu.unlu.integrador.modelo.Jugador;
import ar.edu.unlu.integrador.modelo.Puntaje;
import ar.edu.unlu.integrador.vista.VistaPartida;

public class ControladorPartida {
    private Partida partidaActual;
    private final VistaPartida vistaPartida;


    public ControladorPartida(VistaPartida vistaPartida){
        this.vistaPartida = vistaPartida;
        vistaPartida.inicializarPartida(this);
        Partida partida = new Partida();
        partida.iniciarJugadores(4);
        this.partidaActual = partida;

    }
    public void setearJugadores(int cantidadJugadores){
        this.partidaActual.iniciarJugadores(cantidadJugadores);
    }
    public void agregarJugador(String nombreJugador) throws JugadoresLleno {
        Jugador jugador = new Jugador(nombreJugador,  new Puntaje());

        if (this.partidaActual.setJugadores(jugador)){
            throw new RuntimeException();
        }

        
    }

    public void inicializarDados(){
        partidaActual.inicializarDados();
    }
    public void tirarDados(){
        partidaActual.tirarDados();
    }

    public DadoColores[] devolverDadosColores(){
        return this.partidaActual.getDadosColores();
    }

    public Dado devolverDadoBlanco(){
        return this.partidaActual.getDadoBlanco();
    }

    public void cambiarTurno(){
        
    }
}
