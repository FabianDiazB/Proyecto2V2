/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */

public class ZombieMedioAlcance extends Zombie{

    public ZombieMedioAlcance(int vida, int damage,String name,  String skin, String disparo, int lvlAparicion, int espacio) {
        super(vida, damage, name, skin, disparo, lvlAparicion, espacio);
    }
    

    @Override
    public void atacar() {
        
    }

    @Override
    public void mover() {

    }

  
    @Override
    public Arma detectar(ArrayList<Arma> enemigos) { 
        for(Arma defensa: enemigos){
            int xD = defensa.getX();
            int yD = defensa.getY();
        
            int xZ = this.getX();
            int yZ = this.getY();
        
            if((xZ>=(xD - 100)) && (yZ>=yD - 150 && yZ<=yD+150) ){
                return defensa;
            }
            if((xZ<= (xD +150)) && (yZ>=yD - 150 && yZ<=yD+150) ){
                return defensa;
            }
            if((yZ<= (yD -150)) && (xZ>=xD - 150 && xZ<=xD) ){
                return defensa;
            }
            if((yZ>= (yD +150)) && (xZ >= xD - 150  && xZ<=xD+150) ){
                return defensa;
            }

        }
        return null;
    }
}