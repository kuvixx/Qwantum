package ar.edu.unlu.integrador.vista;

import ar.edu.unlu.integrador.Partida;
import ar.edu.unlu.integrador.controlador.ControladorPartida;
import ar.edu.unlu.integrador.exceptions.JugadoresLleno;
import ar.edu.unlu.integrador.modelo.Dado;
import ar.edu.unlu.integrador.modelo.DadoColores;
import ar.edu.unlu.integrador.modelo.Jugador;
import ar.edu.unlu.integrador.modelo.Puntaje;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Arrays;

public class VistaPartida implements IVistaPartida {
    private JButton tirarButton;
    private ButtonGroup colores;
    private JRadioButton amarilloRadioButton;
    private JRadioButton violetaRadioButton;
    private JRadioButton azulRadioButton;
    private JRadioButton rojoRadioButton;
    private JButton sumarButton;
    private JButton descartarButton;
    private JList list1;
    private JList list2;
    private JPanel panelPrincipal;
    private JPanel panelDados;
    private JPanel panelDados2;
    private JPanel panelDados1;
    private JLabel dado4;
    private JLabel dado5;
    private JLabel dado6;
    private JLabel dado1;
    private JLabel dado2;
    private JLabel dado3;
    private JLabel dadoBlanco;
    private ControladorPartida partida;
    private boolean state = false;
    private final JFrame frame;

    private DefaultListModel<String> listModel;



    public  VistaPartida(){
        frame = new JFrame("Jugador");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setContentPane(panelPrincipal);

        listModel = new DefaultListModel<>();
        list2.setModel(listModel);


        sumarButton.setEnabled(false);
        descartarButton.setEnabled(false);
        dado1.setOpaque(true);
        dado2.setOpaque(true);
        dado3.setOpaque(true);
        dado4.setOpaque(true);
        dado5.setOpaque(true);
        dado6.setOpaque(true);

        frame.pack();

        tirarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tirarDados();
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(null, "error");
                    throw new RuntimeException(ex);
                }
                // tirarButton.setEnabled(false);
            }
        }
        );

        sumarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sumar();
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(null, "error");
                    throw new RuntimeException(ex);
                }
            }

        }
        );


        descartarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    descartarTurno();
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(null, "error");
                    throw new RuntimeException(ex);
                }
            }
        });
    }

        @Override
        public void sumar() throws RemoteException {
            boolean insertado = false;
            if (amarilloRadioButton.isSelected() ){
                insertado = partida.sumarPuntaje(Color.YELLOW);
            } else if (azulRadioButton.isSelected()) {
                insertado = partida.sumarPuntaje(Color.blue);
            } else if (violetaRadioButton.isSelected()) {
                insertado = partida.sumarPuntaje(Color.magenta);
            }else if (rojoRadioButton.isSelected()) {
                insertado = partida.sumarPuntaje(Color.red);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un color para agregar al puntaje");
            }

            if (!insertado){
                JOptionPane.showMessageDialog(null, "El valor a agregar es incorrecto");
            }else {
                JOptionPane.showMessageDialog(null, "Valor ingresado correctamente, siguiente turno");
                partida.finTurno();
            }

        }
        @Override
        public void altaJugador(){
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
            this.partida.tirarDados();
            actualizarDados();
            sumarButton.setEnabled(true);
            descartarButton.setEnabled(true);

        }

        @Override
        public  void inicializarPartida(ControladorPartida controladorPartida){
            this.partida  = controladorPartida;
        }



    public void actualizarDados() throws RemoteException {
        DadoColores[] dados = this.partida.devolverDadosColores();
        Dado db = partida.devolverDadoBlanco();
        dadoBlanco.setText(Integer.toString(db.getNumeroActual()));

        dado1.setText( Integer.toString(dados[0].getNumeroActual()) );
        dado2.setText(Integer.toString(dados[1].getNumeroActual()));
        dado3.setText(Integer.toString(dados[2].getNumeroActual()));
        dado4.setText(Integer.toString(dados[3].getNumeroActual()));
        dado5.setText(Integer.toString(dados[4].getNumeroActual()));
        dado6.setText(Integer.toString(dados[5].getNumeroActual()));


        dado1.setBackground(dados[0].getColorActual());
        dado2.setBackground(dados[1].getColorActual());
        dado3.setBackground(dados[2].getColorActual());
        dado4.setBackground(dados[3].getColorActual());
        dado5.setBackground(dados[4].getColorActual());
        dado6.setBackground(dados[5].getColorActual());
    }


    @Override
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
        listModel.addElement(mensaje);


    }

    public void descartarTurno() throws RemoteException {
        partida.sumarDescarte();
        try {
            partida.finTurno();
        }catch ( RemoteException ex){
            JOptionPane.showMessageDialog(null, "Error");
        }

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
    public void habilitarJugador(){
        tirarButton.setEnabled(true);

    }

    @Override
    public void deshabilitarJugador(){
        tirarButton.setEnabled(false);
        sumarButton.setEnabled(false);
        descartarButton.setEnabled(false);
    }



    @Override
    public void mostrarMenuPrincipal() {
        altaJugador();
        mostrar();
    }





}
