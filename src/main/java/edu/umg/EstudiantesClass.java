package edu.umg;

import java.util.Objects;

public class EstudiantesClass {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String email;

    // Métodos de acceso para el campo 'idEstudiante'
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Métodos de acceso para el campo 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos de acceso para el campo 'apellido'
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Métodos de acceso para el campo 'email'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método 'equals' para comparar objetos EstudiantesClass
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Comprueba si es la misma instancia
        if (o == null || getClass() != o.getClass()) return false; // Comprueba la igualdad de clases
        EstudiantesClass that = (EstudiantesClass) o;
        return idEstudiante == that.idEstudiante && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(email, that.email);
    }

    // Método 'hashCode' para calcular el código hash del objeto
    @Override
    public int hashCode() {
        return Objects.hash(idEstudiante, nombre, apellido, email);
    }
}
