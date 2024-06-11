package app;

import app.componentes.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Programa {
	
	public static void main(String[] args) {
        double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5}; // Pesos de los vértices
        Grafo grafo = new Grafo(6, pesos);

        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 3);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(1, 5);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(3, 4);
        grafo.agregarArista(3, 5);
        grafo.agregarArista(4, 5);

        Set<Integer> cliqueMaximaPeso = encontrarCliqueMaximaPesoGolosa(grafo, pesos, 6);
        if (cliqueMaximaPeso != null) {
            System.out.println("Clique de peso máximo (golosa): " + cliqueMaximaPeso);
            System.out.println("Peso máximo (golosa): " + calcularPeso(cliqueMaximaPeso, pesos));
        } else {
            System.out.println("No se encontró ninguna clique.");
        }
    }
	int cantidadVertices = 6;
    double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5}; // Pesos de los vértices
    Grafo grafo = new Grafo(cantidadVertices, pesos);
    
    Set<Integer> cliqueMaximaPeso = encontrarCliqueMaximaPesoGolosa(grafo, pesos, cantidadVertices);
    
    private static Set<Integer> encontrarCliqueMaximaPesoGolosa(Grafo grafo, double[] pesos, int cantidadVertices) {
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            vertices.add(i);
        }
        vertices.sort((v1, v2) -> Double.compare(pesos[v2], pesos[v1]));

        Set<Integer> clique = new HashSet<>();
        for (Integer v : vertices) {
            boolean puedeAgregar = true;
            for (Integer u : clique) {
                if (!grafo.sonVecinos(v, u)) {
                    puedeAgregar = false;
                    break;
                }
            }
            if (puedeAgregar) {
                clique.add(v);
            }
        }
        return clique;
    }
    
    private static double calcularPeso(Set<Integer> clique, double[] pesos) {
        double peso = 0;
        if (clique != null) {
            for (Integer v : clique) {
                peso += pesos[v];
            }
        }
        return peso;
    }
}
