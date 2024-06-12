package app.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import app.componentes.Grafo;
import app.logica.CliqueMaximaService;

public class ViewPantallaFinal extends JFrame {

    public ViewPantallaFinal(Grafo grafo) {
        setTitle("Visualización del Grafo y la Clique");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panelGrafo = new JPanel();
        panelGrafo.setLayout(new BorderLayout());
        getContentPane().add(panelGrafo, BorderLayout.CENTER);

        try {
            Set<Integer> clique = CliqueMaximaService.encontrarCliqueMaximaPesoGolosa(grafo, grafo.getPesos(), grafo.getV());
            double pesoMaximo = CliqueMaximaService.calcularPeso(clique, grafo.getPesos());
            dibujarGrafo(grafo, clique, panelGrafo);

            JTextArea textAreaResultado = new JTextArea();
            textAreaResultado.setEditable(false);
            textAreaResultado.setText("La clique de peso máximo en este grafo es " + clique + ", y tiene un peso de " + pesoMaximo + ".");
            textAreaResultado.setFont(new Font("Arial", Font.PLAIN, 24));
            textAreaResultado.setLineWrap(true);
            textAreaResultado.setWrapStyleWord(true);
            textAreaResultado.setAlignmentX(SwingConstants.CENTER);

            getContentPane().add(new JScrollPane(textAreaResultado), BorderLayout.SOUTH);
        } catch (Exception e) {
            JTextArea textAreaError = new JTextArea();
            textAreaError.setEditable(false);
            textAreaError.setText(e.getMessage());
            textAreaError.setFont(new Font("Arial", Font.PLAIN, 24));
            textAreaError.setLineWrap(true);
            textAreaError.setWrapStyleWord(true);
            textAreaError.setAlignmentX(SwingConstants.CENTER);

            getContentPane().add(new JScrollPane(textAreaError), BorderLayout.SOUTH);
        }
    }
    
    private void dibujarGrafo(Grafo grafo, Set<Integer> clique, JPanel panel) {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object[] vertices = new Object[grafo.getV()];
            for (int i = 0; i < grafo.getV(); i++) {
                String label = "V" + i + " (" + grafo.obtenerPeso(i) + ")";
                if (clique.contains(i)) {
                    vertices[i] = graph.insertVertex(parent, null, label, 100, 100, 80, 30, "fillColor=yellow");
                } else {
                    vertices[i] = graph.insertVertex(parent, null, label, 100, 100, 80, 30);
                }
            }

            for (int i = 0; i < grafo.getV(); i++) {
                for (int j = i + 1; j < grafo.getV(); j++) {
                    if (grafo.sonVecinos(i, j)) {
                        if (clique.contains(i) && clique.contains(j)) {
                            graph.insertEdge(parent, null, "", vertices[i], vertices[j], "strokeColor=red");
                        } else {
                            graph.insertEdge(parent, null, "", vertices[i], vertices[j]);
                        }
                    }
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setCenterPage(true);  // Centrar el grafo en la página
        panel.add(graphComponent, BorderLayout.CENTER);

        mxCircleLayout layout = new mxCircleLayout(graph);
        layout.execute(parent);
    }
}
