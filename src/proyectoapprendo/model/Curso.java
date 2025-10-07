/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoapprendo.model;


public class Curso {
    private long idCurso;
    private String nombreCurso;
    private String programa; 
    private String ficha;    
    private String jornada;
    
    public Curso(){}

    public Curso(int idCurso, String nombreCurso, String programa, String ficha, String jornada) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.programa = programa;
        this.ficha = ficha;
        this.jornada = jornada;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return "Curso{" + "idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", programa=" + programa + ", ficha=" + ficha + ", jornada=" + jornada + '}';
    }
    
    
}
