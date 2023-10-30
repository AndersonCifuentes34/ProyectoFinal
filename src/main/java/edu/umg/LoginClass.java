package edu.umg;

import java.util.Objects;

public class LoginClass {
    private int id;
    private String username;
    private String password;

    // Métodos de acceso para el campo 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Métodos de acceso para el campo 'username'
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Métodos de acceso para el campo 'password'
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método 'equals' para comparar objetos LoginClass
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Comprueba si es la misma instancia
        if (o == null || getClass() != o.getClass()) return false; // Comprueba la igualdad de clases
        LoginClass that = (LoginClass) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    // Método 'hashCode' para calcular el código hash del objeto
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
