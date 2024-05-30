/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;

/**
 *
 * @author nieto
 */
public class Juego {
    private int numJug;
    private Tablero tablero;
    private ArrayList<Zombie> listaZombies = new ArrayList<>();
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

    public ArrayList<Zombie> getListaZombies() {
        return listaZombies;
    }
    
    public void iniciarJuego(){
        
    }
}
