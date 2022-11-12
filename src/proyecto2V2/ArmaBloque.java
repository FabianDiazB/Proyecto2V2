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

public class ArmaBloque extends Arma{

    public ArmaBloque(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        super(vida, damage, nombre, skin, disparo, lvlAparicion, espacio);
    }
    
    

    @Override
    public void atacar(Zombie zombie) {
        while(this.getVida()>0 && zombie.isVivo()){
            zombie.setVida(zombie.getVida()-this.getDamage());
            //this.addRegistroAtq(zombie,this);
            //zombie.addRegistroDmg(zombie, this);
            this.setAtacando(true);
        }
        this.setAtacando(false);
        zombie.setVivo(false);
        zombie.getLabelZ().setText("");
        zombie.getLabelZ().setVisible(false);
        zombies.remove(zombie); 
    }

    @Override
    public Zombie detectar(ArrayList<Zombie> zombies) {
        if(zombies==null){
            return null;
        }
        for(Zombie z: zombies){
            if(interseccion(z) && z.isVivo()) return z;
        }
        return null; 
    }
    public boolean interseccion(Zombie defensa){
        
            int tw = 0;
            int th = 0;
            int rw = 0;
            int rh = 0;
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
    
}