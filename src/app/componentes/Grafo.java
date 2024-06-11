package app.componentes;

public class Grafo {
	private int V;
	private int[][] matrizAdyacencia;
	double[] pesos;

	public Grafo(int V, double[] pesos) {
		this.V = V;
		this.pesos = pesos;
		matrizAdyacencia = new int[V][V];
	}
	
	public Grafo(int V, double[] pesos, int[][] mAdyacencia) {
		this.V = V;
		this.pesos = pesos;
		matrizAdyacencia = mAdyacencia;
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
	
	public int getV() {
		return this.V;
	}
	
	public double[] getPesos() {
		return this.pesos;
	}
	
    public int[][] getMatrizAdyacencia() {
        int[][] copia = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                copia[i][j] = matrizAdyacencia[i][j];
            }
        }
        return copia;
    }
    
    public void imprimirMatrizAdyacencia() {
        System.out.println("Matriz de Adyacencia:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void imprimirPesos() {
        System.out.println("Pesos de los Vértices:");
        for (int i = 0; i < pesos.length; i++) {
            System.out.print(pesos[i] + " ");
        }
        System.out.println();
    }
}
