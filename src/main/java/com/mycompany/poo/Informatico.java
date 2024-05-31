/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author delac
 */
public class Informatico extends HumanoCombatiente {

    public Informatico(Casilla casilla) {
        super(1, 3, casilla);
    }

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        if (zombie.getHambre() >= 4) {
            zombie.setHambre(zombie.getHambre() - 2);

            //el informatico podia dañar si se lo comen
            if (((Math.random() * 2) + 1) == 1) {
                zombie.setNumHeridas(zombie.getNumHeridas() + 1);
            }
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
    /*
    @Override
    public void activarse() {
        Coordenada casSalida(tablero.getFilas(),tablero.getColumnas()); 
        //
        if(this.getCoordenada()==casSalida){
            //sale del juego
        }
        else if(!(this.getCoordenada().getX()==casSalida.getX())){
            this.moverse(tablero, this.getCoordenada().setX(this.getCoordenada().getX()+1));
        }
        else{
            this.moverse( this.getCoordenada().sety(this.getCoordenada().getY()+1));
        }
    }
    */

    @Override
    public void atacar(Casilla posicion){
        this.getCasilla().getNumZombie().get(0).setNumHeridas(+1);
    }

    @Override
    public Casilla getCasilla() {
        Casilla nueva = new Casilla(this.getCasilla().getCoordenada());
        return nueva;
    }

    @Override
    public void activarse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
