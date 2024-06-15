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
    private Ataque devorar = new Devorar();
    private Ataque ataqueEspecial = new AtaqueEspecial();
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

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public void incrementarAcciones() {
        this.numAcciones++;
    }

    @Override
    public void moverse(Tablero tablero, Casilla posicion) {
        int xActual = this.getCasilla().getCoordenada().getX();
        int yActual = this.getCasilla().getCoordenada().getY();
        int xDestino = posicion.getCoordenada().getX();
        int yDestino = posicion.getCoordenada().getY();

        // Verifica si la casilla posicion es contigua en sentido vertical u horizontal
        boolean esContiguaHorizontalmente = (xActual == xDestino) && (Math.abs(yActual - yDestino) == 1);
        boolean esContiguaVerticalmente = (yActual == yDestino) && (Math.abs(xActual - xDestino) == 1);

        if (esContiguaHorizontalmente || esContiguaVerticalmente) {
            Casilla casillaActual = tablero.getCasilla(this.getCasilla().getCoordenada());
            if (casillaActual.getNumHumano().size() == 0) {
                ArrayList<Zombie> zombiesCasillaActual = casillaActual.getNumZombie();
                zombiesCasillaActual.remove(this);
                casillaActual.setNumZombie(zombiesCasillaActual);

                Casilla casillaObjetivo = tablero.getCasilla(posicion.getCoordenada());
                ArrayList<Zombie> zombiesCasillaObjetivo = casillaObjetivo.getNumZombie();
                zombiesCasillaObjetivo.add(this);
                casillaObjetivo.setNumZombie(zombiesCasillaObjetivo);

                this.setCasilla(casillaObjetivo);
                System.out.println("El zombie " + this.getNombre() + " se ha movido a la posicion " + posicion.toString());
                numAcciones++;

            } else if (casillaActual.getNumHumano().size() == 1) {
                if (this.getNumAcciones() < 2) {
                    ArrayList<Zombie> zombiesCasillaActual = casillaActual.getNumZombie();
                    zombiesCasillaActual.remove(this);
                    casillaActual.setNumZombie(zombiesCasillaActual);

                    Casilla casillaObjetivo = tablero.getCasilla(posicion.getCoordenada());
                    ArrayList<Zombie> zombiesCasillaObjetivo = casillaObjetivo.getNumZombie();
                    zombiesCasillaObjetivo.add(this);
                    casillaObjetivo.setNumZombie(zombiesCasillaObjetivo);

                    this.setCasilla(casillaObjetivo);
                    System.out.println("El zombie " + this.getNombre() + " se ha movido a la posicion " + posicion.toString());
                    this.setNumAcciones(this.getNumAcciones() + 2);
                } else {
                    System.out.println("El zombie " + this.getNombre() + " no se puede mover, utiliza la accion en otra accion diferente a moverse");
                }

            } else if (casillaActual.getNumHumano().size() == 2) {
                if (this.getNumAcciones() < 1) {
                    ArrayList<Zombie> zombiesCasillaActual = casillaActual.getNumZombie();
                    zombiesCasillaActual.remove(this);
                    casillaActual.setNumZombie(zombiesCasillaActual);

                    Casilla casillaObjetivo = tablero.getCasilla(posicion.getCoordenada());
                    ArrayList<Zombie> zombiesCasillaObjetivo = casillaObjetivo.getNumZombie();
                    zombiesCasillaObjetivo.add(this);
                    casillaObjetivo.setNumZombie(zombiesCasillaObjetivo);

                    this.setCasilla(casillaObjetivo);
                    System.out.println("El zombie " + this.getNombre() + " se ha movido a la posicion " + posicion.toString());
                    this.setNumAcciones(this.getNumAcciones() + 3);
                } else {
                    System.out.println("El zombie " + this.getNombre() + " no se puede mover, utiliza la accion en otra accion diferente a moverse");
                }

            } else {
                System.out.println("El zombie " + this.getNombre() + " no se puede mover porque esta rodeado de zombies, utiliza la accion en otra accion diferente a moverse");
            }

        } else {
            System.out.println("El zombie " + this.getNombre() + " no se puede mover hasta esa posicion porque esta muy lejos, prueba con una coordendad valida");
        }

    }

    @Override
    public void activarse(Tablero tablero, Juego juego) {
        if (estado.equals("ACTIVO")) {
            while (this.getNumAcciones() < this.maxAcciones) {
                System.out.println("ZOMBIE : " + this.getNombre() + " ACCIONES DISPONIBLES: " + this.getNumAcciones());
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
                        Casilla objetivoAtacar = tablero.getCasilla(coordAtacar);
                        atacar(tablero, objetivoAtacar,juego);//ESTO SE PODRIA CAMBIAR ELIMINANDO EL ATRIBUTO DE TABLERO EN ATACAR Y PASNADOLE LA CASILLA DEL TABLERO DIRECT
                        tablero.imprimirTablero();//PROVISIONAL
                        break;
                    case 2:
                        System.out.println("Ingrese la coordenada que desea moverse X:");
                        int x1 = ent.nextInt();
                        System.out.println("Y:");
                        int y1 = ent.nextInt();
                        Coordenada coordMoverse = new Coordenada(x1, y1);
                        Casilla objetivoMoverse = tablero.getCasilla(coordMoverse);
                        moverse(tablero, objetivoMoverse);
                        tablero.imprimirTablero();//PROVISIONAL
                        break;
                    case 3:
                        buscarComida(tablero);
                        tablero.imprimirTablero();//PROVISIONAL
                        break;
                    case 4:
                        noHacerNada();
                        tablero.imprimirTablero();//PROVISIONAL
                        break;
                }
            }
            if (this.getHambre() < 5) {
                this.setHambre(getHambre() + 1);
            }
            setNumAcciones(0);
        }
    }

    @Override
    public void atacar(Tablero tablero, Casilla posicion,Juego juego) {
        //BUSCAMOS ESA CASILLA EN EL TABLERO
        Casilla casillaTablero = tablero.getCasilla(posicion.getCoordenada());
        Scanner ent = new Scanner(System.in);
        System.out.println("Alcance de ataqueespecial: " + this.ataqueEspecial.getAlcance());
        System.out.println("Que ataque desea ejercer (Devorar(1)/AtaqueEspecial(2)): ");
        int opcion = ent.nextInt();
        if (opcion == 1) {
            devorar.realizarAtaque(this, casillaTablero);
        } else if (opcion == 2) {
            int dx = Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX());
            int dy = Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY());

            if ((dx + dy) <= ataqueEspecial.getAlcance()) {
                ataqueEspecial.realizarAtaque(this, casillaTablero);
            } else {
                System.out.println("El zombie " + this.getNombre() + " ha malgastado una accion ya que no se puede alcanzar con el ataque esta posicion " + this.getCasilla().getCoordenada().toString());
            }
        }
        numAcciones++;
    }

    /*
    @Override
    public Coordenada getCoordenada() {
        return casilla.getCoordenada();
    }
     */
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
            Casilla casillaHumano = tablero.getCasilla(coord);
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
            Casilla casillaConejo = tablero.getCasilla(coord);
            Conejo nuevoConejo = new Conejo("ConejoPrueba", 1, casillaConejo);
            ArrayList<Conejo> conejosEnCasilla = tablero.getCasilla(nuevoConejo.getCasilla().getCoordenada()).getNumConejos();
            conejosEnCasilla.add(nuevoConejo);
            tablero.getCasilla(nuevoConejo.getCasilla().getCoordenada()).setNumConejos(conejosEnCasilla);
            System.out.println("Ha aparecido un Conejo en la coordenada " + nuevoConejo.getCasilla().getCoordenada().toString());
        } else {
            // 20% de probabilidad de no aparecer nada
            System.out.println("No ha aparecido ningún comestible.");
        }
        numAcciones++;
    }

    public void noHacerNada() {
        System.out.println("El zombie " + this.getNombre() + " no ha hecho nada");
        numAcciones++;
    }
}
//PARA EL ATAQUE HACER EQUIPO=ATAQUE, VIVERES=ATAQUE ESPECIAL Y DEVORAR ES LO MISMO QUE UN ATAQUE ESPECIAL EN CONCRETO.
