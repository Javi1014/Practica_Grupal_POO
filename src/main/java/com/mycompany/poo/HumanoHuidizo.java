/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author delac
 */
public class HumanoHuidizo extends Humano {

    public HumanoHuidizo(Casilla casilla) {
        super(1, 1, casilla);
    }

    @Override
    public void moverse(Tablero tablero, Casilla posicion) {
        Casilla nueva;
        if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) <= Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getY() > posicion.getCoordenada().getY()) {
            nueva = new Casilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() - 1));
        } else if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) < Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getY() < posicion.getCoordenada().getY()) {
            nueva = new Casilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1));
        } else if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) >= Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getX() > posicion.getCoordenada().getX()) {
            nueva = new Casilla(new Coordenada(this.getCasilla().getCoordenada().getX() - 1, this.getCasilla().getCoordenada().getY()));
        } else {
            nueva = new Casilla(new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY()));
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
            System.out.println("El huidizo se ha movido a la posicion " + nueva.getCoordenada().getX() + " " + nueva.getCoordenada().getY());
        } else {
            System.out.println("El huidizo no se puede mover porque esta rodeado de zombies, utiliza la accion en otra accion diferente a moverse");
        }
    }

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        zombie.setHambre(0);
    }

    @Override
    public void atacar(Tablero tablero, Juego juego) {
        //EL H. HUIDIZO NO ATACA

    }

    @Override
    public void activarse(Tablero tablero, Juego juego) {

        if (this.getCasilla().getCoordenada() == new Coordenada(tablero.getFilas(), tablero.getColumnas())) {
            //COMPRUEBA SI ESTA EN LA CASILLA DE SALIDA
            //BORRA EL ZOMBI Q HA HUIDO
            juego.getListaHumanos().remove(this);
            System.out.println("El humano huidizo se ha escapado.");

        } else {//SE MUEVE HACIA LA CASILLA DE SALIDA
            if (!(this.getCasilla().getCoordenada().getY() == tablero.getColumnas())) {
                Coordenada objetivo = new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1);
                Casilla nueva = new Casilla(objetivo);
                moverse(tablero, nueva);
            } else {
                Coordenada objetivo = new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY());
                Casilla nueva = new Casilla(objetivo);
                moverse(tablero, nueva);
            }
        }

        /* 
        if(this.getCasilla().getNumZombie().isEmpty()){
            Coordenada objetivo=this.zombieMasCercano(tablero, juego);
            Casilla nueva= tablero.getCasilla(objetivo);
            System.out.println(objetivo.getX());
            this.moverse(tablero,nueva);
        }else{
            this.atacar(tablero,this.getCasilla(),juego);
        }
         */
    }
}
