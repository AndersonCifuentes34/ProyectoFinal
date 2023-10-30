package edu.umg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrodeCursosFrame extends JFrame {


    private JTextField idInscripcionField;
    private JTextField idAlumnoField;
    private JTextField idCursoField;
    private JTextField fechaInscripcionField;
    private JButton addButton;

    public RegistrodeCursosFrame() {
        setTitle("Agregar Inscripción");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un panel y elementos de la interfaz de usuario
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel idInscripcionLabel = new JLabel("ID de Inscripción:");
        JLabel idAlumnoLabel = new JLabel("ID de Alumno:");
        JLabel idCursoLabel = new JLabel("ID de Curso:");

        idInscripcionField = new JTextField(20);
        idAlumnoField = new JTextField(20);
        idCursoField = new JTextField(20);
        fechaInscripcionField = new JTextField(20);
        addButton = new JButton("Agregar");

        // Configurar colores personalizados
        Color backgroundColor = new Color(34, 49, 63); // Color de fondo oscuro
        Color labelColor = new Color(236, 240, 241); // Color de etiquetas
        Color buttonColor = new Color(46, 204, 113); // Color del botón (Turquesa)
        Color buttonTextColor = new Color(255, 255, 255); // Color del texto en los botones (Blanco)

        panel.setBackground(backgroundColor);
        idInscripcionLabel.setForeground(labelColor);
        idAlumnoLabel.setForeground(labelColor);
        idCursoLabel.setForeground(labelColor);

        idInscripcionField.setBackground(Color.LIGHT_GRAY);
        idAlumnoField.setBackground(Color.LIGHT_GRAY);
        idCursoField.setBackground(Color.LIGHT_GRAY);
        fechaInscripcionField.setBackground(Color.LIGHT_GRAY);
        addButton.setBackground(buttonColor);
        addButton.setForeground(buttonTextColor);

        // Agregar ActionListener al botón de agregar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarInscripcion();
            }
        });

        // Agregar elementos de la interfaz al panel y el panel a la ventana
        panel.add(idInscripcionLabel);
        panel.add(idInscripcionField);
        panel.add(idAlumnoLabel);
        panel.add(idAlumnoField);
        panel.add(idCursoLabel);
        panel.add(idCursoField);

        panel.add(fechaInscripcionField);
        panel.add(new JLabel()); // Espacio vacío para alinear el botón
        panel.add(addButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarInscripcion() {
        String idInscripcion = idInscripcionField.getText();
        String idAlumno = idAlumnoField.getText();
        String idCurso = idCursoField.getText();
        String fechaInscripcion = fechaInscripcionField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "5Th98.Ki877";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para insertar una nueva inscripción
            String sql = "INSERT INTO inscripciones (id_inscripcion, id_alumno, id_curso, fecha_inscripcion) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, idInscripcion);
            statement.setString(2, idAlumno);
            statement.setString(3, idCurso);
            statement.setString(4, fechaInscripcion);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(RegistrodeCursosFrame.this,
                        "Inscripción agregada correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de agregar la inscripción
                idInscripcionField.setText("");
                idAlumnoField.setText("");
                idCursoField.setText("");
                fechaInscripcionField.setText("");
            } else {
                JOptionPane.showMessageDialog(RegistrodeCursosFrame.this,
                        "No se pudo agregar la inscripción",
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
        // Iniciar la aplicación Swing en un hilo de eventos separado
        SwingUtilities.invokeLater(RegistrodeCursosFrame::new);
    }
}
