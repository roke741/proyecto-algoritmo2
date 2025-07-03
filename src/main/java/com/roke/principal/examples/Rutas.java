/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roke.principal.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rutas {
     // Clase para representar una arista desde un nodo a otro con cierto peso
    static class Ruta {
        int destino;
        int peso;

        Ruta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static void main(String[] args) {
        int N = 9; // Nodos del 1 al 8 (posición 0 no se usa)

        // Grafo representado como lista de adyacencia
        List<Ruta>[] grafo = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            grafo[i] = new ArrayList<>();
        }

        // Agregamos las aristas con sus pesos
        grafo[1].add(new Ruta(2, 1));
        grafo[1].add(new Ruta(3, 2));
        grafo[2].add(new Ruta(3, 1));
        grafo[2].add(new Ruta(4, 5));
        grafo[2].add(new Ruta(5, 2));
        grafo[3].add(new Ruta(4, 2));
        grafo[3].add(new Ruta(5, 1));
        grafo[3].add(new Ruta(6, 4));
        grafo[4].add(new Ruta(5, 3));
        grafo[4].add(new Ruta(7, 8));
        grafo[5].add(new Ruta(6, 3));
        grafo[5].add(new Ruta(7, 7));
        grafo[6].add(new Ruta(7, 5));
        grafo[6].add(new Ruta(8, 2));
        grafo[7].add(new Ruta(8, 6));

        // Inicialización de distancias y estructura para guardar caminos
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0; // Nodo de inicio

        // Lista de caminos mínimos para cada nodo
        List<List<Integer>>[] caminos = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            caminos[i] = new ArrayList<>();
        }

        // El camino al nodo 1 es simplemente [1]
        caminos[1].add(Arrays.asList(1));

        // Conjunto de nodos no procesados
        boolean[] visitado = new boolean[N];

        // Algoritmo de Dijkstra (sin estructuras avanzadas)
        for (int i = 1; i <= 8; i++) {
            int u = -1;
            int min = Integer.MAX_VALUE;

            // Encontrar el nodo no visitado con menor distancia
            for (int j = 1; j <= 8; j++) {
                if (!visitado[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }

            if (u == -1) break; // ya no quedan accesibles
            visitado[u] = true;

            // Relajar vecinos
            for (Ruta r : grafo[u]) {
                int v = r.destino;
                int peso = r.peso;
                int nuevaDist = dist[u] + peso;

                if (nuevaDist < dist[v]) {
                    // Nuevo camino más corto encontrado
                    dist[v] = nuevaDist;

                    // Limpiamos caminos anteriores y copiamos los del actual
                    caminos[v].clear();
                    for (List<Integer> camino : caminos[u]) {
                        List<Integer> nuevoCamino = new ArrayList<>(camino);
                        nuevoCamino.add(v);
                        caminos[v].add(nuevoCamino);
                    }

                } else if (nuevaDist == dist[v]) {
                    // Otro camino con misma distancia encontrada
                    for (List<Integer> camino : caminos[u]) {
                        List<Integer> nuevoCamino = new ArrayList<>(camino);
                        nuevoCamino.add(v);
                        caminos[v].add(nuevoCamino);
                    }
                }
            }
        }

        // Mostrar todos los caminos mínimos desde nodo 1 a los demás
        System.out.println("Rutas más cortas desde el nodo 1:");
        for (int i = 2; i <= 8; i++) {
            System.out.println("Nodo 1 -> Nodo " + i + ": Distancia = " + dist[i]);
            System.out.println("Caminos mínimos posibles:");
            for (List<Integer> camino : caminos[i]) {
                for (int j = 0; j < camino.size(); j++) {
                    System.out.print(camino.get(j));
                    if (j < camino.size() - 1) System.out.print(" -> ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
