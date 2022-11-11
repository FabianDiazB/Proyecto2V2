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
public class ZombieContacto extends Zombie{

    public ZombieContacto(int vida, int damage,String name, String skin, String disparo, int lvlAparicion, int espacio) {
        super(vida, damage, name, skin, disparo, lvlAparicion, espacio);
    }
    
    

    @Override
    public void atacar() {
    }

   

  
    @Override
    public Arma detectar(ArrayList<Arma> enemigos) {
        Arma defensa;
        for(Arma d: enemigos){
            defensa = d;
            int xD = defensa.getX();
            int yD = defensa.getY();
        
            int xZ = this.getX();
            int yZ = this.getY();
        
            if((xZ>=(xD - 50)) && (yZ>=yD - 50 && yZ<=yD+100) ){
                System.out.println("primero");
                return defensa;
            }
            if((xZ<= (xD +100)) && (yZ>=yD - 50 && yZ<=yD+100) ){
                System.out.println("segundo");
                return defensa;
            }
            if((yZ>= (yD -50)) && (xZ>=xD - 50 && xZ<=xD+100) ){
                System.out.println("tercero");
                return defensa;
            }
            if((yZ>= (yD +100)) && (xZ >= xD - 50  && xZ<=xD+100) ){
                System.out.println("cuarto");
                return defensa;
            }

        }
        return null;
    }

    
}
    