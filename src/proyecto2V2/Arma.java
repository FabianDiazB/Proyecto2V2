/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public abstract class Arma extends Thread implements Serializable {
    private int vida;
    private int damage;
    private String nombre;
    private String skin;
    private String disparo;
    private int lvlAparicion;
    private int espacio;
    private int x,y;
    public ArrayList <Zombie> zombies = new ArrayList<Zombie>();
    public JLabel labelA;
    private boolean vivo;
    private boolean atacando;
    public ArrayList <String> registroAtq;
    public ArrayList <String> registroDmg;
    
    public Arma(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        this.vida = vida;
        this.damage = damage;
        this.nombre = nombre;
        this.skin = skin;
        this.disparo = disparo;
        this.lvlAparicion = lvlAparicion;
        this.espacio = espacio;
        this.vivo = true;
        this.atacando = false;
    }
    
    
    public abstract Zombie detectar(ArrayList<Zombie> zombies);
    public abstract void atacar(Zombie zombie);
        
    public void addRegistroDmg(Zombie zombie, Arma arma){
        registroAtq.add(arma.getNombre() + " recibio un golpe por: " + zombie.getDamage() + " de <- " + zombie.getNombre());
    }
    
    public void addRegistroAtq(Zombie zombie, Arma arma){
        registroAtq.add(arma.getNombre() + " ataque por: " + arma.getDamage() + " a -> " + zombie.getNombre());
    }
    public int getVida() {
        return vida;
    }
    public void setAtacando(boolean s){
        this.atacando = s;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public JLabel getLabelA() {
        return labelA;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setLabelA(JLabel labelA) {
        this.labelA = labelA;
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
        return "Arma{" + "vida=" + vida + ", damage=" + damage + ", nombre=" + nombre + ", skin=" + skin + ", disparo=" + disparo + ", lvlAparicion=" + lvlAparicion + ", espacio=" + espacio + '}';
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }
    
    
    
    public void setCoordenadas(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    
    public void run(){
        while(this.vivo == true){
            Zombie detectado = detectar(this.zombies);
            if (detectado!= null && atacando == false){
                this.atacando = true;
                atacar(detectado);
            }
        }
    }
    
}
