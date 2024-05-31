/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;
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
        Tablero tabla=new Tablero(this.numJug);
        Coordenada inicio=new Coordenada(0,0);
        Casilla comienzo=new Casilla(inicio);
        for(int i=1;i<=this.numJug;i++){
            Scanner ent = new Scanner(System.in);
            System.out.println("Nombre "+i);
            String nombre=ent.nextLine();
            Zombie zom=new Zombie(nombre, "ACTIVO",0,0,comienzo );
            tabla.getCasilla(inicio).getNumZombie().add(zom);
            tabla.getCasilla(inicio).setNumZombie(tabla.getCasilla(inicio).getNumZombie());
        }
        Humano humano1 = new HumanoHuidizo(tabla.getCasilla(new Coordenada(2,1)));
        tabla.getCasilla(new Coordenada(2,1)).getNumHumano().add(humano1);
        tabla.getCasilla(new Coordenada(2,1)).setNumHumano(tabla.getCasilla(new Coordenada(2,1)).getNumHumano());
        Humano humano2 = new HumanoHuidizo(tabla.getCasilla(new Coordenada(2,3)));
        tabla.getCasilla(new Coordenada(2,3)).getNumHumano().add(humano2);
        tabla.getCasilla(new Coordenada(2,3)).setNumHumano(tabla.getCasilla(new Coordenada(2,3)).getNumHumano());
        Conejo con1=new Conejo("Pep",1,tabla.getCasilla(new Coordenada(1,0)));
        tabla.getCasilla(new Coordenada(1,0)).getNumConejos().add(con1);
        tabla.getCasilla(new Coordenada(1,0)).setNumConejos(tabla.getCasilla(new Coordenada(1,0)).getNumConejos());
        Zombie zombie1 = tabla.getCasilla(inicio).getNumZombie().get(0);
        Zombie zombie2 = tabla.getCasilla(inicio).getNumZombie().get(1);
        tabla.imprimirTablero();

        for(int i=0;i<=1;i++){
            zombie1.activarse(tabla);
            zombie2.activarse(tabla);
            
        }
    }
        
}

