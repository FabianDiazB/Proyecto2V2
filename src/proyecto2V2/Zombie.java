/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public abstract class Zombie extends Thread implements Serializable{
    private int vida;
    private int damage;
    private String nombre;
    private String skin;
    private String disparo;
    private int lvlAparicion;
    private int espacio;
    private int x,y;

    public Zombie(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        this.vida = vida;
        this.damage = damage;
        this.nombre = nombre;
        this.skin = skin;
        this.disparo = disparo;
        this.lvlAparicion = lvlAparicion;
        this.espacio = espacio;
        System.out.println("Creado");
    }
    
    public abstract boolean detectar(Arma defensa);
    public abstract void atacar();
    public abstract void mover();
    
    public void ubicar(){
        System.out.println("entrando al ubicaaaaar");
        int r = (int)(Math.random()*4+1);
        
        switch (r) {
            case 1:
                this.x = 10;
                this.y = (int)(Math.random()*150+1);
                break;
            case 2:
                this.x = 1080;
                this.y = (int)(Math.random()*150+1);
                break;
             case 3:
                this.x = (int)(Math.random()*1080+1);
                this.y = 10;
                break;
             case 4:
                this.x = (int)(Math.random()*1080+1);
                this.y = 143;
                break;
                
        }
        System.out.println("Zombie:" +this.nombre + "en:");
        System.out.println("("+this.x +","+this.y+")");
        System.out.println("");
    }
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getDisparo() {
        return disparo;
    }

    public void setDisparo(String disparo) {
        this.disparo = disparo;
    }

    public int getLvlAparicion() {
        return lvlAparicion;
    }

    public void setLvlAparicion(int lvlAparicion) {
        this.lvlAparicion = lvlAparicion;
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    @Override
    public String toString() {
        return "Zombie{" + "vida=" + vida + ", damage=" + damage + ", nombre=" + nombre + ", skin=" + skin + ", disparo=" + disparo + ", lvlAparicion=" + lvlAparicion + ", espacio=" + espacio + '}';
    }
    
    @Override
    public void run(){
        
    }
    
    
    
}