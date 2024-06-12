package app.tests;

import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.componentes.Grafo;
import app.logica.CliqueMaximaService;

public class CliqueMaximaTest {
	@Test
     void testEncontrarCliqueMaximaPesoGolosa() {
        Grafo grafo = GrafoTestConfig.grafoDefault();
        double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5};
        Set<Integer> clique = CliqueMaximaService.encontrarCliqueMaximaPesoGolosa(grafo, pesos, grafo.getV());
        assertNotNull(clique);
    }

    @Test
     void testCalcularPeso() {
        Grafo grafo = GrafoTestConfig.grafoDefault();
        double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5};
        Set<Integer> clique = CliqueMaximaService.encontrarCliqueMaximaPesoGolosa(grafo, pesos, grafo.getV());
        double peso = CliqueMaximaService.calcularPeso(clique, pesos);
        assertTrue(peso > 0); 
    }
    
    @Test
     void testCalcularPesoCliqueVacio() {
        double[] pesos = {11.0, 5.5, 1.1, 7.0, 2.5, 3.5};
        Set<Integer> clique = Set.of();
        double peso = CliqueMaximaService.calcularPeso(clique, pesos);
        assertEquals(0, peso);
    }
}
