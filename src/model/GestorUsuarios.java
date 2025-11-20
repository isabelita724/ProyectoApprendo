/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author rafaj
 */
public class GestorUsuarios {
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    
    public static void inicializarUsuariosBase(){
        listaUsuarios.add(new Usuario("admin", "1234", "Administrador"));
        listaUsuarios.add(new Usuario("gestor", "1234", "Gestor"));
    }
    
    public static void registrarUsuario(Usuario nuevoUsuario){
        listaUsuarios.add(nuevoUsuario);
    }
    public static Usuario verificarLogin(String inputUser, String password){
        for(Usuario i : listaUsuarios){
            if (i.getContraseña().equals(password)){
                if (i.getUsuario().equals(inputUser) || i.getDocumento().equals(inputUser)){
                    return i;
                }
            }
        }
        return null;
    }
}
