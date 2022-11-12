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

public class ZombieLargoAlcance extends Zombie{

    public ZombieLargoAlcance(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        super(vida, damage, nombre, skin, disparo, lvlAparicion, espacio);
    }

    
    
    
  

   public boolean interseccion(Arma defensa){
        
            int tw = 200;
            int th = 200;
            int rw = 200;
            int rh = 200;
            if(rw<=0 || rh <=0 || tw<=0 || th <= 0){
                return false;
            }
            int tx = this.getX();
            int ty = this.getY();
            int rx = defensa.getX();
            int ry = defensa.getY();
            rw += rx;
            rh += ry;
            tw += tx;
            th += ty;
            return ((rw < rx || rw > tx) &&
                    (rh < ry || rh > ty) &&
                    (tw < tx || tw > rx) &&
                    (th < ty || th > ry));
   }

    @Override
    public Arma detectar(ArrayList<Arma> enemigos) {        
        if(enemigos==null){
            return null;
        }
        for(Arma defensa: enemigos){
            if(interseccion(defensa)) return defensa;
        }
        return null;              
    }
}
