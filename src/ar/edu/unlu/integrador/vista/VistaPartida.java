package ar.edu.unlu.integrador.vista;

import ar.edu.unlu.integrador.Partida;
import ar.edu.unlu.integrador.controlador.ControladorPartida;
import ar.edu.unlu.integrador.exceptions.JugadoresLleno;
import ar.edu.unlu.integrador.modelo.Dado;
import ar.edu.unlu.integrador.modelo.DadoColores;
import ar.edu.unlu.integrador.modelo.Jugador;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPartida {
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
    private Jugador jugador;
    private ControladorPartida partida;
    private boolean state = false;



    public  VistaPartida(){
        tirarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirarDados();
            }
        }
        );

        sumarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (amarilloRadioButton.isSelected() ){
                    JOptionPane.showMessageDialog(null, "amarillo");
                } else if (azulRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "azul");
                } else if (violetaRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "violeta");
                }else {
                    JOptionPane.showMessageDialog(null, "rojo");
                }

            }
        }
        );


        }
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
            }

        }

        public void tirarDados(){

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

        public  void inicializarPartida(ControladorPartida controladorPartida){
            this.partida  = controladorPartida;
        }


    public  void main() {

        altaJugador();
        JFrame frame = new JFrame("VistaGrafica");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        partida.inicializarDados();

        frame.setSize(800, 500);
        frame.setVisible(true);


        frame.setLocationRelativeTo(null);

        frame.setContentPane(new VistaPartida().panelPrincipal);
        frame.pack();

    }
}
