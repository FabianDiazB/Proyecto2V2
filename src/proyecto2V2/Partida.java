/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class Partida implements Serializable{
    int nivel;
    String nombre;

    ArrayList<Bicho> bichos;
    ArrayList<Arma> armas;
    ArrayList<Zombie> zombies;
    
    
    
    public Partida(int lvl){
        this.nivel = lvl; 
        this.nombre = "Partida ";
        this.zombies = new ArrayList<Zombie>();
        this.armas = new ArrayList<Arma>();
        try {
            try (ObjectInputStream leyendoFichero = new ObjectInputStream( 
                    new FileInputStream("bichos.txt") )) {
                ArrayList<Bicho> bd = (ArrayList<Bicho>)leyendoFichero.readObject();
                for (Bicho bicho : bd)
                if (bicho.getLvlAparicion()== this.nivel){
                    if(bicho.getTipo().equals("Zombie")){
                        switch(bicho.getTipoAtaque()){
                            case "Aereo":
                                ZombieAereo zombie = new ZombieAereo((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                        bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                zombies.add(zombie);
                                break;
                            case "Medio alcance":
                                ZombieMedioAlcance zombie1 = new ZombieMedioAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                        bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                zombies.add(zombie1);
                                break;
                            case "Contacto":
                                ZombieContacto zombie2 = new ZombieContacto((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                        bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
                                zombies.add(zombie2);
                                break;
                            case "Largo alcance":
                                ZombieLargoAlcance zombie3 = new ZombieLargoAlcance((int)bicho.getVida(),(int)bicho.getDamage(),bicho.getNombre(),
                                        bicho.getImagenAtaque(),bicho.getImagenDisparo(),(int)bicho.getLvlAparicion(),(int)bicho.getEspacio());
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
            this.bichos = bd;
            }
            System.out.println("Base de datos de usuarios cargada.....");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fichero bichos no encontrado");
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
    
   
}
