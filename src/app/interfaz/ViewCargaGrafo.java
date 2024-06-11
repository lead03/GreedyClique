package app.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import app.componentes.Grafo;

public class ViewCargaGrafo extends JFrame {
    private Grafo grafo;
    private JTextField vertexField;
    private JTextField weightField;
    private JTextArea edgeArea;
    private JTextField fromField;
    private JTextField toField;
    
    public ViewCargaGrafo() {
        setTitle("Carga de Grafo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblVertex = new JLabel("Número de Vértices:");
        panel.add(lblVertex);

        vertexField = new JTextField();
        panel.add(vertexField);
        vertexField.setColumns(10);

        JLabel lblWeight = new JLabel("Peso de Vértices:");
        panel.add(lblWeight);

        weightField = new JTextField();
        panel.add(weightField);
        weightField.setColumns(10);

        JLabel lblEdge = new JLabel("Agregar Arista:");
        panel.add(lblEdge);

        JPanel edgePanel = new JPanel();
        edgePanel.setLayout(new GridLayout(1, 4));
        fromField = new JTextField();
        fromField.setColumns(2);
        edgePanel.add(fromField);
        edgePanel.add(new JLabel(" -> "));
        toField = new JTextField();
        toField.setColumns(2);
        edgePanel.add(toField);
        JButton btnAddEdge = new JButton("Agregar");
        edgePanel.add(btnAddEdge);
        panel.add(edgePanel);

        edgeArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(edgeArea);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);
        edgeArea.setEditable(false);

        JButton btnCreateGraph = new JButton("Crear Grafo");
        panel.add(btnCreateGraph);

        btnAddEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarArista();
            }
        });

        btnCreateGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearGrafo();
            }
        });
    }

    private void agregarArista() {
        try {
            int from = Integer.parseInt(fromField.getText());
            int to = Integer.parseInt(toField.getText());
            grafo.agregarArista(from, to);
            edgeArea.append("Arista agregada: " + from + " -> " + to + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos para los vértices.");
        }
    }

    private void crearGrafo() {
        try {
            int V = Integer.parseInt(vertexField.getText());
            String[] weightsStr = weightField.getText().split(",");
            double[] weights = new double[V];
            for (int i = 0; i < V; i++) {
                weights[i] = Double.parseDouble(weightsStr[i]);
            }
            grafo = new Grafo(V, weights);
            JOptionPane.showMessageDialog(this, "Grafo creado con " + V + " vértices.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido de vértices y pesos.");
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Ingrese el número correcto de pesos para los vértices.");
        }
    }
}

