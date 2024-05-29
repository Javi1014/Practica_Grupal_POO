/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author nieto
 */
public class Devorar extends Ataque {
    public Devorar(){
        super("Devorar",1,4,0);
    }

    @Override
    public void realizarAtaque(Zombie zombie, Casilla objetivo) {
        //PRIMERO CALCULAMOS EL NUMERO DE IMPACTOS
        int dados = this.getPotencia() + zombie.getHambre();
        int impactos = 0;
        for (int i = 0; i < dados; i++) {
            int resultado = Dado.tirarDado();
            if (resultado >= this.getValorExito()) {
                impactos++;
            }
        }
        //UNA VEZ SABEMOS LOS IMPACTOS QUE TENEMOS IMPLEMENTAMOS EL ATAQUE
        for (Humano humano: objetivo.getNumHumano()){
            if (impactos > 0) {
                if (humano.getAguante() <= impactos) {
                    humano.calmarHambreZombie(zombie);
                    impactos -= humano.getAguante();
                }
            } else {
                break;
            }
        }
    }
    
}
