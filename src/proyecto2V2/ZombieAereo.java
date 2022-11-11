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
public class ZombieAereo extends Zombie{

    public ZombieAereo(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        super(vida, damage, nombre, skin, disparo, lvlAparicion, espacio);
    }

    
    
    

    @Override
    public void atacar() {
        
    }

    @Override
    public void mover() {

    }

    
   @Override
    public boolean detectar(ArrayList<Arma> enemigos) {
        for(Arma defensa: enemigos){
            int xD = defensa.getX();
            int yD = defensa.getY();
        
            int xZ = this.getX();
            int yZ = this.getY();
        
            if((xZ>=(xD - 50)) && (yZ>=yD - 100 && yZ<=yD+100) ){
                return true;
            }
            if((xZ<= (xD +100)) && (yZ>=yD - 100 && yZ<=yD+100) ){
                return true;
            }
            if((yZ<= (yD -100)) && (xZ>=xD - 100 && xZ<=xD) ){
                return true;
            }
            if((yZ>= (yD +100)) && (xZ >= xD - 100  && xZ<=xD+100) ){
                return true;
            }

        }
        return false;
    }
}
    