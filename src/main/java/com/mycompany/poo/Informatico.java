/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;

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
    public void moverse(Tablero tablero, Casilla posicion){
        Casilla nueva;
        if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) <= Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getY() > posicion.getCoordenada().getY()) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() - 1));
        } else if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) < Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getY() < posicion.getCoordenada().getY()) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1));
        } else if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) >= Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getX() > posicion.getCoordenada().getX()) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX() - 1, this.getCasilla().getCoordenada().getY()));
        } else {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY()));
        }
        
        int xActual = this.getCasilla().getCoordenada().getX();
        int yActual = this.getCasilla().getCoordenada().getY();
        int xDestino = nueva.getCoordenada().getX();
        int yDestino = nueva.getCoordenada().getY();

        // Verifica si la casilla posicion es contigua en sentido vertical u horizontal
        boolean esContiguaHorizontalmente = (xActual == xDestino) && (Math.abs(yActual - yDestino) == 1);
        boolean esContiguaVerticalmente = (yActual == yDestino) && (Math.abs(xActual - xDestino) == 1);

        if (esContiguaHorizontalmente || esContiguaVerticalmente) {
            Casilla casillaActual = tablero.getCasilla(this.getCasilla().getCoordenada());
            ArrayList<Humano> humanosCasillaActual = casillaActual.getNumHumano();
            humanosCasillaActual.remove(this);
            casillaActual.setNumHumano(humanosCasillaActual);

            Casilla casillaObjetivo = tablero.getCasilla(nueva.getCoordenada());
            ArrayList<Humano> humanosCasillaObjetivo = casillaObjetivo.getNumHumano();
            humanosCasillaObjetivo.add(this);
            casillaObjetivo.setNumHumano(humanosCasillaObjetivo);

            this.setCasilla(casillaObjetivo);
            System.out.println("El informatico se ha movido a la posicion "  + nueva.getCoordenada().getX()+" "+nueva.getCoordenada().getY());
        }
        else {
            System.out.println("El informatico no se puede mover porque esta rodeado de zombies, utiliza la accion en otra accion diferente a moverse");
        }
    }

    @Override
    public void atacar(Tablero tablero,Juego juego){
        Casilla casillaTablero = tablero.getCasilla(this.getCasilla().getCoordenada());
        if(casillaTablero.getNumZombie().get(0).getNumHeridas()<5){
            casillaTablero.getNumZombie().get(0).setNumHeridas(casillaTablero.getNumZombie().get(0).getNumHeridas()+1);
            System.out.println("El zombie "+casillaTablero.getNumZombie().get(0).getNombre()+" tiene "+casillaTablero.getNumZombie().get(0).getNumHeridas()+" heridas");
            if(casillaTablero.getNumZombie().get(0).getNumHeridas()==5){
                ArrayList<Zombie> zombies = casillaTablero.getNumZombie();
                ArrayList<Zombie> jugadores = casillaTablero.getNumZombie();
                System.out.println("El humano blindado ha matado al zombie "+casillaTablero.getNumZombie().get(0).getNombre());
                zombies.remove(casillaTablero.getNumZombie().get(0));
                casillaTablero.setNumZombie(zombies);
                juego.getListaJugadores().remove(jugadores.get(0));
                juego.setListaJugadores(juego.getListaJugadores());
            }
        }
    } 

    @Override
    public void activarse(Tablero tablero, Juego juego){
        Coordenada opc1 = new Coordenada(this.getCasilla().getCoordenada().getX()+1,this.getCasilla().getCoordenada().getY());
        Casilla opcion1=null;
        if(tablero.getFilas()-1>=opc1.getX()&&tablero.getColumnas()-1>=opc1.getY()){
            opcion1 = tablero.getCasilla(opc1);
        }
        Coordenada opc2 = new Coordenada(this.getCasilla().getCoordenada().getX(),this.getCasilla().getCoordenada().getY()+1);
        Casilla opcion2=null;
        if(tablero.getFilas()-1>=opc1.getX()&&tablero.getColumnas()-1>=opc1.getY()){
            opcion2 = tablero.getCasilla(opc2);
        }
        Coordenada opc3 = new Coordenada(this.getCasilla().getCoordenada().getX()-1,this.getCasilla().getCoordenada().getY());
        Casilla opcion3=null;
        if(tablero.getFilas()-1>=opc1.getX()&&tablero.getColumnas()-1>=opc1.getY()){
            opcion3 = tablero.getCasilla(opc3);
        }
        Coordenada opc4 = new Coordenada(this.getCasilla().getCoordenada().getX(),this.getCasilla().getCoordenada().getY()-1);
        Casilla opcion4=null;
        if(tablero.getFilas()-1>=opc1.getX()&&tablero.getColumnas()-1>=opc1.getY()){
            opcion4 = tablero.getCasilla(opc4);
        }
        if(!this.getCasilla().getNumZombie().isEmpty()){
            this.atacar(tablero,juego);
            this.atacar(tablero,juego);
            tablero.imprimirTablero();
        }else if(opcion1.getNumZombie().isEmpty()&&opcion2.getNumZombie().isEmpty()&&opcion3.getNumZombie().isEmpty()&&opcion4.getNumZombie().isEmpty()){
            Coordenada objetivo=this.zombieMasCercano(tablero, juego);
            Casilla nueva= tablero.getCasilla(objetivo);
            this.moverse(tablero,nueva);
            Coordenada objetivo1 = this.zombieMasCercano(tablero, juego);
            Casilla nueva1= tablero.getCasilla(objetivo1);
            this.moverse(tablero,nueva1);
            tablero.imprimirTablero();
        }
        else{
            if(!opcion1.getNumZombie().isEmpty()){
                this.atacar(tablero,juego);
            }
            else if(!opcion2.getNumZombie().isEmpty()){
                this.atacar(tablero,juego);
            }
            else if(!opcion3.getNumZombie().isEmpty()){
                this.atacar(tablero,juego);
            }
            else{
                this.atacar(tablero,juego);
            }
            tablero.imprimirTablero();
        }
    }
}
