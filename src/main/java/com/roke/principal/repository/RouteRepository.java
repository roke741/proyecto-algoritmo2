package com.roke.principal.repository;

import com.roke.principal.model.City;
import com.roke.principal.model.Route;

import java.util.ArrayList;
import java.util.List;

// Repositorio que almacena y gestiona las rutas entre ciudades
public class RouteRepository {
    // Lista que almacena todas las rutas del sistema
    private final ArrayList<Route> routes;

    
    // Constructor que inicializa la lista de rutas utilizando las ciudades del CityRepository
    public RouteRepository(CityRepository cityRepo) {
        routes = new ArrayList<>();
        // Se agregan rutas entre ciudades, usando IDs y distancia
       
        add(cityRepo, 1, 2, 1.0);
        add(cityRepo, 1, 3, 2.0);

        add(cityRepo, 2, 3, 1.0);
        add(cityRepo, 2, 4, 5.0);
        add(cityRepo, 2, 5, 2.0);

        add(cityRepo, 3, 4, 2.0);
        add(cityRepo, 3, 5, 1.0);
        add(cityRepo, 3, 6, 4.0);

        add(cityRepo, 4, 5, 3.0);
        add(cityRepo, 4, 6, 6.0);
        add(cityRepo, 4, 7, 8.0);

        add(cityRepo, 5, 6, 3.0);
        add(cityRepo, 5, 7, 7.0);

        add(cityRepo, 6, 7, 5.0);
        add(cityRepo, 6, 8, 2.0);

        add(cityRepo, 7, 8, 6.0);
    }
    
    // Método auxiliar privado para agregar una ruta usando IDs de ciudades y distancia
    private void add(CityRepository repo, int fromCityId, int toCityId, double dist) {
        City origin = repo.getById(fromCityId);
        City destination = repo.getById(toCityId);
        routes.add(new Route(origin, destination, dist));
    }

    /** Devuelve una copia de la lista de rutas */
    public List<Route> getRoutes() {
        return new ArrayList<>(routes);
    }

    /** Permite agregar rutas dinámicamente */
    public void addRoute(City origin, City destination, double distance) {
        routes.add(new Route(origin, destination, distance));
    }
}
