/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

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
        this.casilla = casilla;
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

    public void aparicion() {

    }
    
  public void zombieMasCercano(){
      
  }
}
