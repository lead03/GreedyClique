package app.tests;

import app.componentes.Grafo;

public class GrafoTestConfig {
	public static Grafo grafoDefault() {
		double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5};
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
		return grafo;
	}
}
