/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author delac
 */
public class Soldado extends HumanoCombatiente {

    public Soldado(Casilla casilla) {
        super(1, 1, casilla);
    }
    
    @Override
    public void moverse(Tablero tablero,Casilla posicion){
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
    public void activarse(Tablero tablero){
        if(this.getCasilla().getNumZombie().isEmpty()){
            //this.moverse();
        }else{
            //this.atacar(this.getCasilla());
        }
    }



    @Override
    public void atacar(Tablero tablero,Casilla posicion){
        this.getCasilla().getNumZombie().get(0).setNumHeridas(+1);
    }

    @Override
    public Casilla getCasilla() {
        Casilla nueva = new Casilla(this.getCasilla().getCoordenada());
        return nueva;
    }

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        zombie.setHambre(0);
    }
    
    
}
