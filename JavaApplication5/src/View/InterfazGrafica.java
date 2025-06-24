package View;

import ViewModel.*;
import Model.SerializationAndFileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InterfazGrafica extends JFrame {
    private JTextField txtNombre, txtEdad;
    private JButton btnEstudiante, btnProfesor, btnHistorial;
    private JTextArea outputArea;

    public InterfazGrafica() {
        setTitle("¿Qué estás haciendo?");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Instrucciones
        JLabel Deberes = new JLabel("Historial De Deberes:");
        Deberes.setHorizontalAlignment(SwingConstants.CENTER);
        Deberes.setFont(new Font("Arial", Font.BOLD, 14));
        add(Deberes, BorderLayout.NORTH);

        // Panel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(2, 2));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelEntrada.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);
        panelEntrada.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelEntrada.add(txtEdad);
        add(panelEntrada, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        btnEstudiante = new JButton("Soy Estudiante");
        btnProfesor = new JButton("Soy Profesor");
        btnHistorial = new JButton("📖 Ver Historial");

        panelBotones.add(btnEstudiante);
        panelBotones.add(btnProfesor);
        panelBotones.add(btnHistorial);
        add(panelBotones, BorderLayout.SOUTH);

        // Área de resultados
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(outputArea), BorderLayout.EAST);

        // Acciones
        btnEstudiante.addActionListener(e -> procesar("estudiante"));
        btnProfesor.addActionListener(e -> procesar("profesor"));
        btnHistorial.addActionListener(e -> mostrarHistorial());
    }

    private void procesar(String tipo) {
        String nombre = txtNombre.getText().trim();
        String edadTexto = txtEdad.getText().trim();

        if (nombre.isEmpty() || edadTexto.isEmpty()) {
            outputArea.setText("⚠️ Ingresa tu nombre y edad.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadTexto);
            Person persona = tipo.equals("estudiante")
                    ? new Student(nombre, edad)
                    : new Profesor(nombre, edad);

            SerializationAndFileHandler.guardarPersona(persona);
            outputArea.setText(persona.getCurrentActivity());
        } catch (NumberFormatException ex) {
            outputArea.setText("⚠️ Edad no válida.");
        }
    }

    private void mostrarHistorial() {
        ArrayList<Person> personas = SerializationAndFileHandler.cargarPersonas();
        if (personas.isEmpty()) {
            outputArea.setText("📭 No hay acciones registradas aún.");
            return;
        }

        StringBuilder sb = new StringBuilder("📋 Historial de actividades:\n");
        for (Person p : personas) {
            sb.append("- ").append(p.getCurrentActivity()).append("\n");
        }

        outputArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica gui = new InterfazGrafica();
            gui.setVisible(true);
        });
    }
}