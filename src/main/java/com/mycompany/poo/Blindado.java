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
    public void moverse(Tablero tablero,Casilla posicion){
        Coordenada objetivo=null;
        objetivo = posicion.getCoordenada();
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
    
    @Override
    public void activarse(Tablero tablero, Juego juego){
        if(this.getCasilla().getNumZombie().isEmpty()){
            Coordenada objetivo=this.zombieMasCercano(tablero, juego);
            Casilla nueva=new Casilla(objetivo);
            this.moverse(tablero,nueva);
        }else{
            this.atacar(tablero,this.getCasilla());
        }
    }
   

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        zombie.setHambre(0);
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

    
    
}
