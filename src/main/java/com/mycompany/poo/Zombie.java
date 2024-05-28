/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

import java.util.ArrayList;

/**
 *
 * @author nieto
 */
public class Zombie implements Activable {
    private String nombre;
    private String estado;
    private final int maxAcciones=3;
    private int numAcciones =0;
    private ArrayList<Comestible> elementosConsumidos = new ArrayList<>();
    private int numHeridas;
    private int hambre;
    private Ataque devorar;
    private Ataque ataqueEspecial;
    private Coordenada coordenada;
}
//PARA EL ATAQUE HACER EQUIPO=ATAQUE, VIVERES=ATAQUE ESPECIAL Y DEVORAR ES LO MISMO QUE UN ATAQUE ESPECIAL EN CONCRETO.