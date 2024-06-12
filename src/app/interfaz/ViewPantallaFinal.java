package app.interfaz;

import java.awt.BorderLayout;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import app.componentes.Grafo;
import app.logica.CliqueMaximaService;

public class ViewPantallaFinal extends JFrame {

	public ViewPantallaFinal(Grafo grafo) {
        setTitle("Carga de Grafo desde JSON");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Panel para el ejemplo de JSON
        JPanel panelEjemplo = new JPanel();
        panelEjemplo.setLayout(new BorderLayout());
        JTextArea textAreaEjemplo = new JTextArea();
        textAreaEjemplo.setEditable(false);
        textAreaEjemplo.setText("aqui");
        panelEjemplo.add(new JScrollPane(textAreaEjemplo), BorderLayout.CENTER);
        
        getContentPane().add(panelEjemplo, BorderLayout.CENTER);
        
        try {
        	Set<Integer> clique = CliqueMaximaService.encontrarCliqueMaximaPesoGolosa(grafo, grafo.getPesos(), grafo.getV());
        	double pesoMaximo = CliqueMaximaService.calcularPeso(clique, grafo.getPesos());
        	 textAreaEjemplo.setText(" La clique de peso máximo en este grafo es " + clique + ", y tiene un peso de " + pesoMaximo + ".");
        }catch (Exception e) {
        	 textAreaEjemplo.setText(e.getMessage());
		}     
    }	
}
