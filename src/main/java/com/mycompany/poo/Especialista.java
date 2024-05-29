/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author delac
 */
public class Especialista extends HumanoCombatiente {

    public Especialista(Casilla casilla) {
        super(2, 1, casilla);
    }

    
    
    @Override
    public void reaccionar(Zombie zombie, Object ataque) {
        if(zombie.getCasilla().equals(this.getCasilla())){
            zombie.setNumHeridas(zombie.getNumHeridas()+1);
        }
        else{
            //SE MUEVE HACIA EL ZOMBIE MAS CERCANO 
        }
    }
    
    
}
