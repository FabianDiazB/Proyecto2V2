/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

/**
 *
 * @author Usuario
 */
public class ZombieContacto extends Zombie{

    public ZombieContacto(int vida, int damage,String name, String skin, String disparo, int lvlAparicion, int espacio) {
        super(vida, damage, name, skin, disparo, lvlAparicion, espacio);
    }
    
    

    @Override
    public void atacar() {
    }

    @Override
    public void mover() {

    }

    @Override
    public boolean detectar(Arma defensa) {
        int xD = defensa.getX();
        int yD = defensa.getY();
        
        int xZ = this.getX();
        int yZ = this.getY();
        
        if((xZ>=(xD - 15)) && (yZ>=yD - 15 && yZ<=yD+75) ){
            return true;
        }
        if((xZ<= (xD +75)) && (yZ>=yD - 15 && yZ<=yD+75) ){
            return true;
        }
        if((yZ<= (yD -75)) && (xZ>=xD - 75 && xZ<=xD) ){
            return true;
        }
        if((yZ>= (yD +75)) && (xZ >= xD - 15  && xZ<=xD+75) ){
            return true;
        }
        return false;
    }
}
    