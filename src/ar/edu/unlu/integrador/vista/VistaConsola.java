package ar.edu.unlu.integrador.vista;

import ar.edu.unlu.integrador.Serializacion;
import ar.edu.unlu.integrador.TopJugadores;
import ar.edu.unlu.integrador.controlador.ControladorPartida;
import ar.edu.unlu.integrador.exceptions.JugadoresLleno;
import ar.edu.unlu.integrador.modelo.Dado;
import ar.edu.unlu.integrador.modelo.DadoColores;
import ar.edu.unlu.integrador.modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Locale;

public class VistaConsola implements IVistaPartida{
    private JPanel panel1;
    private JTextField textField1;
    private JList list1;
    private JButton enviarButton;
    private ControladorPartida partida;
    private final JFrame frame;
    private int tirado = 0;
    private boolean habilitado = true;

    private TopJugadores topJugadores;
    private DefaultListModel<String> listModel;



    public  VistaConsola(){
        frame = new JFrame("Jugador");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel1);
        topJugadores = Serializacion.obtenerTop();
        System.out.println("prueba " + topJugadores.get(0).getPuntaje().getTotalPuntos());

        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        frame.pack();




        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    procesarTexto(textField1.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public void menuPrincipal(){
        listModel.clear();
        if (habilitado) {
            listModel.addElement("1- Tirar dados");
            listModel.addElement("2- Sumar puntos");
            listModel.addElement("3- Descartar");
            listModel.addElement("4- Mostrar top 5");
        }else {
            listModel.addElement("4- Mostrar top 5");
        }
    }


    public void procesarTexto(String cadena) throws RemoteException {
        mostrarMensaje("----------------------------");
        if (habilitado) {
            switch (cadena.toLowerCase()) {
                case "0":
                    menuPrincipal();
                    break;
                case "1":
                    tirarDados();
                    break;
                case "2":
                    sumar();
                    break;
                case "3":
                    descartarTurno();
                    break;
                case "4":
                    mostrarTop5();
                    break;
                case "rojo":
                    sumarAux("rojo");
                    break;
                case "azul":
                    sumarAux("azul");
                    break;
                case "amarillo":
                    sumarAux("amarillo");
                    break;
                case "violeta":
                    sumarAux("violeta");
                    break;
                default:
                    mostrarMensaje("Incorrecto, vuelva a elegir una opción");
                    break;

            }
        } else {
            switch (cadena) {
                case "0":
                    menuPrincipal();
                    break;
                case "4":
                    mostrarTop5();
                    break;
                default:
                    mostrarMensaje("Incorrecto, vuelva a elegir una opción");
                    break;
            }
        }
    }

    @Override
    public void sumar() throws RemoteException {
        if (tirado == 0) {
            mostrarMensaje("Debes tirar primero para poder sumar puntos");
        }else {
            mostrarMensaje("Ingresa el color que quieres sumar ('Rojo','Azul','Amarillo','Violeta'");
        }

    }

    public void sumarAux(String cadena) throws RemoteException{
        boolean insertado = false;
        cadena = cadena.toLowerCase(Locale.ROOT);

        switch (cadena) {
            case "amarillo" -> insertado = partida.sumarPuntaje(Color.YELLOW);
            case "azul" -> insertado = partida.sumarPuntaje(Color.blue);
            case "violeta" -> insertado = partida.sumarPuntaje(Color.magenta);
            case "rojo" -> insertado = partida.sumarPuntaje(Color.red);
            default -> mostrarMensaje("Incorrecto");
        }

        if (!insertado){
            mostrarMensaje( "El valor a agregar es incorrecto");
        }else {
            mostrarMensaje( "Valor ingresado correctamente, siguiente turno");
            partida.finTurno();
        }

    }

    @Override
    public void altaJugador() {
        String nombreJugador= "";
        try{ nombreJugador = ( JOptionPane.showInputDialog("Ingrese nombre de jugador"));}
        catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error, vuelva a ingresar nombre");
        }
        try {
            this.partida.agregarJugador(nombreJugador);
        }catch (JugadoresLleno ex){
            JOptionPane.showMessageDialog(null, "Jugadores llenos");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Max jugadores");
            throw new RuntimeException(e);
        }


    }

    @Override
    public void tirarDados() throws RemoteException {
        if (tirado < 2) {
            tirado ++;
            mostrarMensaje("Tirada n°"+ tirado);
            this.partida.tirarDados();
        }else{
            mostrarMensaje("No puedes tirar el dado 3 veces");

        }

    }

    @Override
    public void inicializarPartida(ControladorPartida controladorPartida) {
        this.partida  = controladorPartida;
    }

    @Override
    public void mostrar() {
        frame.setSize(new Dimension(800, 450));
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void mostrarPuntos() throws RemoteException {
        listModel.clear();

        listModel.addElement("Azul : " + Arrays.toString(partida.devolverPuntaje().getPuntosAzul()));
        listModel.addElement("Rojo : " + Arrays.toString(partida.devolverPuntaje().getPuntosRojo()));
        listModel.addElement("Violeta : " + Arrays.toString(partida.devolverPuntaje().getPuntosVioleta()));
        listModel.addElement("Amarillo : " + Arrays.toString(partida.devolverPuntaje().getPuntosAmarillo()));
    }

    @Override
    public void mostrarTop5() {
        listModel.clear();

        listModel.addElement(topJugadores.getJugadores() );

        listModel.addElement("0 - Mostrar menú principal");

        // listModel.addElement("Azul : " + Arrays.toString(partida.devolverPuntaje().getPuntosAzul()));

    }

    @Override
    public void actualizarTop5(Jugador jugador) {
        System.out.println("act");
        topJugadores.agregarJugadores(jugador);
        mostrarTop5();
        Serializacion.guardarTop(topJugadores);
    }

    @Override
    public void habilitarJugador() {
        tirado = 0;
        habilitado = true;
        menuPrincipal();
    }

    @Override
    public void deshabilitarJugador() {
        habilitado = false;
    }

    @Override
    public void descartarTurno() throws RemoteException {
        partida.sumarDescarte();
        try {
            listModel.clear();
            mostrarMensaje("Turno de otro jugador...");
            partida.finTurno();
        }catch ( RemoteException ex){
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    @Override
    public void actualizarDados() throws RemoteException {
        DadoColores[] dados = this.partida.devolverDadosColores();
        Dado db = partida.devolverDadoBlanco();
        mostrarMensaje("----------------------------");
        listModel.addElement(Integer.toString(dados[0].getNumeroActual()) +" color "+ dados[0].getTextColor());
        listModel.addElement(Integer.toString(dados[1].getNumeroActual()) +" color "+ dados[1].getTextColor());
        listModel.addElement(Integer.toString(dados[2].getNumeroActual()) +" color "+ dados[2].getTextColor());
        listModel.addElement(Integer.toString(dados[3].getNumeroActual()) +" color "+ dados[3].getTextColor());
        listModel.addElement(Integer.toString(dados[4].getNumeroActual()) +" color "+ dados[4].getTextColor());
        listModel.addElement(Integer.toString(dados[5].getNumeroActual()) +" color "+ dados[5].getTextColor());
        listModel.addElement(Integer.toString(db.getNumeroActual()) +" color "+ "Blanco");


    }

    @Override
    public void mostrarMenuPrincipal() {
        altaJugador();
        mostrar();

    }

    @Override
    public void mostrarMensaje(String cadena) {
        listModel.addElement(cadena);
    }
}
