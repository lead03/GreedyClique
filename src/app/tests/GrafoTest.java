package app.tests;

import app.componentes.Grafo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrafoTest {
    
    @Test
    void testCrearGrafo() {
        int cantidadVertices = 6;
        double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5};
        Grafo grafo = new Grafo(cantidadVertices, pesos);

        // Verificar que el número de vértices es correcto
        assertEquals(cantidadVertices, grafo.getV());

        // Verificar que los pesos están correctamente asignados
        assertArrayEquals(pesos, grafo.getPesos());

        // Verificar que la matriz de adyacencia está correctamente inicializada
        int[][] matriz = grafo.getMatrizAdyacencia();
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                assertEquals(0, matriz[i][j]);
            }
        }
    }

    @Test
    void testAgregarArista() {
        Grafo grafo = GrafoTestConfig.grafoDefault();

        // Verificar que las aristas se agregaron correctamente
        int[][] matriz = grafo.getMatrizAdyacencia();
        assertEquals(1, matriz[0][1]);
        assertEquals(1, matriz[1][0]); // Porque el grafo es no dirigido

        assertEquals(1, matriz[0][3]);
        assertEquals(1, matriz[3][0]); 

        assertEquals(1, matriz[1][2]);
        assertEquals(1, matriz[2][1]); 

        assertEquals(1, matriz[1][3]);
        assertEquals(1, matriz[3][1]); 

        assertEquals(1, matriz[1][4]);
        assertEquals(1, matriz[4][1]); 

        assertEquals(1, matriz[1][5]);
        assertEquals(1, matriz[5][1]); 

        assertEquals(1, matriz[2][3]);
        assertEquals(1, matriz[3][2]); 

        assertEquals(1, matriz[3][4]);
        assertEquals(1, matriz[4][3]); 

        assertEquals(1, matriz[3][5]);
        assertEquals(1, matriz[5][3]); 

        assertEquals(1, matriz[4][5]);
        assertEquals(1, matriz[5][4]); 
    }

    @Test
    void testSonVecinos() {
        Grafo grafo = GrafoTestConfig.grafoDefault();

        // Verificar que son vecinos
        assertTrue(grafo.sonVecinos(0, 1));
        assertTrue(grafo.sonVecinos(0, 3));
        assertTrue(grafo.sonVecinos(1, 2));
        assertTrue(grafo.sonVecinos(1, 3));
        assertTrue(grafo.sonVecinos(1, 4));
        assertTrue(grafo.sonVecinos(1, 5));
        assertTrue(grafo.sonVecinos(2, 3));
        assertTrue(grafo.sonVecinos(3, 4));
        assertTrue(grafo.sonVecinos(3, 5));
        assertTrue(grafo.sonVecinos(4, 5));

        // Verificar que no son vecinos
        assertFalse(grafo.sonVecinos(0, 2));
        assertFalse(grafo.sonVecinos(2, 4));
        assertFalse(grafo.sonVecinos(2, 5));
        assertFalse(grafo.sonVecinos(0, 4));
        assertFalse(grafo.sonVecinos(0, 5));
    }

    @Test
    void testObtenerPeso() {
        Grafo grafo = GrafoTestConfig.grafoDefault();

        // Verificar que los pesos se obtienen correctamente
        assertEquals(11.0, grafo.obtenerPeso(0));
        assertEquals(5.5, grafo.obtenerPeso(1));
        assertEquals(1.1, grafo.obtenerPeso(2));
    }

    @Test
    void testObtenerPesoFueraDeRango() {
        Grafo grafo = GrafoTestConfig.grafoDefault();

        // Verificar que se lanza una excepción cuando el índice está fuera de rango
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.obtenerPeso(10);
        });
    }
}
