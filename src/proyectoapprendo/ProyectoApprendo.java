/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoapprendo;

import model.GestorUsuarios;

/**
 *
 * @author ISABELLA
 */
public class ProyectoApprendo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorUsuarios.inicializarUsuariosBase();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true); 
            }
        });
    }
    
}
