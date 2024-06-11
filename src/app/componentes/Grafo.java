package app.componentes;

public class Grafo {
	private int V;
	private int[][] matrizAdyacencia;
	double[] pesos;

	Grafo(int V, double[] pesos) {
		this.V = V;
		this.pesos = pesos;
		matrizAdyacencia = new int[V][V];
	}

	/***
	 * Este método agrega aristas teniendo en cuenta que es un grafo no dirigido
	 * 
	 * @param vertice inicial
	 * @param vertice final
	 */
	public void agregarArista(int v, int w) {
		matrizAdyacencia[v][w] = 1;
		matrizAdyacencia[w][v] = 1; // Ya que el grafo es no dirigido
	}

	/***
	 * Dados dos vértices, nos informa si son vecinos
	 * 
	 * @param vertice 1
	 * @param vertice 2
	 * @return
	 */
	public boolean sonVecinos(int v, int w) {
		return matrizAdyacencia[v][w] == 1;
	}
}
