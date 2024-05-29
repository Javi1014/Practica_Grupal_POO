/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author delac
 */
public class HumanoHuidizo extends Humano {

    public HumanoHuidizo(Casilla casilla) {
        super(1, 1, casilla);
    }

    
    @Override
    public void reaccionar(Zombie zombie, Object ataque) {
        //SE VA HACIA LA CASILLA DE SALIDA
    }
    
}
