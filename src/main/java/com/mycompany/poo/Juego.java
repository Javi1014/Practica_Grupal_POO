/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nieto
 */
public class Juego {
    private int numJug;
    private Tablero tablero;
    private ArrayList<Zombie> listaJugadores = new ArrayList<>();
    private ArrayList<Humano> listaHumanos = new ArrayList<>();
    
    public Juego(int numJug){
        this.numJug=numJug;
        this.tablero= new Tablero(numJug);
    }

    public int getNumJug() {
        return numJug;
    }

    public void setNumJug(int numJug) {
        this.numJug = numJug;
    }

    public ArrayList<Zombie> getListaJugadores() {
        return listaJugadores;
    }

    public ArrayList<Humano> getListaHumanos() {
        return listaHumanos;
    }
    
    
    public void iniciarJuego(){
        //INICIA JUEGO CON EL NUMERO DE JUGADORES QUE VAN A SER
        Coordenada inicio=new Coordenada(0,0);
        Casilla comienzo=new Casilla(inicio);
        for(int i=1;i<=this.numJug;i++){
            Scanner ent = new Scanner(System.in);
            System.out.println("Nombre "+i);
            String nombre=ent.nextLine();
            Zombie zom=new Zombie(nombre, "ACTIVO",0,0,comienzo );
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);
        }
        //SE CREAN 3 HUMANOS POR CADA JUGADOR
        for(int i=0;i<numJug;i++){
            for(int j=0;j<3;j++){
                Random random=new Random();
                int numeroAleatorio1= random.nextInt(tablero.getFilas()-1);
                int numeroAleatorio2= random.nextInt(tablero.getColumnas()-1);
                Coordenada coor=new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion=new Casilla(coor);
                Humano humano=Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
        }
        //SE CREA UN CONEJO DE PRUEBA
        Conejo con1=new Conejo("Pep",1,tablero.getCasilla(new Coordenada(1,0)));
        tablero.getCasilla(new Coordenada(1,0)).getNumConejos().add(con1);
        tablero.getCasilla(new Coordenada(1,0)).setNumConejos(tablero.getCasilla(new Coordenada(1,0)).getNumConejos());
        
        tablero.imprimirTablero();
           
        Coordenada nueva=new Coordenada(tablero.getFilas(), tablero.getColumnas());
        Casilla objetivo=new Casilla(nueva);
        while(!listaJugadores.get(0).getCasilla().equals(objetivo)){
            for(int i=0;i<listaJugadores.size();i++){
                listaJugadores.get(i).activarse(tablero,this);
            }
            for(int j=0;j<listaHumanos.size();j++){
                listaHumanos.get(j).activarse(tablero, this);
            }
            for(int x=0;x<listaJugadores.size();x++){
                Random random=new Random();
                int numeroAleatorio1= random.nextInt(tablero.getFilas()-1);
                int numeroAleatorio2= random.nextInt(tablero.getColumnas()-1);
                Coordenada coor=new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion=new Casilla(coor);
                Humano humano=Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
            tablero.imprimirTablero();
        }
    }
        
}

