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

    public Tablero(Integer numJug) {
        switch (numJug) {
            case 1:
                inicializarTablero(7, 7);
                break;
            case 2:
                inicializarTablero(8, 8);
                break;
            case 3:
                inicializarTablero(9, 9);
                break;
            case 4:
                inicializarTablero(10, 10);
                break;
            default:
                throw new IllegalArgumentException("Número no válido. Introduce un número del 1 al 4.");
        }
    }

    private void inicializarTablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        casillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Coordenada cor = new Coordenada(i, j);
                casillas[i][j] = new Casilla(cor);
            }
        }
    }

    public void imprimirTablero() {
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            String contenido = "";

            // Añadir los nombres de los zombis en la casilla
            if (!casillas[i][j].getNumZombie().isEmpty()) {
                for (Zombie zombie : casillas[i][j].getNumZombie()) {
                    contenido += "Z:" + zombie.getNombre() + " ";
                }
            }

            // Añadir los nombres de los conejos en la casilla
            if (!casillas[i][j].getNumConejos().isEmpty()) {
                for (Conejo conejo : casillas[i][j].getNumConejos()) {
                    contenido += "C:" + conejo.getNombre() + " ";
                }
            }

            // Añadir los nombres de los humanos en la casilla
            if (!casillas[i][j].getNumHumano().isEmpty()) {
                for (Humano humano : casillas[i][j].getNumHumano()) {
                    contenido += "H:" + humano.getClass().getSimpleName()+ " ";
                }
            }

            // Si la casilla está vacía, dejar el espacio en blanco
            if (contenido.isEmpty()) {
                System.out.print("[  ] ");
            } else {
                System.out.print("[" + contenido.trim() + "] ");
            }
        }
        System.out.println();
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
