package app.logica;

import app.componentes.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CliqueMaximaService {
	
    public static Set<Integer> encontrarCliqueMaximaPesoGolosa(Grafo grafo, double[] pesos, int cantidadVertices) {
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
    
    public static double calcularPeso(Set<Integer> clique, double[] pesos) {
        double peso = 0;
        if (clique != null) {
            for (Integer v : clique) {
                peso += pesos[v];
            }
        }
        return peso;
    }
}
