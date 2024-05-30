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

    /* 
    @Override
    public void reaccionar(Zombie zombie, Ataque ataque) {//ESTO HAY QUE QUITARLO PORQUE ES ATACAR O MOVERSE
        //SE VA HACIA LA CASILLA DE SALIDA
    }
     */
    @Override
    public void calmarHambreZombie(Zombie zombie) {
        zombie.setHambre(0);
    }

    @Override
    public void moverse(Tablero tablero,Coordenada posicion) {
    }

    @Override
    public void activarse() {
        Coordenada casSalida(tablero.getFilas(),tablero.getColumnas()); 
        //
        if(this.getCoordenada()==casSalida){
            //sale del juego
        }else if(!(this.getCoordenada().getX()==casSalida.getX())){
            this.moverse(tablero, this.getCoordenada().setX(this.getCoordenada().getX()+1));
        }else{
            this.moverse( this.getCoordenada().sety(this.getCoordenada().getY()+1));
        }
    }

    @Override
    public void atacar(Casilla posicion) {
    }

    @Override
    public Coordenada getCoordenada() {
        return this.getCasilla().getCoordenada();
    }

}
