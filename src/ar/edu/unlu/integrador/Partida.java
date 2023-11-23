package ar.edu.unlu.integrador;

import ar.edu.unlu.integrador.modelo.Dado;
import ar.edu.unlu.integrador.modelo.DadoColores;
import ar.edu.unlu.integrador.modelo.Jugador;
import ar.edu.unlu.integrador.modelo.Puntaje;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Partida {
    private  Jugador[] jugadores ;

    private DadoColores[] dados = new DadoColores[6];
    private Dado dadoBlanco = new Dado();
    private int maxJugadores;

    public void iniciarJugadores(int maxJugadores){

        this.maxJugadores = maxJugadores;
        jugadores = new Jugador[maxJugadores];

    }

    public boolean setJugadores(Jugador jugador) {
        int i = 0;
        boolean insertado = false;
        while (jugadores[i] != null && !insertado){
            i++;
            if (jugadores[i] == null ){
                jugadores[i] = jugador;
                insertado = true;
            }
        }
        return insertado;
    }

    public void inicializarDados(){

        DadoColores dado1 = new DadoColores();
        DadoColores dado2 = new DadoColores();
        DadoColores dado3 = new DadoColores();
        DadoColores dado4 = new DadoColores();
        DadoColores dado5 = new DadoColores();
        DadoColores dado6 = new DadoColores();

        dado1.cargarDado(1,2,3,4);
        dado2.cargarDado(1,2,3,4);
        dado3.cargarDado(2,3,4,5);
        dado4.cargarDado(3,4,5,6);
        dado5.cargarDado(4,5,6,1);
        dado6.cargarDado(5,6,1,2);

        this.dados[0] =dado1;
        this.dados[1] =dado2;
        this.dados[2] =dado3;
        this.dados[3] =dado4;
        this.dados[4] =dado5;
        this.dados[5] =dado6;

    }

    public DadoColores[] getDadosColores(){
        return this.dados;
    }

    public Dado getDadoBlanco() {
        return dadoBlanco;
    }

    public void tirarDados(){
        dadoBlanco.tirarDado();
        for (int i = 0; i==dados.length;i++){
            dados[i].tirarDado();
        }

    }

    public void nuevaPartida(){
        Scanner scanner = new Scanner(System.in);
        //preguntar cantidad de jugadores y crear.
        int cant = 2;
        boolean b = true;
        System.out.println("Ingrese la cantidad de jugadores (1-6)");
        while (b) {
            cant = scanner.nextInt();
            if(cant<1 || cant>6){
                System.out.println("Ingrese un numero valido");
            }else{
                b=false;
            }
        }




        for(int i=0;i<=jugadores.length;i++){
            Jugador jugadorActual = jugadores[i];
            int repetido = 0;
            while (repetido <2) {

                System.out.println("TURNO JUGADOR: " + i + 1);
                for (int j = 0; j==dados.length;j++){
                    dados[j].tirarDado();
                }


                int amarillo = dadoBlanco.getNumeroActual();
                int violeta = dadoBlanco.getNumeroActual();
                int azul = dadoBlanco.getNumeroActual();
                int rojo = dadoBlanco.getNumeroActual();
                /*
                for (int j = 0; j <= dados.length; j++) {
                    DadoColores dadoAux = dados[j];
                    switch (dadoAux.getColorActual()) {
                        case "amarillo":
                            amarillo = amarillo + dadoAux.getNumeroActual();
                            break;
                        case "violeta":
                            violeta = violeta + dadoAux.getNumeroActual();
                            break;
                        case "azul":
                            azul = azul + dadoAux.getNumeroActual();
                            break;
                        case "rojo":
                            rojo = rojo + dadoAux.getNumeroActual();
                            break;
                    }
                }
                */

                System.out.println("amarillo: " + amarillo);
                System.out.println("violeta: " + violeta);
                System.out.println("azul: " + azul);
                System.out.println("rojo: " + rojo);

                repetido ++;
                if (repetido == 1) {
                    System.out.println("Acepta esta tirada o desea tirar de nuevo? Y=si N=no");
                    String yesorno = scanner.next().toLowerCase(Locale.ROOT);
                    while (yesorno.equals("y") || yesorno.equals("n")){
                        System.out.println("Valor incorrecto  Y=si N=no");
                        yesorno = scanner.next().toLowerCase(Locale.ROOT);
                    }
                    if (yesorno.equals("n")){
                        repetido=2;
                    }
                }
            } //fin tirada

            //verificacion puntaje
            Puntaje puntajeActual = jugadorActual.getPuntaje();
            puntajeActual.mostrarPuntajes();




        }
    }


    
}
