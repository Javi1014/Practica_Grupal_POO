/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author nieto
 */
public interface Activable {
    public void moverse(Tablero tablero ,Coordenada posicion);
    public void activarse();
    public void atacar (Casilla posicion);
    public Coordenada getCoordenada();
}
