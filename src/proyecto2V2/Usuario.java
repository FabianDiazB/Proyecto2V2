/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class Usuario implements Serializable {
    private String nombre;
    private ArrayList<Partida> partidas = new ArrayList<Partida>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    
    public void setPartida(Partida partida){
        this.partidas.add(partida);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

