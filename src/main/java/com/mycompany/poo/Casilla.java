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
public class Casilla {
    private Coordenada coordenada;
    private ArrayList<Zombie> numZombie=new ArrayList<>();
    private ArrayList<Humano> numHumano=new ArrayList<>();
    private ArrayList<Conejo> numConejos = new ArrayList<>();

    public Casilla(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
    
   

    public Coordenada getCoordenada(){
        return this.coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    //FALTARIA AÑADIR SI ESO SETYGET DE ZOMBIES Y SUPERVIVIENTES CND LOS TENGAMOS HECHOS

}
