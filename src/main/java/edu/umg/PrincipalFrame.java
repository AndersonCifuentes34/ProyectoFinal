package edu.umg;


import javax.swing.*;
import java.awt.*;

class PrincipalFrame extends JFrame {
    private JButton addButtonStudent;
    private JButton addButtonCourse;
    private JButton addButtonRegistration;

    public PrincipalFrame() {
        setTitle("Menú");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel y botones
        JPanel panel = new JPanel(new GridLayout(3, 1));
        addButtonStudent = new JButton("Agregar Estudiante");
        addButtonCourse = new JButton("Agregar Curso");
        addButtonRegistration = new JButton("Agregar Inscripción");

        // Configurar colores personalizados
        Color backgroundColor = new Color(34, 49, 63); // Color de fondo oscuro
        Color buttonColor = new Color(46, 204, 113); // Color del botón (Turquesa)
        Color buttonTextColor = new Color(255, 255, 255); // Color del texto en los botones (Blanco)

        Font buttonFont = new Font("Arial", Font.BOLD, 16); // Fuente personalizada para los botones

        panel.setBackground(backgroundColor);
        addButtonStudent.setBackground(buttonColor);
        addButtonCourse.setBackground(buttonColor);
        addButtonRegistration.setBackground(buttonColor);

        addButtonStudent.setForeground(buttonTextColor);
        addButtonCourse.setForeground(buttonTextColor);
        addButtonRegistration.setForeground(buttonTextColor);

        addButtonStudent.setFont(buttonFont);
        addButtonCourse.setFont(buttonFont);
        addButtonRegistration.setFont(buttonFont);

        // Agregar ActionListener a los botones para abrir formularios
        addButtonStudent.addActionListener(e -> abrirFormulario(new StudentDetailsFrame()));
        addButtonCourse.addActionListener(e -> abrirFormulario(new DetallesdeCursosFrame()));
        addButtonRegistration.addActionListener(e -> abrirFormulario(new InscripcionesFrame())); // Enlazar con InscripcionFrame

        // Agregar botones al panel y el panel a la ventana
        panel.add(addButtonStudent);
        panel.add(addButtonCourse);
        panel.add(addButtonRegistration);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void abrirFormulario(JFrame frame) {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Iniciar la aplicación Swing en un hilo de eventos separado
        SwingUtilities.invokeLater(PrincipalFrame::new);
    }
}