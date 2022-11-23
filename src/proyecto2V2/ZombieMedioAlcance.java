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

    public ZombieMedioAlcance(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        super(vida, damage, nombre, skin, disparo, lvlAparicion, espacio);
    }
  
   public boolean interseccion(Arma defensa){
            int tw = 100;
            int th = 100;
            int rw = 100;
            int rh = 100;
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
            if(interseccion(defensa) && defensa.getVida()>0) return defensa;
        }
        return null;              
    }
}