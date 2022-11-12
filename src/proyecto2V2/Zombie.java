/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

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
    private ArrayList<Arma> armas = new ArrayList<Arma>();
    private JLabel labelZ;
    private boolean vivo = true;
    public ArrayList <String> registroAtq = new ArrayList<String>();
    public ArrayList <String> registroDmg = new ArrayList<String>();
    boolean ejecutando = true;

    public Zombie(int vida, int damage, String nombre, String skin, String disparo, int lvlAparicion, int espacio) {
        this.vida = vida;
        this.damage = damage;
        this.nombre = nombre;
        this.skin = skin;
        this.disparo = disparo;
        this.caminando=true;
        this.lvlAparicion = lvlAparicion;
        this.espacio = espacio;
        this.vivo = true;
    }
    
    
    public abstract Arma detectar(ArrayList<Arma> armas);
    public void atacar(Arma arma){
        System.out.println(this.getNombre()+" atacando a" +arma.getNombre());
        while(this.vida>0 && arma.getVida()>0){
            arma.setVida(arma.getVida()-this.damage);
            System.out.println(arma.getVida());
            //this.addRegistroAtq(this,arma);
            //arma.addRegistroDmg(this, arma);
        }
        if(this.vida<=0){
            System.out.println(this.getNombre() +" ha muerto");
            this.vivo=(false);
            this.ejecutando=false;
            return;
        }
        if(arma.getVida()<=0){
            arma.setVivo(false);
            arma.getLabelA().setText("");
            armas.remove(arma);
            this.caminando=true;
            return;
            
        }
        //registro.add();
        
        
    }    
    
    public void addRegistroDmg(Zombie zombie, Arma arma){
        registroAtq.add(arma.getNombre() + " recibio un golpe por: " + zombie.getDamage() + " de <- " + zombie.getNombre());
    }
    
    public void addRegistroAtq(Zombie zombie, Arma arma){
        registroAtq.add(arma.getNombre() + " ataque por: " + arma.getDamage() + " a -> " + zombie.getNombre());
    }
    
    public void mover() throws InterruptedException{
        Arma detectado = detectar(armas);
        sleep(10);
        if(detectado!=null){
            this.caminando=false;
            atacar(detectado);
        }else{
        if (this.x < reliquia.getX()) 
            this.x++;
        else this.x--;
        if (this.y < reliquia.getY())
            this.y++;
        else this.y--;
        }
        this.labelZ.setLocation(x, y);
    }

    public JLabel getLabelZ() {
        return labelZ;
    }

    public void setLabelZ(JLabel labelZ) {
        this.labelZ = labelZ;
    }

    public Reliquia getReliquia() {
        return reliquia;
    }

    public ArrayList<Arma> getArmas() {
        return armas;
    }
    
    
    
    public void ubicar(){
        System.out.println("entrando al ubicaaaaar");
        int r = (int)(Math.random()*4+1);
        System.out.println(r);//int n = (int) (Math.random() * (1049 - 949)) + 949;
        switch (r) {
            case 1:
                this.x = 50;
                this.y = (int)(Math.random()*(656-606)+606);
                break;
            case 2:
                this.x = 1050;
                this.y = (int)(Math.random()*606+1);
                break;
             case 3:
                this.x = (int)(Math.random()*1049+1);
                this.y = 50;
                break;
             case 4:
                this.x = (int)(Math.random()*1049+1);
                this.y = 606;
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

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean isVivo() {
        return vivo;
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

        
        ArmaContacto nueva =new ArmaContacto(reliquia.getVida(), 0, "Reliquia", "skin", "sin", 1, 0);
        nueva.setCoordenadas(reliquia.getX(), reliquia.getY());
        armas.add(nueva);
        while(ejecutando){
            //System.out.println("("+this.x+","+this.y+")");
            if(this.vida>0){
                if(this.caminando==true){
                    try {
                        mover();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    ejecutando = false;
                    //atacar aqui
                }
            }
            if(vivo==false){
            this.labelZ.setText("");
            System.out.println(this.nombre + "ha muerto");
        }
    }
 }
    
    
    
}