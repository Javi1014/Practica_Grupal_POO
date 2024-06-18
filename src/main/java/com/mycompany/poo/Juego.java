/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nieto
 */
public class Juego {

    private int numJug;
    private Tablero tablero;
    private ArrayList<Zombie> listaJugadores = new ArrayList<>();
    private ArrayList<Humano> listaHumanos = new ArrayList<>();
    private ArrayList<Conejo> listaConejos = new ArrayList<>();

    public Juego(int numJug) {
        this.numJug = numJug;
        this.tablero = new Tablero(numJug);
    }

    public int getNumJug() {
        return numJug;
    }

    public void setNumJug(int numJug) {
        this.numJug = numJug;
    }

    public ArrayList<Zombie> getListaJugadores() {
        return listaJugadores;
    }

    public ArrayList<Humano> getListaHumanos() {
        return listaHumanos;
    }

    public void setListaJugadores(ArrayList<Zombie> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public void setListaHumanos(ArrayList<Humano> listaHumanos) {
        this.listaHumanos = listaHumanos;
    }

    public ArrayList<Conejo> getListaConejos() {
        return listaConejos;
    }

    public void setListaConejos(ArrayList<Conejo> listaConejos) {
        this.listaConejos = listaConejos;
    }

    public void iniciarJuego() {

        Coordenada inicio = new Coordenada(0, 0);
        Casilla comienzo = new Casilla(inicio);
        for (int i = 1; i <= this.numJug; i++) {
            Scanner ent = new Scanner(System.in);
            System.out.println("Nombre " + i);
            String nombre = ent.nextLine();
            Zombie zom = new Zombie(nombre, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);
        }
        for (int i = 0; i < numJug; i++) {
            for (int j = 0; j < 3; j++) {
                Random random = new Random();
                int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion = tablero.getCasilla(coor);
                Humano humano = Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
        }
        Conejo con1 = new Conejo("Pep", 1, tablero.getCasilla(new Coordenada(1, 0)));
        tablero.getCasilla(new Coordenada(1, 0)).getNumConejos().add(con1);
        tablero.getCasilla(new Coordenada(1, 0)).setNumConejos(tablero.getCasilla(new Coordenada(1, 0)).getNumConejos());
        this.listaConejos.add(con1);

        Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
        Casilla objetivo = tablero.getCasilla(nueva);

        tablero.imprimirTablero();

        while (0 == 0) {
            for (int i = 0; i < this.getNumJug(); i++) {
                //COMPRUEBA SI ESTA VIVO O NO 
                if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                    listaJugadores.get(i).activarse(this.tablero, this);
                }
            }
            for (Humano humano : this.getListaHumanos()) {
                if (!listaJugadores.isEmpty()) {
                    humano.activarse(this.tablero, this);
                }
            }
            for (int i = 0; i < this.getNumJug(); i++) {
                Random random = new Random();
                int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion = tablero.getCasilla(coor);
                Humano humano = Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
            tablero.imprimirTablero();
        }

        /*
        Humano humano1 = new HumanoHuidizo(tablero.getCasilla(new Coordenada(2,1)));
        tablero.getCasilla(new Coordenada(2,1)).getNumHumano().add(humano1);
        tablero.getCasilla(new Coordenada(2,1)).setNumHumano(tablero.getCasilla(new Coordenada(2,1)).getNumHumano());
        Humano humano2 = new HumanoHuidizo(tablero.getCasilla(new Coordenada(2,3)));
        tablero.getCasilla(new Coordenada(2,3)).getNumHumano().add(humano2);
        tablero.getCasilla(new Coordenada(2,3)).setNumHumano(tablero.getCasilla(new Coordenada(2,3)).getNumHumano());
        Conejo con1=new Conejo("Pep",1,tablero.getCasilla(new Coordenada(1,0)));
        tablero.getCasilla(new Coordenada(1,0)).getNumConejos().add(con1);
        tablero.getCasilla(new Coordenada(1,0)).setNumConejos(tablero.getCasilla(new Coordenada(1,0)).getNumConejos());
        Zombie zombie1 = tablero.getCasilla(inicio).getNumZombie().get(0);
        Zombie zombie2 = tablero.getCasilla(inicio).getNumZombie().get(1);
        tablero.imprimirTablero();

        for(int i=0;i<=1;i++){
            zombie1.activarse(this.tablero,this);
            zombie2.activarse(this.tablero,this);
            
        }
         */
    }

}
