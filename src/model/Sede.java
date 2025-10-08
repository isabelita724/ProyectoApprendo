package model;

public class Sede {
    
     private String codigo;
    private String nombre;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String jefeSede;
    private int capacidadMaxima;
    private int cursosActivos;

    public Sede() {
    }

    public Sede(String codigo, String nombre, String direccion, String telefono, 
                String ciudad, String jefeSede, int capacidadMaxima) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.jefeSede = jefeSede;
        this.capacidadMaxima = capacidadMaxima;
        this.cursosActivos = 0;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getJefeSede() { return jefeSede; }
    public void setJefeSede(String jefeSede) { this.jefeSede = jefeSede; }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { 
        this.capacidadMaxima = capacidadMaxima; 
    }

    public int getCursosActivos() { return cursosActivos; }
    public void setCursosActivos(int cursosActivos) { 
        this.cursosActivos = cursosActivos; 
    }

    // Método para incrementar cursos activos
    public void agregarCurso() {
        if (this.cursosActivos < this.capacidadMaxima) {
            this.cursosActivos++;
        }
    }

    // Método para disminuir cursos activos
    public void eliminarCurso() {
        if (this.cursosActivos > 0) {
            this.cursosActivos--;
        }
    }

    // Verificar si hay capacidad para más cursos
    public boolean tieneCapacidad() {
        return this.cursosActivos < this.capacidadMaxima;
    }

    // Obtener cupos disponibles
    public int getCuposDisponibles() {
        return this.capacidadMaxima - this.cursosActivos;
    }
    
}
