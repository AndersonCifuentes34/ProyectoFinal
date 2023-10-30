package edu.umg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class InscripcionesFrame extends JFrame {
    private JTextField idInscripcionField;
    private JTextField idEstudianteField;
    private JTextField idCursoField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    public InscripcionesFrame() {
        setTitle("Gestión de Inscripciones");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel idInscripcionLabel = new JLabel("ID Inscripción:");
        JLabel idEstudianteLabel = new JLabel("ID Estudiante:");
        JLabel idCursoLabel = new JLabel("ID Curso:");

        idInscripcionField = new JTextField();
        idEstudianteField = new JTextField();
        idCursoField = new JTextField();

        // Establecer un ancho preferido personalizado para los campos de texto
        Dimension textFieldDimension = new Dimension(200, 25);
        idInscripcionField.setPreferredSize(textFieldDimension);
        idEstudianteField.setPreferredSize(textFieldDimension);
        idCursoField.setPreferredSize(textFieldDimension);

        addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarInscripcion();
            }
        });

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarInscripcion();
            }
        });

        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInscripcion();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(idInscripcionLabel, constraints);

        constraints.gridx = 1;
        panel.add(idInscripcionField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(idEstudianteLabel, constraints);

        constraints.gridx = 1;
        panel.add(idEstudianteField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(idCursoLabel, constraints);

        constraints.gridx = 1;
        panel.add(idCursoField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(15, 5, 5, 5);
        panel.add(addButton, constraints);

        constraints.gridy = 4;

        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(deleteButton, constraints);

        constraints.gridx = 1;
        panel.add(updateButton, constraints);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarInscripcion() {
        String idInscripcionStr = idInscripcionField.getText();
        String idEstudianteStr = idEstudianteField.getText();
        String idCursoStr = idCursoField.getText();

        if (idInscripcionStr.isEmpty() || idEstudianteStr.isEmpty() || idCursoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        int idInscripcion = Integer.parseInt(idInscripcionStr);
        int idEstudiante = Integer.parseInt(idEstudianteStr);
        int idCurso = Integer.parseInt(idCursoStr);

        // Conexión a la base de datos PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "5Th98.Ki877";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Insertar la inscripción en la base de datos
            String sql = "INSERT INTO inscripciones (id_inscripcion, id_estudiante, id_curso, fecha_inscripcion) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idInscripcion);
            statement.setInt(2, idEstudiante);
            statement.setInt(3, idCurso);

            // Obtener la fecha actual
            Date fechaInscripcion = new Date(System.currentTimeMillis());
            statement.setDate(4, fechaInscripcion);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Inscripción agregada exitosamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar la inscripción. Por favor, inténtelo de nuevo.");
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    private void eliminarInscripcion() {
        String idInscripcion = idInscripcionField.getText();

        if (idInscripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de inscripción.");
            return;
        }

        // Conexión a la base de datos PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "5Th98.Ki877";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Eliminar la inscripción de la base de datos
            String sql = "DELETE FROM inscripciones WHERE id_inscripcion = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(idInscripcion));
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Inscripción eliminada exitosamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna inscripción con ese ID.");
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    private void actualizarInscripcion() {
        String idInscripcion = idInscripcionField.getText();
        String idEstudiante = idEstudianteField.getText();
        String idCurso = idCursoField.getText();

        if (idInscripcion.isEmpty() || idEstudiante.isEmpty() || idCurso.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        // Conexión a la base de datos PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "5Th98.Ki877";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Actualizar la inscripción en la base de datos
            String sql = "UPDATE inscripciones SET id_estudiante = ?, id_curso = ? WHERE id_inscripcion = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(idEstudiante));
            statement.setInt(2, Integer.parseInt(idCurso));
            statement.setInt(3, Integer.parseInt(idInscripcion));
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Inscripción actualizada exitosamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna inscripción con ese ID.");
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        idInscripcionField.setText("");
        idEstudianteField.setText("");
        idCursoField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InscripcionesFrame();
            }
        });
    }
}
