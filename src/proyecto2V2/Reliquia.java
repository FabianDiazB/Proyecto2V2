/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class Reliquia implements Serializable {
    private int vida,x,y;
    private JLabel reliquia;


    public Reliquia(int vida) {
        this.vida = vida;

    }

    public JLabel getLabelA() {
        return reliquia;
    }

    public void setReliquia(JLabel reliquia) {
        this.reliquia = reliquia;
    }
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
