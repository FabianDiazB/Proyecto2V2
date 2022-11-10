/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2V2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class BDUsuario implements Serializable{
    private ArrayList<Usuario> users;

    public BDUsuario() {
        this.users = new ArrayList<Usuario>();
    }
    
    public void guardar() throws IOException{
        try (ObjectOutputStream escribiendoFichero = new ObjectOutputStream( 
                new FileOutputStream("bdUsuarios.txt") )) {
            escribiendoFichero.writeObject(this.users);
        }
    }
    
    public void restaurar() throws IOException, ClassNotFoundException{
        try {
            try (ObjectInputStream leyendoFichero = new ObjectInputStream( 
                    new FileInputStream("bdUsuarios.txt") )) {
                ArrayList<Usuario> bd = (ArrayList<Usuario>)leyendoFichero.readObject();
                this.users = bd;
            }
            System.out.println("Base de datos de usuarios restaurada.....");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fichero usuarios no encontrado");
        }
         
    }

    public ArrayList<Usuario> getUsers() {
        return users;
    }
    
    
    
    
    
    
    
}
