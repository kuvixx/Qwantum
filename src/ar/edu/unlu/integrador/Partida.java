package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.modelo.*;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import ar.edu.unlu.integrador.Serializacion;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Partida extends ObservableRemoto implements IPartida {
    private  Jugador[] jugadores ;

    private int indiceJugadorActual = 0;
    private int jugadoresActuales = 0;

    private DadoColores[] dados = new DadoColores[6];
    private Dado dadoBlanco = new Dado();
    final private int maxJugadores;
    private Jugador ganador;
    private TopJugadores topJugadores;


    public Partida(int maxJugadores) {
        this.maxJugadores = maxJugadores;
        this.jugadores = new Jugador[maxJugadores];
        System.out.println("1--");
        topJugadores = Serializacion.obtenerTop();
        System.out.println("2--");
        inicializarDados();
    }
    public void actualizarTop5(Jugador jugador) throws RemoteException{
        topJugadores.agregarJugadores(jugador);
        Serializacion.guardarTop(topJugadores);
    }
    public String devolverTop() throws RemoteException{
        return(topJugadores.getJugadores());
    }

    public int setJugadores(Jugador jugador) throws RemoteException{

        if (jugadoresActuales == maxJugadores) {
            return -1;
        }
        jugadores[jugadoresActuales] = jugador;
        this.jugadoresActuales ++;
        System.out.println(jugadoresActuales);

        this.notificarObservadores(Eventos.AGREGAR_JUGADOR);

        return jugadoresActuales -1;

    }

    private void inicializarDados(){

        DadoColores dado1 = new DadoColores();
        DadoColores dado2 = new DadoColores();
        DadoColores dado3 = new DadoColores();
        DadoColores dado4 = new DadoColores();
        DadoColores dado5 = new DadoColores();
        DadoColores dado6 = new DadoColores();

        dado1.cargarDado(1,2,3,4);
        dado2.cargarDado(2,3,4,1);
        dado3.cargarDado(3,4,1,2);
        dado4.cargarDado(4,1,2,3);
        dado5.cargarDado(4,3,2,1);
        dado6.cargarDado(1,2,3,4);

        this.dados[0] =dado1;
        this.dados[1] =dado2;
        this.dados[2] =dado3;
        this.dados[3] =dado4;
        this.dados[4] =dado5;
        this.dados[5] =dado6;

    }

    public DadoColores[] getDadosColores() throws RemoteException{
        return this.dados;
    }

    public Dado getDadoBlanco() throws RemoteException {
        return dadoBlanco;
    }

    public int getIndiceJugadorActual() throws RemoteException{
        return indiceJugadorActual;
    }

    public void tirarDados() throws RemoteException{
        dadoBlanco.tirarDado();
        for (int i = 0; i<dados.length;i++){
            dados[i].tirarDado();
            System.out.println(dados[i].getNumeroActual());
        }
        this.notificarObservadores(Eventos.ACTUALIZAR_DADOS);

    }

    @Override
    public Jugador getJugador(int indice) throws RemoteException {
        return jugadores[indice];
    }

    public boolean sumarPuntajes(Color color) throws RemoteException{
        Puntaje puntaje = jugadores[indiceJugadorActual].getPuntaje();
        int auxiliar = dadoBlanco.getNumeroActual();


        for (int j = 0; j < dados.length; j++) {
            DadoColores dadoAux = dados[j];
            if(dadoAux.getColorActual().equals(color)){
                auxiliar = auxiliar + dadoAux.getNumeroActual();
            }
        }


        if ( puntaje.getUltimo(color) != 0)  {
            if (puntaje.getUltimo(color)< 6){
                if (puntaje.getUltimo(color) >=4){
                    if(auxiliar < puntaje.getPuntos(color)[puntaje.getUltimo(color)-1]){
                        puntaje.setPuntos(color, auxiliar);
                    }else {
                        return false;
                    }
                } else if (auxiliar > puntaje.getPuntos(color)[puntaje.getUltimo(color)-1]) {
                    puntaje.setPuntos(color, auxiliar);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }else {
            puntaje.setPuntos(color, auxiliar);
        }
        System.out.println(color.toString());
        return true;
    }

    public void cambiarTurno() throws RemoteException {
        if(comprobarFinDelJuego()){
            System.out.println("fin");
        }else{
            this.indiceJugadorActual ++;
            this.indiceJugadorActual = indiceJugadorActual % this.maxJugadores;
            this.notificarObservadores(Eventos.CAMBIAR_TURNO);
        }


    }

    public void sumarDescarte() throws RemoteException{
        jugadores[indiceJugadorActual].getPuntaje().addContadorErrores();
    }


    public boolean comprobarFinDelJuego() throws RemoteException {
        if (this.jugadores[indiceJugadorActual].getPuntaje().comprobarFinPartida()){
            calcularGanador();
            // ACA PODRÍA LLAMAR A ACTUALIZAR TOP
            actualizarTop5(ganador);

            this.notificarObservadores(Eventos.FIN_DEL_JUEGO);
            return true;
        }else{
            return false;
        }
    }

    public Jugador getGanador() throws RemoteException {
        return ganador;
    }

    public void calcularGanador() throws RemoteException{
        int mayorAux = 0;

        for(Jugador jugador:jugadores) {
            if (jugador.getPuntaje().calcularPuntajeFinal() > mayorAux){
                this.ganador = jugador;
            }
        }
    }


    @Override
    public void setValorAccion1(Integer valor) throws RemoteException {

    }

    @Override
    public void setValorAccion2(Integer valor) throws RemoteException {

    }

    @Override
    public Integer getAccion1() throws RemoteException {
        return null;
    }

    @Override
    public Integer getAccion2() throws RemoteException {
        return null;
    }
}