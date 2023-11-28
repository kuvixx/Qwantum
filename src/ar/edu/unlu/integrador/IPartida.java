package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.modelo.Dado;
import ar.edu.unlu.integrador.modelo.DadoColores;
import ar.edu.unlu.integrador.modelo.Jugador;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IPartida extends IObservableRemoto {

    void setValorAccion1(Integer valor) throws RemoteException;
    void setValorAccion2(Integer valor) throws RemoteException;
    Integer getAccion1() throws RemoteException;
    Integer getAccion2() throws RemoteException;

    int setJugadores(Jugador jugador) throws RemoteException;

    void tirarDados()throws RemoteException;

    DadoColores[] getDadosColores() throws RemoteException;;

    Dado getDadoBlanco() throws RemoteException;

    Jugador getGanador() throws RemoteException;

    void cambiarTurno() throws RemoteException;

    void sumarDescarte() throws RemoteException;

    boolean sumarPuntajes(Color color) throws RemoteException;

    Jugador getJugador(int indice) throws RemoteException;

    int getIndiceJugadorActual() throws  RemoteException;
}
