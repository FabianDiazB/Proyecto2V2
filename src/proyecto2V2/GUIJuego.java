/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto2V2;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Thread.sleep;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import static javax.swing.text.DefaultEditorKit.cutAction;

/**
 *
 * @author Usuario
 */
public class GUIJuego extends javax.swing.JFrame {
    private Partida partida;
    private BDUsuario bd;
    private JLabel label;
    private JLabel field;
    private JLabel matriz[][];
    private int reliq = 1;
    boolean iniciada=false;
    /**
     * Creates new form GUIJuego
     */
    public GUIJuego(Partida p, BDUsuario bd) throws IOException, ClassNotFoundException {
        System.out.println("comenzando a crear");
        initComponents();
        System.out.println("componentes creados");
        this.partida = p;
        System.out.println("partida añadida");
        this.bd = bd;
        System.out.println("base añadida");
        if(iniciada){
            //restaurarArmas();
            //generateReli();
            //generateTextFields();
            restaurarZombies();
            dibujar();
        }else{
       // generateTextFields();
            if(this.partida.getMatriz() == null){
                this.matriz = this.partida.generateSlots(this.panelMapa); 
                generateTextFields();
                generateReli();

            }else{
                this.matriz = new JLabel[22][13];
                this.matriz = this.partida.getMatriz();
                dibujar();
                //restaurarArmas();
            }
        }
        
    }

    private GUIJuego() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void dibujar(){
        for(int i=0;i<22;i++){
            for(int j=0;j<13;j++){
                panelMapa.add(this.matriz[i][j]);
            }
        }

    }
   
