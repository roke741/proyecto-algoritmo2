package com.roke.principal.service;

import com.roke.principal.model.City;
import com.roke.principal.model.Route;
import com.roke.principal.repository.CityRepository;
import com.roke.principal.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GraphService {
    private final CityRepository cityRepo;
    private final RouteRepository routeRepo;

    // adj.get(u) = lista de rutas salientes desde la ciudad con id=u
    private final ArrayList<ArrayList<Route>> adj;
    private final int numCities;

    public GraphService(CityRepository cityRepo, RouteRepository routeRepo) {
        this.cityRepo = cityRepo;
        this.routeRepo = routeRepo;

        // Número de ciudades
        List<City> cities = cityRepo.getCities();
        numCities = cities.size();

        // Creamos lista de adyacencia 1-based (índice 0 sin usar)
        adj = new ArrayList<>();
        for (int i = 0; i <= numCities; i++) {
            adj.add(new ArrayList<>());
        }

        // Rellenamos aristas; como el grafo es no dirigido, metemos cada ruta y su inversa
        for (Route r : routeRepo.getRoutes()) {
            int u = r.getOrigin().getId();
            int v = r.getDestination().getId();
            double w = r.getDistance();

            // arista u -> v
            adj.get(u).add(r);
            // agregamos la inversa v -> u
            adj.get(v).add(new Route(r.getDestination(), r.getOrigin(), w));
        }
    }


    private static class DijkstraResult {
        final double[] dist;
        final int[] prev;
        DijkstraResult(double[] dist, int[] prev) {
            this.dist = dist;
            this.prev = prev;
        }
    }

    /**
     * Corre Dijkstra desde sourceId.
     * @throws NoSuchElementException si sourceId inválido.
     */
    private DijkstraResult dijkstra(int sourceId) {
        if (sourceId < 1 || sourceId > numCities) {
            throw new NoSuchElementException("Ciudad origen inválida: " + sourceId);
        }

        double[] dist = new double[numCities + 1];
        int[] prev    = new int[numCities + 1];
        boolean[] used = new boolean[numCities + 1];

        // 1) Inicialización
        for (int i = 1; i <= numCities; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1;
        }
        dist[sourceId] = 0;

        // 2) Bucle principal O(n²)
        for (int k = 1; k <= numCities; k++) {
            // Elegir u no usado con dist mínima
            int u = -1;
            double best = Double.POSITIVE_INFINITY;
            for (int i = 1; i <= numCities; i++) {
                if (!used[i] && dist[i] < best) {
                    best = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;  // ya no quedan alcanzables
            used[u] = true;

            // Relajar todas las aristas salientes de u
            for (Route edge : adj.get(u)) {
                int v = edge.getDestination().getId();
                double w = edge.getDistance();
                if (!used[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                }
            }
        }

        return new DijkstraResult(dist, prev);
    }

    /**
     * Devuelve la lista de City que forma la ruta más corta
     * entre sourceId y destId (ambos 1-based).
     * @throws NoSuchElementException si no hay camino.
     */
    public List<City> shortestPath(int sourceId, int destId) {
        DijkstraResult res = dijkstra(sourceId);
        if (res.dist[destId] == Double.POSITIVE_INFINITY) {
            throw new NoSuchElementException(
                    "No existe camino de " + sourceId + " a " + destId);
        }

        // Reconstrucción inversa
        List<City> path = new ArrayList<>();
        for (int at = destId; at != -1; at = res.prev[at]) {
            path.add(0, cityRepo.getById(at));
        }
        return path;
    }

    /**
     * Devuelve la distancia mínima entre sourceId y destId (o Infinity si no hay camino).
     */
    public double shortestDistance(int sourceId, int destId) {
        return dijkstra(sourceId).dist[destId];
    }

}
