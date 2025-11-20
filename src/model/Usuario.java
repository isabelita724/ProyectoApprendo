/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rafaj
 */
public class Usuario {
    private String usuario;
    private String contraseña;
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String tipoDocumento;
    private String genero;
    private String fechaNac;
    private String rol = "Estudiante";
    
    public Usuario(String usuario, String contraseña, String documento, String nombre, String apellido, String telefono, String correo, String tipoDocumento, String genero, String fechaNac){
    this.usuario = usuario;
    this.contraseña = contraseña;
    this.documento = documento;
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.correo = correo;
    this.tipoDocumento = tipoDocumento;
    this.genero = genero;
    this.fechaNac = fechaNac;

    }
    
    public Usuario(String usuario, String contraseña, String rol){
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.documento = "";
    }
    
    public String getUsuario(){return usuario;}
    public String getContraseña(){return contraseña;}
    public String getDocumento(){return documento;}
    public String getRol(){return rol;}
    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getTelefono(){return telefono;}
    public String getCorreo(){return correo;}
    public String getTipoDocumento(){return tipoDocumento;}
    public String getGenero(){return genero;}
    public String getFechaNacimiento(){return fechaNac;}
}



