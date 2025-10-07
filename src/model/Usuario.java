
package model;


public class Usuario {
    
    long id;
    String nombre;
    String apellido;
    String curso;
    String correo;
    long telefono;
    
    public Usuario(){
        
    }

    public Usuario(long id, String nombre, String apellido, String curso, String correo, long telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.correo = correo;
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    
    
    
    
}
