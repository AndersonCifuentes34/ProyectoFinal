package edu.umg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Autenticación {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String usuarioDB = "postgres";
        String contrasenaDB = "5Th98.Ki877";
        String nombreUsuario = "Anderson";
        String contrasenaUsuario = "123";

        try {
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para verificar las credenciales
            String sql = "SELECT COUNT(*) FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombreUsuario);
            statement.setString(2, contrasenaUsuario);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count == 1) {
                    System.out.println("Inicio de sesión exitoso");
                } else {
                    System.out.println("Credenciales incorrectas");
                }
            }

            resultSet.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
