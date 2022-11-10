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
public class BDPartidas implements Serializable{
        private ArrayList<Partida> partidas = new ArrayList<Partida>();
    
    public void addPartida(Partida p){
        partidas.add(p);
    }

    public ArrayList<Partida> getPartida() {
        return partidas;
    }

    public void setPartida(Partida partida) {
        this.partidas.add(partida);
    }
}
