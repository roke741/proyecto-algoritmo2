package com.roke.principal.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Proyecto2 {
/*
    // Clase para representar una ruta del grafo: destino y peso (distancia)
    static class Ruta {
        int destino;  // nodo al que va
        int peso;     // peso o distancia de la ruta

        Ruta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static void main(String[] args) {
        int N = 9; // Usamos nodos del 1 al 8 (posición 0 no se usa)

        // Declaramos un arreglo de listas para representar el grafo (lista de adyacencia)
        List<Ruta>[] grafo = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            grafo[i] = new ArrayList<>(); // Inicializamos cada lista
        }

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

        // Arreglo que guarda la distancia más corta desde el nodo 1 a cada nodo
        int[] dist = new int[N];
        // Arreglo que guarda el nodo anterior en el camino más corto (para reconstruir rutas)
        int[] anterior = new int[N];
        // Arreglo que indica si un nodo ya fue visitado (procesado)
        boolean[] visitado = new boolean[N];

        // Inicializamos las distancias a infinito (menos para el nodo de origen)
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(anterior, -1);
        dist[1] = 0; // El nodo origen (1) tiene distancia 0

        // Algoritmo de Dijkstra simplificado
        for (int i = 1; i <= 7; i++) {
            int nodoMin = -1;
            int minDist = Integer.MAX_VALUE;

            // Buscamos el nodo no visitado con la distancia más corta
            for (int j = 1; j <= 7; j++) {
                if (!visitado[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    nodoMin = j;
                }
            }

            if (nodoMin == -1) break; // Si no hay nodo, terminamos
            visitado[nodoMin] = true; // Marcamos como visitado

            // Relajamos las aristas del nodo actual
            for (Ruta r : grafo[nodoMin]) {
                int nuevo = dist[nodoMin] + r.peso; // distancia nueva candidata
                if (nuevo < dist[r.destino]) { // si encontramos una mejor
                    dist[r.destino] = nuevo;
                    anterior[r.destino] = nodoMin;
                }
            }
        }

        // Mostrar resultados: para cada nodo desde 2 hasta 7
        for (int i = 2; i <= 7; i++) {
            System.out.print("Nodo 1 -> Nodo " + i + ": Distancia = " + dist[i] + " | Camino: ");
            imprimirCamino(i, anterior); // imprimimos el camino completo
            System.out.println();
        }
    }

    // Método para reconstruir e imprimir el camino desde nodo 1 hasta el nodo destino
    public static void imprimirCamino(int destino, int[] anterior) {
        List<Integer> camino = new ArrayList<>();

        // Volvemos desde el destino hasta el origen usando el arreglo anterior
        while (destino != -1) {
            camino.add(destino);
            destino = anterior[destino];
        }

        // Invertimos el camino (ya que lo recolectamos al revés)
        Collections.reverse(camino);

        // Imprimimos el camino paso a paso
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));
            if (i < camino.size() - 1) System.out.print(" -> ");
        }
    }
*/
}
