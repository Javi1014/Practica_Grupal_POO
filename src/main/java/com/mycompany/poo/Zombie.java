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
    public void moverse() {
        
    }

    @Override
    public void activarse() {
        if (estado.equals("ACTIVO")) {
            while (numAcciones < maxAcciones) {
                System.out.println("Ingrese la accion que desea hacer (Atacar(1)/Moverse(2)/Buscar Comida(3)/No Hacer Nada(4)");
                Scanner ent = new Scanner(System.in);
                int opcion = ent.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese la coordenada que desea atacar X:");
                        int x = ent.nextInt();
                        System.out.println("Y:");
                        int y = ent.nextInt();
                        Coordenada coordAtacar = new Coordenada(x, y);
                        Casilla objetivo = new Casilla(coordAtacar);
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
            int dx = this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX();
            int dy = this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY();
            if ((Math.abs(dx) + Math.abs(dy)) <= ataqueEspecial.getAlcance()) {
                ataqueEspecial.realizarAtaque(this, posicion);
            } else {
                System.out.println("No se puede realizar un ataque a esta posicion");
            }
        }
    }



    public void buscarComida(Tablero tablero) {
        Random random = new Random();
        int resultado = random.nextInt(100); // Genera un número entre 0 y 99

        if (resultado < 30) {
            // 30% de probabilidad de aparecer un humano huidizo

            //GENERAMOS UNA COORDENADA ALEATORIA DONDE NO HAYA MAS DE 3 HUMANOS EN ESA CASILLA
            Random random1 = new Random();
            int x, y;
            Coordenada coord = null;
            do {
                x = random1.nextInt(tablero.getColumnas()); // Genera un número entre 0 y el ancho del tablero(columnas)
                y = random1.nextInt(tablero.getFilas()); // Genera un número entre 0 y (alto-1)
                coord = new Coordenada(x, y);
            } while (tablero.getCasilla(coord).getNumHumano().size() > 3);
            Casilla casillaHumano = new Casilla(coord);
            //CONSTRUCTOR DE HUMANO HUIDIZO
            HumanoHuidizo humano1 = new HumanoHuidizo(casillaHumano);
            //AGREGAMOS EL HUMANO HUIDIZO A ESA CASILLA
            ArrayList<Humano> humanosEnCasilla = tablero.getCasilla(humano1.getCasilla().getCoordenada()).getNumHumano();
            humanosEnCasilla.add(humano1);
            tablero.getCasilla(humano1.getCasilla().getCoordenada()).setNumHumano(humanosEnCasilla);
            System.out.println("Ha aparecido un Humano Huidizo en la coordenada " + humano1.getCasilla().getCoordenada().toString());
        } else if (resultado < 80) {
            // 50% de probabilidad de aparecer un conejo (30+50=80)
            Random random1 = new Random();
            int x = random1.nextInt(tablero.getColumnas());
            int y = random1.nextInt(tablero.getFilas());
            Coordenada coord = new Coordenada(x, y);
            Casilla casillaConejo = new Casilla(coord);
            Conejo nuevoConejo = new Conejo("ConejoPrueba", 1, casillaConejo);
            ArrayList<Conejo> conejosEnCasilla = tablero.getCasilla(nuevoConejo.getCasilla().getCoordenada()).getNumConejos();
            conejosEnCasilla.add(nuevoConejo);
            tablero.getCasilla(nuevoConejo.getCasilla().getCoordenada()).setNumConejos(conejosEnCasilla);
            System.out.println("Ha aparecido un Conejo en la coordenada " + nuevoConejo.getCasilla().getCoordenada().toString());
        } else {
            // 20% de probabilidad de no aparecer nada
            System.out.println("No ha aparecido ningún comestible.");
        }
    }


}
//PARA EL ATAQUE HACER EQUIPO=ATAQUE, VIVERES=ATAQUE ESPECIAL Y DEVORAR ES LO MISMO QUE UN ATAQUE ESPECIAL EN CONCRETO.
