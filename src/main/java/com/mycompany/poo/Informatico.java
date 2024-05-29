/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo;

/**
 *
 * @author delac
 */
public class Informatico extends HumanoCombatiente {

    public Informatico(Casilla casilla) {
        super(1, 3, casilla);
    }

    
    @Override
    public void reaccionar(Zombie zombie, Ataque ataque) {
        if(zombie.getCasilla().equals(this.getCasilla())){
            zombie.setNumHeridas(zombie.getNumHeridas()+2);
        }else if(1==1){
            //ATACA A UN ZOMBIE QUE ESTE A UNA DISTACIA MAXIMA DE 1 CASILLA 
        }
        else{
            //SE MUEVE DOS CASILLAS HASTA EL ZOMBIE MAS CERCANO
        }  
}

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        if(zombie.getHambre()>=4){
            zombie.setHambre(zombie.getHambre()-2);
        
            //el informatico podia dañar si se lo comen
            if (((Math.random() * 2) + 1)==1){
                zombie.setNumHeridas(zombie.getNumHeridas()+1);
        }
        }
        
    }
    }
