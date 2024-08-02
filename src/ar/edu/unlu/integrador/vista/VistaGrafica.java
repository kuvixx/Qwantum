package ar.edu.unlu.integrador.vista;

import ar.edu.unlu.integrador.TopJugadores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaGrafica {
    private JButton jugarButton;
    private JPanel panel1;
    private JButton TOP5Button;

    public VistaGrafica(){
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidadJugadores = 0;
                try{ cantidadJugadores = Integer.parseInt( JOptionPane.showInputDialog("Elija cantidad de jugadores mayor o igual a 2"));
                    while (cantidadJugadores <2){
                        cantidadJugadores = Integer.parseInt( JOptionPane.showInputDialog("Numero invalido. Elija una cantidad de jugadores mayor o igual a 2"));
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"No es un número valido");
                }


            }
        });


       /* TOP5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, topJugadores.getJugadores());
            }
        });*/
    }

    public static void main() {
        JFrame frame = new JFrame("VistaGrafica");

        frame.setSize(800,500);
        frame.setVisible(true);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("Qwantum.jpg"));

        frame.add(label);


        frame.setContentPane(new VistaGrafica().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();




    }
}
