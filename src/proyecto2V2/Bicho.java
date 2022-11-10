/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;
import java.io.Serializable;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Bicho implements Serializable{
    private String nombre,tipoAtaque,imagenAtaque,imagenDisparo,tipo;
    private int vida, lvlAparicion,damage,espacio;

    public Bicho(String nombre, String tipoAtaque, String imagenA,String imagenD,String tipo, int vida, int lvlAparicion, int damage, int espacio) {
        this.nombre = nombre;
        this.tipoAtaque = tipoAtaque;
        this.imagenAtaque = imagenA;
        this.imagenDisparo = imagenD;
        this.tipo = tipo;
        this.vida = vida;
        this.lvlAparicion = lvlAparicion;
        this.damage = damage;
        this.espacio = espacio;
    }

    @Override
    public String toString() {
        return "Bicho{" + "nombre=" + nombre + ", tipoAtaque=" + tipoAtaque + ", imagenAtaque=" + imagenAtaque + ", imagenDisparo=" + imagenDisparo + ", tipo=" + tipo + ", vida=" + vida + ", lvlAparicion=" + lvlAparicion + ", damage=" + damage + ", espacio=" + espacio + '}';
    }

    public int getLvlAparicion() {
        return lvlAparicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoAtaque() {
        return tipoAtaque;
    }

    public String getImagenAtaque() {
        return imagenAtaque;
    }

    public String getImagenDisparo() {
        return imagenDisparo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getDamage() {
        return damage;
    }

    public int getEspacio() {
        return espacio;
    }
    
  
    
}
