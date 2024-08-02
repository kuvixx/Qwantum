package ar.edu.unlu.integrador.controlador;

import ar.edu.unlu.integrador.IPartida;
import ar.edu.unlu.integrador.Partida;
import ar.edu.unlu.integrador.exceptions.JugadoresLleno;
import ar.edu.unlu.integrador.modelo.*;
import ar.edu.unlu.integrador.vista.IVistaPartida;
import ar.edu.unlu.integrador.vista.VistaPartida;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.awt.*;
import java.rmi.RemoteException;

public class ControladorPartida implements IControladorRemoto {
    private IPartida partidaActual;
    private final IVistaPartida vistaPartida;
    private int indiceJugador;



    public ControladorPartida(IVistaPartida vistaPartida){
        this.vistaPartida = vistaPartida;
        vistaPartida.inicializarPartida(this);

    }

    public void agregarJugador(String nombreJugador) throws JugadoresLleno, RemoteException {
        Jugador jugador = new Jugador(nombreJugador,  new Puntaje());

        int iJugadorAux = this.partidaActual.setJugadores(jugador) ;
        if (iJugadorAux == -1){
            throw new JugadoresLleno();
        }else {
            this.indiceJugador = iJugadorAux;
            System.out.println(iJugadorAux);
            if (this.indiceJugador != 0){
                vistaPartida.deshabilitarJugador();
            }
        }

        
    }

    public void tirarDados() throws RemoteException {

        partidaActual.tirarDados();
    }

    public DadoColores[] devolverDadosColores() throws RemoteException {

        return this.partidaActual.getDadosColores();

    }

    public void sumarDescarte() throws RemoteException {
        partidaActual.sumarDescarte();
    }

    public Puntaje devolverPuntaje() throws RemoteException {
        return this.partidaActual.getJugador(indiceJugador).getPuntaje();
    }

    public boolean sumarPuntaje(Color color) throws RemoteException {

        boolean b = partidaActual.sumarPuntajes(color);
        vistaPartida.mostrarPuntos();

        return b;
    }

    public Dado devolverDadoBlanco() throws RemoteException {
        return this.partidaActual.getDadoBlanco();
    }

    public void cambiarTurno() throws RemoteException {
        partidaActual.cambiarTurno();
    }

    public void finTurno() throws RemoteException {
        vistaPartida.deshabilitarJugador();
        partidaActual.cambiarTurno();
    }

    public void comprobarTurnoActual() throws RemoteException {
        if (partidaActual.getIndiceJugadorActual() == indiceJugador){
            System.out.println("Turno de jugador "+ indiceJugador);
            vistaPartida.habilitarJugador();
        }else {
            vistaPartida.deshabilitarJugador();
        }

    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.partidaActual = (IPartida) modeloRemoto;
    }

    @Override
    public void actualizar(IObservableRemoto modelo, Object cambio) throws RemoteException {
        if (cambio instanceof Eventos) {
            switch ((Eventos) cambio) {
                case ACTUALIZAR_DADOS:
                    this.vistaPartida.actualizarDados();
                    break;
                case CAMBIAR_TURNO:
                    comprobarTurnoActual();
                    break;
                case FIN_DEL_JUEGO:
                    this.vistaPartida.deshabilitarJugador();
                    this.vistaPartida.actualizarTop5(partidaActual.getGanador());
                    this.vistaPartida.mostrarMensaje("El ganador de la partida es :" + partidaActual.getGanador().getNombre() + "Con " + + partidaActual.getGanador().getPuntaje().calcularPuntajeFinal() + " Puntos"  );
                    break;
                case AGREGAR_JUGADOR:
                    comprobarTurnoActual();
                    break;
                default:
                    break;

            }
        }

    }
}
