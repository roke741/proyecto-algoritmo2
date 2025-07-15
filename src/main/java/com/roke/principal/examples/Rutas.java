/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roke.principal.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rutas {
     // Clase para representar una arista desde un nodo a otro con cierto peso
    public static class Ruta {
        public int destino;
        public int peso;

        public Ruta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }
    
    // Método para encontrar las rutas más cortas y todos los caminos mínimos
    public static Map<Integer, PathResult> findShortestPathsAndAllMinPaths(int numVertices, List<Ruta>[] grafo, int startNode) {
        int[] dist = new int[numVertices + 1]; // Nodos del 1 al numVertices
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0; // Nodo de inicio

        // Mapa para almacenar los resultados: distancia y lista de caminos
        Map<Integer, PathResult> results = new HashMap<>();
        for (int i = 1; i <= numVertices; i++) {
            results.put(i, new PathResult(Integer.MAX_VALUE));
        }
        results.get(startNode).distance = 0;
        results.get(startNode).paths.add(Arrays.asList(startNode));

        boolean[] visitado = new boolean[numVertices + 1];

        // Algoritmo de Dijkstra (sin PriorityQueue)
        for (int count = 1; count <= numVertices; count++) {
            int u = -1;
            int min = Integer.MAX_VALUE;

            // Encontrar el nodo no visitado con menor distancia
            for (int j = 1; j <= numVertices; j++) {
                if (!visitado[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }

            if (u == -1) break; // No quedan nodos accesibles
            visitado[u] = true;

            // Relajar vecinos
            for (Ruta r : grafo[u]) {
                int v = r.destino;
                int peso = r.peso;
                int nuevaDist = dist[u] + peso;

                if (nuevaDist < dist[v]) {
                    // Nuevo camino más corto encontrado
                    dist[v] = nuevaDist;
                    results.get(v).distance = nuevaDist;

                    // Limpiamos caminos anteriores y copiamos los del actual
                    results.get(v).paths.clear();
                    for (List<Integer> camino : results.get(u).paths) {
                        List<Integer> nuevoCamino = new ArrayList<>(camino);
                        nuevoCamino.add(v);
                        results.get(v).paths.add(nuevoCamino);
                    }

                } else if (nuevaDist == dist[v]) {
                    // Otro camino con misma distancia encontrada
                    for (List<Integer> camino : results.get(u).paths) {
                        List<Integer> nuevoCamino = new ArrayList<>(camino);
                        nuevoCamino.add(v);
                        results.get(v).paths.add(nuevoCamino);
                    }
                }
            }
        }
        return results;
    }

    // Clase auxiliar para encapsular la distancia y los caminos
    public static class PathResult {
        public int distance;
        public List<List<Integer>> paths;

        public PathResult(int distance) {
            this.distance = distance;
            this.paths = new ArrayList<>();
        }
    }
    
}