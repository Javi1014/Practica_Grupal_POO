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

        // Obtener todos los objetivos (conejos y humanos) en la casilla objetivo del tablero
        ArrayList<Comestible> comestiblesEnCasilla = new ArrayList<>();
        // Agregar los comestibles por orden de prioridad
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Informatico) {
                comestiblesEnCasilla.add(humano);
            }
        }
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Soldado) {
                comestiblesEnCasilla.add(humano);
            }
        }
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Blindado) {
                comestiblesEnCasilla.add(humano);
            }
        }
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Especialista) {
                comestiblesEnCasilla.add(humano);
            }
        }
        comestiblesEnCasilla.addAll(conejosEnCasilla);
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof HumanoHuidizo) {
                comestiblesEnCasilla.add(humano);
            }
        }

        // Realizar el ataque
        if (!comestiblesEnCasilla.isEmpty()) {
            int dados = this.getPotencia() + zombie.getHambre(); //SI EN LA CASILLA HAY COMESTIBLES ENTONCES EMPEZAMOS
            int impactos = 0;
            System.out.print("Valor Exito: " + this.getValorExito() + ". Has obtenido los siguentes numeros: ");
            for (int i = 0; i < dados; i++) {
                int resultado = Dado.tirarDado();
                System.out.print(resultado + ", ");//ESTO HAY QUE HACERLO UN POCO MAS EXPLICATIVO
                if (resultado >= this.getValorExito()) {
                    impactos++;
                }
            }
            for (Comestible comestible : comestiblesEnCasilla) {
                if (impactos > 0) {
                    System.out.println("Has tenido exito en la accion devorar");
                    if (comestible instanceof Humano humano) {
                        if (humano.getAguante() <= impactos) {
                            humano.calmarHambreZombie(zombie); // Devorar al humano
                            System.out.println("El humano ha muerto.");
                            humanosEnCasilla.remove(humano);
                            objetivo.setNumHumano(humanosEnCasilla); // Eliminar el humano de la casilla
                            ArrayList<Comestible> elementosConsumidos = zombie.getElementosConsumidos();
                            elementosConsumidos.add(humano);
                            zombie.setElementosConsumidos(elementosConsumidos);
                            System.out.println(this.getNombre() + " ha matado un humano ");
                            break; // Salir del bucle FOR COMESTIBLE después de devorar un objetivo
                        }
                    } else if (comestible instanceof Conejo conejo) {
                        conejo.calmarHambreZombie(zombie); // Devorar al conejo y calmar el hambre del zombie
                        //AQUI DEBERIAMOS METER EL SYSTEM.OUT.PRINTLN("TAL HUMANO HA MUERTO O HA SIDO DEVORADO POR TAL ZOMBIE")
                        conejosEnCasilla.remove(conejo);
                        objetivo.setNumConejos(conejosEnCasilla); // Eliminar el conejo de la casilla
                        ArrayList<Comestible> elementosConsumidos = zombie.getElementosConsumidos();
                        elementosConsumidos.add(conejo);
                        zombie.setElementosConsumidos(elementosConsumidos);
                        System.out.println(this.getNombre() + " ha matado al conejo " + conejo.getNombre());
                        break; // Salir del bucle después de devorar un objetivo
                    }
                } else {
                    System.out.println("NO has tenido exito en la accion devorar");
                    break; // Salir del bucle FOR COMESTIBLE si no quedan impactos disponibles
                }
            }

        } else {
            System.out.println("Has malgastado la accion porque en esta casilla no habia ningun humano o conejo");
        }

    }

}
