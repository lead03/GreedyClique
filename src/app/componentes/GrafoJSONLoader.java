package app.componentes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class GrafoJSONLoader {
	public static Grafo cargarGrafoDesdeJson(File archivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }
        }

        String jsonString = contenido.toString();
        JSONObject jsonObject = new JSONObject(jsonString);

        int V = jsonObject.getInt("V");
        JSONArray pesosArray = jsonObject.getJSONArray("pesos");
        double[] pesos = new double[pesosArray.length()];
        for (int i = 0; i < pesosArray.length(); i++) {
            pesos[i] = pesosArray.getDouble(i);
        }

        JSONArray matrizArray = jsonObject.getJSONArray("matrizAdyacencia");
        int[][] matrizAdyacencia = new int[V][V];
        for (int i = 0; i < matrizArray.length(); i++) {
            JSONArray fila = matrizArray.getJSONArray(i);
            for (int j = 0; j < fila.length(); j++) {
                matrizAdyacencia[i][j] = fila.getInt(j);
            }
        }

        Grafo grafo = new Grafo(V, pesos, matrizAdyacencia);
        return grafo;
    }
}
