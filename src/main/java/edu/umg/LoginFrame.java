package edu.umg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private PrincipalFrame principalFrame;

    public LoginFrame() {
        setTitle("BIENVENIDO, INICIA SESIÓN");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel y elementos de la interfaz de usuario
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Usuario:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Entrar");
        exitButton = new JButton("Salir"); // Agregado el botón de "Salir"

        // Configurar colores personalizados
        Color backgroundColor = new Color(83, 141, 30); // Color de fondo
        Color labelColor = new Color(0, 0, 0); // Color de etiquetas
        Color buttonColor = new Color(255, 255, 255); // Color de botón

        Font customFont = new Font("Attraction", Font.PLAIN, 14);
        getContentPane().setBackground(backgroundColor);
        usernameLabel.setForeground(labelColor);
        passwordLabel.setForeground(labelColor);
        usernameField.setBackground(Color.LIGHT_GRAY);
        passwordField.setBackground(Color.LIGHT_GRAY);
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.WHITE); // Color del botón de "Salir" en rojo
        exitButton.setForeground(Color.BLACK); // Texto en blanco para el botón de "Salir"

        // Agregar ActionListener al botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Llamar al método 'autenticar' para verificar las credenciales
                if (autenticar(username, password)) {
                    // Abrir la ventana principal
                    abrirVentanaPrincipal();
                } else {
                    // Mostrar un mensaje de error en caso de credenciales incorrectas
                    JOptionPane.showMessageDialog(LoginFrame.this, "Las Credenciales son Incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Borrar la contraseña en memoria y el campo de contraseña visible
                Arrays.fill(passwordChars, ' ');
                passwordField.setText("");
            }
        });

        // Agregar ActionListener al botón de "Salir" para cerrar la aplicación
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });

        // Agregar elementos de la interfaz al panel y el panel a la ventana
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton); // Agregar el botón de "Salir" al panel
        add(panel);

        // Configurar la ubicación de la ventana y hacerla visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean autenticar(String username, String password) {
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "5Th98.Ki877";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para verificar las credenciales
            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Las credenciales son correctas
                resultSet.close();
                statement.close();
                conexion.close();
                return true;
            }

            resultSet.close();
            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
        return false; // Autenticación fallida por defecto
    }

    private void abrirVentanaPrincipal() {
        // Crear una instancia de la ventana principal
        principalFrame = new PrincipalFrame();
        principalFrame.setVisible(true);
        dispose(); // Cierra la ventana de inicio de sesión
    }

    public static void main(String[] args) {
        // Iniciar la aplicación Swing en un hilo de eventos separado
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}

class StudentDetailsFrame extends JFrame {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    public StudentDetailsFrame() {
        setTitle("Gestión de Estudiantes");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un panel y elementos de la interfaz de usuario
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel nameLabel = new JLabel("Nombre:");
        JLabel lastNameLabel = new JLabel("Apellido:");
        JLabel emailLabel = new JLabel("Email:");
        nameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        addButton = new JButton("Agregar");
        deleteButton = new JButton("Eliminar");
        updateButton = new JButton("Actualizar");

        // Configurar colores personalizados
        Color backgroundColor = new Color(56, 140, 30); // Color de fondo oscuro
        Color labelColor = new Color(236, 240, 241); // Color de etiquetas
        Color buttonColor = new Color(46, 204, 113); // Color del botón
        Color buttonTextColor = new Color(255, 255, 255); // Color del texto

        panel.setBackground(backgroundColor);
        nameLabel.setForeground(labelColor);
        lastNameLabel.setForeground(labelColor);
        emailLabel.setForeground(labelColor);
        nameField.setBackground(Color.LIGHT_GRAY);
        lastNameField.setBackground(Color.LIGHT_GRAY);
        emailField.setBackground(Color.LIGHT_GRAY);
        addButton.setBackground(buttonColor);
        addButton.setForeground(buttonTextColor);
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(buttonTextColor);
        updateButton.setBackground(buttonColor);
        updateButton.setForeground(buttonTextColor);

        // Agregar ActionListeners a los botones
        addButton.addActionListener(e -> agregarEstudiante());
        deleteButton.addActionListener(e -> eliminarEstudiante());
        updateButton.addActionListener(e -> actualizarEstudiante());

        // Agregar elementos de la interfaz al panel y el panel a la ventana
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarEstudiante() {
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String email = emailField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "5Th98.Ki877";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para insertar un nuevo estudiante
            String sql = "INSERT INTO estudiantes (nombre, apellido, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "Estudiante agregado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de agregar el estudiante
                nameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "No se pudo agregar el estudiante",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }

    private void eliminarEstudiante() {
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "5Th98.Ki877";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para eliminar un estudiante por nombre y apellido
            String deleteSQL = "DELETE FROM estudiantes WHERE nombre = ? AND apellido = ?";
            PreparedStatement deleteStatement = conexion.prepareStatement(deleteSQL);
            deleteStatement.setString(1, nombre);
            deleteStatement.setString(2, apellido);

            int filasAfectadas = deleteStatement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "Estudiante eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de eliminar el estudiante
                nameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "No se pudo eliminar el estudiante",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            // Cerrar la declaración de eliminación
            deleteStatement.close();

            // Restablecer la secuencia "estudiantes_id_estudiante_seq" a 1
            String resetSequenceSQL = "ALTER SEQUENCE estudiantes_id_estudiante_seq RESTART WITH 1";
            Statement resetStatement = conexion.createStatement();
            resetStatement.execute(resetSequenceSQL);
            resetStatement.close();

            // Cerrar la conexión
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }


    private void actualizarEstudiante() {
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String email = emailField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "5Th98.Ki877";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para actualizar el email de un estudiante por nombre y apellido
            String sql = "UPDATE estudiantes SET email = ? WHERE nombre = ? AND apellido = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, nombre);
            statement.setString(3, apellido);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "Estudiante actualizado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "No se pudo actualizar el estudiante",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configurar el color de fondo
            Color backgroundColor = new Color(60, 199, 38); // Color de fondo oscuro
            UIManager.put("Panel.background", backgroundColor);
            UIManager.put("OptionPane.background", backgroundColor);

            // Configurar la fuente para todos los componentes de Swing
            Font customFont = new Font("Arial", Font.PLAIN, 14); // Cambia "Arial" por tu fuente personalizada
            UIManager.put("Button.font", customFont);
            UIManager.put("Label.font", customFont);
            UIManager.put("TextField.font", customFont);
            UIManager.put("PasswordField.font", customFont);
            UIManager.put("ComboBox.font", customFont);
            UIManager.put("List.font", customFont);
            UIManager.put("OptionPane.messageFont", customFont);
            UIManager.put("OptionPane.buttonFont", customFont);

            LoginFrame loginFrame = new LoginFrame();
        });
    }
}