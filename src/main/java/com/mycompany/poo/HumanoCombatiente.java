/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author delac
 */
public abstract class HumanoCombatiente extends Humano {

    public HumanoCombatiente(int num_activaciones, int aguante, Casilla casilla) {
        super(num_activaciones, aguante, casilla);
    }
    public void zombieMasCercano(){
        Coordenada coormascerca;
        int distancia = 300;
        for(int i=1,i<juego.getNumJug(),i++){
            tablero.calcularDistancia(this.getCasilla(),juego.getListaZombies(i));
            if( (tablero.calcularDistancia(this.getCasilla(),juego.getListaZombies(i)) < distancia)){
                
            }
        }
    }
    /* 
    @Override
    public abstract void reaccionar(Zombie zombie, Ataque ataque);//ESTO HAY QUE QUITARLO PORQUE ES ATACAR O MOVERSE
    
    }
     */
}
