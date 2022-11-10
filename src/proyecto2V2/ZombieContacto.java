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
    }
}
    