/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import static java.lang.Math.abs;

/**
 *
 * @author ramir
 */
public class Tablero {

    private Casilla[][] casillas;
    private int filas;
    private int columnas;


    //Implementar todo lo relacionado con juego
    public Tablero(Integer numJug) {
        switch (numJug) {
            case 1:
                casillas = new Casilla[7][7];
                filas = 7;
                columnas = 7;
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        Coordenada cor = new Coordenada(i, j);
                        casillas[i][j] = new Casilla(cor);
                    }
                }
                break;
            case 2:
                casillas = new Casilla[8][8];
                filas = 8;
                columnas = 8;
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        Coordenada cor = new Coordenada(i, j);
                        casillas[i][j] = new Casilla(cor);
                    }
                }
                break;
            case 3:
                casillas = new Casilla[9][9];
                filas = 9;
                columnas = 9;
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        Coordenada cor = new Coordenada(i, j);
                        casillas[i][j] = new Casilla(cor);
                    }
                }
                break;
            case 4:
                casillas = new Casilla[10][10];
                filas = 10;
                columnas = 10;
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        Coordenada cor = new Coordenada(i, j);
                        casillas[i][j] = new Casilla(cor);
                    }
                }
                break;
        }
    }

    public Casilla getCasilla(Coordenada coordenada) {
        return casillas[coordenada.getX()][coordenada.getY()];
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    public int calcularDistancia(Casilla c1, Casilla c2){
        int disttotal = 0;
        if (!(c1.getCoordenada().getX()==c2.getCoordenada().getX())){
            disttotal = disttotal+abs(c1.getCoordenada().getX()-c2.getCoordenada().getX());
        }
        if (!(c1.getCoordenada().getY()==c2.getCoordenada().getY())){
            disttotal = disttotal+abs(c1.getCoordenada().getY()-c2.getCoordenada().getY());
        }
        
       return disttotal; 
    }
}