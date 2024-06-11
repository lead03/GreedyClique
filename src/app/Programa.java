package app;

import app.componentes.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Programa {
	
	int cantidadVertices = 6;
	
    double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5}; // Pesos de los vértices
    Grafo grafo = new Grafo(cantidadVertices, pesos);



    Set<Integer> cliqueMaximaPeso = encontrarCliqueMaximaPesoGolosa(grafo);
    if (cliqueMaximaPeso != null) {
        System.out.println("Clique de peso máximo (golosa): " + cliqueMaximaPeso);
        System.out.println("Peso máximo (golosa): " + calcularPeso(cliqueMaximaPeso, pesos));
    } else {
        System.out.println("No se encontró ninguna clique.");
    }

    // Imprimir la matriz de adyacencia
    /*System.out.println("Matriz de Adyacencia:");
    for (int i = 0; i < grafo.V; i++) {
        for (int j = 0; j < cantidadVertices; j++) {
            System.out.print(grafo.matrizAdyacencia[i][j] + " ");
        }
        System.out.println();
    }*/
	
	
	

	private static Set<Integer> encontrarCliqueMaximaPesoGolosa (Grafo grafo){
        // Crear una lista de vértices y ordenarla por peso descendente
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < grafo.V; i++) {
            vertices.add(i);
        }
        vertices.sort((v1, v2) -> Double.compare(grafo.pesos[v2], grafo.pesos[v1]));

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

    // Método para calcular el peso de una clique
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
