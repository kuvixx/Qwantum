package ar.edu.unlu.integrador;
import java.rmi.RemoteException;


import javax.swing.JOptionPane;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class AppServidor {

    public static void main(String[] args) {
        ArrayList<String> ips = Util.getIpDisponibles();
        String ip = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ips.toArray(),

                "127.0.0.1"
        );
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                8888
        );
        int cantidadJugadores = 0;
        try{ cantidadJugadores = Integer.parseInt( JOptionPane.showInputDialog("Elija cantidad de jugadores mayor o igual a 2"));
            while (cantidadJugadores <2){
                cantidadJugadores = Integer.parseInt( JOptionPane.showInputDialog("Numero invalido. Elija una cantidad de jugadores mayor o igual a 2"));
            }
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"No es un número valido");

        }
        Partida modelo = new Partida(cantidadJugadores);
        Servidor servidor = new Servidor(ip, Integer.parseInt(port));
        try {
            servidor.iniciar(modelo);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RMIMVCException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}