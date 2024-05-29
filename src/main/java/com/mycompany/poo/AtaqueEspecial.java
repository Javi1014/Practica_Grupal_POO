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
public class AtaqueEspecial extends Ataque {

    public AtaqueEspecial(String nombre) {
        super(nombre, asignarPotencia(nombre), asignarValorExito(nombre), asignarAlcance(nombre));
    }

    private static int asignarPotencia(String nombre) {
        switch (nombre) {
            case "Tsunami de Sangre":
                return 3;
            case "Lluvia de Huesos":
                return 4;
            case "Golpe de Terror":
                return 5;
            case "Vendaval de Garras":
                return 2;
            case "Explosión de Putrefacción":
                return 4;
            case "Grito Mortal":
                return 3;
            case "Sombra de la Muerte":
                return 3;
            default:
                throw new IllegalArgumentException("Nombre de ataque desconocido: " + nombre);
        }
    }

    private static int asignarValorExito(String nombre) {
        switch (nombre) {
            case "Tsunami de Sangre":
                return 3;
            case "Lluvia de Huesos":
                return 4;
            case "Golpe de Terror":
                return 3;
            case "Vendaval de Garras":
                return 2;
            case "Explosión de Putrefacción":
                return 3;
            case "Grito Mortal":
                return 5;
            case "Sombra de la Muerte":
                return 3;
            default:
                throw new IllegalArgumentException("Nombre de ataque desconocido: " + nombre);
        }
    }

    private static int asignarAlcance(String nombre) {
        switch (nombre) {
            case "Tsunami de Sangre":
                return 2;
            case "Lluvia de Huesos":
                return 3;
            case "Golpe de Terror":
                return 1;
            case "Vendaval de Garras":
                return 4;
            case "Explosión de Putrefacción":
                return 2;
            case "Grito Mortal":
                return 3;
            case "Sombra de la Muerte":
                return 1;
            default:
                throw new IllegalArgumentException("Nombre de ataque desconocido: " + nombre);
        }
    }

    @Override
    public void realizarAtaque(Zombie zombie, Casilla objetivo) {
        ArrayList<Conejo> conejosEnCasilla = objetivo.getNumConejos();
        ArrayList<Humano> humanosEnCasilla = objetivo.getNumHumano();

        // Obtener todos los objetivos (conejos y humanos) en la casilla
        ArrayList<Comestible> comestiblesEnCasilla = new ArrayList<>();
        // Agregar los ingenieros informáticos
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
                if (impactos <= 0) {
                    break; // No quedan impactos
                }
                if (comestible instanceof Humano humano) {
                    if (humano.getAguante() <= impactos) {
                        humano.calmarHambreZombie(zombie); // Devorar al humano
                        //AQUI DEBERIAMOS METER EL SYSTEM.OUT.PRINTLN("TAL HUMANO HA MUERTO O HA SIDO DEVORADO POR TAL ZOMBIE")
                        humanosEnCasilla.remove(humano);
                        objetivo.setNumHumano(humanosEnCasilla); // Eliminar el humano de la casilla
                        ArrayList<Comestible> elementosConsumidos = zombie.getElementosConsumidos();
                        elementosConsumidos.add(humano);
                        zombie.setElementosConsumidos(elementosConsumidos);
                        impactos -= humano.getAguante(); // Decrementar la cantidad de impactos restantes
                    }
                } else if (comestible instanceof Conejo conejo) {
                    if (impactos > 0) {
                        conejo.calmarHambreZombie(zombie); // Devorar al conejo y calmar el hambre del zombie
                        //AQUI DEBERIAMOS METER EL SYSTEM.OUT.PRINTLN("TAL HUMANO HA MUERTO O HA SIDO DEVORADO POR TAL ZOMBIE")
                        conejosEnCasilla.remove(conejo);
                        objetivo.setNumConejos(conejosEnCasilla); // Eliminar el conejo de la casilla
                        ArrayList<Comestible> elementosConsumidos = zombie.getElementosConsumidos();
                        elementosConsumidos.add(conejo);
                        zombie.setElementosConsumidos(elementosConsumidos);
                        impactos--; // Decrementar la cantidad de impactos restantes
                    }
                }
            }

        }
    }

}
