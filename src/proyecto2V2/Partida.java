/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
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
    ArrayList<Arma> armas=new ArrayList<Arma>();
    ArrayList<Zombie> zombies =new ArrayList<Zombie>();
    JLabel matriz[][];
    Reliquia reliquia;
    int cantidadZombies;
    ArrayList<Zombie> zDesplegados = new ArrayList<Zombie>();
    ArrayList<Arma> aDesplegados = new ArrayList<Arma>();
    
   
    
    
    public Partida(int lvl){
        this.nivel = lvl; 
        this.cantidadZombies =5 + this.nivel*3;
        this.reliquia = new Reliquia(20);
        if(this.nivel==1){
            this.espacioDisponible=15;
        }else{
            this.espacioDisponible= 10+(5*this.nivel);
        }
        this.nombre = "Partida ";
        try {
            try (ObjectInputStream leyendoFichero = new ObjectInputStream( 
                    new FileInputStream("bichos.txt") )) {
                ArrayList<Bicho> bd = (ArrayList<Bicho>)leyendoFichero.readObject();
                for (Bicho bicho : bd){
                    if (bicho.getLvlAparicion()<= this.nivel){
                        if(bicho.getTipo().equals("Zombie")){
                            switch(bicho.getTipoAtaque()){
                                case "Aereo":
                                ZombieAereo zombie = new ZombieAereo((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                this.zombies.add(zombie);
                                break;
                                
                                case "Medio alcance":
                                ZombieMedioAlcance zombie1 = new ZombieMedioAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                this.zombies.add(zombie1);
                                break;
                                
                                case "Contacto":
                                ZombieContacto zombie2 = new ZombieContacto((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                this.zombies.add(zombie2);
                                break;
                                
                                case "Largo alcance":
                                ZombieLargoAlcance zombie3 = new ZombieLargoAlcance((int)bicho.getVida(),
                                        (int)bicho.getDamage(),bicho.getNombre(),bicho.getImagenAtaque(),bicho.getImagenDisparo(),
                                        (int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                this.zombies.add(zombie3);
                                break;

                        }
                    }
                    else{
                        switch(bicho.getTipoAtaque()){
                        case "Aereo":
                            ArmaAereo arma = new ArmaAereo((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            this.armas.add(arma);
                            break;
                        case "Medio alcance":
                            ArmaMedioAlcance arma1 = new ArmaMedioAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            this.armas.add(arma1);
                            break;
                        case "Contacto":
                            ArmaContacto arma2 = new ArmaContacto((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            this.armas.add(arma2);
                            break;
                        case "Largo alcance":
                            ArmaLargoAlcance arma3 = new ArmaLargoAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                    bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                            this.armas.add(arma3);
                            break;
                            }
                        }
                    }
                }
            this.bichos = bd;
            asignarReliquia();
            generarZDesplegables();
            
            }
            System.out.println("Base de datos de usuarios cargada.....");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fichero bichos no encontrado");
        }
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
                MouseListener listener = new Partida.DragMouseAdapt();
                field.addMouseListener(listener);
                field.setBorder(border);
                panel.add(field);
                matriz[i][j] = field;
               // field.pare+
            }
        }
        return matriz;
    }
    public void asignarReliquia(){
        for (Zombie z : this.zDesplegados){
                z.setArmas(this.aDesplegados);
                z.setReliquia(this.reliquia);
            }
            for (Arma a : armas){
                a.setZombies(this.zDesplegados);
                }
    }

    public int getEspacioDisponible() {
        return espacioDisponible;
    }

    public Reliquia getReliquia() {
        return reliquia;
    }

    public ArrayList<Arma> getaDesplegados() {
        return aDesplegados;
    }

    public void setaDesplegados(ArrayList<Arma> aDesplegados) {
        this.aDesplegados = aDesplegados;
    }
    
    
    
    public void generarZDesplegables(){ 
        for (int i = 0; i < cantidadZombies; i++){
            System.out.println("zombie #" + i);
            int r = (int) (Math.random()*zombies.size());
            Zombie zR = zombies.get(r);
            System.out.println(zR.toString());
            String tipo = zR.getClass().getSimpleName();

            switch(tipo){
                case "ZombieAereo":
                    ZombieAereo nuevo = new ZombieAereo((int)zR.getVida(),
                                        (int)zR.getDamage(),zR.getNombre(),zR.getSkin(),zR.getDisparo(),
                                        (int)zR.getLvlAparicion(),(int)zR.getEspacio()); 
                    nuevo.ubicar();
                    
                    this.zDesplegados.add(nuevo);                    
                    break;
                case "ZombieContacto":
                    ZombieContacto nuevo1 = new ZombieContacto((int)zR.getVida(),
                                        (int)zR.getDamage(),zR.getNombre(),zR.getSkin(),zR.getDisparo(),
                                        (int)zR.getLvlAparicion(),(int)zR.getEspacio()); 
                    nuevo1.ubicar();
                    this.zDesplegados.add(nuevo1);                   
                    break;
                case "ZombieMedioAlcance":
                    ZombieMedioAlcance nuevo2 = new ZombieMedioAlcance((int)zR.getVida(),
                                        (int)zR.getDamage(),zR.getNombre(),zR.getSkin(),zR.getDisparo(),
                                        (int)zR.getLvlAparicion(),(int)zR.getEspacio()); 
                    nuevo2.ubicar();
                    this.zDesplegados.add(nuevo2);
                    break;
                case "ZombieLargoAlcance":
                    ZombieLargoAlcance nuevo3 = new ZombieLargoAlcance((int)zR.getVida(),
                                        (int)zR.getDamage(),zR.getNombre(),zR.getSkin(),zR.getDisparo(),
                                        (int)zR.getLvlAparicion(),(int)zR.getEspacio()); 
                    nuevo3.ubicar();
                    this.zDesplegados.add(nuevo3);
                    break;
            }
            
        }

    }
   

   private class DragMouseAdapt extends MouseAdapter {


        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            JLabel l = (JLabel)c;
            if(l.getText().equals("Reliquia")){
                reliquia.getReliquia().setEnabled(true);
            }
            l.setText("");
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
    
    public void toStringBichos(){
        for(Bicho b: this.bichos){
            System.out.println(b.toString());
        }
    }
  
    public void toStringZombies(){
        for(Zombie b: this.zombies){
            System.out.println(b.toString());
        }
        
        
    }
    
    public void toStringZDesplegados(){
        for(Arma b: this.aDesplegados){
            System.out.println(b.toString());
        }
        
        
    }

    public int getCantidadZombies() {
        return cantidadZombies;
    }

    public void setCantidadZombies(int cantidadZombies) {
        this.cantidadZombies = cantidadZombies;
    }

    public ArrayList<Zombie> getzDesplegados() {
        return zDesplegados;
    }

    public void setzDesplegados(ArrayList<Zombie> zDesplegados) {
        this.zDesplegados = zDesplegados;
    }
    
}
