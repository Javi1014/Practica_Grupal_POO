/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nieto
 */
public class Zombie implements Activable {

    private String nombre;
    private String estado = "ACTIVO";
    private final int maxAcciones = 3;
    private int numAcciones = 0;
    private ArrayList<Comestible> elementosConsumidos = new ArrayList<>();
    private int numHeridas;
    private int hambre;
    private Ataque devorar;
    private Ataque ataqueEspecial;
    private Casilla casilla;

    public Zombie(String nombre, String estado, int numHeridas, int hambre, Casilla casilla) {
        this.nombre = nombre;
        this.estado = estado;
        this.numHeridas = numHeridas;
        this.hambre = hambre;
        this.casilla = casilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumAcciones() {
        return numAcciones;
    }

    public void setNumAcciones(int numAcciones) {
        this.numAcciones = numAcciones;
    }

    public ArrayList<Comestible> getElementosConsumidos() {
        return elementosConsumidos;
    }

    public void setElementosConsumidos(ArrayList<Comestible> elementosConsumidos) {
        this.elementosConsumidos = elementosConsumidos;
    }

    public int getNumHeridas() {
        return numHeridas;
    }

    public void setNumHeridas(int numHeridas) {
        this.numHeridas = numHeridas;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public Ataque getDevorar() {
        return devorar;
    }

    public void setDevorar(Ataque devorar) {
        this.devorar = devorar;
    }

    public Ataque getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(Ataque ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    @Override
    public void moverse(Casilla posicion) {
        
    }

    @Override
    public void activarse() {
        if (estado.equals("ACTIVO")) {
            while (numAcciones < maxAcciones) {
                System.out.println("Ingrese la accion que desea hacer (Atacar(1)/Moverse(2)/Buscar Comida(3)/No Hacer Nada(4)");
                Scanner ent = new Scanner(System.in);
                int opcion =ent.nextInt();
                switch(opcion){
                    case 1:
                        System.out.println("Ingrese la coordenada que desea atacar X:");
                        int x=ent.nextInt();
                        System.out.println("Y:");
                        int y =ent.nextInt();
                        Coordenada coordAtacar=new Coordenada(x,y);
                        Casilla objetivo =new Casilla(coordAtacar);
                        atacar(objetivo);
                    case 2:
                    case 3:
                    case 4:
                }
            }
            setNumAcciones(0);
        }
    }

    @Override
    public void atacar(Casilla posicion) {
        Scanner ent = new Scanner(System.in);
        System.out.println("Que ataque desea ejercer (Devorar(1)/AtaqueEspecial(2): ");
        int opcion = ent.nextInt();
        if (opcion == 1) {
            devorar.realizarAtaque(this, this.getCasilla());
        } else if (opcion == 2) {
            int dx = this.getCoordenada().getX() - posicion.getCoordenada().getX();
            int dy = this.getCoordenada().getY() - posicion.getCoordenada().getY();
            if ((Math.abs(dx) + Math.abs(dy)) <= ataqueEspecial.getAlcance()) {
                ataqueEspecial.realizarAtaque(this, posicion);
            } else {
                System.out.println("No se puede realizar un ataque a esta posicion");
            }
        }
    }

    @Override
    public Coordenada getCoordenada() {
        return casilla.getCoordenada();
    }

    public void buscarComida() {
        int aleatorio = (int) ((Math.random() * 10) + 1);
        if (aleatorio < 5) {
            //aparece conejo
        } else if ((aleatorio < 8) && (aleatorio > 5)) {
            //aparece humano huidizo
        }
    }

}
//PARA EL ATAQUE HACER EQUIPO=ATAQUE, VIVERES=ATAQUE ESPECIAL Y DEVORAR ES LO MISMO QUE UN ATAQUE ESPECIAL EN CONCRETO.
