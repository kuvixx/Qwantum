package ar.edu.unlu.integrador.vista;

import ar.edu.unlu.integrador.controlador.ControladorPartida;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVistaPartida {
    void sumar() throws RemoteException;

    void altaJugador();

    void tirarDados() throws RemoteException;

    void inicializarPartida(ControladorPartida controladorPartida);

    void mostrar();

    void mostrarPuntos() throws RemoteException;

    void mostrarTop5() ;

    void habilitarJugador();

    void deshabilitarJugador();

    void descartarTurno() throws RemoteException;


    void actualizarDados() throws RemoteException;

    void mostrarMenuPrincipal();
    void mostrarMensaje(String cadena);
}
