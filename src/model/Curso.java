
package model;

public class Curso {
    private String codigo;
    private String nombre;
    private String tutor;
    private String horario;
    private String sede;
    private int cuposMaximos;
    private int cuposDisponibles;

    public Curso() {
    }
    
    public Curso(String codigo, String nombre, String tutor, String horario, String sede, int cuposMaximos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tutor = tutor;
        this.horario = horario;
        this.sede = sede;
        this.cuposMaximos = cuposMaximos;
        this.cuposDisponibles = cuposMaximos;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public void setCuposMaximos(int cuposMaximos) {
        this.cuposMaximos = cuposMaximos;
        this.cuposDisponibles = cuposMaximos; // Resetear cupos disponibles
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    // Método para registrar un estudiante (disminuir cupos)
    public boolean registrarEstudiante() {
        if (cuposDisponibles > 0) {
            cuposDisponibles--;
            return true;
        }
        return false;
    }

    // Método para eliminar un estudiante (aumentar cupos)
    public void eliminarEstudiante() {
        if (cuposDisponibles < cuposMaximos) {
            cuposDisponibles++;
        }
    }
}

