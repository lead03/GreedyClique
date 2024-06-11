package app.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.componentes.Grafo;

import app.componentes.Grafo;

public class ViewCargaGrafo extends JFrame {
    private Grafo grafo;

    public ViewCargaGrafo() {
        setTitle("Carga de Grafo desde JSON");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Panel para el ejemplo de JSON
        JPanel panelEjemplo = new JPanel();
        panelEjemplo.setLayout(new BorderLayout());
        JTextArea textAreaEjemplo = new JTextArea();
        textAreaEjemplo.setEditable(false);
        textAreaEjemplo.setText(getJsonExample());
        panelEjemplo.add(new JScrollPane(textAreaEjemplo), BorderLayout.CENTER);
        
        JLabel labelEjemplo = new JLabel("Ejemplo de Formato JSON:");
        labelEjemplo.setHorizontalAlignment(SwingConstants.CENTER);
        panelEjemplo.add(labelEjemplo, BorderLayout.NORTH);
        
        // Botón para cargar el archivo JSON
        JButton btnUploadJson = new JButton("Cargar Grafo desde JSON");
        btnUploadJson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarGrafoDesdeJson();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout());
        panelBoton.add(btnUploadJson);

        getContentPane().add(panelEjemplo, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);
    }

    private void cargarGrafoDesdeJson() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos JSON", "json"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ObjectMapper mapper = new ObjectMapper();
                grafo = mapper.readValue(selectedFile, Grafo.class);
                JOptionPane.showMessageDialog(this, "Grafo cargado correctamente desde " + selectedFile.getName());
            } catch (IOException e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo JSON: " + e.getMessage());
            }
        }
    }

    private String getJsonExample() {
        return "{\n" +
               "  \"V\": 6,\n" +
               "  \"pesos\": [11.0, 5.5, 1.1, 7.0, 2.5, 3.5],\n" +
               "  \"matrizAdyacencia\": [\n" +
               "    [0, 1, 0, 1, 0, 0],\n" +
               "    [1, 0, 1, 1, 1, 0],\n" +
               "    [0, 1, 0, 0, 0, 1],\n" +
               "    [1, 1, 0, 0, 0, 1],\n" +
               "    [0, 1, 0, 0, 0, 1],\n" +
               "    [0, 0, 1, 1, 1, 0]\n" +
               "  ]\n" +
               "}";
    }
}