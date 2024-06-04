/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poo;

import java.util.Scanner;

/**
 *
 * @author delac
 */
public class POO {

    public static void main(String[] args) {
        //PantallaInicio inicio = new PantallaInicio();
        //inicio.setVisible(true);
        
        
        Scanner ent= new Scanner(System.in);
        System.out.println("Introduzca el numero de jugadores: ");
        int numJug= ent.nextInt();
        Juego juego = new Juego(numJug);
        juego.iniciarJuego();
        
        
    }
}
