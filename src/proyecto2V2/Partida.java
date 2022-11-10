/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Sebas
 */
public class Partida implements Serializable{
    int nivel;
    String nombre;
    int espacioDisponible;
    ArrayList<Bicho> bichos;
    ArrayList<Arma> armas;
    ArrayList<Zombie> zombies;
    JLabel matriz[][];
    
    
    
    
    public Partida(int lvl){
        this.nivel = lvl; 
        if(this.nivel==1){
            this.espacioDisponible=15;
        }else{
            this.espacioDisponible= 10+(5*this.nivel);
        }
        this.nombre = "Partida ";
        this.zombies = new ArrayList<Zombie>();
        this.armas = new ArrayList<Arma>();
        try {
            try (ObjectInputStream leyendoFichero = new ObjectInputStream( 
                    new FileInputStream("bichos.txt") )) {
                ArrayList<Bicho> bd = (ArrayList<Bicho>)leyendoFichero.readObject();
                for (Bicho bicho : bd){
                    System.out.println(bicho.getNombre() + " es de tipo "+bicho.getTipo());
                    if (bicho.getLvlAparicion()<= this.nivel){
                        if(bicho.getTipo().equals("Zombie")){
                            System.out.println("Encontre un zombie");
                            switch(bicho.getTipoAtaque()){
                                case "Aereo":
                                ZombieAereo zombie = new ZombieAereo((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                zombies.add(zombie);
                                break;
                                
                                case "Medio alcance":
                                ZombieMedioAlcance zombie1 = new ZombieMedioAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                zombies.add(zombie1);
                                break;
                                
                                case "Contacto":
                                ZombieContacto zombie2 = new ZombieContacto((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                zombies.add(zombie2);
                                break;
                                
                                case "Largo alcance":
                                ZombieLargoAlcance zombie3 = new ZombieLargoAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                zombies.add(zombie3);
                                break;

                        }
                    }
                    else{
                        switch(bicho.getTipoAtaque()){
                        case "Aereo":
                            ArmaAereo arma = new ArmaAereo((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            armas.add(arma);
                            break;
                        case "Medio alcance":
                            ArmaMedioAlcance arma1 = new ArmaMedioAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            armas.add(arma1);
                            break;
                        case "Contacto":
                            ArmaContacto arma2 = new ArmaContacto((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            armas.add(arma2);
                            break;
                        case "Largo alcance":
                            ArmaLargoAlcance arma3 = new ArmaLargoAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            armas.add(arma3);
                            break;
                            default:
                                ArmaBloque arma4 = new ArmaBloque((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                armas.add(arma4);
                            }
                        }
                    }
                }
            this.bichos = bd;
            }
            System.out.println("Base de datos de usuarios cargada.....");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fichero bichos no encontrado");
        }
        ubicarZombies();
    }
    public JLabel[][] generateSlots(JPanel panel){
        matriz =  new JLabel[22][13];
        Border border = LineBorder.createGrayLineBorder();
        for (int i = 0; i < 22; i++){
            for (int j = 0; j < 13;j++){
                JLabel field = new JLabel();
                
                field.setSize(50, 50);
                field.setLocation(i*50, j*50);
                //field.setb;
                field.setTransferHandler(new TransferHandler("text"));
                MouseListener listener = new Partida.DragMouseAdapt(field);
                field.addMouseListener(listener);
                field.setBorder(border);
                panel.add(field);
                matriz[i][j] = field;
               // field.pare
            }
        }
        return matriz;
    }
   

   private class DragMouseAdapt extends MouseAdapter {
        JLabel label;
        public DragMouseAdapt(JLabel label){
            this.label = label;
        }
        
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);   
            //handler.getDragImage();
        }
    }
    public void ubicarZombies(){
        System.out.println("Aqui comienza a ubicar zombies");
        for(Zombie z: this.zombies){
            z.ubicar();
        }
    }
    

    public int getNivel() {
        return nivel;
    }

    public String getNombre(int i) {
        return nombre + i + " ,Nivel: " + getNivel();
    }

    public ArrayList<Bicho> getBichos() {
        return bichos;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Arma> getArmas() {
        return armas;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public JLabel[][] getMatriz() {
        return matriz;
    }
    
    
  
}
