/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Sebas
 */
public class BDBichos implements Serializable{
    private ArrayList<Bicho> bichos = new ArrayList<Bicho>();
    
    public void addBicho(Bicho b){
        bichos.add(b);
    }

    public ArrayList<Bicho> getBichos() {
        return bichos;
    }

    public void setBichos(ArrayList<Bicho> bichos) {
        this.bichos = bichos;
    }
}