    public void restaurarZombies(){
        for(Zombie z: this.partida.zombies){
            panelMapa.add(z.getLabelZ());
            z.getLabelZ().setVisible(true);
        }
    }
    private void generateReli(){
        JLabel reli = new JLabel("Reliquia");
        reli.setSize(50, 50);
        reli.setLocation(50, 15);
        reli.setTransferHandler(new TransferHandler("text"));
        MouseListener listener = new DragMouseAdapter();
        reli.addMouseListener(listener);
        reli.setVerticalTextPosition(JLabel.BOTTOM);
        panelBarra.add(reli);
        this.partida.reliquia.setReliquia(reli);
        
        }
    private void generateTextFields(){
        int posX = 0;
        for (Arma arma : partida.armas){
            label = new JLabel(arma.getNombre());     
            label.setSize(50, 50);
            label.setLocation(150+(60*posX++), 15);
            label.setTransferHandler(new TransferHandler("text"));
            MouseListener listener1 = new DragMouseAdapter();
            label.addMouseListener(listener1);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            panelBarra.add(label);
       }  
        
    }    
    
        
    private class DragMouseAdapter extends MouseAdapter {
        
        
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            JLabel objeto = (JLabel) c;
            if(objeto.getText().equals("Reliquia") && partida.reliquia.getReliquia().isEnabled()){
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }else{
                if(objeto.getText().equals("Reliquia")){
                    System.out.println("ya no tiene reliquias");
                }else{
                     TransferHandler handler = c.getTransferHandler();
                    handler.exportAsDrag(c, e, TransferHandler.COPY);
                }
            }
            if(objeto.getText().equals("Reliquia")){
                partida.reliquia.getReliquia().setEnabled(false);
            }
        }
    }
    private class Borrar extends MouseAdapter {
            public void mouseClicked(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            JLabel objeto = (JLabel) c;
            TransferHandler handler = c.getTransferHandler();
            JLabel label = (JLabel)c;
            label.setText("");
            
        }
    }
    
    public void ejecutarThreads(){
        for (int i = 0; i<22; i++){
            for (int j = 0; j<13; j++){
                String textoLbl =this.matriz[i][j].getText();
                if(!(textoLbl.equals(""))){
                    for(Arma defensa: partida.getArmas()){
                        if(textoLbl.equals(defensa.getNombre())){
                            String tipoDefensa = defensa.getClass().getSimpleName();
                            System.out.println(tipoDefensa);
                            switch (tipoDefensa) {
                                case "ArmaContaco":
                                    ArmaContacto nuevo = new ArmaContacto(defensa.getVida(),defensa.getDamage(),defensa.getNombre(),defensa.getSkin(),defensa.getDisparo(),defensa.getLvlAparicion(),defensa.getEspacio());
                                    nuevo.setCoordenadas(matriz[i][j].getX(), matriz[i][j].getY());
                                    nuevo.setLabelA(matriz[i][j]);
                                    partida.aDesplegados.add(nuevo);
                                    break;
                                case "ArmaBloque":
                                    ArmaBloque nuevo1 = new ArmaBloque(defensa.getVida(),defensa.getDamage(),defensa.getNombre(),defensa.getSkin(),defensa.getDisparo(),defensa.getLvlAparicion(),defensa.getEspacio());
                                    nuevo1.setCoordenadas(matriz[i][j].getX(), matriz[i][j].getY());
                                    nuevo1.setLabelA(matriz[i][j]);
                                    partida.aDesplegados.add(nuevo1);
                                    break;
                                case "ArmaAereo":
                                    ArmaAereo nuevo2 = new ArmaAereo(defensa.getVida(),defensa.getDamage(),defensa.getNombre(),defensa.getSkin(),defensa.getDisparo(),defensa.getLvlAparicion(),defensa.getEspacio());
                                    nuevo2.setCoordenadas(matriz[i][j].getX(), matriz[i][j].getY());
                                    nuevo2.setLabelA(matriz[i][j]);
                                    partida.aDesplegados.add(nuevo2);
                                    break;
                                case "ArmaMedioAlcance":
                                    ArmaMedioAlcance nuevo3 = new ArmaMedioAlcance(defensa.getVida(),defensa.getDamage(),defensa.getNombre(),defensa.getSkin(),defensa.getDisparo(),defensa.getLvlAparicion(),defensa.getEspacio());
                                    nuevo3.setCoordenadas(matriz[i][j].getX(), matriz[i][j].getY());
                                    nuevo3.setLabelA(matriz[i][j]);
                                    partida.aDesplegados.add(nuevo3);
                                    break;
                                case "ArmaLargoAlcance":
                                    ArmaLargoAlcance nuevo4 = new ArmaLargoAlcance(defensa.getVida(),defensa.getDamage(),defensa.getNombre(),defensa.getSkin(),defensa.getDisparo(),defensa.getLvlAparicion(),defensa.getEspacio());
                                    nuevo4.setCoordenadas(matriz[i][j].getX(), matriz[i][j].getY());
                                    nuevo4.setLabelA(matriz[i][j]);
                                    partida.aDesplegados.add(nuevo4);
                                    break;
                                
                            }
                        }
                    }
                }else{
                    continue;
                }
                
            }
        }
//         System.out.println("Armas desplegadas en partidas");
//         partida.toStringZDesplegados();
        
        
//        for(Zombie z: this.partida.zDesplegados){
//            System.out.println("\n\nZombie desplegado:");
//            System.out.println(z.toString());
//            JLabel nuevo = new JLabel(z.getNombre());
//            nuevo.setSize(50,50);
//            nuevo.setLocation(z.getX(),z.getY());
//            z.setReliquia(this.partida.getReliquia());
//            z.setLabelZ(nuevo);
//            panelMapa.add(nuevo);
//            nuevo.setVisible(true);
//            z.start();
//        }
    }
    
    public void pruebazombi(){
        System.out.println("creando zombie prueba");
        ZombieAereo z = new ZombieAereo(3, 3, "PRUEBA", "a", "b", 1, 3);
        z.ubicar();
        z.setReliquia(this.partida.getReliquia());
        JLabel nuevo = new JLabel(z.getNombre());
        nuevo.setSize(50,50);
        nuevo.setLocation(z.getX(), z.getY());
        panelMapa.add(nuevo);
        revalidate();
        z.setLabelZ(nuevo);
        partida.zombies.add(z);
        z.start();
    }
    public void crearReliquia(){
        
        javax.swing.JLabel reliquia = new javax.swing.JLabel();
        try {
            reliquia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reliquia.png"))); 
            

        } catch (Exception e) {
            System.out.println("Imagen no encontrada");
        }
        reliquia.setSize(600, 600);
        reliquia.setLocation(200, 200);
        panelMapa.add(reliquia);
        
    }
    
    public void guardarCoordenadas(){
        for (int i = 0; i<22; i++){
            for (int j = 0; j<13; j++){
                if(matriz[i][j].getText().equals("Reliquia")){
                    System.out.println(matriz[i][j].getX());
                    System.out.println(matriz[i][j].getY());
                    partida.reliquia.setX(matriz[i][j].getX());
                    partida.reliquia.setY(matriz[i][j].getY());
                    return;
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraImagenes = new javax.swing.JTextField();
        panelMapa = new javax.swing.JPanel();
        panelBarra = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnSiguienteLvl = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblnivel = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnPrueba = new javax.swing.JButton();

        barraImagenes.setBackground(new java.awt.Color(204, 204, 204));
        barraImagenes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        barraImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barraImagenesActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMapa.setBackground(new java.awt.Color(0, 102, 102));
        panelMapa.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        panelMapa.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout panelMapaLayout = new javax.swing.GroupLayout(panelMapa);
        panelMapa.setLayout(panelMapaLayout);
        panelMapaLayout.setHorizontalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        panelMapaLayout.setVerticalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        panelBarra.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelBarraLayout = new javax.swing.GroupLayout(panelBarra);
        panelBarra.setLayout(panelBarraLayout);
        panelBarraLayout.setHorizontalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBarraLayout.setVerticalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnGuardar.setBackground(new java.awt.Color(255, 0, 51));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Juego");
        btnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 204, 204)));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSiguienteLvl.setText("Siguiente Nivel");
        btnSiguienteLvl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteLvlActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel3.setText("________________________");

        jLabel4.setText("Nivel: ");

        lblnivel.setText("0");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnPrueba.setBackground(new java.awt.Color(0, 204, 0));
        btnPrueba.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPrueba.setForeground(new java.awt.Color(255, 255, 255));
        btnPrueba.setText("Iniciar");
        btnPrueba.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 204), new java.awt.Color(51, 255, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 204, 204)));
        btnPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSiguienteLvl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblnivel)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblnivel))
                .addGap(144, 144, 144)
                .addComponent(btnPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSiguienteLvl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(panelBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barraImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barraImagenesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barraImagenesActionPerformed

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        // TODO add your handling code here:
        btnGuardar.setBackground(new java.awt.Color(255,0,51));
        btnGuardar.setForeground(Color.white);
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCoordenadas();
        try {
            this.bd.guardar();
            JOptionPane.showMessageDialog(this, "Partida guardada","SAVE",JOptionPane.DEFAULT_OPTION);
            this.partida.toStringZombies();
        } catch (IOException ex) {
            Logger.getLogger(GUIJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSiguienteLvlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteLvlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteLvlActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        guardarCoordenadas();
        try {
            this.bd.guardar();
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(GUIJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebaActionPerformed
        ejecutarThreads();
        //pruebazombi();
    }//GEN-LAST:event_btnPruebaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIJuego().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barraImagenes;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPrueba;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSiguienteLvl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblnivel;
    private javax.swing.JPanel panelBarra;
    private javax.swing.JPanel panelMapa;
    // End of variables declaration//GEN-END:variables
}
