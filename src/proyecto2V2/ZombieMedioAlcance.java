/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

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
    public boolean detectar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}