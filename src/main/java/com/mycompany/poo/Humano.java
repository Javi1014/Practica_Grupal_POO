/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.Random;

/**
 *
 * @author delac
 */
public abstract class Humano implements Comestible, Activable {

    private int num_activaciones;
    private int aguante;
    private Casilla casilla;

    public Humano(int num_activaciones, int aguante, Casilla casilla) {
        this.num_activaciones = num_activaciones;
        this.aguante = aguante;
        this.casilla=casilla;
    }

    public int getNum_activaciones() {
        return num_activaciones;
    }

    public void setNum_activaciones(int num_activaciones) {
        this.num_activaciones = num_activaciones;
    }

    public int getAguante() {
        return aguante;
    }

    public void setAguante(int aguante) {
        this.aguante = aguante;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public static Humano aparicion(Casilla casilla) {
        Humano humano;
        Random random = new Random();
        int numeroAleatorio= random.nextInt(99)+1;
        if (numeroAleatorio<=60){
            int numeroAleatorio2 = random.nextInt(100)+1;
            if (numeroAleatorio2 <=60){
                humano = new Blindado(casilla);
            }else if (numeroAleatorio2>60 && numeroAleatorio2<=90){
                humano = new Especialista(casilla);
            }else {
                humano = new Soldado(casilla);
            }
        }else if((numeroAleatorio>60)&&(numeroAleatorio<=85)){
            humano=new HumanoHuidizo(casilla);
        }else{
            humano = new Informatico(casilla);
        }
        return humano;
    }
    
    public Coordenada zombieMasCercano(Tablero tablero, Juego juego){
      Coordenada coormascerca = juego.getListaJugadores().get(0).getCasilla().getCoordenada();
        int distancia = 300;
        for(int i=0;i<juego.getNumJug();i++){
            if( (tablero.calcularDistancia(this.getCasilla(),juego.getListaJugadores().get(i).getCasilla()) < distancia)){
                coormascerca.setX(juego.getListaJugadores().get(i).getCasilla().getCoordenada().getX());
                coormascerca.setY(juego.getListaJugadores().get(i).getCasilla().getCoordenada().getY());
            }
        }
        return coormascerca;
    }
}
