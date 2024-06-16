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
    
    public Juego(int numJug){
        this.numJug=numJug;
        this.tablero= new Tablero(numJug);
    }

    public Tablero getTablero() {
        return tablero;
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
    
    
    
    public void iniciarJuego(){
        //INICIA JUEGO CON EL NUMERO DE JUGADORES QUE VAN A SER
        Coordenada inicio=new Coordenada(0,0);
        Casilla comienzo=tablero.getCasilla(inicio);
        for(int i=1;i<=this.numJug;i++){
            Scanner ent = new Scanner(System.in);
            System.out.println("Nombre "+i);
            String nombre=ent.nextLine();
            Zombie zom=new Zombie(nombre, "ACTIVO",0,0,comienzo );
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);
        }
        //SE CREAN 3 HUMANOS POR CADA JUGADOR
        for(int i=0;i<numJug;i++){
            for(int j=0;j<3;j++){
                Random random=new Random();
                int numeroAleatorio1= random.nextInt(tablero.getFilas()-1);
                int numeroAleatorio2= random.nextInt(tablero.getColumnas()-1);
                Coordenada coor=new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion=tablero.getCasilla(coor);
                Humano humano=Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
        }
        //SE CREA UN CONEJO DE PRUEBA
        Conejo con1=new Conejo("Pep",1,tablero.getCasilla(new Coordenada(1,0)));
        tablero.getCasilla(new Coordenada(1,0)).getNumConejos().add(con1);
        tablero.getCasilla(new Coordenada(1,0)).setNumConejos(tablero.getCasilla(new Coordenada(1,0)).getNumConejos());
        
        //1
        tablero.imprimirTablero();
           
        Coordenada nueva=new Coordenada(tablero.getFilas()-1, tablero.getColumnas()-1);
        Casilla objetivo=tablero.getCasilla(nueva);
        
        
        
        
        this.jugarJuego();
        /*
        while(!listaJugadores.get(0).getCasilla().equals(objetivo)||!listaJugadores.isEmpty()){
            for(int i=0;i<listaJugadores.size();i++){
                listaJugadores.get(i).activarse(tablero,this);
            }
            for(int j=0;j<listaHumanos.size();j++){
                if(listaJugadores.isEmpty()){
                    break;
                }
                else{
                    listaHumanos.get(j).activarse(tablero, this);
                }
            }
            for(int x=0;x<listaJugadores.size();x++){
                Random random=new Random();
                int numeroAleatorio1= random.nextInt(tablero.getFilas()-1);
                int numeroAleatorio2= random.nextInt(tablero.getColumnas()-1);
                Coordenada coor=new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion=tablero.getCasilla(coor);
                Humano humano=Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
            tablero.imprimirTablero();
        }
        if(listaJugadores.isEmpty()){
                System.out.println("El juego ha terminado porque los zombies han muerto");
            }
*/

    }
    
    
    public void jugarJuego(){
       while(0==0){
           this.turnoZombi();
           this.turnoHumano();
           
           for(int i=0;i<this.getNumJug();i++){
                Random random=new Random();
                int numeroAleatorio1= random.nextInt(tablero.getFilas()-1);
                int numeroAleatorio2= random.nextInt(tablero.getColumnas()-1);
                Coordenada coor=new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion=tablero.getCasilla(coor);
                Humano humano=Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
            tablero.imprimirTablero();
       }
       
        
    }
    public void turnoZombi(){
    for(int i=0;i<this.getNumJug();i++){
        //this.getListaJugadores().get(1).activarse(tablero, this);
        tablero.imprimirTablero();
        listaJugadores.get(0).activarse(tablero,this);
        
    }
    
}

public void turnoHumano(){
    for(int i=0;i<this.getListaHumanos().size();i++){
        //this.getListaHumanos().get(1).activarse(tablero, this);
        listaHumanos.get(i).activarse(tablero,this);
    }
    
}
        
}

