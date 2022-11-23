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
public class ArmaMedioAlcance extends Arma{

    public ArmaMedioAlcance(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
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
        if(this.getVida()<=0){
            System.out.println(this.getNombre() +" ha muerto");
            this.setVivo(false);
            this.labelA.setText("");
            this.labelA.setVisible(false);
            this.interrupt();
            return;
        }
        if(zombie.getVida()<=0){
            this.setAtacando(false);
            return;
        } 
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
   
}
    