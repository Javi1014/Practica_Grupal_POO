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
public class Devorar extends Ataque {

    public Devorar() {
        super("Devorar", 1, 4, 0);
    }

    @Override
    public void realizarAtaque(Zombie zombie, Casilla objetivo) {

        ArrayList<Conejo> conejosEnCasilla = objetivo.getNumConejos();
        ArrayList<Humano> humanosEnCasilla = objetivo.getNumHumano();

        // Obtener todos los objetivos (conejos y humanos) en la casilla
        ArrayList<Comestible> comestiblesEnCasilla = new ArrayList<>();
        comestiblesEnCasilla.addAll(conejosEnCasilla);
        comestiblesEnCasilla.addAll(humanosEnCasilla);

        // Realizar el ataque
        if (!comestiblesEnCasilla.isEmpty()) {
            int dados = this.getPotencia() + zombie.getHambre(); //SI EN LA CASILLA HAY COMESTIBLES ENTONCES EMPEZAMOS
            int impactos = 0;
            for (int i = 0; i < dados; i++) {
                int resultado = Dado.tirarDado();
                if (resultado >= this.getValorExito()) {
                    impactos++;
                }
            }
            for (Comestible comestible : comestiblesEnCasilla) {
                if (impactos > 0) {
                    if (comestible instanceof Conejo conejo) {
                        conejo.calmarHambreZombie(zombie); // Devorar al conejo y calmar el hambre del zombie
                        conejosEnCasilla.remove(conejo);
                        objetivo.setNumConejos(conejosEnCasilla); // Eliminar el conejo de la casilla
                        ArrayList<Comestible> elementosConsumidos = zombie.getElementosConsumidos();
                        elementosConsumidos.add(conejo);
                        zombie.setElementosConsumidos(elementosConsumidos);
                        break; // Salir del bucle después de devorar un objetivo
                    } else if (comestible instanceof Humano humano) {
                        if (humano.getAguante() <= impactos) {
                            humano.calmarHambreZombie(zombie); // Devorar al humano
                            humanosEnCasilla.remove(humano);
                            objetivo.setNumHumano(humanosEnCasilla); // Eliminar el humano de la casilla
                            ArrayList<Comestible> elementosConsumidos = zombie.getElementosConsumidos();
                            elementosConsumidos.add(humano);
                            zombie.setElementosConsumidos(elementosConsumidos);
                            break; // Salir del bucle después de devorar un objetivo
                        }

                    }
                } else {
                    break; // Salir del bucle si no quedan impactos disponibles
                }
            }
        }

    }

}
