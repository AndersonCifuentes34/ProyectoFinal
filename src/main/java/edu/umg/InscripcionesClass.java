package edu.umg;

import java.sql.Date;
import java.util.Objects;

public class InscripcionesClass {
    private int idInscripcion;
    private Integer idEstudiante;
    private Integer idCurso;
    private Date fechaInscripcion;

    // Métodos de acceso para el campo 'idInscripcion'
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    // Métodos de acceso para el campo 'idEstudiante'
    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Métodos de acceso para el campo 'idCurso'
    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    // Métodos de acceso para el campo 'fechaInscripcion'
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    // Método 'equals' para comparar objetos InscripcionesClass
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Comprueba si es la misma instancia
        if (o == null || getClass() != o.getClass()) return false; // Comprueba la igualdad de clases
        InscripcionesClass that = (InscripcionesClass) o;
        return idInscripcion == that.idInscripcion && Objects.equals(idEstudiante, that.idEstudiante) && Objects.equals(idCurso, that.idCurso) && Objects.equals(fechaInscripcion, that.fechaInscripcion);
    }

    // Método 'hashCode' para calcular el código hash del objeto
    @Override
    public int hashCode() {
        return Objects.hash(idInscripcion, idEstudiante, idCurso, fechaInscripcion);
    }
}
