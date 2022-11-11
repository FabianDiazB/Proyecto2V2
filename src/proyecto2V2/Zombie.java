/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int x;
    private int y;
    private boolean caminando;
    private Reliquia reliquia;
    private ArrayList<Arma> armas;

    public Zombie(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        this.vida = vida;
        this.damage = damage;
        this.nombre = nombre;
        this.skin = skin;
        this.disparo = disparo;
        this.caminando=true;
        this.lvlAparicion = lvlAparicion;
        this.espacio = espacio;
        System.out.println("Creado");
    }
    
    public abstract Arma detectar(ArrayList<Arma> enemigos);
    public abstract void atacar();
    public void mover() throws InterruptedException{
        sleep(1000);
        Arma detectado = detectar(armas);
        
        if(detectado!=null){
            this.caminando=false;
            atacar();
            return;
        }

        if (this.x < reliquia.getX()) 
            this.x++;
        else this.x--;
        if (this.y < reliquia.getY())
            this.y++;
        else this.y++;
 
    }
    
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

    public void setArmas(ArrayList<Arma> armas) {
        this.armas = armas;
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

    public void setReliquia(Reliquia reliquia) {
        this.reliquia = reliquia;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCaminando() {
        return caminando;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCaminando(boolean caminando) {
        this.caminando = caminando;
    }
    
    

    @Override
    public String toString() {
        return "Zombie{" + "vida=" + vida + ", damage=" + damage + ", nombre=" + nombre + ", skin=" + skin + ", disparo=" + disparo + ", lvlAparicion=" + lvlAparicion + ", espacio=" + espacio + '}';
    }
    
    @Override
    public void run(){
        boolean ejecutando=true;
        
        while(ejecutando){
            if(this.vida>0){
                if(this.caminando==true){
                    try {
                        mover();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else {ejecutando=false;
            }
        }
    }
    
    
    
}