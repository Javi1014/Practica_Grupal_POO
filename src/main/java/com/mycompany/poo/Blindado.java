/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author delac
 */
public class Blindado extends HumanoCombatiente {

    public Blindado(Casilla casilla) {
        super(1, 2, casilla);
    }

    
    @Override
    public void reaccionar(Zombie zombie, Ataque ataque) {//ESTO HAY QUE QUITARLO PORQUE ES ATACAR O MOVERSE
        if(zombie.getCasilla().equals(this.getCasilla())){
            zombie.setNumHeridas(zombie.getNumHeridas()+1);
        }
        else{
            //SE MUEVE HACIA EL ZOMBIE MAS CERCANO
        }
    }
    @Override
    public void moverse(){
        Coordenada objetivo;
        objetivo = this.zombieMasCercano();
        if(!(this.getCasilla().getCoordenada().getX() == objetivo.getX())){
            if(this.getCasilla().getCoordenada().getX() < objetivo.getX()){
                this.getCasilla().getCoordenada().setX(this.getCasilla().getCoordenada().getX()+1);
            }else{
                this.getCasilla().getCoordenada().setX(this.getCasilla().getCoordenada().getX()-1);
            }
        }else{
            if(this.getCasilla().getCoordenada().getY() < objetivo.getY()){
                this.getCasilla().getCoordenada().setY(this.getCasilla().getCoordenada().getY()+1);
            }else{
                this.getCasilla().getCoordenada().setY(this.getCasilla().getCoordenada().getY()-1);
            }
        }
        
    }
    //se activa cuando es su turno y hace lo que le toque
    @Override
    public void activarse(){
        if(this.getCasilla().getNumZombie().isEmpty()){
            this.moverse();
        }else{
            this.atacar(this.getCasilla());
        }
}
   

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        zombie.setHambre(0);
    }
    
}
